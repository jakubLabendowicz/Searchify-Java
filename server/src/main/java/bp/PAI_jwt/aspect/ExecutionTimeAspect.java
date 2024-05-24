package bp.PAI_jwt.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//Tydzień 11, Programowanie aspektowe
@Aspect
@Component
public class ExecutionTimeAspect {

    private Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Pointcut("execution(* bp.PAI_jwt.controller.UserController.*(..))")
    public void userControllerMethods() {}

    @Before("userControllerMethods()")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Executing method: {}", methodName);
    }

    @After("userControllerMethods()")
    public void afterMethodExecution(JoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} execution time: {} ms", methodName, System.currentTimeMillis() - startTime);
    }
}
//Koniec, Tydzień 11, Programowanie aspektowe
