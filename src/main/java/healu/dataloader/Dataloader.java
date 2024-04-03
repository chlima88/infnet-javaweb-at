package healu.dataloader;

import healu.model.*;
import healu.service.*;

public class Dataloader {

    public static void load() throws Exception {

        for (int i=1; i <= 3; i++) {
            PatientService.add(new Patient(
                    "patient " + String.valueOf(i),
                    new Address("20010020"),
                    String.valueOf(i),
                    true
            ));
        }

        for (int i=1; i <= 3; i++) {
            DoctorService.add(new Doctor(
                    "doctor " + String.valueOf(i),
                    new Address("20010020"),
                    String.valueOf(i),
                    "ortopedista"
            ));
        }

        for (int i=1; i <= 3; i++) {
            MedicalCareService.add(new MedicalCare(
                    "medical care " + String.valueOf(i),
                    String.valueOf(i),
                    30
            ));
        }

        AppointmentService.add(new Appointment(
                "2024-06-01T10:00",
                PatientService.getById(1),
                DoctorService.getById(1),
                MedicalCareService.getById(1),
                "appointment"
        ));

        LaboratoryService.add(new Laboratory(
                "2024-06-01T11:00",
                PatientService.getById(2),
                DoctorService.getById(2),
                MedicalCareService.getById(2),
                "laboratory"
        ));

        ImageDiagnosticService.add(new ImageDiagnostic(
                "2024-06-01T12:00",
                PatientService.getById(3),
                DoctorService.getById(3),
                MedicalCareService.getById(3),
                "image-diagnostic"
        ));

    }
}
