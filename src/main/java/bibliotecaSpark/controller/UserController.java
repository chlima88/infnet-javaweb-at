package bibliotecaSpark.controller;

import bibliotecaSpark.model.User;
import bibliotecaSpark.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;

public class UserController {

    public static Route getUser = (request, response) -> {
        User user = UserService.getById(Integer.parseInt(request.params("id")));
        if (user == null) {
            throw new Exception("UserNotFoundException");
        }
        response.body(UserController.toJson(user));
        return response.body();
    };

    public static Route listUser = (request, response) -> {
        response.body(UserController.toJson(UserService.list()));
        return response.body();
    };

    public static Route addUser = (request, response) -> {
        User user = UserController.toObject(request.body());
        UserService.add(user);
        response.body(UserController.toJson(user));
        return response.body();
    };

    public static Route deleteUser = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        User user = UserService.getById(id);
        UserService.delete(user.getUserId());
        response.status(204);
        response.body("");
        return response.body();
    };

    private static String toJson(Object employee) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(employee);
    }

    private static User toObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, User.class);
    }
}
