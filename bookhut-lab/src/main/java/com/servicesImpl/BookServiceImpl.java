package com.servicesImpl;

import com.entities.Book;
import com.mapper.ModelParser;
import com.mapper.ModelParserImpl;
import com.models.bindingModels.AddBookModel;
import com.models.viewModels.ViewBookModel;
import com.repository.BookRepository;
import com.repositoryImpl.BookRepositoryImpl;
import com.services.BookService;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ModelParser modelParser;

    public BookServiceImpl() {

        this.bookRepository = BookRepositoryImpl.getInstance();
        this.modelParser = new ModelParserImpl();
    }

    @Override
    public void saveBook(AddBookModel addBookModel) {

        Book book = this.modelParser.convert(addBookModel, Book.class);
        this.bookRepository.saveBook(book);
    }

    @Override
    public List<ViewBookModel> getAllBooks() {

        List<Book> books = this.bookRepository.getAllBooks();
        List<ViewBookModel> viewBookModels = new ArrayList<>();

        for (Book book : books) {

            ViewBookModel bookModel = this.modelParser.convert(book, ViewBookModel.class);
            viewBookModels.add(bookModel);
        }

        return viewBookModels;
    }

    @Override
    public ViewBookModel findBookByTitle(String title) {

        Book book = this.bookRepository.findBookByTitle(title);
        ViewBookModel bookModel = this.modelParser.convert(book, ViewBookModel.class);
        return bookModel;
    }

    @Override
    public void deleteBookByTitle(String title) {

        this.bookRepository.deleteBookByTitle(title);
    }
}
