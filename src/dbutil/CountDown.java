package dbutil;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import UI.StudentFrm;

public class CountDown extends Thread{
	private int limitSec=300;
	private int curSec;
	public void run(){
		while(true){
			curSec=limitSec;
			Timer timer = new Timer();  
	        timer.schedule(new TimerTask(){  
	            public void run(){  
	                //System.out.println("Time remians "+ --curSec +" s"); 
	            	--curSec;
	                int h=curSec/3600;
	                int m=(curSec%3600)/60;
	                int s=(curSec%3600)%60;
	                StudentFrm.remain.setText("剩余时间："+h+"时"+m+"分"+s+"秒");
	            }  
	        },0,1000);  
             try {
            	 TimeUnit.SECONDS.sleep(limitSec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
                 timer.cancel();  
                JOptionPane.showMessageDialog(null, "时间到了哟", "提示", JOptionPane.WARNING_MESSAGE);
                System.exit(0);

            }

		}
	}

