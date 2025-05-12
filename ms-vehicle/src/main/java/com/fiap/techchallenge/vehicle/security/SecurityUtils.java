package com.fiap.techchallenge.vehicle.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;
import java.util.UUID;

public class SecurityUtils {

    /**
     * Obtém o ID do usuário armazenado no contexto de segurança
     *
     * @return UUID do usuário
     */
    public static UUID getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getDetails() != null) {
            Map<String, Object> details = (Map<String, Object>) authentication.getDetails();
            return (UUID) details.get("id_user");
        }
        throw new IllegalStateException("Não foi possível obter o ID do usuário");
    }

    /**
     * Obtém o nome do usuário armazenado no contexto de segurança
     *
     * @return nome do usuário
     */
    public static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getDetails() != null) {
            Map<String, Object> details = (Map<String, Object>) authentication.getDetails();
            return (String) details.get("name_user");
        }
        throw new IllegalStateException("Não foi possível obter o nome do usuário");
    }

    /**
     * Obtém o email do usuário armazenado no contexto de segurança
     *
     * @return email do usuário
     */
    public static String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getDetails() != null) {
            Map<String, Object> details = (Map<String, Object>) authentication.getDetails();
            return (String) details.get("email_user");
        }
        throw new IllegalStateException("Não foi possível obter o email do usuário");
    }

}
