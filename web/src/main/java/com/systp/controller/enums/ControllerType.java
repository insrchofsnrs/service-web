package by.it.web.command.enums;

import by.it.web.command.Controller;
import by.it.web.command.impl.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Smile on 27.08.2017.
 */
@Getter
@AllArgsConstructor
public enum ControllerType {

    LOGIN("login.jsp", "Login", "login.title", new LoginController()),
    LOGOUT("login.jsp", "Logout", "logout.title", new LogoutController()),
    CUSTOMERS("customers/main.jsp", "Customers", "customers.title", new CreateCustomerController()),
    CREATE_CUSTOMER("customers/main.jsp", "addCustomers", "customers.title", new CreateCustomerController()),
    MAIN("main/main.jsp", "Main", "main.title", new MainController()),
    ITEMS("items/main.jsp", "Items", "items.title", new ItemController()),
    ADD_ITEM("items/main.jsp", "addItem", "items.title", new AddItemController());

    private String pagePath;
    private String pageName;
    private String i18nKey;
    private Controller controller;

    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
        return MAIN;
    }


}
