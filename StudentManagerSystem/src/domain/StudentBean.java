package domain;

public class StudentBean {
	private String stu_number;
	private int class_number;
	private String name;
	private String sex;
	private String stu_type;
	private String password;
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public StudentBean() {
		super();
	}
	public StudentBean(String stuNumber, int classNumber, String name,
			String sex, String stuType) throws Exception {
		super();
		this.stu_number = stuNumber;
		this.class_number = classNumber;
		this.name = name;
		if(sex.equals("male")||sex.equals("female")||sex.equals("others"))
		this.sex = sex;
		else throw new Exception("student sex is error");
		if(stuType.equals("postgraduate")||stuType.equals("undergraduate"))
			this.stu_type = stuType;
			else throw new Exception("student type in student errors");
	}
	public String getStuNumber() {
		return stu_number;
	}
	public void setStuNumber(String stuNumber) throws Exception {
		if(!stuNumber.matches("[0|1|2]([0-9]{10})")){
			throw new Exception("wageNumber illegel : in CourseQueryDao 17 lines");
		}
		this.stu_number = stuNumber;
	}
	public int getClassNumber() {
		return class_number;
	}
	public void setClassNumber(int classNumber) {
		this.class_number = classNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) throws Exception {
		if("male".equals(sex)||"female".equals(sex)||"others".equals(sex)){
			this.sex = sex;
	}else{ 
		       System.out.println("error------->"+sex);
				throw new Exception("student sex is error");
			}
	}
	
	public String getStuType() {
		return stu_type;
	}
	public void setStuType(String stuType) throws Exception {
		if(stuType.equals("postgraduate")||stuType.equals("undergraduate"))
			this.stu_type = stuType;
			else throw new Exception("student type in student errors");
	}

}
