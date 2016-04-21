package domain;

public class CourseRecordBean {
	
	
	private int sequence;
	private String course_content;
	private String type;

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
	

}