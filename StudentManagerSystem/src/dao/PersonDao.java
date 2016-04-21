package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import databaseUtil.BeanHander;
import databaseUtil.MysqlTool;
import domain.TeacherBean;

public class PersonDao {
	
/*	@Test
	public void test() throws SQLException{
		TeacherBean teacher=query("123");
		System.out.println(teacher.getName()+"---"+teacher.getWage_number());
	}*/
	public static TeacherBean query(String wageNumber) throws SQLException{
		if(wageNumber==null)
			return null;
		String sql="select * from teacher where wage_Number=?";
		Object parameters[]={wageNumber};
		return (TeacherBean) MysqlTool.query(sql, parameters, new BeanHander(TeacherBean.class));
	}
	public static void update(TeacherBean teacher) throws SQLException{
		if(teacher==null)
			return;
		String sql="update teacher set wage_number=?,name=?,job=? where wage_number=?";
		Object[] parameters={teacher.getWage_number(),teacher.getName(),teacher.getJob(),teacher.getWage_number()};
		MysqlTool.update(sql, parameters);
	}
	public static ArrayList<Integer> getClass(String wage_number) throws SQLException{
		String sql="select class_number from teacher_class where wage_number=?";
		Object[] parameters={wage_number};
		ArrayList<Object> clazz=MysqlTool.queryList(sql, parameters);
		ArrayList<Integer> myclass=new ArrayList<Integer>();
		Iterator<Object> it=clazz.iterator();
		while(it.hasNext()){
			myclass.add((Integer) it.next());
		}
		return myclass;
	}

}
