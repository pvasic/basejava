package com.javaops.web.storage.serializer;

import com.javaops.web.model.*;
import com.javaops.web.util.DateUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Vasichkin Pavel
 */
public class DataStreamSerializer<T> implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeInt(dos, contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSections();
            writeInt(dos, sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionName = entry.getKey();
                switch (sectionName) {
                    case PERSONAL:
                    case OBJECTIVE:
                        writeSectionName(dos, sectionName);
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeSectionName(dos, sectionName);
                        List<String> items = ((ListSection) entry.getValue()).getItems();
                        dos.writeInt(items.size());
                        for (String str : items) {
                            dos.writeUTF(str);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeSectionName(dos, sectionName);
                        List<Organization> organizations = ((OrganizationSection) entry.getValue()).getOrganizations();
                        writeInt(dos, organizations.size());
                        for (Organization org : organizations) {
                            dos.writeUTF(org.getHomePage().getName());
                            dos.writeUTF(writeCheckNull(org.getHomePage().getUrl()));
                            List<Organization.Position> positions = org.getPositions();
                            writeInt(dos, positions.size());
                            for (Organization.Position pos : positions) {
                                dos.writeUTF(pos.getStartDate().toString());
                                dos.writeUTF(pos.getEndDate().toString());
                                dos.writeUTF(pos.getPositionName());
                                dos.writeUTF(writeCheckNull(pos.getResponsibility()));
                            }
                        }
                }
            }
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
            for (int i = 0; i < size ; i++) {
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

    private void writeWithException(List<? extends T> list, DataOutputStream dos, Writable section) {

    }

    private void writeInt(DataOutputStream dos, int size) throws IOException {
        dos.writeInt(size);
    }

    private void writeSectionName(DataOutputStream dos, SectionType sectionType) throws IOException {
        dos.writeUTF(sectionType.name());
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
