package bibliotecaSpark.controller;

import bibliotecaSpark.model.ImageDiagnosis;
import bibliotecaSpark.service.ImageDiagnosisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;

public class ImageDiagnosisController {

    public static Route getImageDiagnosis = (request, response) -> {
        ImageDiagnosis imageDiagnosis = ImageDiagnosisService.getById(Integer.parseInt(request.params("id")));
        if (imageDiagnosis == null) {
            throw new Exception("ImageDiagnosisNotFoundException");
        }
        response.body(ImageDiagnosisController.toJson(imageDiagnosis));
        return response.body();
    };

    public static Route listImageDiagnosis = (request, response) -> {
        response.body(ImageDiagnosisController.toJson(ImageDiagnosisService.list()));
        return response.body();
    };

    public static Route addImageDiagnosis = (request, response) -> {
        ImageDiagnosis imageDiagnosis = ImageDiagnosisController.toObject(request.body());
        ImageDiagnosisService.add(imageDiagnosis);
        response.body(ImageDiagnosisController.toJson(imageDiagnosis));
        return response.body();
    };

    public static Route deleteImageDiagnosis = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        ImageDiagnosis imageDiagnosis = ImageDiagnosisService.getById(id);
        ImageDiagnosisService.delete(imageDiagnosis.getMedicalCareId());
        response.status(204);
        response.body("");
        return response.body();
    };

    private static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(object);
    }

    private static ImageDiagnosis toObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, ImageDiagnosis.class);
    }
}
