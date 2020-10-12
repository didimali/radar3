package radar.Tools;
import java.io.File;
import java.io.IOException;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;

public class Rstart {

//	public static void main(String[] args) throws REXPMismatchException, REngineException{
//		// TODO Auto-generated method stub
//		Rfunction();
//	}

	public static  void Rstart() throws REXPMismatchException, REngineException {  
		Runtime sc =Runtime.getRuntime();
		  try {
			sc.exec("E:\\R\\R-3.5.2\\bin\\x64\\Rserve.exe");    //根据绝对路径启动Rserve
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("false");
			e.printStackTrace();
		}
	    RConnection rc = new RConnection();
	    //	 rc.eval("library(DBI)");
	    //	 rc.eval("library(RMySQL)");	 
	    //	 rc.eval("con = dbConnect(MySQL(),host=\"127.0.0.1\",dbname=\"radar3\","
	                //	 		+ "user=\"root\",password=\"smit0296139\")");
	    rc.eval("source(\"F:/testFunc.R\")");      //根据绝对路径调用R脚本文件
	    REXP allData=rc.eval("test()");            //调用文件中方法
	    RList dataList = allData.asList();   
	    //REXP at1 = dataList.at(1);               //取出返回结果中第一个值
	    //Double data1=at1.asDouble();
	    //rc.eval("dbDisconnect(con)");              //关闭连接
	    rc.eval("rm(list=ls())");
	    rc.close();
	  }
	
	
	
	

}
