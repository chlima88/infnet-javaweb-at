package bibliotecaSpark.service;

import bibliotecaSpark.model.Laboratory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LaboratoryService {

    private static final Map<Integer, Laboratory> laboratoryDb = new HashMap<>();

    public static void add(Laboratory laboratory){
        laboratoryDb.put(laboratory.getMedicalCareId(), laboratory);
    }

    public static void delete (int laboratoryId){
        laboratoryDb.remove(laboratoryId);
    }

    public static Collection<Laboratory> list() {
        return laboratoryDb.values();
    }

    public static Laboratory getById(int itemId) {
        return laboratoryDb.get(itemId);
    }
}
