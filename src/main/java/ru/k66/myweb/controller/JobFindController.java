package ru.k66.myweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.k66.myweb.model.Vacancy;

import ru.k66.myweb.model.Validator404;
import ru.k66.myweb.service.VacancyService;

import java.util.Map;

@Controller
public class JobFindController {
    @Autowired
    private Validator404 validatorTest;


    @Qualifier("vacancyServiceImpl")
    @Autowired
    private VacancyService vacancyService;

    @RequestMapping("/job")
    public String setupForm(Map<String,Object> map){

        Vacancy vac = new Vacancy();
        map.put("vacancy",vac);
        map.put("vacancyList", vacancyService.getAllVac());
        return "jobFind";
    }




    @RequestMapping (value = "/job.do", method = RequestMethod.POST)
    public String doActions(@ModelAttribute Vacancy vac, BindingResult result ,  @RequestParam String action, Map<String,Object> map   ) {
        Vacancy vacResult = new Vacancy();

        switch (action.toLowerCase()){
            case "add":
                validatorTest.validate(vac, result);
                if (result.hasErrors()) {
                    map.put("vacancy",vac);
                    map.put("vacancyList", vacancyService.getAllVac());
                    return "jobFind";
        }
                vacancyService.add(vac);
                vacResult = vac;
                break;
            case "edit":
                validatorTest.validate(vac, result);
                if (result.hasErrors()) {
                    map.put("vacancy",vac);
                    map.put("vacancyList", vacancyService.getAllVac());
                    return "jobFind";
                }
                vacancyService.edit(vac);
               vacResult = vac;
                break;
            case "delete":
                vacancyService.delete(vac.getId());
                vacResult =  new Vacancy();
                break;
            case "search":
               vacResult = vac;
                map.put("vacancy",vacResult);
                map.put("vacancyList", vacancyService.searchVacancy(vac));
                return "jobFind";
            case "getid":

                Vacancy searchedVac = vacancyService.getVacancy(vac.getId());

                vacResult = searchedVac != null ?  searchedVac : new Vacancy();
                break;
            case "update":

                vacancyService.saveVacList(VacHelper.getVacancy(vac.getTitle()));

                break;

        }
        map.put("vacancy",vacResult);
        map.put("vacancyList", vacancyService.getAllVac());
        return "jobFind";
    }

}