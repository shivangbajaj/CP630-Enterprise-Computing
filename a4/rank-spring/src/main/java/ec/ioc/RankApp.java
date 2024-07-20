package ec.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ec.rank.Rank;
import ec.rank.RankImpl;

public class RankApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("RankBeans.xml");

        Rank rank = (Rank) context.getBean("rank-bean");

        // Get ranks and letter grades for the scores in RankImpl
        Integer[] scores = ((RankImpl) rank).getScores();
        for (int i = 0; i < scores.length; i++) {
            int score = scores[i];
            int rankPosition = rank.getRank(score);
            String letterGrade = rank.getGrade(score);
            System.out.println("score:" + score + ", rank:" + rankPosition + ", grade:" + letterGrade);
        }

        // Predict the rank and grade for a given score (e.g., 76)
        int predictionScore = 76; 
        int predictionRank = rank.getRank(predictionScore);
        String predictionGrade = rank.getGrade(predictionScore);
        System.out.println("Prediction for score:" + predictionScore);
        System.out.println("rank:" + predictionRank + ", grade:" + predictionGrade);
    }
}
