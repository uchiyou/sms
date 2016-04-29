package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import databaseUtil.ListHander;
import databaseUtil.MysqlTool;
import domain.CourseBean;
import domain.CourseRecordBean;
import domain.TeacherCourseBean;


public class CourseRecordDao {
	// write by uchiyou@ sina.com
	public static ArrayList<CourseRecordBean> query(int course_number) throws SQLException{
		String sql="select * from course_record where course_number=?";	    
				Object parameters[]={course_number};		
		return (ArrayList<CourseRecordBean>) MysqlTool.query(sql, parameters, new ListHander(CourseRecordBean.class));
		
	}
	public static ArrayList<CourseRecordBean> queryAll() throws SQLException{
		String sql="select * from course_record";	    	
		Object parameters[]={};		
		return (ArrayList<CourseRecordBean>) MysqlTool.query(sql, parameters, new ListHander(CourseRecordBean.class));		
	}
	public static void insert(CourseRecordBean courseRecord) throws SQLException{
		String sql="insert into course_record values(?,?,?,?,?)";
		Object parameters[]={courseRecord.getCourse_record_id(),courseRecord.getSequence(),
				courseRecord.getCourse_content(),courseRecord.getType(),courseRecord.getCourse_number()};
		for(Object o:parameters)
		System.out.println("-------->"+o.toString());
		MysqlTool.executeSql(sql, parameters);	
	}

}
 