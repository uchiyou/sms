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
			int column=meta.getColumnCount();//获取列的总列数
			for(int i=0;i<column;i++){
				String name=meta.getColumnName(i+1);//得到此列的列名
				Object value=result.getObject(name);
				
				Field f=bean.getDeclaredField(name);//获取反射类所对应的属性
				f.setAccessible(true);//暴力破解
				f.set(b, value);//把bean所对应的属性设置成value
			
			}
			return b;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);	
		}
		
	}
	
}