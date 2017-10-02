package by.it.web.command.impl;

import by.it.entities.Customer;
import by.it.entities.User;
import by.it.services.CustomerService;
import by.it.services.impl.CustomerServiceImpl;
import by.it.web.command.Controller;
import by.it.web.command.enums.ControllerType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Smile on 27.08.2017.
 */
public class CreateCustomerController implements Controller {

    private CustomerService customerService = CustomerServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //User user = (User)req.getSession().getAttribute("user");
        Customer customer = new Customer();
        req.setCharacterEncoding("UTF-8");
        customer.setFirstName(req.getParameter("first_name"));
        customer.setSecondName(req.getParameter("second_name"));
        customer.setOrganization(req.getParameter("organization"));
        customer.setPosition(req.getParameter("position"));
        customer.setPhoneNumberA(req.getParameter("phonea"));
        customer.setPhoneNumberB(req.getParameter("phoneb"));
        customer.setPhoneNumberFax(req.getParameter("phonefax"));
        customer.setEmail(req.getParameter("email"));


        if (customer.getFirstName() != null && customer.getFirstName() != "") {
            customerService.createCustomer(customer);
        }

        List<Customer> customers = customerService.getAll();
        Collections.reverse(customers);
        req.setAttribute("cus", customers);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);


        // resp.sendRedirect(req.getContextPath() + "/frontController?command=customers");
        //req.setAttribute("cus", customerService.getAll());

        /*String contextPath = req.getContextPath();
        RequestDispatcher dispatcher = req.getRequestDispatcher(contextPath + "/frontController?command=customers");
        dispatcher.forward(req, resp);
       */
       /* String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/frontController?command=customers");*/
    }
}
