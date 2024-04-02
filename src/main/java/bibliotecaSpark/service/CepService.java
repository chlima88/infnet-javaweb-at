package bibliotecaSpark.service;

import bibliotecaSpark.model.Address;
import feign.Param;
import feign.RequestLine;

public interface CepService {
    @RequestLine("GET /ws/{cep}/json")
    Address getAddressByCep(@Param("cep") String cep);
}
