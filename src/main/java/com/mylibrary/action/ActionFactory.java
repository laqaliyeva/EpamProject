package com.mylibrary.action;

import java.util.Map;
import java.util.HashMap;
import com.mylibrary.action.get.*;
import com.mylibrary.action.post.*;
import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final Map<String, Action> actionsMap = new HashMap<>();

    static {
        actionsMap.put("/login", new LoginAction());
        actionsMap.put("/profile", new ShowProfileAction());
        actionsMap.put("/logout", new LogoutAction());
        actionsMap.put("/start", new ShowStartPageAction());
        actionsMap.put("/show-catalogue", new ShowCatalogueAction());
        actionsMap.put("/edit-profile", new ShowEditPageAction());
        actionsMap.put("/show-book", new ShowBookAction());
        actionsMap.put("/change-password", new ChangePasswordAction());
        actionsMap.put("/change-info", new ChangeInfoAction());
        actionsMap.put("/order-book", new OrderBookAction());
        actionsMap.put("/cancel-order", new CancelOrderAction());
        actionsMap.put("/change-status", new ChangeOrderStatusAction());
        actionsMap.put("/add-book", new AddBookAction());
        actionsMap.put("/add-author", new AddAuthorAction());
        actionsMap.put("/new-author", new ShowAuthorFormAction());
        actionsMap.put("/new-reader", new ShowReaderFormAction());
        actionsMap.put("/register", new RegisterReaderAction());
        actionsMap.put("/change-lang", new ChangeLanguageAction());
    }

    public static Action defineAction(HttpServletRequest req) {
        String actionPath = req.getPathInfo();
        Action action = actionsMap.get(actionPath);
        if(action == null) {
            action = new NoAction();
        }
        return action;
    }
}