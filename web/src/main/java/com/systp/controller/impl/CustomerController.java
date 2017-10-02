package by.it.web.command.impl;

import by.it.dao.CustomerDAO;
import by.it.entities.Customer;
import by.it.entities.User;
import by.it.services.CustomerService;
import by.it.services.impl.CustomerServiceImpl;
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
public class CustomerController implements Controller {

    CustomerService customerService = CustomerServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Customer> customers = customerService.getAll();
        req.setAttribute("cus", customers);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
