package com.javaops.web.storage;

import com.javaops.web.model.*;
import com.javaops.web.util.DateUtil;

import java.lang.String;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;

/**
 * @author Vasichkin Pavel
 */
public class ResumeTestData {

    private static final String UUID = "UUID1";
    private static final String FULL_NAME = "FullName1";


    private static final String MOBILE = "+7(921) 855-0482";
    private static final String SKYPE = "grigory.kislin";
    private static final String EMAIL = "gkislin@yandex.ru";
    private static final String LINKEDLN = "https://www.linkedin.com/in/gkislin";
    private static final String GITHUB = "https://github.com/gkislin";
    private static final String STACKOVERFLOW = "https://stackoverflow.com/users/548473/grigory-kislin";

    private static final String DESC_PERSONAL = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";
    private static final String DESC_OBJECTIVE = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";

    private static final String DESC_ACHIEVEMENT_1 = "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.";
    private static final String DESC_ACHIEVEMENT_2 = "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.";
    private static final String DESC_QUALIFICATIONS_1 = "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2";
    private static final String DESC_QUALIFICATIONS_2 = "Version control: Subversion, Git, Mercury, ClearCase, Perforce";
    private static final String DESC_QUALIFICATIONS_3 = "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle";

    private static final String NAME_1 = "Java Online Projects";
    private static final String URL_1 = "javaops.ru";
    private static final LocalDate START_DATE_1 = DateUtil.of(2013, Month.JANUARY);
    private static final LocalDate END_DATE_1 = LocalDate.now();
    private static final Period PERIOD_1 = Period.between(START_DATE_1, END_DATE_1);
    private static final String POSITION_NAME_1 = "Автор проекта";
    private static final String RESPONSIBILITY_1 = "Создание, организация и проведение Java онлайн проектов и стажировок";

    private static final String NAME_2 = "Coursera";
    private static final String URL_2 = null;
    private static final LocalDate START_DATE_2 = DateUtil.of(2013, Month.MARCH);
    private static final LocalDate END_DATE_2 = DateUtil.of(2013, Month.MAY);
    private static final Period PERIOD_2 = Period.between(START_DATE_2, END_DATE_2);
    private static final String POSITION_NAME_2 = "\"Functional Programming Principles in Scala\" by Martin Odersky";
    private static final String RESPONSIBILITY_2 = null;

    private static final String NAME_3 = "Coursera";
    private static final String URL_3 = null;
    private static final LocalDate START_DATE_3 = DateUtil.of(2015, Month.FEBRUARY);
    private static final LocalDate END_DATE_3 = DateUtil.of(2016, Month.OCTOBER);
    private static final Period PERIOD_3 = Period.between(START_DATE_3, END_DATE_3);
    private static final String POSITION_NAME_3 = "\"Java Enterprise\" by Bill";
    private static final String RESPONSIBILITY_3 = null;

    private static final BusyPeriod BUSY_PERIOD_1;
    private static final BusyPeriod BUSY_PERIOD_2;
    private static final BusyPeriod BUSY_PERIOD_3;

    private static final Map<LocalDate, BusyPeriod> PERIODS_1 = new TreeMap<>();
    private static final Map<LocalDate, BusyPeriod> PERIODS_2 = new TreeMap<>();

    private static final Organisation EXPERIENCE_ORGANISATION;
    private static final Organisation EDUCATION_ORGANISATION;

    private static final List<String> ACHIEVEMENT_LIST = new ArrayList<>();;
    private static final List<String> QUALIFICATIONS_LIST = new ArrayList<>();;

    private static final List<Organisation> EXPERIENCE_LIST = new ArrayList<>();;
    private static final List<Organisation> EDUCATION_LIST = new ArrayList<>();;

    private static final Section PERSONAL_SECTION;
    private static final Section OBJECTIVE_SECTION;
    private static final Section ACHIEVEMENT_SECTION;
    private static final Section QUALIFICATIONS_SECTION;
    private static final Section EXPERIENCE_SECTION;
    private static final Section EDUCATION_SECTION;

    private static final Resume RESUME;

