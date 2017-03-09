package ru.k66.myweb.model;

/**
 * Created by ikydp on 14.02.2017.
 */

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Comparator;
import java.util.Date;



/**
 * Created by Kydp9 on 14.06.2016.
 */
@Entity
@Table(name="VACANCYMYSQL")
public class Vacancy {


    @Id
//@Column(name = "id" , unique = true, nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO) //
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "salary")
    private String salary;
    @Size(min=0, max=240)
    @Column(name = "city")
    private String city;
    @Size(min=0, max=240)
    @Column(name = "companyName")
    private String companyName;
    @Size(min=0, max=240)
    @Column(name = "siteName")
    private String siteName;
    @Size(min=0, max=240)
    @Column(name = "url")
    private String url;
 //   @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "joining_date")
   // @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private Date updateDate;

    public Vacancy() {
        this.updateDate = DateHelper.getDate();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;
        if (!siteName.equals(vacancy.siteName)) return false;
        if (!companyName.equals(vacancy.companyName)) return false;
        if (!title.equals(vacancy.title)) return false;
        if (!salary.equals(vacancy.salary)) return false;
        if (!city.equals(vacancy.city)) return false;


        return url.equals(vacancy.url);

    }


    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + salary.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + companyName.hashCode();
        result = 31 * result + siteName.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }

    public static class Comparators {

        public static Comparator<Vacancy> titleName = new Comparator<Vacancy>() {
            @Override
            public int compare(Vacancy o1, Vacancy o2) {
                return o1.title.compareTo(o2.title);
            }
        };
        public static Comparator<Vacancy> sitename = new Comparator<Vacancy>() {
            @Override
            public int compare(Vacancy o1, Vacancy o2) {
                return o1.siteName.compareTo(o2.siteName);
            }
        };
        public static Comparator<Vacancy> CompareVacancy = new Comparator<Vacancy>() {
            @Override
            public int compare(Vacancy o1, Vacancy o2) {
                int result;
                result = o1.getCompanyName().compareTo(o2.getCompanyName());
                if (result != 0) return result;

                return o1.getTitle().compareTo(o2.getTitle());
            }
        };


    }
}
