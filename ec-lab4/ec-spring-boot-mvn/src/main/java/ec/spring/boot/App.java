package ec.spring.boot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class App  implements CommandLineRunner {

	@Autowired
    private GradeService gs;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        System.out.println(gs.predict(80));
    }
}




//@SpringBootApplication
//public class App  implements CommandLineRunner {
//
//	@Autowired
//    private GradeService gs;
//	
//	public static void main(String[] args) throws Exception {
//		SpringApplication.run(App.class, args);
//	}
//	
//	@Bean
//	public GradeService getGradeService(){
//		return  new GradeService();
//	}
//	
//	@Override
//    public void run(String... args) throws Exception {
//		// using bean for injection
//		System.out.println(getGradeService().getGrade(60));
//
//		// using autowired bean
//        if (args.length > 0) {
//            System.out.println(gs.getGrade(Integer.parseInt(args[0])));
//        } else {
//            System.out.println(gs.getGrade(60));
//        }
//    }
//}