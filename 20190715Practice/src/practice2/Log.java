package practice2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Log implements Loggable {
	String file = "";
	public Log(String logfilename) {
		this.file = logfilename;
	}
	
	@Override
	public void addLog(TYPE type, String logContent) {
		// TODO Auto-generated method stub
//		String back = readLog();
		SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记  
        Date date = new Date();// 获取当前时间 
        
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
		
//			out.write(back);
			out.write("******************************");
			out.newLine();
			out.write(sdf.format(date));//时间
			out.newLine();
			out.write(type.toString());//type
			out.newLine();
			out.write(logContent);//log内容
			out.newLine();
			out.write("-END-");
			out.newLine();
			out.write("******************************");
			out.newLine();
			out.flush();
			out.close();	
			
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		
	}



	@Override
	public String readLog() {
		// TODO Auto-generated method stub
		
		
		String result="";
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line="";
			try {
				line = in.readLine();
				if(line != null) {
					result+=line;
					result+="\n";
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
            while ( line != null ) { 
            	try {
					line = in.readLine();
					if(line != null ) {
						result+=line;
						result+="\n";
					}
					
//	            	System.out.println(line);
				} catch (IOException e) {
					e.printStackTrace();
				}
    	    }
            try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try{
	            File file = new File("LogTest",this.file);
	            file.createNewFile();
	        }
	        catch(IOException ioe) {
	            ioe.printStackTrace();
	        }
//			e.printStackTrace();
		}
		return result;
	}
}
