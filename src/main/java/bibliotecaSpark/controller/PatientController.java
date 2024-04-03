package bibliotecaSpark.controller;

import bibliotecaSpark.model.Patient;
import bibliotecaSpark.service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;

public class PatientController {

    public static Route getPatient = (request, response) -> {
        Patient patient = PatientService.getById(Integer.parseInt(request.params("id")));
        response.body(PatientController.toJson(patient));
        return response.body();
    };

    public static Route listPatient = (request, response) -> {
        response.body(PatientController.toJson(PatientService.list()));
        return response.body();
    };

    public static Route addPatient = (request, response) -> {
        Patient patient = PatientController.toObject(request.body());
        PatientService.add(patient);
        response.body(PatientController.toJson(patient));
        return response.body();
    };

    public static Route deletePatient = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        PatientService.deleteById(id);
        response.status(204);
        response.body("");
        return response.body();
    };

    private static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(object);
    }
    private static Patient toObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Patient.class);
    }
}
