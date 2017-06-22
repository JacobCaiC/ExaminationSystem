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
import javax.swing.JTextField;

import Dao.ExamDao;
import UI.StudentFrm;
public class ExamFrm5 extends JPanel{
	private ExamDao examDao=new ExamDao();
	private JButton btnstart=new JButton("开始答题");
	private JButton btnForward=new JButton("上一题");
    private JButton btnBack=new JButton("下一题");
    //private JButton btnComplete=new JButton("提交试卷");
    private JLabel lblid=new JLabel("题号：");
    private JTextField txtid=new JTextField(5);
    public int id5;
    private JLabel lblTitle=new JLabel("题目：");
    private JTextArea txtTitle=new JTextArea(10,50);
	private JLabel lblAnswer=new JLabel("答案");
	private JTextArea txtAnswer=new JTextArea(10,50);
	private String exam5=null;  
	LoginFrm loginFrm;
    
    public ExamFrm5(){
		this.setLayout(new BorderLayout());
		JPanel topPane=new JPanel();topPane.add(lblid);
		topPane.add(lblTitle);topPane.add(txtTitle);
			   
		JPanel centerPane=new JPanel();
		centerPane.setLayout(new GridLayout(1,2));
		centerPane.add(lblAnswer);centerPane.add(txtAnswer);
		JSplitPane splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,topPane,centerPane);
        this.add(splitPane,BorderLayout.NORTH);
		JPanel btnPane=new JPanel();
		btnPane.add(btnstart);
		btnPane.add(btnForward);btnPane.add(btnBack);//btnPane.add(btnComplete);
		this.add(btnPane,BorderLayout.SOUTH);
		
	    btnstart.addActionListener(new ActionListener() {
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
			id5=examDao.queryTypeNums("单选题")+examDao.queryTypeNums("多选题")+examDao.queryTypeNums("填空题")+examDao.queryTypeNums("判断题")+1;txtid.setText(id5+"");
			lblid.setText("题号:"+id5);
			exam5=examDao.updateEaxm3ByID(id5);
			txtTitle.setText(exam5);
	}

		protected void btnBack_Clicked() {
			btnForward.setEnabled(true);
			String stu_id=loginFrm.uid;
			String timu_answer=txtAnswer.getText();
			String timu_id=txtid.getText();
			examDao.updateAnswertoPaper(stu_id, timu_id, txtTitle.getText(),null,null,null,null, timu_answer);
			id5++;txtid.setText(id5+"");lblid.setText("题号："+id5);
			if (id5<=examDao.queryTypeNums("单选题")+examDao.queryTypeNums("多选题")+examDao.queryTypeNums("填空题")+examDao.queryTypeNums("判断题")+examDao.queryTypeNums("简答题")) {
				exam5=examDao.updateEaxm3ByID(id5);
				txtTitle.setText(exam5);
				txtAnswer.setText("");
			}else {
				id5--;txtid.setText(id5+"");lblid.setText("题号："+id5);
				JOptionPane.showMessageDialog(null, "简答题已做完");
				btnBack.setEnabled(false);
			}
			
		}

		private void btnForward_Clicked(){
			btnBack.setEnabled(true);
			 id5--;txtid.setText(id5+"");lblid.setText("题号："+id5);
			 if (id5>examDao.queryTypeNums("单选题")+examDao.queryTypeNums("多选题")+examDao.queryTypeNums("填空题")+examDao.queryTypeNums("判断题")) {
				 exam5=examDao.updateEaxm3ByID(id5);
					txtTitle.setText(exam5);
				    if (id5==examDao.queryTypeNums("单选题")+examDao.queryTypeNums("多选题")+examDao.queryTypeNums("填空题")+examDao.queryTypeNums("判断题")+1) {
						btnForward.setEnabled(false);
					}
			}
			 
	 }		   
}
