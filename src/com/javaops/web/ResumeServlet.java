package com.javaops.web;

import com.javaops.config.Config;
import com.javaops.model.*;
import com.javaops.storage.Storage;
import com.javaops.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
        try {
            Class.forName("com.javaops.model.Organization.Link");
            Class.forName("com.javaops.model.Organization.Position");
            Class.forName("com.javaops.util.DateUtil");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (isNotNull(value)) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    String content = request.getParameter(type.name());
                    if (isNotNull(content)) {
                        r.addSection(type, new TextSection(content));
                    } else {
                        removeSection(r, type);
                    }
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    List<String> items = Arrays.asList(request.getParameterValues(type.name()));
                    if (!items.isEmpty()) {
                        List<String> list = new ArrayList<>(items.size());
                        for (String item : items) {
                            if (isNotNull(item)) {
                                list.add(item);
                            }
                        }
                        r.addSection(type, new ListSection(list));
                    } else {
                        removeSection(r, type);
                    }
                    break;
//                case EXPERIENCE:
//                case EDUCATION:
//                    List<Organization> organizations = new ArrayList<>();
//                    List<Organization> list = ((OrganizationSection) r.getSections().get(type)).getOrganizations();
//                    for (int i = 0; i < (list.size()); i++) {
//                        Organization.Link link = new Organization.Link(request.getParameter(type.name()), request.getParameter(type.name()));
//                        List<Organization.Position> positions = new ArrayList<>();
//                        for (int j = 0; j < list.get(i).getPositions().size(); j++) {
//                            Organization.Position position = new Organization.Position(DateUtil.of(request.getParameter(type.name())), DateUtil.of(request.getParameter(type.name())), request.getParameter(type.name()), request.getParameter(type.name()));
//                            positions.add(position);
//                        }
//                        organizations.add(new Organization(link, positions));
//                    }
//                    if (!organizations.isEmpty()) {
//                        r.addSection(type, new OrganizationSection(organizations));
//                    } else {
//                        removeSection(r, type);
//                    }
//                    break;
            }
        }
        storage.update(r);
        response.sendRedirect("resume");
    }

    private void removeSection(Resume r, SectionType type) {
        r.getSections().remove(type);
    }

    private boolean isNotNull(String value) {
        return value != null && value.trim().length() != 0;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                r = storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);


        //        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
////       response.setHeader("Content-Type", "text/html; charset=UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        String name = request.getParameter("name");
//        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');

//        String uuid = request.getParameter("uuid");

//        List<Resume> resumes = storage.getAllSorted();
//        request.setAttribute("resumes", resumes);
//        request.setAttribute("contactType", ContactType.EMAIL);
//        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
    }
}
