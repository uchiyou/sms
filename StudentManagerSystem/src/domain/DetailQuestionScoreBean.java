package domain;

public class DetailQuestionScoreBean {
	private int detail_question_id;
	private int score;
	public DetailQuestionScoreBean(int detail_question_id, int score) {
		super();
		this.detail_question_id = detail_question_id;
		this.score = score;
	}
	public DetailQuestionScoreBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDetail_question_id() {
		return detail_question_id;
	}
	public void setDetail_question_id(int detail_question_id) {
		this.detail_question_id = detail_question_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}