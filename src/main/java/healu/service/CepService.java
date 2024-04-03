package healu.service;

import healu.model.Address;
import feign.Param;
import feign.RequestLine;

public interface CepService {
    @RequestLine("GET /ws/{cep}/json")
    Address getAddressByCep(@Param("cep") String cep);
}
