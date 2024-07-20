package ec.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

@RestController
class ControllerJson {
	@RequestMapping("/json/{name}")
	String hello(@PathVariable String name) {
		return "{\"name\": " + name + "}";
	}
}

@RestController
class ControllerXML {
	@RequestMapping("/xml/{name}")
	String hello(@PathVariable String name) {
		return "<name>" + name + "</name>";
	}
}
