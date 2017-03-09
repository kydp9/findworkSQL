package ru.k66.myweb.dao;

import ru.k66.myweb.model.Vacancy;

import java.util.List;

/**
 * Created by ikydp on 16.02.2017.
 */
public interface VacancyDao {
    public void add(Vacancy vacancy);
    public void edit(Vacancy vacancy);
    public void delete(int  vacId);
    public  Vacancy getVac(int vacId);
    public List getAllVac();
    List seatchVacancy(Vacancy vacancy);
    public void saveVacList(List<Vacancy> vacancyList);

}
