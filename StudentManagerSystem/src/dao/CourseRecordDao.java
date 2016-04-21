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

	public static ArrayList<CourseRecordBean> query(int course_number) throws SQLException{
		String sql="select sequence,course_content,type from course_record where course_number=?";
	    
				Object parameters[]={course_number};
		
		return (ArrayList<CourseRecordBean>) MysqlTool.query(sql, parameters, new ListHander(CourseRecordBean.class));
		
	}
	public static void insert(CourseRecordBean courseRecord,int course_number,int course_record_id) throws SQLException{
		String sql="insert into course_record values(?,?,?,?,?) where course_record_id=?";
		Object parameters[]={course_record_id,courseRecord.getSequence(),
				courseRecord.getCourse_content(),courseRecord.getType(),course_number,
				course_record_id};
		MysqlTool.update(sql, parameters);	
	}

}
