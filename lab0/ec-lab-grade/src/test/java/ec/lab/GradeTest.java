package ec.lab;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ec.lab.Grade;

public class GradeTest {
    
    private static Grade grade;

    @BeforeClass
    public static void initCalculator() {
        grade = new ECGrade();
    }
    
    @Before
    public void beforeEachTest() {
        System.out.println("This is executed before each Test");
    }

    @After
    public void afterEachTest() {
        System.out.println("This is executed after each Test");
    }
    
    @Test
    public void testGetGrade() {
        assertEquals("A+", grade.getGrade(95));
        assertEquals("A", grade.getGrade(89));
        assertEquals("A-", grade.getGrade(84));
        assertEquals("B+", grade.getGrade(79));
        assertEquals("B", grade.getGrade(76));
        assertEquals("F", grade.getGrade(65));
    }

    @Test
    public void testPredictPass() {
        assertEquals("pass", grade.predictPass(71));
        assertEquals("fail", grade.predictPass(69));
    }
}