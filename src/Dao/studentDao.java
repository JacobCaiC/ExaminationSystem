package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class studentDao {
	private static  String driver = "com.mysql.jdbc.Driver";
	private static  String url = "jdbc:mysql://localhost:3306/examination";
	private static  String user= "root";
	private static  String pwd= "";
	
	public static  boolean validate2(String uid,String upwd){
		boolean flag=false;
		try{
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,user,pwd);
			Statement cmd=conn.createStatement();
			String sql="select login_pw from t_stu where login_id='"+uid+"'";
			ResultSet rs=cmd.executeQuery(sql);
			   if(rs.next()){
				   if(upwd.equals(rs.getString(1)))
					   flag=true;
			   }
			   conn.close();
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
		   return flag;
	   }
	

}
