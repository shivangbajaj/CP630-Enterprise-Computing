package ec.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"ec.spring", "ec.rank", "ec.grade", "ec.weka"})
@EntityScan("ec.weka")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
