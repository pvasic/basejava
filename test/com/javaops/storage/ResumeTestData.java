package com.javaops.storage;

import com.javaops.model.*;
import com.javaops.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Vasichkin Pavel
 */
public class ResumeTestData {

    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final String FULL_NAME_1 = "FullName1";
    public static final String FULL_NAME_2 = "FullName2";
    public static final String FULL_NAME_3 = "FullName3";
    public static final String FULL_NAME_4 = "FullName4";
    public static final String FULL_NAME_UPDATE = "FullNameUpdate";
    public static final String LINKEDLN_UPDATE = "LinkedlnUpdate";

    private static final String MOBILE_1 = "+7(921) 855-0482";
    private static final String MOBILE_2 = "+7(926) 665-0482";
    private static final String MOBILE_3 = "+7(921) 999-0482";
    private static final String MOBILE_4 = "+7(921) 111-0482";
    private static final String SKYPE_1 = ".kislin";
    private static final String SKYPE_2 = "grigory.";
    private static final String SKYPE_3 = "pavel";
    private static final String SKYPE_4 = "egor";
    private static final String EMAIL_1 = "pavel@yandex.ru";
    private static final String EMAIL_2 = "egor@yandex.ru";
    private static final String EMAIL_3 = "vasya@yandex.ru";
    private static final String EMAIL_4 = "olga@yandex.ru";
    private static final String LINKEDLN_1 = "https://www.linkedin.com/in/gkislin";
    private static final String LINKEDLN_2 = "https://www.linkedin.com/in/pavel";
    private static final String LINKEDLN_3 = "https://www.linkedin.com/in/vasya";
    private static final String LINKEDLN_4 = "https://www.linkedin.com/in/olga";

    private static final String GITHUB_1 = "https://github.com/gkislin";
    private static final String GITHUB_2 = "https://github.com/pavel";
    private static final String GITHUB_3 = "https://github.com/vasya";
    private static final String GITHUB_4 = "https://github.com/olga";

    private static final String STACKOVERFLOW_1 = "https://stackoverflow.com/users/548473/pavel-pavel";
    private static final String STACKOVERFLOW_2 = "https://stackoverflow.com/users/548473/grigory-kislin";
    private static final String STACKOVERFLOW_3 = "https://stackoverflow.com/users/548473/vasya-vasya";
    private static final String STACKOVERFLOW_4 = "https://stackoverflow.com/users/548473/olga-olga";


    private static final String DESC_PERSONAL_1 = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";
    private static final String DESC_PERSONAL_2 = "сильная логика, креативность, инициативность. Пурист кода и архитектуры.";
    private static final String DESC_PERSONAL_3 = "креативность, инициативность. Пурист кода и архитектуры.";
    private static final String DESC_PERSONAL_4 = "инициативность. Пурист кода и архитектуры.";
    private static final String DESC_OBJECTIVE_1 = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";
    private static final String DESC_OBJECTIVE_2 = "Ведущий корпоративного обучения по Java Web и Enterprise технологиям";
    private static final String DESC_OBJECTIVE_3 = "Ведущий по Java Web и Enterprise технологиям";
    private static final String DESC_OBJECTIVE_4 = "Ведущий  Enterprise технологиям";

    private static final String DESC_ACHIEVEMENT_1 = "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.";
    private static final String DESC_ACHIEVEMENT_2 = "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.";
    private static final String DESC_QUALIFICATIONS_1 = "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2";
    private static final String DESC_QUALIFICATIONS_2 = "Version control: Subversion, Git, Mercury, ClearCase, Perforce";
    private static final String DESC_QUALIFICATIONS_3 = "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle";
    private static final String DESC_QUALIFICATIONS_4 = "Scale";

    private static final String NAME_1 = "Java Online Projects";
    private static final String NAME_4 = "Java";
    private static final String URL_1 = "javaops.ru";
    private static final LocalDate START_DATE_1 = DateUtil.of(2013, Month.JANUARY);
    private static final LocalDate END_DATE_1 = LocalDate.now();
    private static final String POSITION_NAME_1 = "Автор проекта";
    private static final String RESPONSIBILITY_1 = "Создание, организация и проведение Java онлайн проектов и стажировок";

