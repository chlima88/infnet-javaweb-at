package bibliotecaSpark.controller;

import bibliotecaSpark.model.ImageDiagnostic;
import bibliotecaSpark.model.ImageDiagnosticDTO;
import bibliotecaSpark.service.ImageDiagnosticService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;

public class ImageDiagnosticController {

    public static Route getImageDiagnostic = (request, response) -> {
        ImageDiagnostic imageDiagnostic = ImageDiagnosticService.getById(Integer.parseInt(request.params("id")));
        if (imageDiagnostic == null) {
            throw new Exception("ImageDiagnosticNotFoundException");
        }
        response.body(ImageDiagnosticController.toJson(imageDiagnostic));
        return response.body();
    };

    public static Route listImageDiagnostic = (request, response) -> {
        response.body(ImageDiagnosticController.toJson(ImageDiagnosticService.list()));
        return response.body();
    };

    public static Route addImageDiagnostic = (request, response) -> {
        ImageDiagnosticDTO imageDiagnosticDTO = ImageDiagnosticController.toDTO(request.body());
        ImageDiagnostic imageDiagnostic = ImageDiagnosticService.build(imageDiagnosticDTO);
        ImageDiagnosticService.add(imageDiagnostic);
        response.body(ImageDiagnosticController.toJson(imageDiagnostic));
        return response.body();
    };

    public static Route deleteImageDiagnostic = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        ImageDiagnosticService.deleteById(id);
        response.status(204);
        response.body("");
        return response.body();
    };

    private static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(object);
    }

    private static ImageDiagnostic toObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, ImageDiagnostic.class);
    }

    private static ImageDiagnosticDTO toDTO(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, ImageDiagnosticDTO.class);
    }
}
