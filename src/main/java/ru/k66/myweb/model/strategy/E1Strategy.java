package ru.k66.myweb.model.strategy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.k66.myweb.model.Strategy;
import ru.k66.myweb.model.Vacancy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ikydp on 14.02.2017.
 */
public class E1Strategy  implements Strategy
{
    private static final String URL_FORMAT = "https://ekb.zarplata.ru/vacancy?limit=100&offset=%d0&q=%s";
    private static  final int DOWNLOUD_TIME = 1700;
    private static final String FANTOM_DRIVER_EXE  =  "C:\\temp\\findwork\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";

    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancies = new ArrayList<>();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true); // enabled by default
        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                FANTOM_DRIVER_EXE
        );

        WebDriver driver = new PhantomJSDriver(caps);

        int pageNumber = 0;
        List<WebElement> webElements =  new ArrayList<>();
        String siteName = "E1";

        while (true) {
            webElements =  getDocument(driver,searchString, pageNumber++);

            if(webElements.size() == 0 )     break;

            for (WebElement element : webElements) {
                WebElement colum1 = element.findElement(By.xpath(".//div[contains(@class,'column ten wide')]"));
                String vName = colum1.findElement(By.xpath(".//a")).getText();
                String vLink = colum1.findElement(By.xpath(".//a")).getAttribute("href");
                List<WebElement> spanGREY = colum1.findElements(By.xpath(".//span[contains(@class,'ui text small grey')]"));
                String vAdress = spanGREY.get(1).getText();
                String vCompany = colum1.findElement(By.xpath(".//a[contains(@class,'ui text default')]")).getText();                                             //ui text default
                String vSalatary = element.findElement(By.cssSelector("div[class^='column four wide'] span")).getAttribute("innerText");
if(vAdress.length()>250){
    vAdress = vAdress.substring(0, 250);
}
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(vName);
                vacancy.setSalary(vSalatary);
                vacancy.setCity(vAdress);
                vacancy.setCompanyName(vCompany);
                vacancy.setSiteName(siteName);
                vacancy.setUrl(vLink);

                vacancies.add(vacancy);
               /*

                System.out.println("---------------------------------");
                System.out.println("name  = " + vName);
                System.out.println("link = " + vLink);
                System.out.println("adress = " + vAdress);
                System.out.println("Salatary = " + vSalatary);
                System.out.println("Company = " + vCompany);
                System.out.println("++++++++++++++++++++++++++++++++++");
*/
            }

        }

        driver.quit();

        return vacancies;

    }



    protected List<WebElement> getDocument(WebDriver driver, String searchString, int page)
    {   List<WebElement> webElements =  new ArrayList<>();
        try {

            String url = String.format(URL_FORMAT,  page, searchString);
            driver.get(url);
            driver.manage().timeouts().pageLoadTimeout(DOWNLOUD_TIME, TimeUnit.SECONDS);
            if( driver.findElement(By.xpath("//div[contains(@class,'vacancy vacancy-item ui segment')]")).isDisplayed())
            {
                webElements =  driver.findElements(By.xpath("//div[contains(@class,'vacancy vacancy-item ui segment')]"));
            }
        } catch (org.openqa.selenium.NoSuchElementException e)
        {
            return  webElements;
        }
        return  webElements;
    }


}