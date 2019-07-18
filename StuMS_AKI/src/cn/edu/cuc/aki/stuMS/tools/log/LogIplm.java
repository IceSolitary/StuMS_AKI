/**
 * 
 */
package cn.edu.cuc.aki.stuMS.tools.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ming��s PC
 *
 */
public interface LogIplm {
	/**
	 * ��־��Ϣ���������� ��Ϣ�����桢����
	 */
	enum TYPE {
		INFORMATION, WARNING, ERROR
	};

	/**
	 * ���һ����־
	 * 
	 * @return
	 */

	static void addLog(LogIplm.TYPE type, String logContent) {
//		try {
//
//			String fileName = "./log/MyMSlog";
//			BufferedWriter out = new BufferedWriter(new FileWriter(fileName,true));
//			out.write("-----------" + type.toString() + "----------");
//			out.newLine();
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
//			out.write(df.format(new Date()));
//			out.newLine();
//			out.write(logContent);
//			out.newLine();
//			out.write("-----------END----------");
//			out.newLine();
//			out.flush();
//			out.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
	}

	static String readLog() {
		try {
			  File file = new File("./log/MyMSlog.log");
			  InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
		      BufferedReader br = new BufferedReader(isr);
		      String lineTxt = null;
		      while ((lineTxt = br.readLine()) != null) {
		        System.out.println(lineTxt);
		      }
		      br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
