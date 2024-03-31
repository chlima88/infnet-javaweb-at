package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class Address {
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String cep;

    @JsonSetter(value = "logradouro")
    public void setLogradouro(String street) {
        this.street = street;
    }

    @JsonSetter(value = "complemento")
    public void setComplemento(String number) {
        this.number = number;
    }

    @JsonSetter(value = "bairro")
    public void setBairro(String district) {
        this.district = district;
    }

    @JsonSetter(value = "localidade")
    public void setLocalidade(String city) {
        this.city = city;
    }

    @JsonSetter(value = "uf")
    public void setUf(String state) {
        this.state = state;
    }

}
