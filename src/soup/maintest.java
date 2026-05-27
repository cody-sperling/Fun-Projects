package soup;

import java.time.LocalDate;

public class maintest {

	public static void main(String[] args) {
    
		String scores = ScoreParser.pullScores(ScoreParser.getTodaysDate());
		
		Notification.showNotification("Todays Scores",scores,LocalDate.now());
		
		
		

	}

}
