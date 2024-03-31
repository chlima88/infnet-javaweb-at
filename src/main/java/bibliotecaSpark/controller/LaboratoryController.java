package bibliotecaSpark.controller;

import bibliotecaSpark.model.Laboratory;
import bibliotecaSpark.service.LaboratoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;

public class LaboratoryController {

    public static Route getLaboratory = (request, response) -> {
        Laboratory laboratory = LaboratoryService.getById(Integer.parseInt(request.params("id")));
        if (laboratory == null) {
            throw new Exception("LaboratoryNotFoundException");
        }
        response.body(LaboratoryController.toJson(laboratory));
        return response.body();
    };

    public static Route listLaboratory = (request, response) -> {
        response.body(LaboratoryController.toJson(LaboratoryService.list()));
        return response.body();
    };

    public static Route addLaboratory = (request, response) -> {
        Laboratory laboratory = LaboratoryController.toObject(request.body());
        LaboratoryService.add(laboratory);
        response.body(LaboratoryController.toJson(laboratory));
        return response.body();
    };

    public static Route deleteLaboratory = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        Laboratory laboratory = LaboratoryService.getById(id);
        LaboratoryService.delete(laboratory.getMedicalCareId());
        response.status(204);
        response.body("");
        return response.body();
    };

    private static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(object);
    }

    private static Laboratory toObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Laboratory.class);
    }
}
