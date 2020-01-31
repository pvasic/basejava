package com.javaops.web.storage;

import com.javaops.web.model.*;
import com.javaops.web.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasichkin Pavel
 */
public class ResumeTestData {

    private static final String UUID = "UUID1";
    private static final String FULL_NAME = "FullName1";


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

    private static final BusyPeriod BUSY_PERIOD_1;
    private static final BusyPeriod BUSY_PERIOD_2;
    private static final BusyPeriod BUSY_PERIOD_3;

    private static final List<BusyPeriod> PERIODS_1 = new ArrayList<>();
    private static final List<BusyPeriod> PERIODS_2 = new ArrayList<>();

    private static final Organisation EXPERIENCE_ORGANISATION_1;
    private static final Organisation EXPERIENCE_ORGANISATION_2;
    private static final Organisation EXPERIENCE_ORGANISATION_3;
    private static final Organisation EXPERIENCE_ORGANISATION_4;
    private static final Organisation EDUCATION_ORGANISATION_1;
    private static final Organisation EDUCATION_ORGANISATION_2;
    private static final Organisation EDUCATION_ORGANISATION_3;
    private static final Organisation EDUCATION_ORGANISATION_4;

    private static final List<String> ACHIEVEMENT_LIST_1 = new ArrayList<>();
    private static final List<String> ACHIEVEMENT_LIST_2 = new ArrayList<>();
    private static final List<String> ACHIEVEMENT_LIST_3 = new ArrayList<>();
    private static final List<String> ACHIEVEMENT_LIST_4 = new ArrayList<>();
    private static final List<String> QUALIFICATIONS_LIST_1 = new ArrayList<>();
    private static final List<String> QUALIFICATIONS_LIST_2 = new ArrayList<>();
    private static final List<String> QUALIFICATIONS_LIST_3 = new ArrayList<>();
    private static final List<String> QUALIFICATIONS_LIST_4 = new ArrayList<>();

    private static final List<Organisation> EXPERIENCE_LIST_1 = new ArrayList<>();
    private static final List<Organisation> EXPERIENCE_LIST_2 = new ArrayList<>();
    private static final List<Organisation> EXPERIENCE_LIST_3 = new ArrayList<>();
    private static final List<Organisation> EXPERIENCE_LIST_4 = new ArrayList<>();
    private static final List<Organisation> EDUCATION_LIST_1 = new ArrayList<>();
    private static final List<Organisation> EDUCATION_LIST_2 = new ArrayList<>();
    private static final List<Organisation> EDUCATION_LIST_3 = new ArrayList<>();
    private static final List<Organisation> EDUCATION_LIST_4 = new ArrayList<>();

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

        QUALIFICATIONS_LIST_1.add(DESC_QUALIFICATIONS_1);
        QUALIFICATIONS_LIST_2.add(DESC_QUALIFICATIONS_2);
        QUALIFICATIONS_LIST_3.add(DESC_QUALIFICATIONS_3);
        ACHIEVEMENT_SECTION_1 = new ListSection(ACHIEVEMENT_LIST_1);
        ACHIEVEMENT_SECTION_2 = new ListSection(ACHIEVEMENT_LIST_2);
        ACHIEVEMENT_SECTION_3 = new ListSection(ACHIEVEMENT_LIST_3);
        ACHIEVEMENT_SECTION_4 = new ListSection(ACHIEVEMENT_LIST_4);
        QUALIFICATIONS_SECTION_1 = new ListSection(QUALIFICATIONS_LIST_1);
        QUALIFICATIONS_SECTION_2 = new ListSection(QUALIFICATIONS_LIST_2);
        QUALIFICATIONS_SECTION_3 = new ListSection(QUALIFICATIONS_LIST_3);
        QUALIFICATIONS_SECTION_4 = new ListSection(QUALIFICATIONS_LIST_4);

        BUSY_PERIOD_1 = new BusyPeriod(START_DATE_1, END_DATE_1, POSITION_NAME_1, RESPONSIBILITY_1);
        BUSY_PERIOD_2 = new BusyPeriod(START_DATE_2, END_DATE_2, POSITION_NAME_2, RESPONSIBILITY_2);
        BUSY_PERIOD_3 = new BusyPeriod(START_DATE_3, END_DATE_3, POSITION_NAME_3, RESPONSIBILITY_3);

        PERIODS_1.add(BUSY_PERIOD_1);
        PERIODS_2.add(BUSY_PERIOD_2);
        PERIODS_2.add(BUSY_PERIOD_3);


