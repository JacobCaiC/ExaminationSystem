package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExamDao {
	private  String driver = "com.mysql.jdbc.Driver";
	private  String url = "jdbc:mysql://localhost:3306/examination", user = "root", pwd = "";
	int jiaojuanIDisNull;
	public ExamDao(){
		try {
			Class.forName(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} 
	public String[] updateEaxm1ByID(int id){
		   String[] exam1=new String[5];
		   try{
			   Connection conn=DriverManager.getConnection(url,user,pwd);
			   Statement cmd=conn.createStatement();
			   String sql="select qtitle,qa,qb,qc,qd from t_shiti WHERE id='"+id+"'";
			   //System.out.print(sql);
			   ResultSet rs=cmd.executeQuery(sql);
			   while(rs.next()){
			   for (int i = 0,j=1; i < 5; i++,j++){
						exam1[i] = rs.getString(j);
				}
			   }
			   conn.close();
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
		   return exam1;
	   }
	
    public void updateAnswertoPaper(String stu_id,String shiti_id,String shiti_title,String shiti_a,String shiti_b,
    		String shiti_c,String shiti_d,String shiti_answer){
	   try{
		   Connection conn=DriverManager.getConnection(url,user,pwd);
		   Statement cmd=conn.createStatement();
		   //System.out.print(jiaojuanIDisNull(shiti_id));
		   /*if (jiaojuanIDisNull(shiti_id)==1) {
			   String sql="insert into t_jiaojuan(stu_id,shiti_id,shiti_title,shiti_a,shiti_b,shiti_c,shiti_d,shiti_answer)values('"+stu_id+"','"+shiti_id+"','"+shiti_title+"','"+shiti_a+"','"+shiti_b+"','"+shiti_c+"','"+shiti_d+"','"+shiti_answer+"')";
			   cmd.executeUpdate(sql);
			   System.out.print(sql);
		   }else {
			   String sql="UPDATE t_jiaojuan SET stu_id='"+stu_id+"',shiti_id='"+shiti_id+"',shiti_title='"+shiti_title+"',shiti_a='"+shiti_a+"',shiti_b='"+shiti_b+"',shiti_c='"+shiti_c+"',shiti_d='"+shiti_d+"',shiti_answer='"+shiti_answer+"'";
			   cmd.executeUpdate(sql);
			   System.out.print(sql);
		   }*/
		   String sql="UPDATE t_jiaojuan SET stu_id='"+stu_id+"',shiti_id='"+shiti_id+"',shiti_title='"+shiti_title+"',shiti_a='"+shiti_a+"',shiti_b='"+shiti_b+"',shiti_c='"+shiti_c+"',shiti_d='"+shiti_d+"',shiti_answer='"+shiti_answer+"'WHERE shiti_id='"+shiti_id+"'";
		   cmd.executeUpdate(sql);
		   System.out.print(sql);
		   conn.close();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
}
    public int queryTypeNums(String type){
    	int r=0;
    	try {
    		Connection con = DriverManager.getConnection(url,user, pwd);
    		String sql = "select count(*) from t_shiti WHERE qtype='"+type+"'";
    		Statement cmd = con.createStatement();
    		ResultSet rs = cmd.executeQuery(sql);
    		rs.next();
    		r = rs.getInt(1);
    		con.close();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
		return r;
    	
    }
    
    public int jiaojuanIDisNull(String shiti_id){
    	try{
    		Connection conn=DriverManager.getConnection(url,user,pwd);
    		String sql="select * from t_jiaojuan where shiti_id='"+shiti_id+"'";
    		//String sql="select * from t_jiaojuan";
    		
    		Statement cmd=conn.createStatement();
    		ResultSet rs=cmd.executeQuery(sql);
    		while(rs.next()){
    			//int s=Integer.parseInt(shiti_id);
    			if (rs.getString(2)==null) {
					 jiaojuanIDisNull=1;
				}else {
					 jiaojuanIDisNull=0;
				}
    		}
    		   conn.close();
    	   }catch(Exception ex){
    		   ex.printStackTrace();
    	   }
    	return jiaojuanIDisNull;
    }
	public String updateEaxm3ByID(int id3) {
		String exam3 = null;
		   try{
			   Connection conn=DriverManager.getConnection(url,user,pwd);
			   Statement cmd=conn.createStatement();
			   String sql="select qtitle from t_shiti WHERE id='"+id3+"'";
			   //System.out.print(sql);
			   ResultSet rs=cmd.executeQuery(sql);
			   while(rs.next()){
				   exam3=rs.getString(1);
			   }
			   conn.close();
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
		  return exam3;
	}
	
}
