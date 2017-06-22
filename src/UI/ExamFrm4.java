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
//判断题
public class ExamFrm4 extends JPanel{
	private ExamDao examDao=new ExamDao();
	private JButton btnstart=new JButton("开始答题");
	private JButton btnForward=new JButton("上一题");
    private JButton btnBack=new JButton("下一题");
    private JLabel lblid=new JLabel("题号：");
    private JTextField txtid=new JTextField(5);
    public int id4;
    private JLabel lblTitle=new JLabel("题目：");
    private JTextArea txtTitle=new JTextArea(10,50);
	private JLabel lblAnswer=new JLabel("答案");
	private JComboBox cmbAnswer=new JComboBox(new String[]{"正确","错误"});
	private String exam4=null;  
	LoginFrm loginFrm;
    
    public ExamFrm4(){
		this.setLayout(new BorderLayout());
		JPanel topPane=new JPanel();topPane.add(lblid);
		topPane.add(lblTitle);topPane.add(txtTitle);
			   
		JPanel centerPane=new JPanel();
		centerPane.setLayout(new GridLayout(1,2));
		centerPane.add(lblAnswer);centerPane.add(cmbAnswer);
		JSplitPane splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,topPane,centerPane);
        this.add(splitPane,BorderLayout.NORTH);
		JPanel btnPane=new JPanel();
		btnPane.add(btnstart);
		btnPane.add(btnForward);btnPane.add(btnBack);
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
			id4=examDao.queryTypeNums("单选题")+examDao.queryTypeNums("多选题")+examDao.queryTypeNums("填空题")+1;txtid.setText(id4+"");
			lblid.setText("题号:"+id4);
			exam4=examDao.updateEaxm3ByID(id4);
			txtTitle.setText(exam4);
	}

		protected void btnBack_Clicked() {
			btnForward.setEnabled(true);
			String stu_id=loginFrm.uid;
			String timu_answer=(cmbAnswer.getSelectedItem().toString());
			String timu_id=txtid.getText();
			if (timu_answer.equals("正确")) {
				timu_answer="1";
			}else{
				timu_answer="0";
			}
			examDao.updateAnswertoPaper(stu_id, timu_id, txtTitle.getText(),null,null,null,null, timu_answer);
			id4++;txtid.setText(id4+"");lblid.setText("题号："+id4);
			if (id4<=examDao.queryTypeNums("单选题")+examDao.queryTypeNums("多选题")+examDao.queryTypeNums("填空题")+examDao.queryTypeNums("判断题")) {
				exam4=examDao.updateEaxm3ByID(id4);
				txtTitle.setText(exam4);
			}else {
				id4--;txtid.setText(id4+"");lblid.setText("题号："+id4);
				JOptionPane.showMessageDialog(null, "判断题已做完");
				btnBack.setEnabled(false);
			}
			
		}

		private void btnForward_Clicked(){
			btnBack.setEnabled(true);
			 id4--;txtid.setText(id4+"");lblid.setText("题号："+id4);
			 if (id4>examDao.queryTypeNums("单选题")+examDao.queryTypeNums("多选题")+examDao.queryTypeNums("判断题")) {
				 exam4=examDao.updateEaxm3ByID(id4);
					txtTitle.setText(exam4);
				    if (id4==examDao.queryTypeNums("单选题")+examDao.queryTypeNums("多选题")+examDao.queryTypeNums("填空题")+1) {
						btnForward.setEnabled(false);
					}
			}
		}
		
}

