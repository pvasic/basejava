package com.javaops.web.storage.serializer;

import com.javaops.web.model.*;
import com.javaops.web.util.DateUtil;

import java.io.*;
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
                            dos.writeUTF(writeCheckNull(organization.getHomePage().getUrl()));
                            writeCollection(organization.getPositions(), dos, (position -> {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getPositionName());
                                dos.writeUTF(writeCheckNull(position.getResponsibility()));
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
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionName = SectionType.valueOf(dis.readUTF());
                switch (sectionName) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionName, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> items = new ArrayList<>();
                        int sizeItems = dis.readInt();
                        for (int j = 0; j < sizeItems; j++) {
                            items.add(dis.readUTF());
                        }
                        resume.addSection(sectionName, new ListSection(items));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        int sizeOrg;
                        sizeOrg = dis.readInt();
                        for (int k = 0; k < sizeOrg; k++) {
                            String name = dis.readUTF();
                            String url = readCheckNull(dis.readUTF());
                            int sizePos = dis.readInt();
                            List<Organization.Position> positions = new ArrayList<>();
                            for (int l = 0; l < sizePos; l++) {
                                positions.add(new Organization.Position(DateUtil.of(dis.readUTF()), DateUtil.of(dis.readUTF()), dis.readUTF(), readCheckNull(dis.readUTF())));
                            }
                            organizations.add(new Organization(new Organization.Link(name, url), positions));
                        }
                        resume.addSection(sectionName, new OrganizationSection(organizations));
                        break;
                }
            }
            return resume;
        }
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

    private String writeCheckNull(String string) {
        if (string == null) {
            return "null";
        } else {
            return string;
        }
    }

    private String readCheckNull(String string) {
        if (string.equals("null")) {
            return null;
        } else {
            return string;
        }
    }
}
