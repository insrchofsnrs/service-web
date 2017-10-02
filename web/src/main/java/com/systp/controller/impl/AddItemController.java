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
import java.util.List;

/**
 * Created by Evgeni on 31.08.17.
 */
public class AddItemController implements Controller {
    private ItemService itemService = ItemServiceImpl.getInstance();
    CustomerService customerService = CustomerServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
       // long customerId = (long) req.getAttribute("customerId");
        //long customerId = Long.parseLong(req.getParameter("customerId"));
         long customerId = (long) req.getSession().getAttribute("customerId");
        System.out.println("id customer "+ customerId);



        Item item = new Item();

        //item.setCustomerId(Long.parseLong(req.getParameter("customerId")));
        item.setName(req.getParameter("name"));
        item.setMarking(req.getParameter("marking"));
        item.setLabel(req.getParameter("label"));
        item.setStatus("в процессе");

        if (item.getName() != null && item.getName() != "") {
            itemService.createItem(item, customerId);
        }
        System.out.println(item);

        List<Item> items = itemService.getByCustomerId(customerId);
        req.setAttribute("items", items);

        Customer customer = customerService.get(customerId);
        req.setAttribute("customer", customer);
        req.getSession().setAttribute("customerId", customerId);

        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);

    }
}
