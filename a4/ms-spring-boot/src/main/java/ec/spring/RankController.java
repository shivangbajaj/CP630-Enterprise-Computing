package ec.spring;

import ec.grade.Grade;
import ec.rank.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankController {

    @Autowired
    private Rank rank;

    @Autowired
    private Grade grade;

    @GetMapping("/grade/{score}")
    public String getGrade(@PathVariable int score) {
        return grade.getLetterGrade(score);
    }

    @GetMapping("/rank/{score}")
    public int getRank(@PathVariable int score) {
        return rank.getRank(score);
    }

    @GetMapping("/grade-rank/{score}")
    public String getGradeAndRank(@PathVariable int score) {
        String gradeRecd = grade.getLetterGrade(score);
        int rankRecd = rank.getRank(score);
        return "Grade: " + gradeRecd + ", Rank: " + rankRecd;
    }
}
