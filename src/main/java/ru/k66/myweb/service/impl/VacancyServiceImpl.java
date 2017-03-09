package ru.k66.myweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.k66.myweb.dao.VacancyDao;
import ru.k66.myweb.model.Vacancy;
import ru.k66.myweb.service.VacancyService;

import java.util.List;

/**
 * Created by ikydp on 16.02.2017.
 */
@Service
public class VacancyServiceImpl implements VacancyService {

    @Qualifier("vacancyDaoImpl")
    @Autowired
    private VacancyDao vacancyDao;


    @Override
    public void add(Vacancy vacancy) {
        vacancyDao.add(vacancy);
    }

    @Override
    public void edit(Vacancy vacancy) {
vacancyDao.edit(vacancy);
    }

    @Override
    public void delete(int vacancyID) {
vacancyDao.delete(vacancyID);
    }

    @Override
    public Vacancy getVacancy(int vacId) {
        return vacancyDao.getVac(vacId);
    }

    @Override
    public List searchVacancy(Vacancy vacancy) {
        return vacancyDao.seatchVacancy(vacancy);
    }

    @Override
    public List getAllVac() {
        return vacancyDao.getAllVac();
    }

    @Override
    public void saveVacList(List<Vacancy> vacancyList) {
        vacancyDao.saveVacList(vacancyList);
    }


}
