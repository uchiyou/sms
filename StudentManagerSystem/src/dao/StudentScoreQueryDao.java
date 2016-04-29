package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import databaseUtil.BeanHander;
import databaseUtil.ListHander;
import databaseUtil.MysqlTool;
import domain.StudentCourseScoreBean;

public class StudentScoreQueryDao {
	// write by uchiyou@ sina.com,
	/*@Test
	public void test() throws SQLException{
		query(1000,13011);
	}*/
	//此方法找出了某个学生的course_number成绩
	public static StudentCourseScoreBean query(String stu_number,int course_number) throws SQLException{
		if(stu_number==null||course_number<=0)
			return null;
		String sql="select t.ordinary_work_score,t.main_work_score,t.attendance_score,d.test_score from course_detail_score d,course_score_record t where d.course_score_id=t.course_score_id and d.course_score_id in (select r.course_score_id from course_score_relation r where  r.stu_number=? and r.course_number=?)";
		Object[] parameters={stu_number,course_number};
		StudentCourseScoreBean score= (StudentCourseScoreBean)MysqlTool.query(sql, parameters, new BeanHander(StudentCourseScoreBean.class));
		if(score!=null){
		score.setStu_number(stu_number);
		score.setOrderScore(getOrderScore(score));
		}
		return score;
	}
	public static ArrayList<StudentCourseScoreBean> query(int course_number,int class_number) throws SQLException{
		String sql="select t.ordinary_work_score,t.main_work_score,t.attendance_score,d.test_score,student.stu_number from course_detail_score d,course_score_record t,student where d.course_score_id=t.course_score_id and d.course_score_id in (select r.course_score_id from course_score_relation r where r.course_number=? and r.stu_number in (select student.stu_number from student where student.class_number=?) and student.stu_number in (select student.stu_number from student where student.class_number=?));";
		Object[] parameters={course_number,class_number,class_number};
		ArrayList<StudentCourseScoreBean> score= (ArrayList<StudentCourseScoreBean>)MysqlTool.query(sql, parameters, new ListHander(StudentCourseScoreBean.class));
		if(score!=null){
		Iterator<StudentCourseScoreBean> it=score.iterator();
		while(it.hasNext()){
			StudentCourseScoreBean s=it.next();
			s.setOrderScore(getOrderScore(s));
		}}
		return score==null?new ArrayList<StudentCourseScoreBean>():score;
	}
	private static int getOrderScore(StudentCourseScoreBean score){
		return (score.getAttendance_score()+score.getMain_work_score()+
				score.getOrdinary_work_score())/3;
	}

}
