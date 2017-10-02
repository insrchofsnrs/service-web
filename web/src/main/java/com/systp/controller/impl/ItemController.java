package by.it.web.command.impl;

import by.it.entities.Customer;
import by.it.entities.Item;
import by.it.services.CustomerService;
import by.it.services.ItemService;
import by.it.services.impl.CustomerServiceImpl;
import by.it.services.impl.ItemServiceImpl;
import by.it.web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeni on 31.08.17.
 */
public class ItemController implements Controller {
    ItemService itemService = ItemServiceImpl.getInstance();
    CustomerService customerService = CustomerServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long customerId = Long.parseLong(req.getParameter("customerId"));
        List<Item> items = new ArrayList<>();
        if (req.getParameter("customerId")==null){
            items = itemService.getAllItems();
            req.setAttribute("items", items);
        } else {
            items = itemService.getByCustomerId(customerId);
            req.setAttribute("items", items);

            Customer customer = customerService.get(customerId);
            req.setAttribute("customer", customer);
            req.getSession().setAttribute("customerId", customerId);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);


    }
}
