package domain;

public class TeacherCourseBean {
	
	// write by- uchiyou@sina.com
	private int course_number;
	private String wage_number;
	public int getCourse_number() {
		return course_number;
	}
	public void setCourse_number(int course_number) {
		this.course_number = course_number;
	}
	public String getWage_number() {
		return wage_number;
	}
	public void setWage_number(String wage_number) {
		this.wage_number = wage_number;
	}
	public TeacherCourseBean(int course_number, String wage_number) {
		super();
		this.course_number = course_number;
		this.wage_number = wage_number;
	}
	public TeacherCourseBean() {
		super();
	}
	

}
