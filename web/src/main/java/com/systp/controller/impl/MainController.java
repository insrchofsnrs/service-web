package by.it.web.command.impl;

import by.it.entities.Customer;
import by.it.entities.Item;
import by.it.services.ItemService;
import by.it.services.impl.ItemServiceImpl;
import by.it.web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Smile on 30.08.2017.
 */
public class MainController implements Controller {
    ItemService itemService = ItemServiceImpl.getInstance();


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getParameter("customerId")==null || req.getParameter("customerId").equalsIgnoreCase("")){
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        } else{

            List<Item> list = itemService.getByCustomerId(Long.parseLong(req.getParameter("customerId")));
            req.setAttribute("items", list);
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        }
    }
}