    private static final String NAME_2 = "Coursera";
    private static final String URL_2 = null;
    private static final LocalDate START_DATE_2 = DateUtil.of(2013, Month.MARCH);
    private static final LocalDate END_DATE_2 = DateUtil.of(2013, Month.MAY);
    private static final String POSITION_NAME_2 = "\"Functional Programming Principles in Scala\" by Martin Odersky";
    private static final String RESPONSIBILITY_2 = null;

    private static final String NAME_3 = "Coursera";
    private static final String URL_3 = null;
    private static final LocalDate START_DATE_3 = DateUtil.of(2015, Month.FEBRUARY);
    private static final LocalDate END_DATE_3 = DateUtil.of(2016, Month.OCTOBER);
    private static final String POSITION_NAME_3 = "\"Java Enterprise\" by Bill";
    private static final String RESPONSIBILITY_3 = null;

    private static final Organization.Position POSITION_1;
    private static final Organization.Position POSITION_2;
    private static final Organization.Position POSITION_3;

    private static final List<Organization.Position> PERIODS_1 = new ArrayList<>();
    private static final List<Organization.Position> PERIODS_2 = new ArrayList<>();

    private static final Organization EXPERIENCE_ORGANIZATION_1;
    private static final Organization EXPERIENCE_ORGANIZATION_2;
    private static final Organization EXPERIENCE_ORGANIZATION_3;
    private static final Organization EXPERIENCE_ORGANIZATION_4;
    private static final Organization EDUCATION_ORGANIZATION_1;
    private static final Organization EDUCATION_ORGANIZATION_2;
    private static final Organization EDUCATION_ORGANIZATION_3;
    private static final Organization EDUCATION_ORGANIZATION_4;

    private static final List<String> ACHIEVEMENT_LIST_1 = new ArrayList<>();
    private static final List<String> ACHIEVEMENT_LIST_2 = new ArrayList<>();
    private static final List<String> ACHIEVEMENT_LIST_3 = new ArrayList<>();
    private static final List<String> ACHIEVEMENT_LIST_4 = new ArrayList<>();
    private static final List<String> QUALIFICATIONS_LIST_1 = new ArrayList<>();
    private static final List<String> QUALIFICATIONS_LIST_2 = new ArrayList<>();
    private static final List<String> QUALIFICATIONS_LIST_3 = new ArrayList<>();
    private static final List<String> QUALIFICATIONS_LIST_4 = new ArrayList<>();

    private static final List<Organization> EXPERIENCE_LIST_1 = new ArrayList<>();
    private static final List<Organization> EXPERIENCE_LIST_2 = new ArrayList<>();
    private static final List<Organization> EXPERIENCE_LIST_3 = new ArrayList<>();
    private static final List<Organization> EXPERIENCE_LIST_4 = new ArrayList<>();
    private static final List<Organization> EDUCATION_LIST_1 = new ArrayList<>();
    private static final List<Organization> EDUCATION_LIST_2 = new ArrayList<>();
    private static final List<Organization> EDUCATION_LIST_3 = new ArrayList<>();
    private static final List<Organization> EDUCATION_LIST_4 = new ArrayList<>();

