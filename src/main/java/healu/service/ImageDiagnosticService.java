package healu.service;

import healu.exception.DuplicatedEntityException;
import healu.exception.EntityNotFoundException;
import healu.model.*;

import java.util.ArrayList;
import java.util.Collection;

public class ImageDiagnosticService extends ScheduleService{

    public static void add(ImageDiagnostic imageDiagnostic) throws DuplicatedEntityException {
        if ( !scheduleDb.values().stream().filter(imageDiagnostic::equals).toList().isEmpty())
            throw new DuplicatedEntityException("ImageDiagnostic already exists");
        scheduleDb.put(imageDiagnostic.getScheduleId(), imageDiagnostic);
    }

    public static void deleteById (int itemId) throws EntityNotFoundException {
        if(scheduleDb.get(itemId) == null)
            throw new EntityNotFoundException("ImageDiagnostic not found");
        scheduleDb.remove(itemId);
    }

    public static Collection<ImageDiagnostic> list() {
        Collection<ImageDiagnostic> imageDiagnostic = new ArrayList<>();
        for (Schedule schedule: scheduleDb.values()) {
            if (schedule instanceof ImageDiagnostic)
                imageDiagnostic.add((ImageDiagnostic) schedule);
        }
        return imageDiagnostic;
    }

    public static ImageDiagnostic getById(int itemId) throws EntityNotFoundException {
        if(scheduleDb.get(itemId) == null)
            throw new EntityNotFoundException("ImageDiagnostic not found");
        return (ImageDiagnostic) scheduleDb.get(itemId);
    }

    public static ImageDiagnostic build(ImageDiagnosticDTO imageDiagnosticDTO) throws EntityNotFoundException {
        MedicalCare medicalCare = MedicalCareService.getById(imageDiagnosticDTO.getMedicalCareId());
        Patient patient = PatientService.getById(imageDiagnosticDTO.getPatientId());
        Doctor doctor = DoctorService.getById(imageDiagnosticDTO.getDoctorId());

        ImageDiagnostic imageDiagnostic = new ImageDiagnostic(imageDiagnosticDTO.getDatetime(),patient, doctor, medicalCare, imageDiagnosticDTO.getType());
        imageDiagnostic.setType(imageDiagnosticDTO.getType());
        imageDiagnostic.setResult(imageDiagnosticDTO.getResult());
        return imageDiagnostic;
    }
}
