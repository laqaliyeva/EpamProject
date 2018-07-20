package com.mylibrary.action.get;

import java.util.List;
import com.mylibrary.action.*;
import com.mylibrary.entity.Book;
import com.mylibrary.entity.Author;
import com.mylibrary.dao.AuthorDao;
import com.mylibrary.service.BookService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mylibrary.dao.exception.DaoException;
import com.mylibrary.action.exception.ActionException;
import com.mylibrary.service.exception.ServiceException;

public class ShowCatalogueAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<Book> catalogue;
        List<Author> allAuthors;
        try {
            catalogue = new BookService().findAllBooks();
            allAuthors = new AuthorDao().findAll();
        } catch (ServiceException |DaoException e) {
            throw new ActionException();
        }
        req.setAttribute(Attributes.CATALOGUE, catalogue);
        req.setAttribute(Attributes.ALL_AUTHORS, allAuthors);
        return Paths.PAGE_CATALOGUE;
    }
}
