package com.marvelchallenge.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogKeysEnum {
    PRIVATE_KEY("privateKey"),
    PUBLIC_KEY("publicKey"),
    API_VERSION("serviceVersion"),
    ENDPOINT("endpoint"),
    SOURCE_IP("sourceIp"),
    REQUEST_ID("requestId"),
    REQUEST_PARAMS("requestParams"),
    RESPONSE_PARAM("responseParam"),
    REQUEST_URI("requestURI"),
    HTTP_METHOD("httpMethod"),
    HTTP_STATUS("httpStatus"),
    MESSAGES("messages"),
    SERVICE_VERSION("serviceVersion"),
    ERROR_CODE("errorCode"),
    EXCEPTION_MESSAGE("exceptionMessage"),
    LATENCY("latency");

    private final String key;
}
