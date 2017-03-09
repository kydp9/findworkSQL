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
public class RabStrategy implements Strategy
{
    private static final String URL_FORMAT =   "http://www.rabota66.ru/vacancy/search?request=%s&page=%d";


    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        try
        {
            int pageNumber = 0;
            Document doc;
            while (true)
            {
                doc = getDocument(searchString, pageNumber++);
                if (doc == null) break;

                Elements elements = doc.select("div.list-view").select("li");
                String siteName = "https://Rabota66.ru";
                if (elements.size() == 0) break;
                for (Element element : elements)
                {
                    // title
                    Vacancy vacancy = new Vacancy();
                    vacancy.setSiteName(siteName);

                    Element titleElement = element.select("a.title-").first();
                    String title = "title none";
                    String url = "no url";
                    if(titleElement != null){

                        title = titleElement.text();
                        url = siteName + element.select("a").attr("href");

                    }
                    vacancy.setTitle(title);
                    vacancy.setUrl(url);

                    // salary
                    Element salaryElement = element.select("b.salary-").first();

                    String salary = "";
                    if (salaryElement != null)
                    {
                        salary = salaryElement.text();
                    }
                    vacancy.setSalary(salary);

                    // city
                    String city = "none";
                    Element cityE =  element.select("b.address-").first();
                    if ( cityE != null )
                    {
                        city = cityE.text();

                    }
                    if(city.length()>250){
                        city = city.substring(0, 250);
                    }
                    vacancy.setCity(city);

                    // company
                    String companyName = "none COmpany";
                    Element companyE = element.select("span.company-").first();
                    if (companyE != null)
                    {
                        companyName = companyE.text();
                    }
                    vacancy.setCompanyName(companyName);


                    vacancies.add(vacancy);



/*
                    System.out.println("page  = " + pageNumber);
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
        catch (Exception e)
        {
            //e.printStackTrace();
        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {

        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("none")
                .get();

        return document;
    }
}