    private static final Section PERSONAL_SECTION_1;
    private static final Section PERSONAL_SECTION_2;
    private static final Section PERSONAL_SECTION_3;
    private static final Section PERSONAL_SECTION_4;
    private static final Section OBJECTIVE_SECTION_1;
    private static final Section OBJECTIVE_SECTION_2;
    private static final Section OBJECTIVE_SECTION_3;
    private static final Section OBJECTIVE_SECTION_4;
    private static final Section ACHIEVEMENT_SECTION_1;
    private static final Section ACHIEVEMENT_SECTION_2;
    private static final Section ACHIEVEMENT_SECTION_3;
    private static final Section ACHIEVEMENT_SECTION_4;
    private static final Section QUALIFICATIONS_SECTION_1;
    private static final Section QUALIFICATIONS_SECTION_2;
    private static final Section QUALIFICATIONS_SECTION_3;
    private static final Section QUALIFICATIONS_SECTION_4;
    private static final Section EXPERIENCE_SECTION_1;
    private static final Section EXPERIENCE_SECTION_2;
    private static final Section EXPERIENCE_SECTION_3;
    private static final Section EXPERIENCE_SECTION_4;
    private static final Section EDUCATION_SECTION_1;
    private static final Section EDUCATION_SECTION_2;
    private static final Section EDUCATION_SECTION_3;
    private static final Section EDUCATION_SECTION_4;

