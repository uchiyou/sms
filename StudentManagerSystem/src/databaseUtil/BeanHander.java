package databaseUtil;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import databaseUtil.MysqlTool.ResultSetHander;

public class BeanHander implements ResultSetHander{
	private Class bean=null;
	
	public BeanHander(Class bean){
		this.bean=bean;
	}

	@Override
	public Object hander(ResultSet result) {

        try {
			if(!result.next()){
				return null;
			}
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
			return b;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);	
		}
		
	}
	
}