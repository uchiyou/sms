package domain;

public class CourseRecordBean {
	// write by uchiyou
	private int course_record_id;
	private int sequence;
	private String course_content;
	private String type;
	private int course_number;
	

	public CourseRecordBean() {
		super();
	}
	

	public CourseRecordBean(int sequence, String course_content, String type) {
		super();
		this.sequence = sequence;
		this.course_content = course_content;
		this.type = type;
	}


	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getCourse_content() {
		return course_content;
	}

	public void setCourse_content(String course_content) {
		this.course_content = course_content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public int getCourse_record_id() {
		return course_record_id;
	}


	public void setCourse_record_id(int course_record_id) {
		this.course_record_id = course_record_id;
	}


	public int getCourse_number() {
		return course_number;
	}


	public void setCourse_number(int course_number) {
		this.course_number = course_number;
	}
	

}