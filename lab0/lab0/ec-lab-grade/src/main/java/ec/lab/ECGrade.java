package ec.lab;

public class ECGrade implements Grade {
    public String getGrade(float grade) {
        String result = "F";
        if (grade >= 90.00) result = "A+";
        else if (grade >= 85.00) result = "A";
        else if (grade >= 80.00) result = "A-";
        else if (grade >= 77.00) result = "B+";
        else if (grade >= 73.00) result = "B";
        else if (grade >= 70.00) result = "B-";
        return result;      
    }

    public String predictPass(float grade) {
        if (grade >= 70.00) return "pass";
        else return "fail";     
    }
}