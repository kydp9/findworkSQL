package ru.k66.myweb.model;

import java.util.List;

/**
 * Created by ikydp on 14.02.2017.
 */
public class Provider
{
    private Strategy strategy;
    public Provider(Strategy strategy)
    {
        this.strategy = strategy;
    }
    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }
    public List<Vacancy> getJavaVacancies(String searchString) {
        return strategy.getVacancies(searchString);
    }
}
