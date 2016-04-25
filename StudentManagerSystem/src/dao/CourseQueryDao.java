package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import databaseUtil.BeanHander;
import databaseUtil.ListHander;
import databaseUtil.MysqlTool;
import domain.CourseBean;

public class CourseQueryDao {
	
	
	public static ArrayList<CourseBean> queryTeacherCourses(String wageNumber) throws SQLException{
		String sql="select * from course where course_number in ( select course_number from course_teacher where wage_number=?)";
		Object parameters[]={wageNumber};
		return (ArrayList<CourseBean>) MysqlTool.query(sql, parameters, new ListHander(CourseBean.class));
	}
	
	public static CourseBean query(int course_number) throws SQLException{
		String sql="select * from course where course_number=?";
		Object parameters[]={course_number};
		return (CourseBean) MysqlTool.query(sql, parameters, new BeanHander(CourseBean.class));	
	}

}
