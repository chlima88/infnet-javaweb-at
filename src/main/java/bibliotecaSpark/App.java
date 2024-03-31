package bibliotecaSpark;

import bibliotecaSpark.controller.*;
import bibliotecaSpark.exception.HttpStatus;
import bibliotecaSpark.exception.RestExceptionHandler;
import spark.Spark;

public class App {

    public static void main(String[] args) {
        Spark.port(8080);

        Spark.options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        Spark.before((request, response) -> {
            if (request.pathInfo().endsWith("/") && !request.pathInfo().equals("/")) {
                response.redirect(request.pathInfo().replaceAll("/+$", ""));
            }
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
        });

        Spark.get("/", SiteController.homePage);
        Spark.get("/scripts/top-menu.js", (req, res) ->
                App.class.getResourceAsStream("/top-menu.js"));

        Spark.path("/api", () -> {
            Spark.after("/*",(request, response) ->
            {
                response.type("application/json");
            });
            Spark.path("/doctor", () -> {
                Spark.post("", DoctorController.addDoctor);
                Spark.get("", DoctorController.listDoctor);
                Spark.get("/:id", DoctorController.getDoctor);
                Spark.delete("/:id", DoctorController.deleteDoctor);
            });

            Spark.path("/user", () -> {
                Spark.post("", UserController.addUser);
                Spark.get("", UserController.listUser);
                Spark.get("/:id", UserController.getUser);
                Spark.delete("/:id", UserController.deleteUser);
            });

            Spark.path("/patient", () -> {
                Spark.post("", PatientController.addPatient);
                Spark.get("", PatientController.listPatient);
                Spark.get("/:id", PatientController.getPatient);
                Spark.delete("/:id", PatientController.deletePatient);
            });

            Spark.path("/medical-care", () -> {
                Spark.get("", MedicalCareController.listMedicalCare);
                Spark.delete("/:id", MedicalCareController.deleteMedicalCare);

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

                Spark.path("/image-diagnosis", () -> {
                    Spark.post("", ImageDiagnosisController.addImageDiagnosis);
                    Spark.get("", ImageDiagnosisController.listImageDiagnosis);
                    Spark.get("/:id", ImageDiagnosisController.getImageDiagnosis);
                    Spark.delete("/:id", ImageDiagnosisController.deleteImageDiagnosis);
                });

            });

            Spark.path("/schedule", () -> {
                Spark.post("", ScheduleController.addSchedule);
                Spark.get("", ScheduleController.listSchedule);
                Spark.get("/:id", ScheduleController.getSchedule);
                Spark.delete("/:id", ScheduleController.deleteSchedule);
            });
        });

        Spark.internalServerError((req, res) -> {
            res.type("application/json");
            return new RestExceptionHandler()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Sei la, mas acho que deu errado! 😭")
                    .build();

        });
    }
}
