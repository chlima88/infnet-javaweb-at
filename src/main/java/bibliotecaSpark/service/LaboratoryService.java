package bibliotecaSpark.service;

import bibliotecaSpark.exception.DuplicatedEntityException;
import bibliotecaSpark.exception.EntityNotFoundException;
import bibliotecaSpark.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LaboratoryService extends ScheduleService{

    public static void add(Laboratory laboratory) throws DuplicatedEntityException {
        if ( !scheduleDb.values().stream().filter(laboratory::equals).toList().isEmpty())
            throw new DuplicatedEntityException("ImageDiagnostic already exists");
        scheduleDb.put(laboratory.getScheduleId(), laboratory);
    }

    public static void deleteById(int itemId) throws EntityNotFoundException {
        if(scheduleDb.get(itemId) == null)
            throw new EntityNotFoundException("Laboratory not found");
        scheduleDb.remove(itemId);
    }

    public static Collection<Laboratory> list() {
        Collection<Laboratory> laboratory = new ArrayList<>();
        for (Schedule schedule: scheduleDb.values()) {
            if (schedule instanceof Laboratory)
                laboratory.add((Laboratory) schedule);
        }
        return laboratory;
    }

    public static Laboratory getById(int itemId) throws EntityNotFoundException {
        if(scheduleDb.get(itemId) == null)
            throw new EntityNotFoundException("Laboratory not found");
        return (Laboratory) scheduleDb.get(itemId);
    }

    public static Laboratory build(LaboratoryDTO laboratoryDTO) throws EntityNotFoundException {
        MedicalCare medicalCare = MedicalCareService.getById(laboratoryDTO.getMedicalCareId());
        Patient patient = PatientService.getById(laboratoryDTO.getPatientId());
        Doctor doctor = DoctorService.getById(laboratoryDTO.getDoctorId());

        Laboratory laboratory = new Laboratory(laboratoryDTO.getDatetime(),patient, doctor, medicalCare, laboratoryDTO.getType());
        laboratory.setSampleType(laboratoryDTO.getSampleType());
        laboratory.setResult(laboratoryDTO.getResult());
        return laboratory;
    }
}
