package UI;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Dao.studentDao;
import Dao.teacherDao;

public class LoginFrm extends JFrame {
	    private JButton btn_cancel=new JButton("Exit");
	    private JButton btn_ok=new JButton("Login");
	    private JLabel lbl_user=new JLabel("User type");
	    String[] selectionsStrings={"student","teacher"};
	    private JComboBox jComboBox=new JComboBox(selectionsStrings);
	    private JLabel lbl_ID=new JLabel("Login ID");
	    private JLabel lbl_pwd=new JLabel("Password");
	    private JTextField txt_id=new JTextField();
	    private JPasswordField jPasswordField=new JPasswordField();
	    private teacherDao teaDao=new teacherDao();
	    private studentDao stuDao=new studentDao();
	    private JLabel jlpic=new JLabel();
	    
	    private String s ;
	    public static String uid;
        
    public LoginFrm() {
        initComponents();
    }                       
    private void initComponents() {
    	JPanel jPanel=(JPanel)this.getContentPane();
    	jPanel.setLayout(null);
    	
    	lbl_user.setBounds(200, 200, 80,30);
    	jComboBox.setBounds(280, 200, 80, 30);
    	
        lbl_ID.setBounds(200, 240, 100, 30);
        txt_id.setBounds(280, 245, 130, 21);
        
        lbl_pwd.setBounds(200, 280, 100, 30);
        jPasswordField.setBounds(280, 285, 130, 21);
        
        btn_ok.setBounds(200, 320, 80, 23);
        btn_cancel.setBounds(320, 320, 80, 23);

    	this.add(lbl_user);this.add(jComboBox);this.add(lbl_ID);this.add(txt_id);
    	this.add(lbl_pwd);this.add(jPasswordField);this.add(btn_ok);this.add(btn_cancel);
    	//icon
    	Image icon=Toolkit.getDefaultToolkit().getImage("D:\\myeclipse\\workspaces\\ExaminationSystem\\src\\1.jpg");
    	this.setIconImage(icon);
    	//用label设置backgroundImage
    	ImageIcon backImage=new ImageIcon("D:\\myeclipse\\workspaces\\ExaminationSystem\\src\\2.png");
    	backImage.setImage(backImage.getImage().getScaledInstance(backImage.getIconWidth(), backImage.getIconHeight(), Image.SCALE_DEFAULT));
    	System.out.println(backImage.getIconHeight() + "" + backImage.getIconWidth());
    	jlpic.setBounds(-80, -160, 600,600);  
        jlpic.setHorizontalAlignment(0);  
        jlpic.setIcon(backImage);
       
        this.add(jlpic);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("在线考试系统");
        this.setVisible(true);
        this.setSize(470,440);
        //禁用最大化
        this.setResizable(false);
    	this.setLocation(700, 200);
    	
    	txt_id.setText("20133046");
    	jPasswordField.setText("1234");
    	
    	/*jComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(jComboBox.getSelectedItem().toString());
			}
		});*/
        btn_ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s=(jComboBox.getSelectedItem().toString());
				btnOK_Clicked();
			}

		});
    	
        btn_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               btnCancel_Clicked();
            }
			private void btnCancel_Clicked() {
				// TODO Auto-generated method stub
				System.exit(0);
			}
        });
       
    }                     
    /*private void btnOK_Clicked() {
        String uid=txt_id.getText();
        String upwd=jPasswordField.getText();
        if(teaDao.validate1(uid, upwd)){
        	JOptionPane.showMessageDialog(this, "登录成功!");
        	this.setVisible(false);
        	TeacherFrm teacherFrm=new TeacherFrm();
        }
        else
        	JOptionPane.showMessageDialog(this, "用户名或密码错误!");
	}*/

   protected void btnOK_Clicked() {
			// TODO Auto-generated method stub
			if (s.equals("teacher")) {
				uid=txt_id.getText();
				String upwdString=jPasswordField.getText();
				if (teaDao.validate1(uid,upwdString)) {
						JOptionPane.showMessageDialog(this, s+" "+uid+" Login successfully");
						this.setVisible(false);
						TeacherFrm teafrm=new TeacherFrm();
					}else {
						JOptionPane.showMessageDialog(this, "error");
					}
				}else if (s.equals("student")) {
					uid=txt_id.getText();
					String upwdString=jPasswordField.getText();
					if (studentDao.validate2(uid,upwdString)) {
						JOptionPane.showMessageDialog(this, s+" "+uid+" Login successfully");
						this.setVisible(false);
						StudentFrm stufrm=new StudentFrm();
						}else{
							JOptionPane.showMessageDialog(this, "error");
							}
					}
			}
   
  /* public String getUid(){
	   return uid;
   }
   public void setUid(String uid){
	   this.uid=uid;
   }*/
	public static void main(String args[]) {
       LoginFrm login=new LoginFrm();
      
    }
                    
}
