package ec.weka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
	
    @Bean
    public EcModelRepository ecModelRepository() {
        return new EcModelRepositoryImpl();
    }
}
