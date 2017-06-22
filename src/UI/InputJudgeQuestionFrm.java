package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import Dao.QuestionDao;

public class InputJudgeQuestionFrm extends JPanel{
	private JButton btnAdd=new JButton("增加");
    private JButton btnCancel=new JButton("重置");
    private JLabel lblTitle=new JLabel("题目");
    private JTextArea txtTitle=new JTextArea(10,50);
	private JLabel lblAnswer=new JLabel("答案");
	 private JComboBox cmbAnswer=new JComboBox(new String[]{"正确","错误"});
	 
    public InputJudgeQuestionFrm() {
    	this.setLayout(new BorderLayout());
		JPanel topPane=new JPanel();
		topPane.add(lblTitle);topPane.add(txtTitle);
			   
		JPanel centerPane=new JPanel();
		centerPane.setLayout(new GridLayout(1,2));
		centerPane.add(lblAnswer);centerPane.add(cmbAnswer);
		JSplitPane splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,topPane,centerPane);
		this.add(splitPane,BorderLayout.NORTH);
		
		JPanel btnPane=new JPanel();
		btnPane.add(btnAdd);btnPane.add(btnCancel);
		this.add(btnPane,BorderLayout.SOUTH);
		
		this.setVisible(true);
		splitPane.setDividerLocation(0.5);
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
	}

		private void btnAdd_Clicked(){
			   String type="判断题";
			   String Judge_qtitle=txtTitle.getText();
			   String Judge_qanswer=cmbAnswer.getSelectedItem().toString();
			   if (Judge_qanswer.equals("正确")) {
				   Judge_qanswer="1";
			}else {
				Judge_qanswer="0";
			}
			   QuestionDao questionDao=new QuestionDao();
			   int r=questionDao.addJudgeQuestion(type, Judge_qtitle, 
					   Judge_qanswer);
			   if(r>0)
				   JOptionPane.showMessageDialog(this, "判断题录入成功!");
			   else {
				   JOptionPane.showMessageDialog(this, "fail");
				   }
			   
		}
}
