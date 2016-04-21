package domain;

public class StudentCourseScoreBean {
	private String stu_number;
	private int ordinary_work_score;
	private int main_work_score;
	private int attendance_score;
	private int orderScore;//平时成绩应该是三个成绩计算而得，而不是从数据库中获取
	private int test_score;
	
	public StudentCourseScoreBean() {
		super();
	}
	

	public StudentCourseScoreBean(String stu_number, int ordinary_work_score,
			int main_work_score, int attendance_score, int orderScore,
			int test_score) {
		super();
		this.stu_number = stu_number;
		this.ordinary_work_score = ordinary_work_score;
		this.main_work_score = main_work_score;
		this.attendance_score = attendance_score;
		this.orderScore = orderScore;
		this.test_score = test_score;
	}


	public String getStu_number() {
		return stu_number;
	}

	public void setStu_number(String stu_number) {
		this.stu_number = stu_number;
	}

	public int getOrdinary_work_score() {
		return ordinary_work_score;
	}

	public void setOrdinary_work_score(int ordinary_work_score) {
		this.ordinary_work_score = ordinary_work_score;
	}

	public int getMain_work_score() {
		return main_work_score;
	}

	public void setMain_work_score(int main_work_score) {
		this.main_work_score = main_work_score;
	}

	public int getAttendance_score() {
		return attendance_score;
	}

	public void setAttendance_score(int attendance_score) {
		this.attendance_score = attendance_score;
	}

	public int getOrderScore() {
		return orderScore;
	}

	public void setOrderScore(int orderScore) {
		this.orderScore = orderScore;
	}

	public int getTest_score() {
		return test_score;
	}

	public void setTest_score(int test_score) {
		this.test_score = test_score;
	}

}