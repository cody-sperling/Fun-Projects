package soup;

import java.io.IOException;
import java.time.LocalDate;
import org.jsoup.Jsoup;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ScoreParser {
	
	
	public static String getTodaysDate() {
		
		LocalDate today = LocalDate.now();
    	String todayStr = today.toString();
  
    	StringBuilder sb = new StringBuilder();
    	
    	for (char c : todayStr.toCharArray()) {
    		if (c != '-') {
    			sb.append(c);
    			
    		}
    	}
    	
    	String str = sb.toString(); 
    	return str;
		
	}
	
	public static String convertDate(LocalDate date) {
		StringBuilder sb = new StringBuilder();
		String str = date.toString();
    	for (char c : str.toCharArray()) {
    		if (c != '-') {
    			sb.append(c);
    			
    		}
    	}
    	return sb.toString();
	}
    	
    	public static LocalDate convertString(String date) {
    		StringBuilder sb = new StringBuilder();
    	
    		for (int i = 0; i < date.length(); i++) {
    			
    			if (i==4 || i==6) {
    				sb.append("-");
    			}
    			sb.append(date.charAt(i));
    			
    			}
    	String newstr = sb.toString(); 
    	LocalDate dt = LocalDate.parse(newstr);
    	return dt;
	}
    	
    public static String fixFormat(String date) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < date.length(); i++) {
			
			if (i==4 || i==6) {
				sb.append("-");
			}
			sb.append(date.charAt(i));
			
			}
    	return sb.toString();
    	
    }
	
	public static String yesterday(LocalDate today) {
		LocalDate yesterday = today.minusDays(1);
		
		String yesterdayStr = yesterday.toString();
		  
    	StringBuilder sb = new StringBuilder();
    	
    	for (char c : yesterdayStr.toCharArray()) {
    		if (c != '-') {
    			sb.append(c);
    			
    		}
    		}
    	return sb.toString();	
	}
	
	public static String tomorrow(LocalDate today) {
		LocalDate tomorrow = today.plusDays(1);
		
		String tomorrowStr = tomorrow.toString();
		  
    	StringBuilder sb = new StringBuilder();
    	
    	for (char c : tomorrowStr.toCharArray()) {
    		if (c != '-') {
    			sb.append(c);
    			
    		}
    		}
    	return sb.toString();	
	}
	
	

    public static String pullScores(String date) {

        StringBuilder sb = new StringBuilder();

        String url = "https://site.api.espn.com/apis/site/v2/sports/baseball/college-baseball/scoreboard?dates=" + date;

        try {
            String json = Jsoup.connect(url).ignoreContentType(true).execute().body();

            JsonObject root = JsonParser.parseString(json).getAsJsonObject();

            JsonArray events = root.getAsJsonArray("events");
            if (events == null || events.size() == 0) {
                System.out.println("No events found for date " + date);
                return null;
            }

            for (JsonElement eventEl : events) {
                JsonObject event = eventEl.getAsJsonObject();

                JsonArray competitions = event.getAsJsonArray("competitions");
                if (competitions == null || competitions.size() == 0) continue;

                JsonObject comp = competitions.get(0).getAsJsonObject();

                // Game status
                JsonObject statusObj = comp.getAsJsonObject("status");
                String status = statusObj.getAsJsonObject("type").get("description").getAsString();

                sb.append("STATUS: ").append(status).append("\n");

                // Competitors
                JsonArray competitors = comp.getAsJsonArray("competitors");

                for (JsonElement compEl : competitors) {
                    JsonObject competitor = compEl.getAsJsonObject();
                    String homeAway = competitor.get("homeAway").getAsString();
                    String teamName = competitor.getAsJsonObject("team").get("displayName").getAsString();

                    int score = -1;
                    if (competitor.has("score") && !competitor.get("score").isJsonNull()) {
                        score = competitor.get("score").getAsInt();
                    }

                    sb.append(homeAway.toUpperCase())
                      .append(": ")
                      .append(teamName)
                      .append(" → ")
                      .append(score)
                      .append("\n");
                }

                sb.append("--------------------------------------------------\n");
            }
       


        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return sb.toString();
    }
}