package dao;

import java.sql.SQLException;

import org.junit.Test;

import databaseUtil.BeanHander;
import databaseUtil.MysqlTool;
import domain.StudentBean;

public class StudentDao {
	

	
	public static void insert(StudentBean student) throws SQLException{
		if(student==null)
			return;
		String sql="insert into student values('?','?','?','?','?')";
		Object parameters[]={student.getStuNumber(),
				student.getClassNumber(),student.getName(),
				student.getSex(),student.getStuType()};
		MysqlTool.update(sql, parameters);
	}
	
	public static StudentBean query(String stuNumber) throws SQLException{
		if(stuNumber==null) 
			return null;
		String sql="select * from student where stu_number=?";
		Object parameters[]={stuNumber};
	 
		return (StudentBean)MysqlTool.query(sql, parameters, new BeanHander(StudentBean.class));
	}
	public static void update(StudentBean student) throws SQLException{
		if(student==null)
			return;
		String sql="update student set stu_number=?,class_number=?,name=?,sex=?,stu_type=? where stu_number=?";
		Object parameters[]={student.getStuNumber(),
				student.getClassNumber(),student.getName(),
				student.getSex(),student.getStuType(),student.getStuNumber()};
		MysqlTool.update(sql, parameters);
	}
/*	public static void delete(String stuNumber) throws SQLException{
		String sql="delete from student where stu_number=?";
		Object parameters[]={stuNumber};
		MysqlTool.update(sql, parameters);
	}
	若要删除一个学生，需要先删除所有对它有依赖关系的表
	*/

}
