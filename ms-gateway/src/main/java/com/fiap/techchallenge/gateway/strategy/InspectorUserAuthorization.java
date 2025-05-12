package com.fiap.techchallenge.gateway.strategy;

import org.springframework.http.HttpMethod;

public class InspectorUserAuthorization implements AuthorizationStrategy {
    @Override
    public boolean authorize(String requestPath, HttpMethod requestMethod, String userType) {
        boolean isVehicleEndpoint = requestPath.startsWith("/vehicle");
        if(isVehicleEndpoint) {
            return false;
        }
        return true;
    }
}
