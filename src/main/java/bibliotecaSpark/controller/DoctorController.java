package bibliotecaSpark.controller;

import bibliotecaSpark.model.Doctor;
import bibliotecaSpark.service.DoctorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Route;


public class DoctorController {

    public static Route getDoctor = (request, response) -> {
        Doctor doctor = DoctorService.getById(Integer.parseInt(request.params("id")));
        if (doctor == null) {
            throw new Exception("UserNotFoundException");
        }
        response.body(DoctorController.toJson(doctor));
        return response.body();
    };

    public static Route listDoctor = (request, response) -> {
        response.body(DoctorController.toJson(DoctorService.list()));
//        Thread.sleep(2000L);
        return response.body();
    };

    public static Route addDoctor = (request, response) -> {
        Doctor doctor = DoctorController.toObject(request.body());
        DoctorService.add(doctor);
        response.body(DoctorController.toJson(doctor));
        return response.body();
    };

    public static Route deleteDoctor = (request, response) -> {
        int id = Integer.parseInt(request.params("id"));
        Doctor doctor = DoctorService.getById(id);
        DoctorService.delete(doctor.getDoctorId());
        response.status(204);
        response.body("");
        return response.body();
    };

    private static String toJson(Object employee) throws JsonProcessingException {
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(employee);
    }

    private static Doctor toObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Doctor.class);
    }
}
