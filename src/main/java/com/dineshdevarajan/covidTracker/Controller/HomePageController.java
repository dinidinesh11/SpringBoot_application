package com.dineshdevarajan.covidTracker.Controller;

import com.dineshdevarajan.covidTracker.Models.CountryStats;
import com.dineshdevarajan.covidTracker.Services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {
    @Autowired
    CovidDataService covidDataService;

@GetMapping("/")
    public String homePage(Model model){
    List<CountryStats> allStats = covidDataService.getAllStats();
    int totalNumberOfCases = allStats.stream().mapToInt(stat -> stat.getLastUpdated()).sum() ;
    model.addAttribute("countryStats", allStats);
    model.addAttribute("totalNumberOfCases", totalNumberOfCases);
    return "homePage";
    }
}
