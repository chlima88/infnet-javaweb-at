package bibliotecaSpark.service;

import bibliotecaSpark.exception.EntityNotFoundException;
import bibliotecaSpark.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LaboratoryService extends ScheduleService{

    public static void add(Laboratory laboratory){
        scheduleDb.put(laboratory.getScheduleId(), laboratory);
    }

    public static void delete (int laboratoryId){
        scheduleDb.remove(laboratoryId);
    }

    public static Collection<Laboratory> list() {
        Collection<Laboratory> laboratory = new ArrayList<>();
        for (Schedule schedule: scheduleDb.values()) {
            if (schedule instanceof Laboratory)
                laboratory.add((Laboratory) schedule);
        }
        return laboratory;
    }

    public static Laboratory getById(int itemId) {
        return (Laboratory) scheduleDb.get(itemId);
    }

    public static Laboratory build(LaboratoryDTO laboratoryDTO) throws Exception {
        MedicalCare medicalCare = MedicalCareService.getById(laboratoryDTO.getMedicalCareId());
        if (medicalCare == null ) throw new EntityNotFoundException("medicalCareId not found");
        Patient patient = PatientService.getById(laboratoryDTO.getPatientId());
        if (patient == null ) throw new EntityNotFoundException("patientId not found");
        Doctor doctor = DoctorService.getById(laboratoryDTO.getDoctorId());
        if (doctor == null ) throw new EntityNotFoundException("doctorId not found");

        Laboratory laboratory = new Laboratory(laboratoryDTO.getDatetime(),patient, doctor, medicalCare, laboratoryDTO.getType());
        laboratory.setSampleType(laboratoryDTO.getSampleType());
        laboratory.setResult(laboratoryDTO.getResult());
        return laboratory;
    }
}