    static {
        RESUME = new Resume(UUID, FULL_NAME);

        PERSONAL_SECTION = new TextSection(DESC_PERSONAL);
        OBJECTIVE_SECTION = new TextSection(DESC_OBJECTIVE);

        ACHIEVEMENT_LIST.add(DESC_ACHIEVEMENT_1);
        ACHIEVEMENT_LIST.add(DESC_ACHIEVEMENT_2);
        QUALIFICATIONS_LIST.add(DESC_QUALIFICATIONS_1);
        QUALIFICATIONS_LIST.add(DESC_QUALIFICATIONS_2);
        QUALIFICATIONS_LIST.add(DESC_QUALIFICATIONS_3);
        ACHIEVEMENT_SECTION = new ListSection(ACHIEVEMENT_LIST);
        QUALIFICATIONS_SECTION = new ListSection(QUALIFICATIONS_LIST);

        BUSY_PERIOD_1 = new BusyPeriod(PERIOD_1, POSITION_NAME_1, RESPONSIBILITY_1);
        BUSY_PERIOD_2 = new BusyPeriod(PERIOD_2, POSITION_NAME_2, RESPONSIBILITY_2);
        BUSY_PERIOD_3 = new BusyPeriod(PERIOD_3, POSITION_NAME_3, RESPONSIBILITY_3);

        PERIODS_1.put(START_DATE_1, BUSY_PERIOD_1);
        PERIODS_2.put(START_DATE_2, BUSY_PERIOD_2);
        PERIODS_2.put(START_DATE_3, BUSY_PERIOD_3);


        EXPERIENCE_ORGANISATION = new Organisation(NAME_1, URL_1, PERIODS_1);
        EDUCATION_ORGANISATION = new Organisation(NAME_2, URL_2, PERIODS_2);
        EXPERIENCE_LIST.add(EXPERIENCE_ORGANISATION);
        EDUCATION_LIST.add(EDUCATION_ORGANISATION);
        EXPERIENCE_SECTION = new OrganisationSection(EXPERIENCE_LIST);
        EDUCATION_SECTION = new OrganisationSection(EDUCATION_LIST);


        RESUME.getContacts(ContactType.MOBILE).put(ContactType.MOBILE, MOBILE);
        RESUME.getContacts(ContactType.SKYPE).put(ContactType.SKYPE, SKYPE);
        RESUME.getContacts(ContactType.EMAIL).put(ContactType.EMAIL, EMAIL);
        RESUME.getContacts(ContactType.LINKEDLN).put(ContactType.LINKEDLN, LINKEDLN);
        RESUME.getContacts(ContactType.GITHUB).put(ContactType.GITHUB, GITHUB);
        RESUME.getContacts(ContactType.STACKOVERFLOW).put(ContactType.STACKOVERFLOW, STACKOVERFLOW);

        RESUME.getSections(SectionType.PERSONAL).put(SectionType.PERSONAL, PERSONAL_SECTION);
        RESUME.getSections(SectionType.OBJECTIVE).put(SectionType.OBJECTIVE, OBJECTIVE_SECTION);
        RESUME.getSections(SectionType.ACHIEVEMENT).put(SectionType.ACHIEVEMENT, ACHIEVEMENT_SECTION);
        RESUME.getSections(SectionType.QUALIFICATIONS).put(SectionType.QUALIFICATIONS, QUALIFICATIONS_SECTION);
        RESUME.getSections(SectionType.EXPERIENCE).put(SectionType.EXPERIENCE, EXPERIENCE_SECTION);
        RESUME.getSections(SectionType.EDUCATION).put(SectionType.EDUCATION, EDUCATION_SECTION);
    }

    public static void main(String[] args) {

        System.out.println(RESUME);
        RESUME.getContacts(ContactType.MOBILE).put(ContactType.MOBILE, "+79261300855");
        String oldDesc = ((TextSection) RESUME.getSections(SectionType.PERSONAL).get(SectionType.PERSONAL)).getContent();
        RESUME.getSections(SectionType.PERSONAL).put(SectionType.PERSONAL, new TextSection("Новое описание плюс старое :" + oldDesc));
        System.out.println(RESUME);

        List<Organisation> org = ((OrganisationSection) RESUME.getSections(SectionType.EDUCATION).get(SectionType.EDUCATION)).getOrganisations();
        org.remove(0);
        RESUME.getSections(SectionType.EDUCATION).put(SectionType.EDUCATION, new OrganisationSection(org));
        System.out.println(RESUME);

        Resume resume1 = new Resume("resume1");
        System.out.println(RESUME.equals(resume1));
        System.out.println(RESUME.equals(RESUME));
    }

}
