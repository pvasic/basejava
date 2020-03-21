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
public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                String sectionTypeName = entry.getKey().name();
                if (sectionTypeName.equals("PERSONAL") || sectionTypeName.equals("OBJECTIVE")) {
                    dos.writeUTF(sectionTypeName);
                    dos.writeUTF(((TextSection) entry.getValue()).getContent());
                } else {
                    if (sectionTypeName.equals("ACHIEVEMENT") || sectionTypeName.equals("QUALIFICATIONS")) {
                        dos.writeUTF(sectionTypeName);
                        writeItems(dos, ((ListSection) entry.getValue()).getItems());
                    } else {
                        if (sectionTypeName.equals("EXPERIENCE") || sectionTypeName.equals("EDUCATION")) {
                            dos.writeUTF(sectionTypeName);
                            writeOrganizations(dos, ((OrganizationSection) entry.getValue()).getOrganizations());
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
                String sectionTypeName = dis.readUTF();
                if (sectionTypeName.equals("PERSONAL") || sectionTypeName.equals("OBJECTIVE")) {
                    String content = dis.readUTF();
                    resume.addSection(SectionType.valueOf(sectionTypeName), new TextSection(content));
                } else {
                    if (sectionTypeName.equals("ACHIEVEMENT") || sectionTypeName.equals("QUALIFICATIONS")) {
                        List<String> items = new ArrayList<>();
                        int sizeItems = dis.readInt();
                        for (int j = 0; j < sizeItems; j++) {
                            items.add(dis.readUTF());
                        }
                        resume.addSection(SectionType.valueOf(sectionTypeName), new ListSection(items));
                    } else {
                        if (sectionTypeName.equals("EXPERIENCE") || sectionTypeName.equals("EDUCATION")) {
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
                            resume.addSection(SectionType.valueOf(sectionTypeName), new OrganizationSection(organizations));
                        }
                    }
                }
            }
            return resume;
        }
    }

    private void writeItems(DataOutputStream dos, List<String> items) throws IOException {
        dos.writeInt(items.size());
        for (String str : items) {
            dos.writeUTF(str);
        }
    }

    private void writeOrganizations(DataOutputStream dos, List<Organization> organizations) throws IOException {
        dos.writeInt(organizations.size());
        for (Organization org : organizations) {
            dos.writeUTF(org.getHomePage().getName());
            dos.writeUTF(writeCheckNull(org.getHomePage().getUrl()));
            List<Organization.Position> positions = org.getPositions();
            dos.writeInt(positions.size());
            for (Organization.Position pos : positions) {
                dos.writeUTF(pos.getStartDate().toString());
                dos.writeUTF(pos.getEndDate().toString());
                dos.writeUTF(pos.getPositionName());
                dos.writeUTF(writeCheckNull(pos.getResponsibility()));
            }
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
