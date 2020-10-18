package com.aquent.crudapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Simple controller to redirect to the main page.  In the future, we could
 * add other landing page behavior here if we were writing a real application.
 */
@Controller
public class HomeController {
    /**
     * Redirect to the person list.
     * In a real application this could be our landing or login page.
     *
     * @return redirect to the person list
     */
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("home/index");
        // return "redirect:/person/create";
    }
}
