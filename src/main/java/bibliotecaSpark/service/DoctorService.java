package bibliotecaSpark.service;

import bibliotecaSpark.model.Address;
import bibliotecaSpark.model.Doctor;
import feign.Feign;
import feign.jackson.JacksonDecoder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DoctorService {

    private static Map<Integer, Doctor> doctorDb = new HashMap<>();
    private static int id = 0;

    private static CepService cepService = Feign.builder()
            .decoder(new JacksonDecoder())
            .target(CepService.class, "https://viacep.com.br/");

    public static void add(Doctor doctor){
        String number = doctor.getAddress().getNumber();
        Address address = cepService.getAddressByCep(doctor.getAddress().getCep());
        address.setNumber(number);
        doctor.setAddress(address);
        doctorDb.put(doctor.getDoctorId(), doctor);
    }

    public static void delete (int employeeId){
        doctorDb.remove(employeeId);
    }

    public static Collection<Doctor> list() {
        return doctorDb.values();
    }

    public static Doctor getById(int itemId) {
        return doctorDb.get(itemId);
    }
}
