package Dao;

import java.sql.*;

import javax.swing.JPasswordField;


public class teacherDao {
	private  String driver = "com.mysql.jdbc.Driver";
	private  String url = "jdbc:mysql://localhost:3306/examination";
	private  String user= "root";
	private  String pwd= "";
	
	public boolean validate1(String uid,String upwd){
		boolean flag=false;
		try{
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,user,pwd);
			Statement cmd=conn.createStatement();
			String sql="select login_pw from t_tea where login_id='"+uid+"'";
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
