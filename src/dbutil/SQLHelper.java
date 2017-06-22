package dbutil;

import java.sql.*;

public class SQLHelper {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/examination",user="root",pwd="";
	private static Connection conn=null;
	static/*程序运行前运行，仅一次*/{
		try{
			Class.forName(driver);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/*执行select*/
	public static ResultSet executeQuery(String sql){
		ResultSet rs=null;
		try{
			Connection conn=DriverManager.getConnection(url,user,pwd);
			Statement cmd=conn.createStatement();
			rs=cmd.executeQuery(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rs;
	}
	public static int executeUpdate(String sql){
		int r=0;
		try{
			Connection conn=DriverManager.getConnection(url,user,pwd);
			Statement cmd=conn.createStatement();
			r=cmd.executeUpdate(sql);
			conn.close();
		}catch(Exception ex){
			ex.printStackTrace();

		}
		return r;
	}
	public static void closeConnection() {
		// TODO Auto-generated method stub
		try{
  		    if(conn!=null && !conn.isClosed())
  		    	conn.close();
    	 }catch(Exception ex){
    		 ex.printStackTrace();
    	 }
	}
}