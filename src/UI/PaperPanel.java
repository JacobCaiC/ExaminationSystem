package UI;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import dbutil.SQLHelper;
import Dao.*;
public class PaperPanel extends JPanel {
	private JTable table = null;
	private QuestionDao questionDao = new QuestionDao();
	private SQLHelper sqlHelper=new SQLHelper();
	private JButton btn_add = new JButton("添加试题");
	private JButton btn_view = new JButton("查看考卷");
	String[] cols = {"题目类型", "题目内容", "选项a", "选项b","选项c","选项d","答案" };
	
    public String[] strings=new String[7];
	
    public PaperPanel() {
    	initTable();
    	this.setLayout(new BorderLayout());
		JScrollPane jsp = new JScrollPane(table);
		this.add(jsp,BorderLayout.NORTH);
		JPanel jpbtn = new JPanel();
		jpbtn.add(btn_add);jpbtn.add(btn_view);
		this.add(jpbtn,BorderLayout.SOUTH);

		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn_add_clicked();
			}		 
		});
		btn_view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn_view_clicked();
			}
		});
		}
    
		protected void btn_view_clicked() {
		// TODO Auto-generated method stub
		new ViewPaperFrm();
	}
		protected void btn_add_clicked() {
		// TODO Auto-generated method stub
			int r=table.getSelectedRow();
			if (r > -1) {
				String qtypeString=(String) table.getValueAt(r, 0);
				String qtitleString= (String) table.getValueAt(r, 1);
				String qaString= (String) table.getValueAt(r, 2);
				String qbString= (String) table.getValueAt(r, 3);
				String qcString= (String) table.getValueAt(r, 4);
				String qdString= (String) table.getValueAt(r, 5);
				String qanswerString= (String) table.getValueAt(r, 6);
				int s=questionDao.addQuestiontoPaper(qtypeString, qtitleString, 
						   qaString, qbString, qcString, 
						   qdString,qanswerString);
				   if(s>0)
					   JOptionPane.showMessageDialog(this, "试题录入成功!");
				   else {
					   JOptionPane.showMessageDialog(this, "fail");
					   }
			}
	}
		
		private void initTable() {
		// TODO Auto-generated method stub
			String[][] rows=questionDao.queryAllquestions();
			table = new JTable(rows,cols);
	}
		/* 刷新表格 */
		private void updateTable(){
			String[][] rows = questionDao.queryAllquestions();
			table.setModel(new DefaultTableModel(rows, cols));
		}
	
    }	
