package com.aquent.crudapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Simple controller to redirect to the person listing.  In the future, we could
 * add other landing page behavior here if we were writing a real application.
 */
@Controller
public class HomeController {

    /**
     * renders the index page
     *
     * @return index view
     */
    @RequestMapping(value="/")
    public String index() {
        return "index";
    }
}
