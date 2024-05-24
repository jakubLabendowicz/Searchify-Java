package bp.PAI_jwt.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//Tydzień 11, Programowanie aspektowe
@Aspect
@Component
public class ResponseMetadataAspect {

    @Pointcut("execution(* bp.PAI_jwt.controller.StreamController.*(..))")
    public void streamControllerMethods() {}

    @Around("streamControllerMethods()")
    public Object addResponseMetadata(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        if (result instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            Object body = responseEntity.getBody();
            if (body instanceof Map) {
                Map<String, Object> responseBody = (Map<String, Object>) body;
                addMetadata(responseBody, endTime - startTime);
            }
        }

        return result;
    }

    private void addMetadata(Map<String, Object> responseBody, long executionTime) {
        if (responseBody != null) {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("executionTime", executionTime + " ms");
            metadata.put("apiVersion", "1.0");
            responseBody.put("metadata", metadata);
        }
    }
}
//Koniec, Tydzień 11, Programowanie aspektowe