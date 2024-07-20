package ec.lab;

public class MyGrade 
{
    public static void main( String[] args )
    {
        Grade grade = new ECGrade();
        System.out.println(grade.getGrade(92));
        System.out.println(grade.getGrade(88));
        System.out.println(grade.getGrade(83));
        System.out.println(grade.getGrade(78));
        System.out.println(grade.getGrade(75));
        System.out.println(grade.getGrade(72));
        System.out.println(grade.getGrade(69));
        System.out.println(grade.predictPass(69));
        System.out.println(grade.predictPass(70));
    }
}