package com.fiap.techchallenge.gateway.strategy;

import org.springframework.http.HttpMethod;

public class CommonUserAuthorization implements AuthorizationStrategy {
    @Override
    public boolean authorize(String requestPath, HttpMethod requestMethod, String userType) {
//        boolean isVehicleEndpoint = requestPath.startsWith("/vehicle");
//        boolean isCreateOperation  = requestMethod == HttpMethod.POST;
//        if (isVehicleEndpoint && isCreateOperation) {
//            return true;
//        }
        return true;
    }
}
