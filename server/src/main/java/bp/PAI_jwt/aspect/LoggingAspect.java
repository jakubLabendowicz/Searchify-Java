package bp.PAI_jwt.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//Tydzień 11, Programowanie aspektowe
@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* bp.PAI_jwt.controller.JwtAuthenticationController.*(..))")
    public void controllerMethods() {}

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logMethodExecution(Object result) {
        logger.info("Method executed successfully with result: {}", result);
    }
}
//Koniec, Tydzień 11, Programowanie aspektowe
