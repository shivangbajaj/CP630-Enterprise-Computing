package ec.grade;

import org.springframework.stereotype.Component;

@Component
public class GradeImpl implements Grade {
	private String name;
	private int[] gradeBoundary = { 100, 90, 85, 80, 77, 73, 70, 0 };
	private String[] letterGrade = { "A+", "A", "A-", "B+", "B", "B-", "F" };
	private int count = 8;

	public GradeImpl() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int[] getGradeBoundary() {
		return gradeBoundary;
	}

	public void setGradeBoundary(int[] gradeBoundary) {
		this.gradeBoundary = gradeBoundary;
	}

	public String[] getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(String[] letterGrade) {
		this.letterGrade = letterGrade;
	}

	@Override
	public String getLetterGrade(int numerical_grade) {
	    int index = binarySearch(gradeBoundary, numerical_grade);
	    
	    if (index == -1) {
	        return "Invalid";
	    } else {
	        if (index < gradeBoundary.length) {
	            if (index < letterGrade.length) {
	                return letterGrade[index];
	            } else {
	                return letterGrade[letterGrade.length - 1];
	            }
	        } else {
	            return letterGrade[letterGrade.length - 1];
	        }
	    }
	}

	private int binarySearch(int[] arr, int target) {
	    int left = 0;
	    int right = arr.length - 1;

	    while (left <= right) {
	        int mid = left + (right - left) / 2;
	        if (arr[mid] == target) {
	            return mid == 0 ? mid : mid - 1;
	        } else if (arr[mid] > target && (mid == arr.length - 1 || arr[mid + 1] <= target)) {
	            return mid; 
	        } else if (arr[mid] < target) {
	            right = mid - 1;
	        } else {
	            left = mid + 1;
	        }
	    }

	    return -1; 
	}
}
