package com.javaops.storage.serializer;

import com.javaops.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Vasichkin Pavel
 */
public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(contacts.entrySet(), dos, (entry) -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            Map<SectionType, Section> sections = r.getSections();
            writeCollection(sections.entrySet(), dos, (entry) -> {
                SectionType sectionName = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionName.name());
                switch (sectionName) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(((ListSection) section).getItems(), dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(((OrganizationSection) section).getOrganizations(), dos, (organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            writeCollection(organization.getPositions(), dos, (position -> {
                                writeLocalDate(dos, position.getStartDate());
                                writeLocalDate(dos, position.getEndDate());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription());
                            }));
                        }));
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dis, () -> {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            });
            readItems(dis, () -> {
                SectionType sectionName = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionName, readSection(dis, sectionName));
            });
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionName) throws IOException {
        switch (sectionName) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationSection(
                        readList(dis, () -> new Organization(
                                new Organization.Link(
                                        dis.readUTF(), dis.readUTF()), readList(dis, () -> new Organization.Position(
                                                readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()
                        )))));
            default:
                throw new IllegalStateException();
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> elementReader) throws IOException {
        int size = dis.readInt();
        List<T> items = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            items.add(elementReader.read());
        }
        return items;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private interface Writable<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeCollection(Collection<T> collection, DataOutputStream dos, Writable<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private <T> void readItems(DataInputStream dis, ElementProcessor elementProcessor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            elementProcessor.process();
        }
    }
}
