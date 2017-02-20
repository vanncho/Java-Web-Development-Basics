package com.controllers;

import com.config.Config;
import com.models.bindingModels.AddBookModel;
import com.models.viewModels.ViewBookModel;
import com.services.BookService;
import com.servicesImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/shelves/edit/*")
public class EditBookController extends HttpServlet {

    private BookService bookService;
    private ViewBookModel bookModel;

    public EditBookController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] tokens = req.getRequestURI().split("/");
        String title = URLDecoder.decode(tokens[3], "UTF-8");
        this.bookModel = this.bookService.findBookByTitle(title);

        if (this.bookModel != null) {

            req.setAttribute(Config.VIEW_BOOK_MODELS, this.bookModel);
            req.getRequestDispatcher("/templates/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String edit = req.getParameter("edit");

        if (edit != null) {

            String title = req.getParameter("title");
            String author = req.getParameter("author");
            Integer pages = Integer.valueOf(req.getParameter("pages"));
            AddBookModel bookModel = new AddBookModel(title, author, pages);
            this.bookService.deleteBookByTitle(this.bookModel.getTitle());
            this.bookService.saveBook(bookModel);
        }

        resp.sendRedirect("/shelves");
    }
}
