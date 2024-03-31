package bibliotecaSpark.service;

import bibliotecaSpark.model.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ScheduleService {

    private static final Map<Integer, Schedule> scheduleDb = new HashMap<>();

    public static void add(Schedule schedule){
        scheduleDb.put(schedule.getScheduleId(), schedule);
    }

    public static void delete (int scheduleId){
        scheduleDb.remove(scheduleId);
    }

    public static Collection<Schedule> list() {
        return scheduleDb.values();
    }

    public static Schedule getById(int itemId) {
        return scheduleDb.get(itemId);
    }

    public static Schedule build(ScheduleDTO scheduleDTO) {
        MedicalCare medicalCare = MedicalCareService.getById(scheduleDTO.getMedicalCareId());
        Patient patient = PatientService.getById(scheduleDTO.getPatientId());
        Doctor doctor = DoctorService.getById(scheduleDTO.getDoctorId());

        return new Schedule(scheduleDTO.getDatetime(), patient, doctor, medicalCare);
    }
}
