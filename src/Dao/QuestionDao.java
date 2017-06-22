package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import dbutil.SQLHelper;



public class QuestionDao {
	private  String driver = "com.mysql.jdbc.Driver";
	private  String url = "jdbc:mysql://localhost:3306/examination", user = "root", pwd = "";
	public QuestionDao(){
		try {
			Class.forName(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} 
public int addSelectQuestion(String qtype,String qtitle,String qa,String qb,
		   String qc,String qd,String qanswer){
	   int r=0;
	   try{
		   //Class.forName(driver);
		   Connection conn=DriverManager.getConnection(url,user,pwd);
		   Statement cmd=conn.createStatement();
		   String sql="insert into t_timu(qtype,qtitle,qa,qb,qc,qd,qanswer)values('"+qtype+"','"+qtitle+"','"+qa+"','"+qb+"','"+qc+"','"+qd+"','"+qanswer+"')";
		   System.out.print(sql);
		   r=cmd.executeUpdate(sql);
		   conn.close();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	   return r;
   }

public String[][] queryAllquestions() {
	// TODO Auto-generated method stub
	int r=queryRows();
	String[][] allquestions=new String[r][7];
	try{
		Connection conn=DriverManager.getConnection(url,user,pwd);
		String sql="select * from t_timu";
		Statement cmd=conn.createStatement();
		ResultSet rs=cmd.executeQuery(sql);
		int c=0;
		while (rs.next()) {
			for (int i = 0; i < 7; i++)
				allquestions[c][i] = rs.getString(i + 1);
				c++;
			}
		   conn.close();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	return allquestions;
}

private int queryRows() {
	int r = 0;
	try {
		Connection con = DriverManager.getConnection(url,user, pwd);
		String sql = "select count(*) from t_timu";
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

private int queryPaperRows() {
	int r = 0;
	try {
		Connection con = DriverManager.getConnection(url,user, pwd);
		String sql = "select count(*) from t_shiti";
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

public void deleteQuestionByqtitle(String qtitle) {
	// TODO Auto-generated method stub
	try {
		Connection con = DriverManager.getConnection(url,user, pwd);
		String sql = "delete from t_timu where qtitle=?";
		PreparedStatement cmd = con.prepareStatement(sql);
		cmd.setString(1, qtitle);
		cmd.executeUpdate();
		con.close();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
}

public String[] queryQuestionById(int qid) {
	// TODO Auto-generated method stub
	String[] question=new String[8];
	   try{
		   String sql="select * from questions where qid="+qid;
		   ResultSet rs=  SQLHelper.executeQuery(sql);
		   if(rs.next()){
			   for(int i=0;i<question.length;i++)
				   question[i]=rs.getString(i+1);
		   }
		   SQLHelper.closeConnection();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	   return question;
}

public int addQuestiontoPaper(String qtype,String qtitle,String qa,String qb,
		   String qc,String qd,String qanswer){
	   int r=0;
	   int id=queryPaperRows()+1;
	   try{
		   Connection conn=DriverManager.getConnection(url,user,pwd);
		   Statement cmd=conn.createStatement();
		   String sql="insert into t_shiti(id,qtype,qtitle,qa,qb,qc,qd,qanswer)values('"+id+"','"+qtype+"','"+qtitle+"','"+qa+"','"+qb+"','"+qc+"','"+qd+"','"+qanswer+"')";
		   //String sql="UPDATE t_shiti SET qtype='"+qtype+"',qtitle='"+qtitle+"',qa='"+qa+"',qb='"+qb+"',qc='"+qc+"',qd='"+qd+"',qanswer='"+qanswer+"'WHERE id='1'";
		   System.out.print(sql);
		   r=cmd.executeUpdate(sql);
		   conn.close();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	   return r;
}

public String[][] queryPaper() {
	// TODO Auto-generated method stub
	int r=queryPaperRows();
	String[][] allpaperquestions=new String[r][8];
	try{
		Connection conn=DriverManager.getConnection(url,user,pwd);
		String sql="select * from t_shiti";
		Statement cmd=conn.createStatement();
		ResultSet rs=cmd.executeQuery(sql);
		int c=0;
		while (rs.next()) {
			for (int i = 0; i < 8; i++)
				allpaperquestions[c][i] = rs.getString(i + 1);
				c++;
			}
		   conn.close();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	return allpaperquestions;
}

public int addBlankQuestion(String type, String blank_qtitle,
		String blank_qanswer) {
	int r=0;
	   try{
		   Connection conn=DriverManager.getConnection(url,user,pwd);
		   Statement cmd=conn.createStatement();
		   String sql="insert into t_timu(qtype,qtitle,qanswer)values('"+type+"','"+blank_qtitle+"','"+blank_qanswer+"')";
		   System.out.print(sql);
		   r=cmd.executeUpdate(sql);
		   conn.close();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	   return r;
}

public int addJudgeQuestion(String type, String judge_qtitle,
		String judge_qanswer) {
	int r=0;
	   try{
		   Connection conn=DriverManager.getConnection(url,user,pwd);
		   Statement cmd=conn.createStatement();
		   String sql="insert into t_timu(qtype,qtitle,qanswer)values('"+type+"','"+judge_qtitle+"','"+judge_qanswer+"')";
		   System.out.print(sql);
		   r=cmd.executeUpdate(sql);
		   conn.close();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	   return r;}

public int addAnswerQuestion(String type, String blank_qtitle,
		String answer_qanswer) {
	// TODO Auto-generated method stub
	int r=0;
	   try{
		   Connection conn=DriverManager.getConnection(url,user,pwd);
		   Statement cmd=conn.createStatement();
		   String sql="insert into t_timu(qtype,qtitle,qanswer)values('"+type+"','"+blank_qtitle+"','"+answer_qanswer+"')";
		   System.out.print(sql);
		   r=cmd.executeUpdate(sql);
		   conn.close();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	   return r;
}

public void deletePaperQuestionByqtitle(String qtitle) {
	// TODO Auto-generated method stub
	try {
		Connection con = DriverManager.getConnection(url,user, pwd);
		String sql = "delete from t_shiti where qtitle=?";
		PreparedStatement cmd = con.prepareStatement(sql);
		cmd.setString(1, qtitle);
		cmd.executeUpdate();
		con.close();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
}


}
