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
	public StudentBean(String stu_number, int class_number, String name,
			String sex, String stu_type) throws Exception {
		super();
		this.stu_number = stu_number;
		this.class_number = class_number;
		this.name = name;
		if(sex.equals("male")||sex.equals("female")||sex.equals("others"))
		this.sex = sex;
		else throw new Exception("student sex is error");
		if(stu_type.equals("postgraduate")||stu_type.equals("undergraduate"))
			this.stu_type = stu_type;
			else throw new Exception("student type in student errors");
	}
	public String getStu_number() {
		return stu_number;
	}
	public void setStu_number(String stu_number) throws Exception {
		if(!stu_number.matches("[0|1|2]([0-9]{10})")){
			throw new Exception("wageNumber illegel : in CourseQueryDao 17 lines");
		}
		this.stu_number = stu_number;
	}
	public int getClass_number() {
		return class_number;
	}
	public void setClass_number(int class_number) {
		this.class_number = class_number;
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
	
	public String getStu_type() {
		return stu_type;
	}
	public void setStu_type(String stu_type) throws Exception {
		if(stu_type.equals("postgraduate")||stu_type.equals("undergraduate"))
			this.stu_type = stu_type;
			else throw new Exception("student type in student errors");
	}

}
