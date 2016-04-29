package databaseUtil;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import databaseUtil.MysqlTool.ResultSetHander;
//---write  by uchiyou@sina.com
/*
 * ����ʵ�ֱ�Ķ�����¼����
 */
public class ListHander implements ResultSetHander{
	Class bean=null;
	
	public ListHander(Class clazz){
		this.bean=clazz;
	}
	
	

	@Override
	public  Object hander(ResultSet result) {
		
		ArrayList list=new ArrayList();
		
		try {
			if(result==null){
				return null;
			}
			while(result.next()){
				Object b=bean.newInstance();
			//	System.out.println(result.getString(1));
				
				ResultSetMetaData meta=result.getMetaData();
				int column=meta.getColumnCount();//��ȡ�е�������
				for(int i=0;i<column;i++){
					String name=meta.getColumnName(i+1);//�õ����е�����
					Object value=result.getObject(name);
					
					Field f=bean.getDeclaredField(name);//��ȡ����������Ӧ������
					f.setAccessible(true);//�����ƽ�
					f.set(b, value);//��bean����Ӧ���������ó�value
				
				}
				list.add(b);
				//list.add(new BeanHander(bean).hander(result));
			}
			return list.size()>0?list:null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
