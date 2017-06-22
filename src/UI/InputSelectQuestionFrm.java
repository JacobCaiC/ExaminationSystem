package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Dao.QuestionDao;

public class InputSelectQuestionFrm extends JPanel {
	private static final long serialVersionUID=1L;
	private JButton btnAdd=new JButton("增加");
    private JButton btnCancel=new JButton("重置");
    private JLabel lblTitle=new JLabel("题目");
    private JTextArea txtTitle=new JTextArea(10,50);
    private JComboBox cmbType=new JComboBox(new String[]{"单选题","多选题"});
	private JLabel lblA=new JLabel("选项A");
	private JTextField txtA = new JTextField(30);
	private JLabel lblB=new JLabel("选项B");
    private JTextField txtB = new JTextField(30);
	private JLabel lblC=new JLabel("选项C");
	private JTextField txtC = new JTextField(30);
	private JLabel lblD=new JLabel("选项D");
    private JTextField txtD = new JTextField(30);
	private JLabel lblAnswer=new JLabel("答案");
    private JComboBox cmbAnswer=new JComboBox(new String[]{"A","B","C","D","AB",
    		"AC","AD","BC","BD","CD","ABC","ABD","ACD","BCD","ABCD"});
   
    public InputSelectQuestionFrm(){
    	this.setLayout(new BorderLayout());
		JPanel topPane=new JPanel();
		topPane.add(lblTitle);topPane.add(txtTitle);topPane.add(cmbType);
			   
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
		btnPane.add(btnAdd);btnPane.add(btnCancel);
		this.add(btnPane,BorderLayout.SOUTH);
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnCancel_Clicked();
			}

		});
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
						btnAdd_Clicked();
					}
				});
		   }
    
		protected void btnCancel_Clicked() {
		// TODO Auto-generated method stub
		txtTitle.setText("");
		txtA.setText("");
		txtB.setText("");
		txtC.setText("");
		txtD.setText("");
	}

		private void btnAdd_Clicked(){
			   String type=cmbType.getSelectedItem().toString();
			   if (type.equals("单选题")) {
			   String singleselect_qtitle=txtTitle.getText();
			   String singleselect_qa=txtA.getText();
			   String singleselect_qb=txtB.getText();
			   String singleselect_qc=txtC.getText();
			   String singleselect_qd=txtD.getText();
			   String singleselect_qanswer=cmbAnswer.getSelectedItem().toString();
			   //int qtype=1;//1:单选题2：多选 3：判断4：填空5：简答
			   QuestionDao questionDao1=new QuestionDao();
			   int r=questionDao1.addSelectQuestion(type, singleselect_qtitle, 
					   singleselect_qa, singleselect_qb, singleselect_qc, 
					   singleselect_qd, singleselect_qanswer);
			   if(r>0)
				   JOptionPane.showMessageDialog(this, "单选题录入成功!");
			   else {
				   JOptionPane.showMessageDialog(this, "fail");
				   }
			   }
			   if (type.equals("多选题")) {
				   String doubleselect_qtitle=txtTitle.getText();
				   String doubleselect_qa=txtA.getText();
				   String doubleselect_qb=txtB.getText();
				   String doubleselect_qc=txtC.getText();
				   String doubleselect_qd=txtD.getText();
				   String doubleselect_qanswer=cmbAnswer.getSelectedItem().toString();
				   //String qtype="多选题";//1:单选题2：多选 3：判断4：填空5：简答
				   QuestionDao questionDao2=new QuestionDao();
				   int r=questionDao2.addSelectQuestion(type, doubleselect_qtitle, 
						   doubleselect_qa, doubleselect_qb, doubleselect_qc, 
						   doubleselect_qd,doubleselect_qanswer);
				   if(r>0)
					   JOptionPane.showMessageDialog(this, "多选题录入成功!");
				   else {
					   JOptionPane.showMessageDialog(this, "fail");
					   }
			   }
		}
}
	 
	