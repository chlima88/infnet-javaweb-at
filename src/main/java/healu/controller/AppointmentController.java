package healu.controller;

import healu.model.Appointment;
import healu.model.AppointmentDTO;
import healu.service.AppointmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;

public class AppointmentController {

    public static Route getAppointment = (request, response) -> {
        Appointment appointment = AppointmentService.getById(Integer.parseInt(request.params("id")));
        response.body(AppointmentController.toJson(appointment));
        return response.body();
    };

    public static Route listAppointment = (request, response) -> {
        response.body(AppointmentController.toJson(AppointmentService.list()));
        return response.body();
    };

    public static Route addAppointment = (request, response) -> {
        AppointmentDTO appointmentDTO = AppointmentController.toDTO(request.body());
        Appointment appointment = AppointmentService.build(appointmentDTO);
        AppointmentService.add(appointment);
        response.body(AppointmentController.toJson(appointment));
        return response.body();
    };

    public static Route deleteAppointment = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        Appointment appointment = AppointmentService.getById(id);
        AppointmentService.delete(appointment.getScheduleId());
        response.status(204);
        response.body("");
        return response.body();
    };

    private static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(object);
    }

    private static Appointment toObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Appointment.class);
    }

    private static AppointmentDTO toDTO(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, AppointmentDTO.class);
    }
}
