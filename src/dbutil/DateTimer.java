package dbutil;

import java.util.Date;

import javax.swing.JOptionPane;

import UI.StudentFrm;

public class DateTimer extends Thread{
	public void run(){
		while(true){
			Date now=new Date();
			StudentFrm.time.setText(""+now);
            try {
                   sleep(1000);

            } catch (InterruptedException e) {
                   e.printStackTrace();
            }

		}
	}
}