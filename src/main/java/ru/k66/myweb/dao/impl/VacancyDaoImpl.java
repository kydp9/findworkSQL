package ru.k66.myweb.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.k66.myweb.dao.VacancyDao;
import ru.k66.myweb.model.Human;
import ru.k66.myweb.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikydp on 16.02.2017.
 */

@Transactional
@Repository
public class VacancyDaoImpl implements VacancyDao {


    @Autowired
    private SessionFactory session;

    @Override
    public void add(Vacancy vacancy) {
        session.getCurrentSession().save(vacancy);
    }

    @Override
    public void edit(Vacancy vacancy) {

            session.getCurrentSession().update(vacancy);

    }

    @Override
    public void delete(int vacId) {
        session.getCurrentSession().delete(getVac(vacId));
    }

    @Override
    public Vacancy getVac(int vacId) {
        return (Vacancy) session.getCurrentSession().get(Vacancy.class,vacId);
    }

    @Override
    public List getAllVac() {
        return session.getCurrentSession().createQuery("from Vacancy").list();
    }

    @Override
    public List seatchVacancy(Vacancy vacancy) {

        List list = new ArrayList();
        if (vacancy.getTitle()!= null){

            list = getFindList(vacancy.getTitle(), "title");
            if(list.size()>0){
                return list;
            }
        }
String vacTitle = vacancy.getTitle();
        if(list.isEmpty()) {
            list = getFindList(vacTitle, "companyName");
            if(list.size()>0){
                return list;
            }
        }
        if(list.isEmpty()){

            list =  getFindList(vacTitle,"city");
            if(list.size()>0){
                return list;
            }
        }
        if(list.isEmpty()){

            list =  getFindList(vacTitle, "siteName");
            if(list.size()>0){
                return list;
            }
        }

        Vacancy newVac = new Vacancy();
        newVac.setTitle("вернутся");
        newVac.setCity("Повторите запрос");
        newVac.setSalary("Поиск");
        newVac.setSiteName("Не дал");
        list.add(newVac);
        return list;


    }

    @Override
    public void saveVacList(List<Vacancy> vacancyList) {
        StatelessSession statelessSession = session.openStatelessSession();
        Transaction tx = statelessSession.beginTransaction();

        for(Vacancy vacancy : vacancyList) {
            statelessSession.insert(vacancy);
        }

        tx.commit();
        statelessSession.close();
    }

    private List getFindList ( String nameof, String tableSerch){

        Criteria criteria = session.getCurrentSession().createCriteria(Vacancy.class);
        List<Vacancy> list = criteria.add(Restrictions.like( tableSerch,(nameof+"%"))).list();
        return list;
    }
}
