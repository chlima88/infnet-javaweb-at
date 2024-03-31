package bibliotecaSpark.service;

import bibliotecaSpark.model.ImageDiagnosis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ImageDiagnosisService {

    private static final Map<Integer, ImageDiagnosis> imageDiagnosisDb = new HashMap<>();

    public static void add(ImageDiagnosis imageDiagnosis){
        imageDiagnosisDb.put(imageDiagnosis.getMedicalCareId(), imageDiagnosis);
    }

    public static void delete (int imageDiagnosisId){
        imageDiagnosisDb.remove(imageDiagnosisId);
    }

    public static Collection<ImageDiagnosis> list() {
        return imageDiagnosisDb.values();
    }

    public static ImageDiagnosis getById(int itemId) {
        return imageDiagnosisDb.get(itemId);
    }
}
