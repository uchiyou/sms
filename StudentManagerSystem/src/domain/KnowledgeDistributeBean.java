package domain;

import java.util.List;

public class KnowledgeDistributeBean {
	
	private int main_question_id;
	private int detail_question_number;
	private List<Integer> partInOutline;
	private int score;
	private String easy_level;
	private String type;
	
	public KnowledgeDistributeBean() {
		super();
	}

	public KnowledgeDistributeBean(int main_question_id,
			int detail_question_number, int score, String easy_level,
			String type) {
		super();
		this.main_question_id = main_question_id;
		this.detail_question_number = detail_question_number;
		this.score = score;
		this.easy_level = easy_level;
		this.type = type;
	}

	public int getMain_question_id() {
		return main_question_id;
	}

	public void setMain_question_id(int main_question_id) {
		this.main_question_id = main_question_id;
	}

	public int getDetail_question_number() {
		return detail_question_number;
	}

	public void setDetail_question_number(int detail_question_number) {
		this.detail_question_number = detail_question_number;
	}

	public List<Integer> getPartInOutline() {
		return partInOutline;
	}

	public void setPartInOutline(List<Integer> partInOutline) {
		this.partInOutline = partInOutline;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getEasy_level() {
		return easy_level;
	}

	public void setEasy_level(String easy_level) {
		this.easy_level = easy_level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	}