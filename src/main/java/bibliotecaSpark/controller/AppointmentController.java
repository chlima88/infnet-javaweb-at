package bibliotecaSpark.controller;

import bibliotecaSpark.model.Appointment;
import bibliotecaSpark.service.AppointmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;

public class AppointmentController {

    public static Route getAppointment = (request, response) -> {
        Appointment appointment = AppointmentService.getById(Integer.parseInt(request.params("id")));
        if (appointment == null) {
            throw new Exception("AppointmentNotFoundException");
        }
        response.body(AppointmentController.toJson(appointment));
        return response.body();
    };

    public static Route listAppointment = (request, response) -> {
        response.body(AppointmentController.toJson(AppointmentService.list()));
        return response.body();
    };

    public static Route addAppointment = (request, response) -> {
        Appointment appointment = AppointmentController.toObject(request.body());
        AppointmentService.add(appointment);
        response.body(AppointmentController.toJson(appointment));
        return response.body();
    };

    public static Route deleteAppointment = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        Appointment appointment = AppointmentService.getById(id);
        AppointmentService.delete(appointment.getMedicalCareId());
        response.status(204);
        response.body("");
        return response.body();
    };

    private static String toJson(Object employee) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(employee);
    }

    private static Appointment toObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Appointment.class);
    }
}
