package bibliotecaSpark.controller;

import bibliotecaSpark.App;
import spark.Route;

public class SiteController  {

    public static Route homePage = (request, response) ->
            App.class.getResourceAsStream("/index.html");

    public static Route page = (request, response) ->
            App.class.getResourceAsStream("/employee/list-employee.html");

    public static Route pageCreate = (request, response) ->
            App.class.getResourceAsStream("/employee/create-employee.html");


}
