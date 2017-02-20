package com.controllers;

import com.models.bindingModels.AddBookModel;
import com.repositoryImpl.BookRepositoryImpl;
import com.services.BookService;
import com.servicesImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddBookController extends HttpServlet {

    private BookService bookService;

    public AddBookController() {

        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/templates/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String add = req.getParameter("add");

        if (add != null) {

            String title = req.getParameter("title");
            String author = req.getParameter("author");
            Integer pages = Integer.valueOf(req.getParameter("pages"));

            AddBookModel bookModel = new AddBookModel(title, author, pages);
            this.bookService.saveBook(bookModel);

            req.getRequestDispatcher("/templates/add.jsp").forward(req, resp);
        }
    }
}
