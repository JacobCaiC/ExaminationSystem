package UI;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Dao.ExamDao;
import Dao.QuestionDao;

public class ExamFrm1 extends JPanel {
	private static final long serialVersionUID=1L;
	private ExamDao examDao=new ExamDao();
	private JButton btnstart=new JButton("开始答题");
	private JButton btnForward=new JButton("上一题");
    private JButton btnBack=new JButton("下一题");
    private JLabel lblid=new JLabel();
    private JTextField txtid=new JTextField(5);
    public int id1;
    private JLabel lblTitle=new JLabel("题目：");
    private JTextArea txtTitle=new JTextArea(10,50);
	private JLabel lblA=new JLabel("选项A");
	private JTextField txtA = new JTextField(30);
	private JLabel lblB=new JLabel("选项B");
    private JTextField txtB = new JTextField(30);
	private JLabel lblC=new JLabel("选项C");
	private JTextField txtC = new JTextField(30);
	private JLabel lblD=new JLabel("选项D");
    private JTextField txtD = new JTextField(30);
	private JLabel lblAnswer=new JLabel("答案");
	private JComboBox cmbAnswer=new JComboBox(new String[]{"A","B","C","D"});
    //private JComboBox cmbAnswer=new JComboBox(new String[]{"A","B","C","D","AB",
    private String[] exam1=new String[5];  
	LoginFrm loginFrm;
    
    public ExamFrm1(){
    	this.setLayout(new BorderLayout());
		JPanel topPane=new JPanel();         
		topPane.add(lblid);//topPane.add(txtid);
		topPane.add(lblTitle);topPane.add(txtTitle);
			   
		JPanel centerPane=new JPanel();
		centerPane.setLayout(new GridLayout(6,2));
		centerPane.add(lblA);centerPane.add(txtA);
		centerPane.add(lblB);centerPane.add(txtB);
	    centerPane.add(lblC);centerPane.add(txtC);
		centerPane.add(lblD);centerPane.add(txtD);
		centerPane.add(lblAnswer);centerPane.add(cmbAnswer);
		JSplitPane splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,topPane,centerPane);
		
		this.add(splitPane);
		
		JPanel btnPane=new JPanel();
		btnPane.add(btnstart);
		btnPane.add(btnForward);btnPane.add(btnBack);
		this.add(btnPane,BorderLayout.SOUTH);
		
		btnstart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnstart_clicked();
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnBack_Clicked();
			}

		});
		
		btnForward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
						btnForward_Clicked();
					}
				});
		   }
    
		protected void btnstart_clicked() {
		// TODO Auto-generated method stub

			id1=1;txtid.setText(id1+"");
			lblid.setText("题号:"+id1);
			exam1=examDao.updateEaxm1ByID(id1);
			txtTitle.setText(exam1[0]);
		    txtA.setText(exam1[1]);
		    txtB.setText(exam1[2]);
		    txtC.setText(exam1[3]);
		    txtD.setText(exam1[4]);
			
			
	}

		protected void btnBack_Clicked() {
			btnForward.setEnabled(true);
			String stu_id=loginFrm.uid;
			String timu_answer=(cmbAnswer.getSelectedItem().toString());
			String timu_id=txtid.getText();lblid.setText("题号:"+id1);
			examDao.updateAnswertoPaper(stu_id, timu_id, txtTitle.getText(), txtA.getText(), txtB.getText(), txtC.getText(), txtD.getText(), timu_answer);
			id1++;txtid.setText(id1+"");lblid.setText("题号:"+id1);
			//System.out.print(examDao.queryTypeNums("单选题"));
			if (id1<=examDao.queryTypeNums("单选题")) {
				exam1=examDao.updateEaxm1ByID(id1);
				txtTitle.setText(exam1[0]);
			    txtA.setText(exam1[1]);
			    txtB.setText(exam1[2]);
			    txtC.setText(exam1[3]);
			    txtD.setText(exam1[4]);
			}else {
				id1--;txtid.setText(id1+"");lblid.setText("题号:"+id1);
				JOptionPane.showMessageDialog(null, "单选题已做完");
				btnBack.setEnabled(false);
			}
			
		}

		private void btnForward_Clicked(){
			btnBack.setEnabled(true);
			 id1--;txtid.setText(id1+"");lblid.setText("题号:"+id1);
			 if (id1>0) {
				 exam1=examDao.updateEaxm1ByID(id1);
					txtTitle.setText(exam1[0]);
				    txtA.setText(exam1[1]);
				    txtB.setText(exam1[2]);
				    txtC.setText(exam1[3]);
				    txtD.setText(exam1[4]);
				    if (id1==1) {
						btnForward.setEnabled(false);
					}
			}
			 
		   }
		   
	 
	
	
}

