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
            dos.writeUTF(SectionType.PERSONAL.name());
            dos.writeUTF(((TextSection) sections.get(SectionType.PERSONAL)).getContent());
            dos.writeUTF(SectionType.OBJECTIVE.name());
            dos.writeUTF(((TextSection) sections.get(SectionType.OBJECTIVE)).getContent());

            List<String> items = ((ListSection) sections.get(SectionType.ACHIEVEMENT)).getItems();
            dos.writeUTF(SectionType.ACHIEVEMENT.name());
            writeItems(dos, items);

            items = ((ListSection) sections.get(SectionType.QUALIFICATIONS)).getItems();
            writeItems(dos, items);

            List<Organization> organizations = ((OrganizationSection) sections.get(SectionType.EXPERIENCE)).getOrganizations();
            dos.writeUTF(SectionType.EXPERIENCE.name());
            writeOrganizations(dos, organizations);

            organizations = ((OrganizationSection) sections.get(SectionType.EDUCATION)).getOrganizations();
            dos.writeUTF(SectionType.EDUCATION.name());
            writeOrganizations(dos, organizations);
            // TODO implements sections
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

            String sectionType = dis.readUTF();
            String content = dis.readUTF();
            resume.addSection(SectionType.valueOf(sectionType), new TextSection(content));
            sectionType = dis.readUTF();
            content = dis.readUTF();
            resume.addSection(SectionType.valueOf(sectionType), new TextSection(content));

            List<String> items = new ArrayList<>();
            sectionType = dis.readUTF();
            readItems(dis, items);
            resume.addSection(SectionType.valueOf(sectionType), new ListSection(items));

            items.clear();
            sectionType = dis.readUTF();
            readItems(dis, items);
            resume.addSection(SectionType.valueOf(sectionType), new ListSection(items));

            List<Organization> organizations = new ArrayList<>();
            sectionType = dis.readUTF();
            readOrganization(dis, organizations);
            resume.addSection(SectionType.valueOf(sectionType), new OrganizationSection(organizations));

            organizations.clear();
            sectionType = dis.readUTF();
            readOrganization(dis, organizations);
            resume.addSection(SectionType.valueOf(sectionType), new OrganizationSection(organizations));

            return resume;
        }
    }

    private void readItems(DataInputStream dis, List<String> items) throws IOException {
        int size;
        size = dis.readInt();
        for (int i = 0; i < size; i++) {
            items.add(dis.readUTF());
        }
    }

    private void readOrganization(DataInputStream dis, List<Organization> organizations) throws IOException {
        int sizeOrg;
        sizeOrg = dis.readInt();
        for (int i = 0; i < sizeOrg; i++) {
            String name = dis.readUTF();
            String url = readCheckNull(dis.readUTF());
            int sizePos = dis.readInt();
            List<Organization.Position> positions = new ArrayList<>();
            for (int j = 0; j < sizePos; j++) {
                positions.add(new Organization.Position(DateUtil.of(dis.readUTF()), DateUtil.of(dis.readUTF()), dis.readUTF(), readCheckNull(dis.readUTF())));
            }
            organizations.add(new Organization(new Organization.Link(name, url), positions));
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
