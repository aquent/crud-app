package com.aquent.crudapp.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aquent.crudapp.service.SearchService;

/**
 * Controller for handling search operations.
 */
@Controller
public class SearchController {

	@Inject private SearchService searchService;

	/**
     * Renders the search page.
     *
     * @return search view with no search results
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView search() {
        ModelAndView mav = new ModelAndView("search/search");
        return mav;
    }

    /**
     * Renders the search page.
     *
     * @param query string
     * @return search view with a list of search results
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam("query") String query) {
        ModelAndView mav = new ModelAndView("search/search");
        mav.addObject("query", query);
        mav.addObject("searchResults", searchService.search(query));
        return mav;
    }
}
