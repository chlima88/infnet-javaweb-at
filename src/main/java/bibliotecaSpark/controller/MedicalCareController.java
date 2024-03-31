package bibliotecaSpark.controller;

import bibliotecaSpark.model.MedicalCare;
import bibliotecaSpark.service.MedicalCareService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;

public class MedicalCareController {

    public static Route getMedicalCare = (request, response) -> {
        MedicalCare medicalCare = MedicalCareService.getById(Integer.parseInt(request.params("id")));
        if (medicalCare == null) {
            throw new Exception("UserNotFoundException");
        }
        response.body(MedicalCareController.toJson(medicalCare));
        return response.body();
    };

    public static Route listMedicalCare = (request, response) -> {
        response.body(MedicalCareController.toJson(MedicalCareService.list()));
        return response.body();
    };


    public static Route deleteMedicalCare = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        MedicalCare medicalCare = MedicalCareService.getById(id);
        MedicalCareService.delete(medicalCare.getMedicalCareId());
        response.status(204);
        response.body("");
        return response.body();
    };


    private static String toJson(Object employee) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(employee);
    }

    private static MedicalCare toObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, MedicalCare.class);
    }
}
