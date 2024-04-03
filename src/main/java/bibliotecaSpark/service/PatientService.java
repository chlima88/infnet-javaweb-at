package bibliotecaSpark.service;

import bibliotecaSpark.exception.DuplicatedEntityException;
import bibliotecaSpark.exception.EntityNotFoundException;
import bibliotecaSpark.model.Address;
import bibliotecaSpark.model.Patient;
import feign.Feign;
import feign.jackson.JacksonDecoder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PatientService {

    private static final Map<Integer, Patient> patientDb = new HashMap<>();
    private static final CepService cepService = Feign.builder()
            .decoder(new JacksonDecoder())
            .target(CepService.class, "https://viacep.com.br/");

    public static void add(Patient patient) throws Exception {
        if ( !patientDb.values().stream().filter(patient::equals).toList().isEmpty())
            throw new DuplicatedEntityException("Patient already exists");
        String number = patient.getAddress().getNumber();
        Address address = PatientService.cepService.getAddressByCep(patient.getAddress().getCep());
        address.setNumber(number);
        patient.setAddress(address);
        patientDb.put(patient.getPatientId(), patient);
    }

    public static void deleteById (int itemId) throws EntityNotFoundException {
        if(patientDb.get(itemId) == null)
            throw new EntityNotFoundException("patientId not found");
        patientDb.remove(itemId);
    }

    public static Collection<Patient> list() {
        return patientDb.values();
    }

    public static Patient getById(int itemId) throws EntityNotFoundException {
        if(patientDb.get(itemId) == null)
            throw new EntityNotFoundException("patientId not found");
        return patientDb.get(itemId);
    }


}
