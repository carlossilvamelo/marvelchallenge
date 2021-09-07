package com.marvelchallenge.config;

import com.marvelchallenge.config.enumerators.LogKeysEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ApiInterceptor implements HandlerInterceptor {

    static final String DEFAULT_RESPONSE_LOG_MSG = "API - marvel-api";

    @Value("${api.version}")
    private String apiVersion;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean isRequestDispatcher = DispatcherType.REQUEST.name()
                .equals(request.getDispatcherType().name());


        if (isRequestDispatcher) {
            var endpoint = String.format("%s %s"
                    , request.getMethod()
                    , request.getRequestURI());
            String sourceIp = request.getRemoteAddr();
            String requestId = request.getHeader("request-id");
            ThreadContext.put(LogKeysEnum.API_VERSION.getKey(), apiVersion);
            ThreadContext.put(LogKeysEnum.ENDPOINT.getKey(), endpoint);
            ThreadContext.put(LogKeysEnum.SOURCE_IP.getKey(), sourceIp);
            ThreadContext.put(LogKeysEnum.REQUEST_ID.getKey(), requestId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        boolean isRequestDispatcher = DispatcherType.REQUEST.name()
                .equals(request.getDispatcherType().name());
        if (isRequestDispatcher) {

            var requestParams = requestParamsToString(request.getParameterMap());
            var httpMethod = request.getMethod();
            var httpStatus = String.valueOf(response.getStatus());
            var requestUri = request.getRequestURI();
            var errorCode = String.valueOf(response.getStatus());

            ThreadContext.put(LogKeysEnum.HTTP_METHOD.getKey(), httpMethod);
            ThreadContext.put(LogKeysEnum.HTTP_STATUS.getKey(), httpStatus);
            ThreadContext.put(LogKeysEnum.REQUEST_URI.getKey(), requestUri);
            ThreadContext.put(LogKeysEnum.ERROR_CODE.getKey(), errorCode);
            ThreadContext.put(LogKeysEnum.REQUEST_PARAMS.getKey(), requestParams);
            ThreadContext.put(LogKeysEnum.API_VERSION.getKey(), apiVersion);
            log.info(DEFAULT_RESPONSE_LOG_MSG);
        }
    }

    private String requestParamsToString(Map<String, String[]> requestParams) {
        List<String> requestParamList = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
            var values = String.join(",", entry.getValue());

            requestParamList.add(String.format("{%s=[%s]}", entry.getKey(), values));
        }
        return String.join(",", requestParamList);
    }
}
