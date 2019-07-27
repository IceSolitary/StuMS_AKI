package practice2;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class LogTest  {
	public static void main(String str[]) throws IOException {
		String txt = "random.txt";
		Log log = new Log("mylog.log");
//		log.addLog(Loggable.TYPE.INFORMATION,"中文可以🐎");
//		log.addLog(Loggable.TYPE.WARNING, "警告⚠️");
//		log.addLog(Loggable.TYPE.ERROR, "错误🙅");
		DataOutputStream out = null;
		long startTime = System.currentTimeMillis();
		long endTime,cost = 0;
		try {
			out=new DataOutputStream(new FileOutputStream(txt));
			for(int i =0 ;i<100000;i++) {
				Random r = new Random();
				double d = r.nextDouble();
				out.writeDouble(d);
			}
			out.close();
			endTime = System.currentTimeMillis();
			cost = endTime-startTime;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		String logContent = "DONE,cost "+String.valueOf(cost)+" without buffer";
		log.addLog(Loggable.TYPE.INFORMATION, logContent);
		
		long startTime2 = System.currentTimeMillis();
		long endTime2,cost2=0;
		try {
			out=new DataOutputStream(new BufferedOutputStream(new FileOutputStream("random.txt")) );
			for(int i =0 ;i<100000;i++) {
				Random r = new Random();
				double d = r.nextDouble();
				out.writeDouble(d);
			}
			out.close();
			endTime2 = System.currentTimeMillis();
			cost2 = endTime2-startTime2;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		String logContent2 = "DONE,cost "+String.valueOf(cost2)+" with buffer";
		log.addLog(Loggable.TYPE.INFORMATION, logContent2);
		
		
		
		System.out.println(log.readLog());
	}
	

}
