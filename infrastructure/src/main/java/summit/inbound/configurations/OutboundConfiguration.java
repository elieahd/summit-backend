package summit.inbound.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import summit.outbound.OutboundAdapter;

@Configuration
@ComponentScan(
        basePackageClasses = OutboundAdapter.class,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {OutboundAdapter.class})
        })
public class OutboundConfiguration {
}
