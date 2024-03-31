package bibliotecaSpark.service;

import bibliotecaSpark.model.MedicalCare;
import bibliotecaSpark.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MedicalCareService {

    protected static final Map<Integer, MedicalCare> medicalCareDb = new HashMap<>();

    public static void delete (int medicalCareId){
        medicalCareDb.remove(medicalCareId);
    }

    public static Collection<? extends MedicalCare> list() {
        return medicalCareDb.values();
    }

    public static MedicalCare getById(int itemId) {
        return medicalCareDb.get(itemId);
    }
}
