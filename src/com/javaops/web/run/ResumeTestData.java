package com.javaops.web.run;

import com.javaops.web.model.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vasichkin Pavel
 */
public class ResumeTestData {

    private static final String UUID_1 = "UUID1";
    private static final String FULL_NAME_1 = "FullName1";

    private static final Resume resume1;

    private static final Map<ContactType, String> contacts;
    private static final Map<SectionType, Section> sections;

    private static final String TEL_1 = "+7(921) 855-0482";
    private static final String SKYPE_1 = "grigory.kislin";
    private static final String EMAIL_1 = "gkislin@yandex.ru";
    private static final String LINKEDLN_1 = "https://www.linkedin.com/in/gkislin";
    private static final String GITHUB_1 = "https://github.com/gkislin";
    private static final String STACKOVERFLOW_1 = "https://stackoverflow.com/users/548473/grigory-kislin";

    private static final String DESC_PERSONAL = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";
    private static final String DESC_OBJECTIVE = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";

    private static final TextSection PERSONAL_1;
    private static final TextSection OBJECTIVE_1;

    private static final String DESC_ACHIEVEMENT_1 = "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.";
    private static final String DESC_ACHIEVEMENT_2 = "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.";

    private static final String DESC_QUALIFICATIONS_1 = "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2";
    private static final String DESC_QUALIFICATIONS_2 = "Version control: Subversion, Git, Mercury, ClearCase, Perforce";
    private static final String DESC_QUALIFICATIONS_3 = "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle";


    private static final List<String> ACHIEVEMENT_LIST;
    private static final List<String> QUALIFICATIONS_LIST;

    private static final Section ACHIEVEMENT_SECTION;
    private static final Section QUALIFICATIONS_SECTION;




    static {
        resume1 = new Resume(UUID_1, FULL_NAME_1);

        contacts = new EnumMap<>(ContactType.class);
        sections = new EnumMap<>(SectionType.class);

        PERSONAL_1 = new TextSection(DESC_PERSONAL);
        OBJECTIVE_1 = new TextSection(DESC_OBJECTIVE);

        ACHIEVEMENT_LIST = new ArrayList<>();
        ACHIEVEMENT_LIST.add(DESC_ACHIEVEMENT_1);
        ACHIEVEMENT_LIST.add(DESC_ACHIEVEMENT_2);

        QUALIFICATIONS_LIST = new ArrayList<>();
        QUALIFICATIONS_LIST.add(DESC_QUALIFICATIONS_1);
        QUALIFICATIONS_LIST.add(DESC_QUALIFICATIONS_2);
        QUALIFICATIONS_LIST.add(DESC_QUALIFICATIONS_3);

        ACHIEVEMENT_SECTION = new ListSection(ACHIEVEMENT_LIST);
        QUALIFICATIONS_SECTION = new ListSection(QUALIFICATIONS_LIST);


        contacts.put(ContactType.TEL, TEL_1);
        contacts.put(ContactType.SKYPE, SKYPE_1);
        contacts.put(ContactType.EMAIL, EMAIL_1);
        contacts.put(ContactType.LINKEDLN,LINKEDLN_1);
        contacts.put(ContactType.GITHUB, GITHUB_1);
        contacts.put(ContactType.STACKOVERFLOW, STACKOVERFLOW_1);


        sections.put(SectionType.PERSONAL, PERSONAL_1);
        sections.put(SectionType.OBJECTIVE, OBJECTIVE_1);
        sections.put(SectionType.ACHIEVEMENT, ACHIEVEMENT_SECTION);
        sections.put(SectionType.QUALIFICATIONS, QUALIFICATIONS_SECTION);

        resume1.setContacts(contacts);
        resume1.setSections(sections);

    }

    public static void main(String[] args) {

        System.out.println(resume1.toString());
    }

}