    static {
        PERSONAL_SECTION_1 = new TextSection(DESC_PERSONAL_1);
        PERSONAL_SECTION_2 = new TextSection(DESC_PERSONAL_2);
        PERSONAL_SECTION_3 = new TextSection(DESC_PERSONAL_3);
        PERSONAL_SECTION_4 = new TextSection(DESC_PERSONAL_4);
        OBJECTIVE_SECTION_1 = new TextSection(DESC_OBJECTIVE_1);
        OBJECTIVE_SECTION_2 = new TextSection(DESC_OBJECTIVE_2);
        OBJECTIVE_SECTION_3 = new TextSection(DESC_OBJECTIVE_3);
        OBJECTIVE_SECTION_4 = new TextSection(DESC_OBJECTIVE_4);

        ACHIEVEMENT_LIST_1.add(DESC_ACHIEVEMENT_1);
        ACHIEVEMENT_LIST_2.add(DESC_ACHIEVEMENT_2);
        ACHIEVEMENT_LIST_3.add(DESC_ACHIEVEMENT_1);
        ACHIEVEMENT_LIST_3.add(DESC_ACHIEVEMENT_2);
        ACHIEVEMENT_LIST_4.add(DESC_ACHIEVEMENT_1);

        QUALIFICATIONS_LIST_1.add(DESC_QUALIFICATIONS_1);
        QUALIFICATIONS_LIST_2.add(DESC_QUALIFICATIONS_2);
        QUALIFICATIONS_LIST_3.add(DESC_QUALIFICATIONS_3);
        QUALIFICATIONS_LIST_4.add(DESC_QUALIFICATIONS_4);
        ACHIEVEMENT_SECTION_1 = new ListSection(ACHIEVEMENT_LIST_1);
        ACHIEVEMENT_SECTION_2 = new ListSection(ACHIEVEMENT_LIST_2);
        ACHIEVEMENT_SECTION_3 = new ListSection(ACHIEVEMENT_LIST_3);
        ACHIEVEMENT_SECTION_4 = new ListSection(ACHIEVEMENT_LIST_4);
        QUALIFICATIONS_SECTION_1 = new ListSection(QUALIFICATIONS_LIST_1);
        QUALIFICATIONS_SECTION_2 = new ListSection(QUALIFICATIONS_LIST_2);
        QUALIFICATIONS_SECTION_3 = new ListSection(QUALIFICATIONS_LIST_3);
        QUALIFICATIONS_SECTION_4 = new ListSection(QUALIFICATIONS_LIST_4);

        POSITION_1 = new Organization.Position(START_DATE_1, END_DATE_1, POSITION_NAME_1, RESPONSIBILITY_1);
        POSITION_2 = new Organization.Position(START_DATE_2, END_DATE_2, POSITION_NAME_2, RESPONSIBILITY_2);
        POSITION_3 = new Organization.Position(START_DATE_3, END_DATE_3, POSITION_NAME_3, RESPONSIBILITY_3);

        PERIODS_1.add(POSITION_1);
        PERIODS_2.add(POSITION_2);
        PERIODS_2.add(POSITION_3);


        EXPERIENCE_ORGANIZATION_1 = new Organization(new Organization.Link(NAME_1, URL_1), PERIODS_1);
        EXPERIENCE_ORGANIZATION_2 = new Organization(new Organization.Link(NAME_1, URL_1), PERIODS_1);
        EXPERIENCE_ORGANIZATION_3 = new Organization(new Organization.Link(NAME_1, URL_1), PERIODS_1);
        EXPERIENCE_ORGANIZATION_4 = new Organization(new Organization.Link(NAME_1, URL_1), PERIODS_1);
        EDUCATION_ORGANIZATION_1 = new Organization(new Organization.Link(NAME_1, URL_1), PERIODS_2);
        EDUCATION_ORGANIZATION_2 = new Organization(new Organization.Link(NAME_1, URL_1), PERIODS_2);
        EDUCATION_ORGANIZATION_3 = new Organization(new Organization.Link(NAME_1, URL_1), PERIODS_2);
        EDUCATION_ORGANIZATION_4 = new Organization(new Organization.Link(NAME_1, URL_1), PERIODS_2);
        EXPERIENCE_LIST_1.add(EXPERIENCE_ORGANIZATION_1);
        EXPERIENCE_LIST_2.add(EXPERIENCE_ORGANIZATION_2);
        EXPERIENCE_LIST_2.add(EXPERIENCE_ORGANIZATION_3);
        EXPERIENCE_LIST_3.add(EXPERIENCE_ORGANIZATION_3);
        EXPERIENCE_LIST_3.add(EXPERIENCE_ORGANIZATION_1);
        EXPERIENCE_LIST_3.add(EXPERIENCE_ORGANIZATION_4);
        EXPERIENCE_LIST_4.add(EXPERIENCE_ORGANIZATION_1);
        EXPERIENCE_LIST_4.add(EXPERIENCE_ORGANIZATION_2);
        EXPERIENCE_LIST_4.add(EXPERIENCE_ORGANIZATION_3);
        EXPERIENCE_LIST_4.add(EXPERIENCE_ORGANIZATION_4);
        EDUCATION_LIST_1.add(EDUCATION_ORGANIZATION_1);
        EDUCATION_LIST_2.add(EDUCATION_ORGANIZATION_2);
        EDUCATION_LIST_3.add(EDUCATION_ORGANIZATION_3);
        EDUCATION_LIST_3.add(EDUCATION_ORGANIZATION_4);
        EDUCATION_LIST_4.add(EDUCATION_ORGANIZATION_1);
        EDUCATION_LIST_4.add(EDUCATION_ORGANIZATION_2);
        EDUCATION_LIST_4.add(EDUCATION_ORGANIZATION_3);
        EDUCATION_LIST_4.add(EDUCATION_ORGANIZATION_4);
        EXPERIENCE_SECTION_1 = new OrganizationSection(EXPERIENCE_LIST_1);
        EXPERIENCE_SECTION_2 = new OrganizationSection(EXPERIENCE_LIST_2);
        EXPERIENCE_SECTION_3 = new OrganizationSection(EXPERIENCE_LIST_3);
        EXPERIENCE_SECTION_4 = new OrganizationSection(EXPERIENCE_LIST_4);
        EDUCATION_SECTION_1 = new OrganizationSection(EDUCATION_LIST_1);
        EDUCATION_SECTION_2 = new OrganizationSection(EDUCATION_LIST_2);
        EDUCATION_SECTION_3 = new OrganizationSection(EDUCATION_LIST_3);
        EDUCATION_SECTION_4 = new OrganizationSection(EDUCATION_LIST_4);
    }

    public static Resume getRESUME1(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.MOBILE, MOBILE_1);
        resume.addContact(ContactType.SKYPE, SKYPE_1);
        resume.addContact(ContactType.EMAIL, EMAIL_1);
        resume.addContact(ContactType.LINKEDLN, LINKEDLN_1);
        resume.addContact(ContactType.GITHUB, GITHUB_1);
        resume.addContact(ContactType.STACKOVERFLOW, STACKOVERFLOW_1);

