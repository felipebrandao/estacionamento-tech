package com.fiap.techchallenge.auth.model;

import com.fiap.techchallenge.auth.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
@Data
@Builder
public class User {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private UserTypeEnum userType;
    private String password;

}
