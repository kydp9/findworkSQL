package ru.k66.myweb.view;


import ru.k66.myweb.controller.VacController;

/**
 * Created by ikydp on 14.02.2017.
 */
    public class HtmlView implements View
    {

        private VacController vacController;
        private final String filePath = "./src/main/java/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";




        public void setVacController(VacController vacController)
        {
            this.vacController = vacController;
        }

        public void userFindSelectEmulationMethod(){

            vacController.onVacSelect("java");

        }


}
