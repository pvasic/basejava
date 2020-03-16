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
            SectionType sectionType = SectionType.PERSONAL;
            writeTextSection(dos, sections, sectionType);
            sectionType = SectionType.OBJECTIVE;
            writeTextSection(dos, sections, sectionType);

            sectionType = SectionType.ACHIEVEMENT;
            writeListSections(dos, sections, sectionType);
            sectionType = SectionType.QUALIFICATIONS;
            writeListSections(dos, sections, sectionType);

            sectionType = SectionType.EXPERIENCE;
            writeListOrganizationsSection(dos, sections, sectionType);
            sectionType = SectionType.EDUCATION;
            writeListOrganizationsSection(dos, sections, sectionType);
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
            addTextSection(dis, resume);
            addTextSection(dis, resume);
            addListSection(dis, resume);
            addListSection(dis, resume);
            addOrganizationSection(dis, resume);
            addOrganizationSection(dis, resume);
            return resume;
        }
    }

    private void writeTextSection(DataOutputStream dos, Map<SectionType, Section> sections, SectionType sectionType) throws IOException {
        dos.writeUTF(sectionType.name());
        dos.writeUTF(((TextSection) sections.get(sectionType)).getContent());
    }

    private void writeListSections(DataOutputStream dos, Map<SectionType, Section> sections, SectionType sectionType) throws IOException {
        List<String> items = ((ListSection) sections.get(sectionType)).getItems();
        dos.writeUTF(sectionType.name());
        writeItems(dos, items);
    }

    private void writeListOrganizationsSection(DataOutputStream dos, Map<SectionType, Section> sections, SectionType sectionType) throws IOException {
        List<Organization> organizations = ((OrganizationSection) sections.get(sectionType)).getOrganizations();
        dos.writeUTF(sectionType.name());
        writeOrganizations(dos, organizations);
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

    private void addTextSection(DataInputStream dis, Resume resume) throws IOException {
        String sectionType = dis.readUTF();
        String content = dis.readUTF();
        resume.addSection(SectionType.valueOf(sectionType), new TextSection(content));
    }

    private void addListSection(DataInputStream dis, Resume resume) throws IOException {
        List<String> items = new ArrayList<>();
        String sectionType = dis.readUTF();
        readItems(dis, items);

        // TODO Разобраться: в конструктор ListSection, переменная items педается по ссылке? Или должна по значению?
        resume.addSection(SectionType.valueOf(sectionType), new ListSection(items));
    }

    private void addOrganizationSection(DataInputStream dis, Resume resume) throws IOException {
        List<Organization> organizations = new ArrayList<>();
        String sectionType = dis.readUTF();
        readOrganizations(dis, organizations);
        resume.addSection(SectionType.valueOf(sectionType), new OrganizationSection(organizations));
    }

    private void readItems(DataInputStream dis, List<String> items) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            items.add(dis.readUTF());
        }
    }

    private void readOrganizations(DataInputStream dis, List<Organization> organizations) throws IOException {
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

    private String readCheckNull(String string) {
        if (string.equals("null")) {
            return null;
        } else {
            return string;
        }
    }
}
