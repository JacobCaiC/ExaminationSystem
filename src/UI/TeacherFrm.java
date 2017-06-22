package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.*;

import dbutil.initDatas;
public class TeacherFrm extends JFrame{
    private JTree treeMenu;
    //private JTabbedPane tabPane=new JTabbedPane();
    private ClosableTabbedPane tabPane=new ClosableTabbedPane();
    private void initTree(){
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("考试系统后台");
		DefaultMutableTreeNode m1=new DefaultMutableTreeNode("题库管理");
		DefaultMutableTreeNode m11=new DefaultMutableTreeNode("录入选择题");
		DefaultMutableTreeNode m12=new DefaultMutableTreeNode("维护题目");
		//DefaultMutableTreeNode m12=new DefaultMutableTreeNode("多项题管理");
		DefaultMutableTreeNode m13=new DefaultMutableTreeNode("录入判断题");
		DefaultMutableTreeNode m14=new DefaultMutableTreeNode("录入简答题");
		DefaultMutableTreeNode m15=new DefaultMutableTreeNode("录入填空题");
		
		//m11.add(new DefaultMutableTreeNode("录入选择题"));
		//m11.add(new DefaultMutableTreeNode("维护选择题"));
		//m13.add(new DefaultMutableTreeNode("录入判断题"));
		//m13.add(new DefaultMutableTreeNode("维护判断题"));
		//m12.add(new DefaultMutableTreeNode("录入多选题"));
		//m12.add(new DefaultMutableTreeNode("维护多选题"));
		//m14.add(new DefaultMutableTreeNode("录入简答题"));
		//m14.add(new DefaultMutableTreeNode("维护简答题"));
		//m15.add(new DefaultMutableTreeNode("录入填空题"));
		//m15.add(new DefaultMutableTreeNode("维护填空题"));
		
		m1.add(m11);m1.add(m13);m1.add(m14);m1.add(m15);
		m1.add(m12);
		
		DefaultMutableTreeNode m2=new DefaultMutableTreeNode("试卷管理");
		m2.add(new DefaultMutableTreeNode("创建试题"));
		m2.add(new DefaultMutableTreeNode("维护试题"));
		DefaultMutableTreeNode m3=new DefaultMutableTreeNode("评分管理");
		m3.add(new DefaultMutableTreeNode("试卷评分"));
		
		DefaultMutableTreeNode m4=new DefaultMutableTreeNode("统计查询");
		DefaultMutableTreeNode m5=new DefaultMutableTreeNode("信息维护");
		
		root.add(m1);root.add(m2);root.add(m3);root.add(m4);root.add(m5);
		
		treeMenu=new JTree(root);
		treeMenu.addTreeSelectionListener(new TreeSelectionListener(){
         	public void valueChanged(TreeSelectionEvent e) {
         		String selectNode=e.getPath().getLastPathComponent().toString();
				if("录入选择题".equals(selectNode)){
					tabPane.removeAll();
					InputSelectQuestionFrm pane1=new InputSelectQuestionFrm();
					tabPane.addTab("增加选择题", pane1,null);
				}
				if("录入填空题".equals(selectNode)){
					tabPane.removeAll();
					InputBlankQuestionFrm pane=new InputBlankQuestionFrm();
					tabPane.addTab("增加填空题", pane,null);
				}
				if("录入判断题".equals(selectNode)){
					tabPane.removeAll();
					InputJudgeQuestionFrm pane=new InputJudgeQuestionFrm();
					tabPane.addTab("增加判断题", pane,null);
				}
				if("录入简答题".equals(selectNode)){
					tabPane.removeAll();
					InputAnswerQuestionFrm pane=new InputAnswerQuestionFrm();
					tabPane.addTab("增加简答题", pane,null);
				}
			
				if("维护题目".equals(selectNode)){
					tabPane.removeAll();
					UpdateQuestionFrm pane=new UpdateQuestionFrm();
					tabPane.addTab("维护题目", pane,null);
				}
				if("创建试题".equals(selectNode)){
					tabPane.removeAll();
					tabPane.addTab("创建试题", new PaperPanel());
				}
				if("维护试题".equals(selectNode)){
					new ViewPaperFrm();
				}
				
				
						
			}			
		});
	}
    public TeacherFrm(){
    	initTree();
    	JPanel jp=(JPanel)this.getContentPane();
    	JScrollPane jsp_tree=new JScrollPane(treeMenu);
    	JSplitPane splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jsp_tree,tabPane);
    	jp.add(splitPane);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setTitle("考试管理系统(教师端)");
    	this.setSize(1000,1000);
    	//this.setSize(initDatas.screenwidth-400,initDatas.screenheight-300);
    	this.setResizable(false);
    	this.setLocation(500, 0);
    	this.setVisible(true);
    	//设置分割窗口左右显示区域的大小,setvisible之后
    	splitPane.setDividerLocation(0.2);
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TeacherFrm();
	}

}
