package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;

import databaseUtil.BeanHander;
import databaseUtil.ListHander;
import databaseUtil.MysqlTool;
import domain.DetailQuestionScoreBean;
import domain.KnowledgeDistributeBean;
import domain.ScoreRangeBean;

public class TestManagerUtil {
	// write by uchiyou@sina.com,the fellow some function of this system was cut down
	public static HashMap<String,Object> getAllInfo(String wageNumber,int defaultCourseId){
		/*
		 *   the returned HashMap has four  key for follow,
		 *   1 course average scores
		 *   2 the course analyse
		 *   3 knowledge distribute table
		 *   4 the detail question score table
		 *   5 the  score range bean table
		 *   
		 *   ******************
		 *   the map has not all the information for the finally jsp page,
		 *    some data need calculate and completed in the jsp page
		 */
		return null;
		
	}
	
	/*@Test
	public void test() throws SQLException{
	//queryKnowledgeDistribute(1000);
		//queryDetailQuestionScore(1000);
		queryScoreRangeBean("123", 1000);
	}
	*/
	
	
	
	
	//2 the course analyse
	private static HashMap<String,Object> analysePart(String wageNumber,String courseId){
		String sql="";
		Object[] parameters={};
		
		return null;
	}
	
//	 3 the knowledge distribute table
	public static ArrayList<KnowledgeDistributeBean> queryKnowledgeDistribute(int course_number) throws SQLException{
		
		/*
		 * С�������½�������һ�ű��ṩ
		 */
		String sql="select dq.main_question_id,dq.detail_question_number,dq.score,dq.easy_level,dq.type from detail_question dq where dq.main_question_id in (select main_question_id from main_question where course_number=?)";
		Object[] parameters={course_number};
		ArrayList<KnowledgeDistributeBean> distributes=new ArrayList<KnowledgeDistributeBean>();
		ArrayList<KnowledgeDistributeBean> td=(ArrayList<KnowledgeDistributeBean>) MysqlTool.query(sql, parameters, new ListHander(KnowledgeDistributeBean.class));
		if(td!=null)
		distributes.addAll(td);
		
		/*
		 * ��ȡĳһ��С�����ڴ��֪ʶ���½�
		 */
		int size=distributes.size();
		int j=0;
		for(j=0;j<size;++j){
		String getInOutlineSql="select chapter_id from knowledgeinoutline where detail_question_id=?";
	/* ������Ҫ���ƣ���Ϊ���ݿ���γ̵� id ��С��� id Ŀǰû�н���ֱ�ӵ���ϵ��ֻ��һ�ƿγ���С��*****
	 * 
	 * 
	 * */
		Object[] parameters2={j+1};
		
		
		ArrayList<Object> temp=(MysqlTool.queryList(getInOutlineSql, parameters2));
		
		if(temp!=null){
		//����ת������ӵ�bean��
		ArrayList<Integer> tt=new ArrayList<Integer>();
		for(Object i:temp){
			tt.add((Integer) i);
		}
		distributes.get(j).setPartInOutline(tt);
		}
		}
	//	}
		
		
		return distributes.size()>0?distributes:null;
	}
	
	
	
	
  //   4 the detail question score
	public static ArrayList<DetailQuestionScoreBean> queryDetailQuestionScore(int course_id) throws SQLException{
		/*
		 * sql����ȡÿ��С���ƽ���÷֣��ٸ���ÿ��С������ͻ�ȡÿ����Ŀ���͵÷ֱ�
		 * 
		 * �ȸ��ݿγ̺ŵõ��γ̵�����С���id���ڸ���С��id��ȡС���ƽ���÷�
		 */
		String sql="select dqs.detail_question_id,avg(dqs.score) as score from detail_question_score as dqs where dqs.detail_question_id=?";
		ArrayList<DetailQuestionScoreBean> scoreList=new ArrayList<DetailQuestionScoreBean>();
		ArrayList<KnowledgeDistributeBean> list=queryKnowledgeDistribute(course_id);
		if(list==null){
			return null;
		}
		Iterator<KnowledgeDistributeBean> it=list.iterator();
		while(it.hasNext()){
			KnowledgeDistributeBean bean=it.next();
			Object[] parameters={bean.getDetail_question_number()};
			scoreList.add((DetailQuestionScoreBean) MysqlTool.query(sql, parameters, new BeanHander(DetailQuestionScoreBean.class)));	
		}
		
		return scoreList.size()>0?scoreList:null;
		
	}
	
	
	
	
	
	// 5 the  score range bean table
	public static ArrayList<ScoreRangeBean> queryScoreRangeBean(String wageNumber,int course_number) throws SQLException{
		if(wageNumber==null||course_number<=0)
			return null;
		/*
		 * 
���ݹ��ʺźͿγ̺Ų�ѯ�༶��ĳ���γ̵÷�
1 �ҳ���Ӧ�İ༶��
2 ������Ӧ�İ༶�źͿγ̺��ҳ��γ̼�¼�����ݿγ̼�¼�ҳ��༶��ƽ���ɼ�
1
select c.class_number from class c where c.class_number in (select tc.class_number from teacher_class tc where tc.wage_number='123') and c.class_number in (select class_number from course_record_relation as crr where crr.course_number=1000)

2
select a,b,c,d from course_score_range as cs,class where cs.course_record_id in (select crr.course_record_id from course_record_relation as crr where crr.course_number=1000 and crr.class_number=13011)
		 */
		ArrayList<ScoreRangeBean> list=new ArrayList<ScoreRangeBean>();
		String sqlClass="select c.class_number from class c where c.class_number in (select tc.class_number from teacher_class tc where tc.wage_number=?) and c.class_number in (select class_number from course_record_relation as crr where crr.course_number=?)";
		Object[] parameters={wageNumber,course_number};
		ArrayList<Object> classList=MysqlTool.queryList(sqlClass, parameters);
		if(classList==null){
			return  null;
		}
		Iterator<Object> it=classList.iterator();
		while(it.hasNext()){
			Integer class_number=(Integer) it.next();
			String sqlScore="select course_record_id,a,b,c,d from course_score_range as cs,class where cs.course_record_id in (select crr.course_record_id from course_record_relation as crr where crr.course_number=? and crr.class_number=?)";
		    Object[] parameters2={course_number,class_number};
		   ScoreRangeBean sbean= (ScoreRangeBean) MysqlTool.query(sqlScore, parameters2, new BeanHander(ScoreRangeBean.class));
		   if(sbean==null)
			   continue;
		   sbean.setClass_number(class_number);
		    list.add(sbean);
		}
		if(list==null){
			return null;
		}
		
		return list.size()>0?list:null;
		
	}

}
