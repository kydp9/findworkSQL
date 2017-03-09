package ru.k66.myweb.controller;

import ru.k66.myweb.model.Model;
import ru.k66.myweb.model.Provider;
import ru.k66.myweb.model.Vacancy;
import ru.k66.myweb.model.strategy.E1Strategy;
import ru.k66.myweb.model.strategy.HHStrategy;
import ru.k66.myweb.model.strategy.RabStrategy;

import java.util.List;

/**
 * Created by ikydp on 21.02.2017.
 */
public class VacHelper {


public static  List<Vacancy> getVacancy(String name)
    {


        Provider providerHH = new Provider(new HHStrategy());
        Provider providerRab = new Provider(new RabStrategy());
        Provider providerE1 = new Provider(new E1Strategy());
        //  Provider Moikrug = new Provider(new MoikrugStrategy());
        Model xModel = new Model(new Provider[] {providerE1,providerRab,providerHH});
        //  Model xModel = new Model(xView,new Provider[] {providerHH,providerRab,providerE1});


        VacController xCont = new VacController(xModel);
       return   xCont.getVac(name);

    }





}
