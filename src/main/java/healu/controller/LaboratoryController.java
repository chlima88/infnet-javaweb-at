package healu.controller;

import healu.model.Laboratory;
import healu.model.LaboratoryDTO;
import healu.service.LaboratoryService;
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
        LaboratoryDTO laboratoryDTO = LaboratoryController.toDTO(request.body());
        Laboratory laboratory = LaboratoryService.build(laboratoryDTO);
        LaboratoryService.add(laboratory);
        response.body(LaboratoryController.toJson(laboratory));
        return response.body();
    };

    public static Route deleteLaboratory = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        LaboratoryService.deleteById(id);
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
    private static LaboratoryDTO toDTO(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, LaboratoryDTO.class);
    }
}
