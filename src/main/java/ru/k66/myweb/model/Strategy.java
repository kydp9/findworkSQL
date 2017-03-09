package ru.k66.myweb.model;

import java.util.List;

/**
 * Created by ikydp on 14.02.2017.
 */
public interface Strategy
{ List<Vacancy> getVacancies(String searchString);
}
