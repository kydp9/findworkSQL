package ru.k66.myweb.model;

/**
 * Created by ikydp on 07.03.2017.
 */

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class Validator404 implements Validator {


    @Override
    public boolean supports(Class aClass) {
        return Vacancy.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {


        Vacancy vacancyVal = (Vacancy) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty", "title must not be empty.");
        String title = vacancyVal.getTitle();
        if ((title.length()) > 250) {
            errors.rejectValue("title", "title.tooLong", "Title must not more than 250 characters.");
        }





        String city = vacancyVal.getCity();
        if ((city.length()) > 150) {
            errors.rejectValue("city", "city.tooLong", "City must not more than 150 characters.");
        }

        /*
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password must not be empty.");
        if (!(humanVal.getPassword()).equals(humanVal
                .getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Passwords don't match.");
        }

        if( !EmailValidator.getInstance().isValid( humanVal.getEmail() ) ){
            errors.rejectValue("email", "email.notValid", "Email address is not valid.");
        }
       */
    }

    /**
     * Created by ikydp on 05.09.2016.
     */

}