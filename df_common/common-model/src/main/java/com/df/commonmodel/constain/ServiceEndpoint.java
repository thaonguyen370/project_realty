package com.df.commonmodel.constain;

public class ServiceEndpoint {
    private ServiceEndpoint() {
        throw new IllegalStateException("Utility class");
    }

    public static final String STORAGE_SERVICE_ENDPOINT = "http://localhost:8763";
}