        EXPERIENCE_ORGANISATION_1 = new Organisation(NAME_1, URL_1, PERIODS_1);
        EXPERIENCE_ORGANISATION_2 = new Organisation(NAME_2, null, PERIODS_1);
        EXPERIENCE_ORGANISATION_3 = new Organisation(NAME_3, null, PERIODS_1);
        EXPERIENCE_ORGANISATION_4 = new Organisation(NAME_4, URL_1, PERIODS_1);
        EDUCATION_ORGANISATION_1 = new Organisation(NAME_1, URL_2, PERIODS_2);
        EDUCATION_ORGANISATION_2 = new Organisation(NAME_2, null, PERIODS_2);
        EDUCATION_ORGANISATION_3 = new Organisation(NAME_3, null, PERIODS_2);
        EDUCATION_ORGANISATION_4 = new Organisation(NAME_4, URL_2, PERIODS_2);
        EXPERIENCE_LIST_1.add(EXPERIENCE_ORGANISATION_1);
        EXPERIENCE_LIST_2.add(EXPERIENCE_ORGANISATION_2);
        EXPERIENCE_LIST_2.add(EXPERIENCE_ORGANISATION_3);
        EXPERIENCE_LIST_3.add(EXPERIENCE_ORGANISATION_3);
        EXPERIENCE_LIST_3.add(EXPERIENCE_ORGANISATION_1);
        EXPERIENCE_LIST_3.add(EXPERIENCE_ORGANISATION_4);
        EDUCATION_LIST_1.add(EDUCATION_ORGANISATION_1);
        EDUCATION_LIST_2.add(EDUCATION_ORGANISATION_2);
        EDUCATION_LIST_3.add(EDUCATION_ORGANISATION_3);
        EDUCATION_LIST_3.add(EDUCATION_ORGANISATION_4);
        EXPERIENCE_SECTION_1 = new OrganisationSection(EXPERIENCE_LIST_1);
        EXPERIENCE_SECTION_2 = new OrganisationSection(EXPERIENCE_LIST_2);
        EXPERIENCE_SECTION_3 = new OrganisationSection(EXPERIENCE_LIST_3);
        EXPERIENCE_SECTION_4 = new OrganisationSection(EXPERIENCE_LIST_4);
        EDUCATION_SECTION_1 = new OrganisationSection(EDUCATION_LIST_1);
        EDUCATION_SECTION_2 = new OrganisationSection(EDUCATION_LIST_2);
        EDUCATION_SECTION_3 = new OrganisationSection(EDUCATION_LIST_3);
        EDUCATION_SECTION_4 = new OrganisationSection(EDUCATION_LIST_4);
    }

    public static Resume getRESUME1(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.getContacts(ContactType.MOBILE).put(ContactType.MOBILE, MOBILE_1);
        resume.getContacts(ContactType.SKYPE).put(ContactType.SKYPE, SKYPE_1);
        resume.getContacts(ContactType.EMAIL).put(ContactType.EMAIL, EMAIL_1);
        resume.getContacts(ContactType.LINKEDLN).put(ContactType.LINKEDLN, LINKEDLN_1);
        resume.getContacts(ContactType.GITHUB).put(ContactType.GITHUB, GITHUB_1);
        resume.getContacts(ContactType.STACKOVERFLOW).put(ContactType.STACKOVERFLOW, STACKOVERFLOW_1);

        resume.getSections(SectionType.PERSONAL).put(SectionType.PERSONAL, PERSONAL_SECTION_1);
        resume.getSections(SectionType.OBJECTIVE).put(SectionType.OBJECTIVE, OBJECTIVE_SECTION_1);
        resume.getSections(SectionType.ACHIEVEMENT).put(SectionType.ACHIEVEMENT, ACHIEVEMENT_SECTION_1);
        resume.getSections(SectionType.QUALIFICATIONS).put(SectionType.QUALIFICATIONS, QUALIFICATIONS_SECTION_1);
        resume.getSections(SectionType.EXPERIENCE).put(SectionType.EXPERIENCE, EXPERIENCE_SECTION_1);
        resume.getSections(SectionType.EDUCATION).put(SectionType.EDUCATION, EDUCATION_SECTION_1);
        return resume;
    }

    public static Resume getRESUME2(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.getContacts(ContactType.MOBILE).put(ContactType.MOBILE, MOBILE_2);
        resume.getContacts(ContactType.SKYPE).put(ContactType.SKYPE, SKYPE_2);
        resume.getContacts(ContactType.EMAIL).put(ContactType.EMAIL, EMAIL_2);
        resume.getContacts(ContactType.LINKEDLN).put(ContactType.LINKEDLN, LINKEDLN_2);
        resume.getContacts(ContactType.GITHUB).put(ContactType.GITHUB, GITHUB_2);
        resume.getContacts(ContactType.STACKOVERFLOW).put(ContactType.STACKOVERFLOW, STACKOVERFLOW_2);

        resume.getSections(SectionType.PERSONAL).put(SectionType.PERSONAL, PERSONAL_SECTION_2);
        resume.getSections(SectionType.OBJECTIVE).put(SectionType.OBJECTIVE, OBJECTIVE_SECTION_2);
        resume.getSections(SectionType.ACHIEVEMENT).put(SectionType.ACHIEVEMENT, ACHIEVEMENT_SECTION_2);
        resume.getSections(SectionType.QUALIFICATIONS).put(SectionType.QUALIFICATIONS, QUALIFICATIONS_SECTION_2);
        resume.getSections(SectionType.EXPERIENCE).put(SectionType.EXPERIENCE, EXPERIENCE_SECTION_2);
        resume.getSections(SectionType.EDUCATION).put(SectionType.EDUCATION, EDUCATION_SECTION_2);
        return resume;
    }

    public static Resume getRESUME3(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.getContacts(ContactType.MOBILE).put(ContactType.MOBILE, MOBILE_3);
        resume.getContacts(ContactType.SKYPE).put(ContactType.SKYPE, SKYPE_3);
        resume.getContacts(ContactType.EMAIL).put(ContactType.EMAIL, EMAIL_3);
        resume.getContacts(ContactType.LINKEDLN).put(ContactType.LINKEDLN, LINKEDLN_3);
        resume.getContacts(ContactType.GITHUB).put(ContactType.GITHUB, GITHUB_3);
        resume.getContacts(ContactType.STACKOVERFLOW).put(ContactType.STACKOVERFLOW, STACKOVERFLOW_3);

        resume.getSections(SectionType.PERSONAL).put(SectionType.PERSONAL, PERSONAL_SECTION_3);
        resume.getSections(SectionType.OBJECTIVE).put(SectionType.OBJECTIVE, OBJECTIVE_SECTION_3);
        resume.getSections(SectionType.ACHIEVEMENT).put(SectionType.ACHIEVEMENT, ACHIEVEMENT_SECTION_3);
        resume.getSections(SectionType.QUALIFICATIONS).put(SectionType.QUALIFICATIONS, QUALIFICATIONS_SECTION_3);
        resume.getSections(SectionType.EXPERIENCE).put(SectionType.EXPERIENCE, EXPERIENCE_SECTION_3);
        resume.getSections(SectionType.EDUCATION).put(SectionType.EDUCATION, EDUCATION_SECTION_3);
        return resume;
    }

    public static Resume getRESUME4(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.getContacts(ContactType.MOBILE).put(ContactType.MOBILE, MOBILE_4);
        resume.getContacts(ContactType.SKYPE).put(ContactType.SKYPE, SKYPE_4);
        resume.getContacts(ContactType.EMAIL).put(ContactType.EMAIL, EMAIL_4);
        resume.getContacts(ContactType.LINKEDLN).put(ContactType.LINKEDLN, LINKEDLN_4);
        resume.getContacts(ContactType.GITHUB).put(ContactType.GITHUB, GITHUB_4);
        resume.getContacts(ContactType.STACKOVERFLOW).put(ContactType.STACKOVERFLOW, STACKOVERFLOW_4);

        resume.getSections(SectionType.PERSONAL).put(SectionType.PERSONAL, PERSONAL_SECTION_4);
        resume.getSections(SectionType.OBJECTIVE).put(SectionType.OBJECTIVE, OBJECTIVE_SECTION_4);
        resume.getSections(SectionType.ACHIEVEMENT).put(SectionType.ACHIEVEMENT, ACHIEVEMENT_SECTION_4);
        resume.getSections(SectionType.QUALIFICATIONS).put(SectionType.QUALIFICATIONS, QUALIFICATIONS_SECTION_4);
        resume.getSections(SectionType.EXPERIENCE).put(SectionType.EXPERIENCE, EXPERIENCE_SECTION_4);
        resume.getSections(SectionType.EDUCATION).put(SectionType.EDUCATION, EDUCATION_SECTION_4);
        return resume;
    }
}
