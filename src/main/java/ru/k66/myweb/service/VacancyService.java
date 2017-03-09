package ru.k66.myweb.service;

import ru.k66.myweb.model.Vacancy;

import java.util.List;

/**
 * Created by ikydp on 16.02.2017.
 */
public interface VacancyService {

    public void add(Vacancy vacancy);
    public void edit(Vacancy vacancy);
    public void delete(int  vacancyID);
    public  Vacancy getVacancy(int vacId);
    public List searchVacancy(Vacancy vacancy);
    public List getAllVac();
    public void saveVacList(List<Vacancy> vacancyList);
}