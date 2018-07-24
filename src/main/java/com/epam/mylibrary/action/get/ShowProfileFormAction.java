package com.epam.mylibrary.action.get;

import com.epam.mylibrary.action.Paths;
import com.epam.mylibrary.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProfileFormAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return Paths.PAGE_PROFILE_FORM;
    }
}