package ru.k66.myweb.controller;


import ru.k66.myweb.model.Model;
import ru.k66.myweb.model.Vacancy;

import java.util.List;

/**
 * Created by ikydp on 14.02.2017.
 */
public class VacController

{  private Model model;

    public VacController(Model model)
    { if (model==null){
        throw  new IllegalArgumentException("Illegal arguments");
    }
        this.model = model;
    }

    public void onVacSelect(String vacName) {
        model.selectVac(vacName);
    }

    public List<Vacancy> getVac (String vacName) {
      return   model.getVacancy(vacName);
    }
}