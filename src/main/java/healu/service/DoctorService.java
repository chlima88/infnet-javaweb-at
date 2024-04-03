package healu.service;

import healu.exception.DuplicatedEntityException;
import healu.exception.EntityNotFoundException;
import healu.model.Address;
import healu.model.Doctor;
import feign.Feign;
import feign.jackson.JacksonDecoder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DoctorService {

    private static final Map<Integer, Doctor> doctorDb = new HashMap<>();

    private static final CepService cepService = Feign.builder()
            .decoder(new JacksonDecoder())
            .target(CepService.class, "https://viacep.com.br/");

    public static void add(Doctor doctor) throws DuplicatedEntityException {
        if ( !doctorDb.values().stream().filter(doctor::equals).toList().isEmpty())
            throw new DuplicatedEntityException("Doctor already exists");
        String number = doctor.getAddress().getNumber();
        Address address = cepService.getAddressByCep(doctor.getAddress().getCep());
        address.setNumber(number);
        doctor.setAddress(address);
        doctorDb.put(doctor.getDoctorId(), doctor);
    }

    public static void deleteById (int itemId) throws EntityNotFoundException {
        if(doctorDb.get(itemId) == null)
            throw new EntityNotFoundException("doctorId not found");
        doctorDb.remove(itemId);
    }

    public static Collection<Doctor> list() {
        return doctorDb.values();
    }

    public static Doctor getById(int itemId) throws EntityNotFoundException {
        if(doctorDb.get(itemId) == null)
            throw new EntityNotFoundException("doctorId not found");
        return doctorDb.get(itemId);
    }
}
