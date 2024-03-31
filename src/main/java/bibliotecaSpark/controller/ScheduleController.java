package bibliotecaSpark.controller;

import bibliotecaSpark.model.Schedule;
import bibliotecaSpark.model.ScheduleDTO;
import bibliotecaSpark.service.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;

public class ScheduleController {

    public static Route getSchedule = (request, response) -> {
        Schedule schedule = ScheduleService.getById(Integer.parseInt(request.params("id")));
        if (schedule == null) {
            throw new Exception("ScheduleNotFoundException");
        }
        response.body(ScheduleController.toJson(schedule));
        return response.body();
    };

    public static Route listSchedule = (request, response) -> {
        response.body(ScheduleController.toJson(ScheduleService.list()));
        return response.body();
    };

    public static Route addSchedule = (request, response) -> {
        ScheduleDTO scheduleDTO = ScheduleController.toDTO(request.body());
        Schedule schedule = ScheduleService.build(scheduleDTO);
        ScheduleService.add(schedule);
        response.body(ScheduleController.toJson(schedule));
        return response.body();
    };

    public static Route deleteSchedule = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        Schedule schedule = ScheduleService.getById(id);
        ScheduleService.delete(schedule.getScheduleId());
        response.status(204);
        response.body("");
        return response.body();
    };

    private static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(object);
    }

    private static Schedule toObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Schedule.class);
    }
    private static ScheduleDTO toDTO(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, ScheduleDTO.class);
    }
}
