package bibliotecaSpark.service;

import bibliotecaSpark.exception.EntityNotFoundException;
import bibliotecaSpark.model.*;

import java.util.ArrayList;
import java.util.Collection;

public class AppointmentService extends ScheduleService {

    public static void add(Appointment appointment){
        scheduleDb.put(appointment.getScheduleId(), appointment);
    }

    public static void delete (int appointmentId){
        scheduleDb.remove(appointmentId);
    }

    public static Collection<Appointment> list() {
        Collection<Appointment> appointments = new ArrayList<>();
        for (Schedule schedule: scheduleDb.values()) {
            if (schedule instanceof Appointment)
                appointments.add((Appointment) schedule);
        }
        return appointments;
    }

    public static Appointment getById(int itemId) {
        return (Appointment) scheduleDb.get(itemId);
    }

    public static Appointment build(AppointmentDTO appointmentDTO) throws Exception {
        MedicalCare medicalCare = MedicalCareService.getById(appointmentDTO.getMedicalCareId());
        if (medicalCare == null ) throw new EntityNotFoundException("medicalCareId not found");
        Patient patient = PatientService.getById(appointmentDTO.getPatientId());
        if (patient == null ) throw new EntityNotFoundException("patientId not found");
        Doctor doctor = DoctorService.getById(appointmentDTO.getDoctorId());
        if (doctor == null ) throw new EntityNotFoundException("doctorId not found");

        Appointment appointment = new Appointment(appointmentDTO.getDatetime(),patient, doctor, medicalCare, appointmentDTO.getType());
        appointment.setDiagnostic(appointmentDTO.getDiagnostic());
        appointment.setSymptoms(appointmentDTO.getSymptoms());
        appointment.setPrescription(appointmentDTO.getPrescription());
        return appointment;
    }
}
