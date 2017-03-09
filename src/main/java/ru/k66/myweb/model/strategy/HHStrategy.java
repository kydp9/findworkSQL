package ru.k66.myweb.model.strategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.k66.myweb.model.Strategy;
import ru.k66.myweb.model.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikydp on 14.02.2017.
 */
public class HHStrategy implements Strategy
{

    private static final String URL_FORMAT =   "https://ekaterinburg.hh.ru/search/vacancy?clusters=true&area=3&enable_snippets=true&text=%s&page=%d";

    public List<Vacancy> getVacancies(String searchString)
    {  List<Vacancy> vacancies = new ArrayList<>();
        try {
            int pageNumber = 0;
            Document doc;
            while (true) {
                doc = getDocument(searchString, pageNumber++);
                if (doc == null) break;

                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) break;

                for (Element element : elements) {
                    // title
                    Element titleElement = element.select("[data-qa=vacancy-serp__vacancy-title]").first();
                    String title = titleElement.text();

                    // salary
                    Element salaryElement = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "";
                    if (salaryElement != null) {
                        salary = salaryElement.text();
                    }

                    // city
                    String city = element.select("[data-qa=vacancy-serp__vacancy-address]").first().text();
                    if(city.length()>250){
                        city = city.substring(0, 250);
                    }

                    // company
                    String companyName = element.select("[data-qa=vacancy-serp__vacancy-employer]").first().text();

                    // site
                    String siteName = "http://hh.ru/";

                    // url
                    String url = titleElement.attr("href");

                    // add vacancy to the list
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);
                    vacancies.add(vacancy);

/*
System.out.print("page  = " + pageNumber);
                    System.out.println("Title = " + title);
                    System.out.println("Salary = " + salary);
                   System.out.println("City = " + city);
                   System.out.println("CompanyName = " + companyName);
                    System.out.println("SiteName = " + siteName);
                   System.out.println("URL = " + url);
                 System.out.println();
                    System.out.println();
                    */
                }

                //break;
            }
        }
        catch (Exception e) {
            //e.printStackTrace();
        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {

        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("none")
                .get();

        return document;
    }
}
