package summit.inbound.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import summit.UseCase;

@Configuration
@ComponentScan(
        basePackageClasses = UseCase.class,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {UseCase.class})
        })
public class DomainConfiguration {
}
