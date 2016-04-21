package domain;

public class TeacherBean {
	
	private String wage_number;
	private String name;
	private String job;
	
	
	public TeacherBean() {
		super();
	}
	public TeacherBean(String wageNumber, String name, String job) throws Exception {
		super();
		this.wage_number = wageNumber;
		this.name = name;
		if(job.equals("tutor")||job.equals("instructor")||job.equals("professor")||job.equals("associate professor"))
		this.job = job;
		else throw new Exception("teacher job is error");
	}
	public String getWage_number() {
		return wage_number;
	}
	public void setWage_number(String wageNumber) {
		this.wage_number = wageNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) throws Exception {
		if(job.equals("tutor")||job.equals("instructor")||job.equals("professor")||job.equals("associate professor"))
			this.job = job;
			else throw new Exception("teacher job is error");
	}
	

}
