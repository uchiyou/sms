package databaseUtil;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;



public class MysqlTool {
	private static String driverClass=null;
	private static String url=null;
	private static String userName=null;
	private static String psw=null;
	static{
		try{
		InputStream input=MysqlTool.class.getClassLoader().getResourceAsStream("db.properties");
		Properties properties=new Properties();
		properties.load(input);
		
		driverClass=properties.getProperty("driverClass");
		url=properties.getProperty("url");
		userName=properties.getProperty("userName");
		psw=properties.getProperty("psw");
		
		Class.forName(driverClass);
		
		}catch(Exception e){
			e.printStackTrace();
		//	throw new ExceptionInInitializerError();
		}
	
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,userName,psw);
	}
	public static void release(Connection con,Statement state,ResultSet result){
		
		if(result!=null)
			try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(state!=null)
			try {
				state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void executeSql(String sql,Object[] parameters) throws SQLException{
		Connection con=null;
		PreparedStatement state=null;
		
		try{
			con=getConnection();
			state=con.prepareStatement(sql);
			
			for(int i=0;i<parameters.length;++i){
				state.setObject(i+1, parameters[i]);
			}
			state.execute();//ִ��sql��䣬����������ɾ������
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(state!=null)
				state.close();
		}

	}
	
	public static Object query(String sql,Object[] parameters,ResultSetHander rsh) throws SQLException{
		Connection con=null;
		PreparedStatement state=null;
		ResultSet result=null;
		try{
			con=getConnection();
			state=con.prepareStatement(sql);
			for(int i=0;i<parameters.length;i++){
				//	System.out.println(parameters[i]);
				state.setObject(i+1, parameters[i]);//������óɹ��ˣ�Ϊʲô���ص���һ���յ�resultset
			}		
			return rsh.hander(state.executeQuery());
		}finally{
			release(con,state,result);
		}
		}
	
	
	public static HashMap<String,Object> queryMap(String sql,Object[] parameters) throws SQLException{
		
		Connection con=null;
		PreparedStatement state=null;
		ResultSet result=null;
		try{
			con=getConnection();
			state=con.prepareStatement(sql);
			for(int i=0;i<parameters.length;i++){
				//	System.out.println(parameters[i]);
				state.setObject(i+1, parameters[i]);//������óɹ��ˣ�Ϊʲô���ص���һ���յ�resultset
			}
			
			
            result=state.executeQuery();

			if(!result.next()){
				return null;
			}
			HashMap<String,Object> map=new HashMap<String,Object>();
			
			ResultSetMetaData meta=result.getMetaData();
			int column=meta.getColumnCount();//��ȡ�е�������
			for(int i=0;i<column;i++){
				String name=meta.getColumnName(i+1);//�õ����е�����
				Object value=result.getObject(name);
				map.put(name, value);
			}
			return map;
		}finally{
			release(con,state,result);
		}
		
		
	}
	public static ArrayList<Object> queryList(String sql,Object[] parameters) throws SQLException{
		
		Connection con=null;
		PreparedStatement state=null;
		ResultSet result=null;
		try{
			con=getConnection();
			state=con.prepareStatement(sql);
			for(int i=0;i<parameters.length;i++){
				//	System.out.println(parameters[i]);
				state.setObject(i+1, parameters[i]);//������óɹ��ˣ�Ϊʲô���ص���һ���յ�resultset
			}
			
			
			result=state.executeQuery();
			
			if(!result.next()){
				return null;
			}
			ArrayList<Object> list=new ArrayList<Object>();
			
			ResultSetMetaData meta=result.getMetaData();
			int column=meta.getColumnCount();//��ȡ�е�������
			for(int i=0;i<column;i++){
				String name=meta.getColumnName(i+1);//�õ����е�����
				Object value=result.getObject(name);
				list.add(value);
			}
			return list;
		}finally{
			release(con,state,result);
		}
		
		
	}
	
	
	static interface ResultSetHander{//�˽ӿ��������ṩ���书�ܵ�
		Object hander(ResultSet result);
	}}