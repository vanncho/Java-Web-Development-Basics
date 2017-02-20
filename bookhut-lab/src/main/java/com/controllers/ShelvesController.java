package com.controllers;

import com.config.Config;
import com.models.viewModels.ViewBookModel;
import com.services.BookService;
import com.servicesImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shelves")
public class ShelvesController extends HttpServlet{

    private BookService bookService;

    public ShelvesController() {

        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ViewBookModel> bookModels = this.bookService.getAllBooks();
        req.setAttribute(Config.VIEW_BOOK_MODELS, bookModels);
        req.getRequestDispatcher("/templates/shelves.jsp").forward(req, resp);
    }
}
