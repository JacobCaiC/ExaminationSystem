package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dbutil.SQLHelper;
import dbutil.initDatas;

public class BtnUpdateFrm2 extends JFrame{
	private JButton btnOK=new JButton("确定");
    private JButton btnCancel=new JButton("重置");
    
    private JLabel lbltype=new JLabel("题目类型");
    private JTextField txttype=new JTextField(30);
    private JLabel lblTitle=new JLabel("题目");
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
    private JTextField txtanswer=new JTextField(30);
    private JLabel lbltips=new JLabel("题目选项部分，如果是判断题，填空题，简答题，该部分内容可以省略");
    private String[] strings=new String[8];
    SQLHelper sqlHelper=new SQLHelper();
    
	public BtnUpdateFrm2(){
	}
	public BtnUpdateFrm2(String qtype,String qtitle,String qa,String qb,
			String qc,String qd,String qanswer){
		final String oldtitle=qtitle;
		txttype.setText(qtype);
		txtTitle.setText(qtitle);
		txtA.setText(qa);
		txtB.setText(qb);
		txtC.setText(qc);
		txtD.setText(qd);
		txtanswer.setText(qanswer);
		this.setLayout(new BorderLayout());
		JPanel top=new JPanel();
		top.add(lbltype);top.add(txttype);
		top.add(lblTitle);top.add(txtTitle);top.add(lbltips);
		
		
		JPanel center=new JPanel();
		center.setLayout(new GridLayout(6,2));
		center.add( lblA);center.add(txtA);center.add(lblB);center.add(txtB);
		center.add(lblC);center.add(txtC);center.add(lblD);center.add(txtD);
		center.add(lblAnswer);center.add(txtanswer);
		JSplitPane jSplitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,top,center);
		this.add(jSplitPane);
		
		JPanel btnPanel=new JPanel();
		btnPanel.add(btnOK);btnPanel.add(btnCancel);
		this.add(btnPanel,BorderLayout.SOUTH);
		
		this.setTitle("修改试题");
		this.setVisible(true);
		
		this.setSize(initDatas.screenwidth-300,initDatas.screenheight-200);
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BtnUpdateFrm2.this.dispose();
			}
		});
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql="UPDATE t_shiti SET qtype='"+txttype.getText()+"',qtitle='"+txtTitle.getText()+"',qa='"+txtA.getText()+"',qb='"+txtB.getText()+"',qc='"+txtC.getText()+"',qd='"+txtD.getText()+"',qanswer='"+txtanswer.getText()+"'WHERE qtitle='"+oldtitle+"'";
		        System.out.print(sql);
				int r=sqlHelper.executeUpdate(sql);
		        if (r>0) {
					JOptionPane.showMessageDialog(null, "修改成功");
					BtnUpdateFrm2.this.dispose();
				}
			}
		});
	}
}
