package com.systp.controller.impl;


import java.io.IOException;


/**
 * Created by Smile on 30.08.2017.
 */
public class CustomerController implements by.it.web.command.Controller {

    CustomerService customerService = CustomerServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Customer> customers = customerService.getAll();
        req.setAttribute("cus", customers);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