        resume.addSection(SectionType.PERSONAL, PERSONAL_SECTION_1);
        resume.addSection(SectionType.OBJECTIVE, OBJECTIVE_SECTION_1);
        resume.addSection(SectionType.ACHIEVEMENT, ACHIEVEMENT_SECTION_1);
        resume.addSection(SectionType.QUALIFICATIONS, QUALIFICATIONS_SECTION_1);
//        resume.getSections().put(SectionType.EXPERIENCE, EXPERIENCE_SECTION_1);
//        resume.getSections().put(SectionType.EDUCATION, EDUCATION_SECTION_1);
        return resume;
    }

    public static Resume getRESUME2(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.MOBILE, MOBILE_2);
        resume.addContact(ContactType.SKYPE, SKYPE_2);
        resume.addContact(ContactType.EMAIL, EMAIL_2);
//        resume.addContact(ContactType.LINKEDLN, LINKEDLN_2);
        resume.addContact(ContactType.GITHUB, GITHUB_2);
        resume.addContact(ContactType.STACKOVERFLOW, STACKOVERFLOW_2);
//
        resume.addSection(SectionType.PERSONAL, PERSONAL_SECTION_2);
        resume.addSection(SectionType.OBJECTIVE, OBJECTIVE_SECTION_2);
        resume.addSection(SectionType.ACHIEVEMENT, ACHIEVEMENT_SECTION_2);
        resume.addSection(SectionType.QUALIFICATIONS, QUALIFICATIONS_SECTION_2);
//        resume.getSections().put(SectionType.EXPERIENCE, EXPERIENCE_SECTION_2);
//        resume.getSections().put(SectionType.EDUCATION, EDUCATION_SECTION_2);
        return resume;
    }

    public static Resume getRESUME3(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.MOBILE, MOBILE_3);
        resume.addContact(ContactType.SKYPE, SKYPE_3);
        resume.addContact(ContactType.EMAIL, EMAIL_3);
        resume.addContact(ContactType.LINKEDLN, LINKEDLN_3);
        resume.addContact(ContactType.GITHUB, GITHUB_3);
        resume.addContact(ContactType.STACKOVERFLOW, STACKOVERFLOW_3);

        resume.addSection(SectionType.PERSONAL, PERSONAL_SECTION_3);
        resume.addSection(SectionType.OBJECTIVE, OBJECTIVE_SECTION_3);
        resume.addSection(SectionType.ACHIEVEMENT, ACHIEVEMENT_SECTION_3);
        resume.addSection(SectionType.QUALIFICATIONS, QUALIFICATIONS_SECTION_3);
//        resume.getSections().put(SectionType.EXPERIENCE, EXPERIENCE_SECTION_3);
//        resume.getSections().put(SectionType.EDUCATION, EDUCATION_SECTION_3);
        return resume;
    }

    public static Resume getRESUME4(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.MOBILE, MOBILE_4);
        resume.addContact(ContactType.SKYPE, SKYPE_4);
        resume.addContact(ContactType.EMAIL, EMAIL_4);
        resume.addContact(ContactType.LINKEDLN, LINKEDLN_4);
        resume.addContact(ContactType.GITHUB, GITHUB_4);
        resume.addContact(ContactType.STACKOVERFLOW, STACKOVERFLOW_4);

        resume.addSection(SectionType.PERSONAL, PERSONAL_SECTION_4);
        resume.addSection(SectionType.OBJECTIVE, OBJECTIVE_SECTION_4);
        resume.addSection(SectionType.ACHIEVEMENT, ACHIEVEMENT_SECTION_4);
        resume.addSection(SectionType.QUALIFICATIONS, QUALIFICATIONS_SECTION_4);
//        resume.getSections().put(SectionType.EXPERIENCE, EXPERIENCE_SECTION_4);
//        resume.getSections().put(SectionType.EDUCATION, EDUCATION_SECTION_4);
        return resume;
    }
}

