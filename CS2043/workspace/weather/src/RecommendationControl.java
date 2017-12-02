
public class RecommendationControl {
	
	
	public RecommendationControl() {
	}
	
	public String processRecommendation(String gender, int age, String weather) {
		
		return "./images/" + gender + "/" + age + "/" + weather +".png";
	}
}
