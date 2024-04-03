package bibliotecaSpark.service;

import bibliotecaSpark.exception.DuplicatedEntityException;
import bibliotecaSpark.exception.EntityNotFoundException;
import bibliotecaSpark.model.MedicalCare;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MedicalCareService {

    protected static final Map<Integer, MedicalCare> medicalCareDb = new HashMap<>();

    public static void deleteById (int medicalCareId) throws EntityNotFoundException {
        if(medicalCareDb.remove(medicalCareId) == null)
            throw new EntityNotFoundException("medicalCareId not found");
    }

    public static Collection<? extends MedicalCare> list() {
        return medicalCareDb.values();
    }

    public static MedicalCare getById(int itemId) throws EntityNotFoundException {
        if(medicalCareDb.get(itemId) == null)
            throw new EntityNotFoundException("medicalCareId not found");
        return medicalCareDb.get(itemId);
    }

    public static void add(MedicalCare medicalCare) throws DuplicatedEntityException {
        if ( !medicalCareDb.values().stream().filter(medicalCare::equals).toList().isEmpty())
            throw new DuplicatedEntityException("MedicalCare already exists");
        medicalCareDb.put(medicalCare.getMedicalCareId(), medicalCare);
    }
}
