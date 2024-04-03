package bibliotecaSpark.controller;

import bibliotecaSpark.service.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;

public class ScheduleController {

    public static Route listSchedule = (request, response) -> {
        response.body(ScheduleController.toJson(ScheduleService.list()));
        return response.body();
    };

    public static Route deleteSchedule = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        ScheduleService.deleteById(id);
        response.status(204);
        response.body("");
        return response.body();
    };

    private static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(object);
    }

}
