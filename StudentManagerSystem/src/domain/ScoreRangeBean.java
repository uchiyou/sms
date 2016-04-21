package domain;

public class ScoreRangeBean {
	
	private int class_number;
	private int Course_record_id;
	public int getCourse_record_id() {
		return Course_record_id;
	}

	public void setCourse_record_id(int course_record_id) {
		Course_record_id = course_record_id;
	}

	private int a;
	private int b;
	private int c;
	private int d;

	public ScoreRangeBean() {
		super();
	}

	public ScoreRangeBean(int course_record_id, int a, int b, int c, int d) {
		super();
		this.class_number = course_record_id;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public int getClass_number() {
		return class_number;
	}

	public void setClass_number(int class_number) {
		this.class_number = class_number;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	

	}