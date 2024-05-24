package bp.PAI_jwt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"bp.PAI_jwt.controller", "bp.PAI_jwt.aspect"})
public class AspectConfig {

}

