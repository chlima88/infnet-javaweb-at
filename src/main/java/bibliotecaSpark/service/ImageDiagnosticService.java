package bibliotecaSpark.service;

import bibliotecaSpark.exception.EntityNotFoundException;
import bibliotecaSpark.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ImageDiagnosticService extends ScheduleService{

    public static void add(ImageDiagnostic imageDiagnostic){
        scheduleDb.put(imageDiagnostic.getScheduleId(), imageDiagnostic);
    }

    public static void delete (int imageDiagnosticId){
        scheduleDb.remove(imageDiagnosticId);
    }

    public static Collection<ImageDiagnostic> list() {
        Collection<ImageDiagnostic> imageDiagnostic = new ArrayList<>();
        for (Schedule schedule: scheduleDb.values()) {
            if (schedule instanceof ImageDiagnostic)
                imageDiagnostic.add((ImageDiagnostic) schedule);
        }
        return imageDiagnostic;
    }

    public static ImageDiagnostic getById(int itemId) {
        return (ImageDiagnostic) scheduleDb.get(itemId);
    }

    public static ImageDiagnostic build(ImageDiagnosticDTO imageDiagnosticDTO) throws Exception {
        MedicalCare medicalCare = MedicalCareService.getById(imageDiagnosticDTO.getMedicalCareId());
        if (medicalCare == null ) throw new EntityNotFoundException("medicalCareId not found");
        Patient patient = PatientService.getById(imageDiagnosticDTO.getPatientId());
        if (patient == null ) throw new EntityNotFoundException("patientId not found");
        Doctor doctor = DoctorService.getById(imageDiagnosticDTO.getDoctorId());
        if (doctor == null ) throw new EntityNotFoundException("doctorId not found");

        ImageDiagnostic imageDiagnostic = new ImageDiagnostic(imageDiagnosticDTO.getDatetime(),patient, doctor, medicalCare, imageDiagnosticDTO.getType());
        imageDiagnostic.setType(imageDiagnosticDTO.getType());
        imageDiagnostic.setResult(imageDiagnosticDTO.getResult());
        return imageDiagnostic;
    }
}
