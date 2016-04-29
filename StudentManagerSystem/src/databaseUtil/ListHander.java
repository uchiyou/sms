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
 * 用于实现表的多条记录返回
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
				int column=meta.getColumnCount();//获取列的总列数
				for(int i=0;i<column;i++){
					String name=meta.getColumnName(i+1);//得到此列的列名
					Object value=result.getObject(name);
					
					Field f=bean.getDeclaredField(name);//获取反射类所对应的属性
					f.setAccessible(true);//暴力破解
					f.set(b, value);//把bean所对应的属性设置成value
				
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
