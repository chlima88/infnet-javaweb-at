package bibliotecaSpark.model;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String login;
    private String password;
}
