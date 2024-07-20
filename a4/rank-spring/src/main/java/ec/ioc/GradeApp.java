package ec.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ec.grade.Grade;
import ec.grade.GradeImpl;

public class GradeApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("GradeBeans.xml");

		Grade grade = (Grade) context.getBean("grade-bean");

		System.out.println("Letter grades for % grades from 66 to 100:");
		for (int i = 66; i <= 100; i++) {
			System.out.print(i + ":" + grade.getLetterGrade(i) + "\t");
			if (i % 5 == 0) {
				System.out.println();
			}
		}

		GradeImpl newGrade = (GradeImpl) context.getBean("grade-bean");
		newGrade.setGradeBoundary(new int[] { 100, 90, 85, 80, 77, 73, 70, 67, 63, 60, 57, 53, 50, 0 });
		newGrade.setLetterGrade(new String[] { "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F" });

		System.out.println("\nLetter grades for % grades from 46 to 100:");
		for (int i = 46; i <= 100; i++) {
			System.out.print(i + ":" + grade.getLetterGrade(i) + "\t");
			if (i % 5 == 0) {
				System.out.println();
			}
		}
	}
}
