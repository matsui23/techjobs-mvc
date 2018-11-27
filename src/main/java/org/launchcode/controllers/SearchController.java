package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    public ArrayList<String> columns = new ArrayList<>(Arrays.asList("position type", "name", "employer", "location", "core competency"));


    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "/results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm){

        if (searchType.equals("all") && searchTerm.isEmpty()){

                ArrayList<HashMap<String, String>> jobs = JobData.findAll();

                model.addAttribute("jobs", jobs);
                model.addAttribute("searchType", searchType);
                model.addAttribute("searchTerm", searchTerm);
                model.addAttribute("choices", columns);
                model.addAttribute("columns", ListController.columnChoices);

                return "search";
        }

        if (searchType.equals("all") && !searchTerm.isEmpty()){

            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);;

            model.addAttribute("jobs", jobs);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchTerm", searchTerm);
            model.addAttribute("choices", columns);
            model.addAttribute("columns", ListController.columnChoices);

            return "search";
        }

        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);

        model.addAttribute("jobs", jobs);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("choices", columns);
        model.addAttribute("columns", ListController.columnChoices);

        return "search";
        //Returning search for now to test
    }


    // TODO #1 - Create handler to process search request and display results

}
