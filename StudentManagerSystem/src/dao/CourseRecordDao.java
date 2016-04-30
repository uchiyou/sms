package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import databaseUtil.ListHander;
import databaseUtil.MysqlTool;
import domain.CourseRecordBean;
import domain.ScoreRangeBean;
import domain.TeacherBean;


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
	public static int insert(CourseRecordBean courseRecord, TeacherBean teacher) throws SQLException{
		int course_number=courseRecord.getCourse_number();
		String course_record_relation="insert into course_record_relation(course_number,class_number) values(?,?)";
		ArrayList<ScoreRangeBean> list=TestManagerUtil.queryScoreRangeBean(teacher.getWage_number(), course_number);
	   if(list==null||list.size()<1)
		   return -1;
	   
		ScoreRangeBean bean=list.get(0);
		Object crrp[]={course_number,bean.getClass_number()};
		MysqlTool.executeSql(course_record_relation, crrp);
		
		String sql="insert into course_record values(?,?,?,?,?)";
		Object parameters[]={courseRecord.getCourse_record_id(),courseRecord.getSequence(),
				courseRecord.getCourse_content(),courseRecord.getType(),course_number};
		/*for(Object o:parameters)
		System.out.println("-------->"+o.toString());
*/		MysqlTool.executeSql(sql, parameters);	
		return 0;
	}

}
 