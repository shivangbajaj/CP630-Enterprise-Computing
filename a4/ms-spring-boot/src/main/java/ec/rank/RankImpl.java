package ec.rank;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import ec.grade.Grade;

@Component
public class RankImpl implements Rank {
	private String name;
	private Integer[] scores = { 71, 71, 85, 70, 85, 99, 70, 79, 89, 83, 96, 85, 82, 84, 96, 77, 89, 81, 71, 90, 89, 71, 99, 99, 84, 74, 90, 75, 73, 86 };
	private int count;
	private Grade grade;

	public RankImpl() {
		Arrays.sort(scores, Collections.reverseOrder());
		count = scores.length;
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getScores() {
		return scores;
	}

	public void setScores(Integer[] scores) {
		this.scores = scores;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}


	@Override
	public int getRank(int s) {
        int index = Arrays.binarySearch(scores, s, Collections.reverseOrder());

        if (index >= 0) {
            // Adjust for duplicate scores
            while (index > 0 && scores[index - 1].equals(scores[index])) {
                index--;
            }
            return index + 1;
        } else {
            // Adjust for insertion point
            return -index;
        }
	}

	@Override
	public String getGrade(int score) {
		String gradeRecd = grade.getLetterGrade(score);
		return gradeRecd;
	}

}
