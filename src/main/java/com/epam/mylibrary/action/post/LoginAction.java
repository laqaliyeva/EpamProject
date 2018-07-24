package com.epam.mylibrary.action.post;

import com.epam.mylibrary.action.*;
import com.epam.mylibrary.dao.UserDao;
import com.epam.mylibrary.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.mylibrary.validator.InputValidator;
import com.epam.mylibrary.dao.exception.DaoException;
import com.epam.mylibrary.action.exception.ActionException;

public class LoginAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String email = req.getParameter(Parameters.USER_EMAIL.toLowerCase());
        String password = req.getParameter(Parameters.USER_PASSWORD);
        boolean fieldsValid = InputValidator.isTextValid(email) && InputValidator.isTextValid(password);
        if(!fieldsValid) {
            req.getSession().setAttribute(Attributes.LOGIN_MESSAGE, ErrorMessages.TEXT_INPUT_ERROR);
            return Paths.REDIRECT_START_PAGE;
        }
        User user;
        try {
            user = new UserDao().findByEmailAndPassword(email, String.valueOf(password.hashCode()));
        } catch (DaoException e) {
            throw new ActionException();
        }
        if(user == null ) {
            req.getSession().setAttribute(Attributes.LOGIN_MESSAGE, ErrorMessages.LOGIN_ERROR);
            return Paths.REDIRECT_START_PAGE;
        }
        req.getSession().setAttribute(Attributes.USER, user);
        req.getSession().setAttribute(Attributes.ROLE, user.getRole());
        req.getSession().setAttribute(Attributes.PASSWORD, password);
        return Paths.REDIRECT_PROFILE;
    }
}