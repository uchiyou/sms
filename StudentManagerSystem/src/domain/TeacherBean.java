package domain;

public class TeacherBean {
	
	private String wage_number;
	private String name;
	private String job;
	private String password;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	public void setWage_number(String wageNumber) throws Exception{
		if(!wageNumber.matches("[0-9]{3,11}")){
			throw new Exception("wageNumber illegel : in CourseQueryDao 17 lines");			
		}
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
	/*public void setJob(String job) throws Exception {
		if(job.equals("tutor")||job.equals("instructor")||job.equals("professor")||job.equals("associate professor"))
			this.job = job;
			else throw new Exception("teacher job is error");
	}
	约束已经被放弃
	*/
	public void setJob(String job) throws Exception {
		this.job=job;
	}
	

}
