package ru.k66.myweb.model;

import ru.k66.myweb.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikydp on 14.02.2017.
 */
public class Model
{

    private Provider[] providers;

    public Model( Provider[] providers)
    {

        if ( providers.length == 0) {
            throw  new IllegalArgumentException("Illegal arguments");
        }

        this.providers = providers;


    }
    public  void selectVac(String vac) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider provider : providers) {
            vacancies.addAll(provider.getJavaVacancies(vac));
        }

    }


    public  List<Vacancy> getVacancy(String vac) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider provider : providers) {
            vacancies.addAll(provider.getJavaVacancies(vac));
        }

        return  vacancies;
    }

}