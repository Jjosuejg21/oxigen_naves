package com.oxigen.navesespaciales.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Loggin {
    private static final Logger logger = LoggerFactory.getLogger(Loggin.class);

    @Pointcut("execution(* com.oxigen.navesespaciales.controller.NaveEspacialController.getNav(..)) && args(id,..)")
    public void getNaveEspacialById(Long id) {
    }

    @AfterReturning("getNaveEspacialById(id)")
    public void logIfNegativeId(Long id) {
        if (id < 0) {
            logger.warn("ERROR Naves con valor negativo no permitidas: " + id);
        }
    }
}
