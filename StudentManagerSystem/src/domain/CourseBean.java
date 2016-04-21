package domain;

public class CourseBean {
	
	private int course_number;
	private String course_name;
	private String course_student;
	private String course_duration;
	private int course_score;
	private String course_type;
	private String course_direction;
	
	public CourseBean() {
		super();
	}
	
	
	public CourseBean(int course_number, String course_name,
			String course_student, String course_duration, int course_score,
			String course_type, String course_direction) {
		super();
		this.course_number = course_number;
		this.course_name = course_name;
		this.course_student = course_student;
		this.course_duration = course_duration;
		this.course_score = course_score;
		this.course_type = course_type;
		this.course_direction = course_direction;
	}

	public int getCourse_number() {
		return course_number;
	}
	public void setCourse_number(int course_number) {
		this.course_number = course_number;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_student() {
		return course_student;
	}
	public void setCourse_student(String course_student) {
		this.course_student = course_student;
	}
	public String getCourse_duration() {
		return course_duration;
	}
	public void setCourse_duration(String course_duration) {
		this.course_duration = course_duration;
	}
	public int getCourse_score() {
		return course_score;
	}
	public void setCourse_score(int course_score) {
		this.course_score = course_score;
	}
	public String getCourse_type() {
		return course_type;
	}
	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}
	public String getCourse_direction() {
		return course_direction;
	}
	public void setCourse_direction(String course_direction) {
		this.course_direction = course_direction;
	}
}