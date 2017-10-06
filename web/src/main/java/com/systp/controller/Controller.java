package com.systp.controller;


import java.io.IOException;

/**
 * Created by Smile on 27.08.2017.
 */
public interface Controller {
    String MAIN_PAGE ="/WEB-INF/view/layouts/default.jspx";

    void execute (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;

}
