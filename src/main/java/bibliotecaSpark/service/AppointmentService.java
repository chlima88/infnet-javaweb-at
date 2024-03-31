package bibliotecaSpark.service;

import bibliotecaSpark.App;
import bibliotecaSpark.model.Appointment;
import bibliotecaSpark.model.MedicalCare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AppointmentService extends MedicalCareService {

    private static int id = 0;

    public static void add(Appointment appointment){
        medicalCareDb.put(appointment.getMedicalCareId(), appointment);
    }

    public static void delete (int appointmentId){
        medicalCareDb.remove(appointmentId);
    }

    public static Collection<Appointment> list() {
        Collection<Appointment> appointments = new ArrayList<>();
        for (MedicalCare medicalCare: medicalCareDb.values()) {
            if (medicalCare instanceof Appointment)
                appointments.add((Appointment) medicalCare);
        }
        return appointments;
    }

    public static Appointment getById(int itemId) {
        return (Appointment) medicalCareDb.get(itemId);
    }
}
