package com.javaops.web;

import com.javaops.config.Configuration;
import com.javaops.model.ContactType;
import com.javaops.model.Resume;
import com.javaops.services.StorageService;
import com.javaops.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = StorageService.getStorage();
//        storage = Configuration.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
