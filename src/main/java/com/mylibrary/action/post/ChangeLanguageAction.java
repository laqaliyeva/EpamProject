package com.mylibrary.action.post;

import java.util.Map;
import java.util.Locale;
import com.mylibrary.dao.LabelsDao;
import com.mylibrary.action.Paths;
import com.mylibrary.action.Action;
import com.mylibrary.action.Attributes;
import com.mylibrary.action.Parameters;
import com.mylibrary.db.ConnectionPool;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String languageNew = req.getParameter(Parameters.LANGUAGE);
        String languageOld = (String) req.getSession().getAttribute(Attributes.LANGUAGE);
        if(languageNew != null && !languageNew.equals(languageOld)) {
            req.getSession().setAttribute(Attributes.LANGUAGE, languageNew);
            ConnectionPool pool = ConnectionPool.getInstance();
            Map<String, String> labels = new LabelsDao(pool).initLabelData(new Locale(languageNew));
            req.getSession().setAttribute(Attributes.LABELS, labels);
        }
        return Paths.REDIRECT_PROFILE_EDIT_FORM;
    }

}