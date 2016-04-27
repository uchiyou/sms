package dao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.junit.Test;

import databaseUtil.BeanHander;
import databaseUtil.MysqlTool;
import domain.StudentBean;

public class StudentDao {
	

	
	public static void insert(StudentBean student) throws Exception{
		if(student==null)
			throw new Exception("student is null : in SudentDao-line 18");
		
		/*String sql="insert into student values('?','?','?','?','?','?')";*/
		String sql="insert into student values(?,?,?,?,?,?)";
		Object parameters[]={student.getStu_number(),student.getClass_number(),
				student.getName(),student.getSex(),
				student.getStu_type(),student.getPassword()};
		MysqlTool.executeSql(sql, parameters);
	}
	
	public static StudentBean query(String stuNumber) throws SQLException{
		if(stuNumber==null) 
			return null;
		String sql="select * from student where stu_number=?";
		Object parameters[]={stuNumber};
	 
		return (StudentBean)MysqlTool.query(sql, parameters, new BeanHander(StudentBean.class));
	}
	public static void update(StudentBean student) throws SQLException, UnsupportedEncodingException{
		if(student==null)
			return;
		String sql="update student set stu_number=?,class_number=?,name=?,sex=?,stu_type=?,password=? where stu_number=?";
		Object parameters[]={student.getStu_number(),
				student.getClass_number(),student.getName(),
				student.getSex(),student.getStu_type(),student.getStu_number(),student.getPassword()};
		MysqlTool.executeSql(sql, parameters);
	}
	public static void delete(String stuNumber) throws SQLException{
		String sql="delete from student where stu_number=?";
		Object parameters[]={stuNumber};
		MysqlTool.executeSql(sql, parameters);
	}
	

}
