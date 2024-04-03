package bibliotecaSpark;

import bibliotecaSpark.controller.*;
import bibliotecaSpark.application.HttpStatus;
import bibliotecaSpark.application.HttpResponse;
import bibliotecaSpark.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import spark.Spark;

public class App {

    public static void main(String[] args) {
        Spark.port(8080);

        Spark.staticFileLocation("/dist");
        Spark.get("/", (request, response) -> App.class.getResourceAsStream("/dist"));

        Spark.before((request, response) -> {
            if (request.pathInfo().endsWith("/") && !request.pathInfo().equals("/")) {
                response.redirect(request.pathInfo().replaceAll("/+$", ""));
            }
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
        });


        Spark.path("/api", () -> {
            Spark.options("/*", (request, response) -> {
                String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
                if (accessControlRequestHeaders != null) {
                    response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                }

                String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
                if (accessControlRequestMethod != null) {
                    response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                }

                return "OK";
            });


            Spark.after("/*",(request, response) ->
                    response.type("application/json"));

            Spark.path("/doctor", () -> {
                Spark.post("", DoctorController.addDoctor);
                Spark.get("", DoctorController.listDoctor);
                Spark.get("/:id", DoctorController.getDoctor);
                Spark.delete("/:id", DoctorController.deleteDoctor);
            });

            Spark.path("/patient", () -> {
                Spark.post("", PatientController.addPatient);
                Spark.get("", PatientController.listPatient);
                Spark.get("/:id", PatientController.getPatient);
                Spark.delete("/:id", PatientController.deletePatient);
            });

            Spark.path("/medical-care", () -> {
                Spark.post("", MedicalCareController.addMedicalCare);
                Spark.get("", MedicalCareController.listMedicalCare);
                Spark.get("/:id", MedicalCareController.getMedicalCare);
                Spark.delete("/:id", MedicalCareController.deleteMedicalCare);

            });

            Spark.path("/schedule", () -> {
                Spark.get("", ScheduleController.listSchedule);
                Spark.delete("/:id", ScheduleController.deleteSchedule);

                Spark.path("/laboratory", () -> {
                    Spark.post("", LaboratoryController.addLaboratory);
                    Spark.get("", LaboratoryController.listLaboratory);
                    Spark.get("/:id", LaboratoryController.getLaboratory);
                    Spark.delete("/:id", LaboratoryController.deleteLaboratory);
                });

                Spark.path("/appointment", () -> {
                    Spark.post("", AppointmentController.addAppointment);
                    Spark.get("", AppointmentController.listAppointment);
                    Spark.get("/:id", AppointmentController.getAppointment);
                    Spark.delete("/:id", AppointmentController.deleteAppointment);
                });

                Spark.path("/image-diagnostic", () -> {
                    Spark.post("", ImageDiagnosticController.addImageDiagnostic);
                    Spark.get("", ImageDiagnosticController.listImageDiagnostic);
                    Spark.get("/:id", ImageDiagnosticController.getImageDiagnostic);
                    Spark.delete("/:id", ImageDiagnosticController.deleteImageDiagnostic);
                });
            });
        });

        Spark.exception(Exception.class, (exception, request, response) -> {
            String message = getExceptionMessage(exception);

            response.status(HttpStatus.BAD_REQUEST.getCode());
            response.type("application/json");
            response.body(new HttpResponse()
                    .code(HttpStatus.BAD_REQUEST)
                    .message(message)
                    .exception(exception.getClass().getSimpleName())
                    .build());
        });

        Spark.internalServerError((req, response) -> {
            response.type("application/json");
            return new HttpResponse()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Não sei o que, mas acho que deu errado! 😭")
                    .build();
        });
    }

    private static String getExceptionMessage(Exception exception) {
        String message;

        if(exception.getCause() != null){
            message = exception.getCause().getMessage();
//                System.out.println("1 > " + message);
        } else if (exception instanceof JacksonException) {
            message = ((JsonProcessingException) exception).getOriginalMessage();
//                System.out.println("2 >" + message);
        } else {
            message = exception.getMessage();
//                System.out.println("3 >" + message);
        }
        return message;
    }
}
