package com.javaops.web;

import com.javaops.model.Resume;
import com.javaops.services.StorageService;
import com.javaops.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private final Storage storage = StorageService.getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
////       response.setHeader("Content-Type", "text/html; charset=UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        String name = request.getParameter("name");
//        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');

//        String uuid = request.getParameter("uuid");

        List<Resume> resumes = storage.getAllSorted();
        request.setAttribute("resumes", resumes);
        request.getRequestDispatcher("mypage.jsp").forward(request, response);
    }
}