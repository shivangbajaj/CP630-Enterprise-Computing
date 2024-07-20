package ec.spring.boot;

import org.springframework.stereotype.Service;

@Service
public class GradeService {

	public String predict(int score) {
		if (score >= 70)
			return "pass";
		else
			return "fail";
	}
}

//import org.springframework.stereotype.Service;
//
//@Service
//public class GradeService implements GradeServiceI {
//
//	public String getGrade(int score) {
//		String result ="F";
//		if (score >= 90) 
//			result = "A+";
//		else if (score >= 85)
//			result = "A";
//		else if (score >= 80)
//			result = "A-";
//		else if (score >= 77)
//			result = "B+";
//		else if (score >= 73)
//			result = "B";
//		else if (score >= 70)
//			result = "B-";
//
//		return result;
//	}
//
//}
