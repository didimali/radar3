package radar.Tools;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.JxlWriteException;

public class ExportExcel {
	
	/*
	  * 报表生成类
	 * @author madi
	 */
	
	
	public ExportExcel() {
		
	}	
	
	//导出excle方法 只需把要导出数据的集合当参数即可 jxl好像有缺点，慎用
	/*
	 * @param title:Excel标题
	 * @param filepath: 文件下载保存地址
	 * @param header: Excel表头
	 * @param list : 数据
	 * @return 是否成功导出并下载表格
	 */
	public Boolean exportExcel(String title,String filepath,String[] header,Object[][] list) {
		
		String filePath = filepath;	
		//检查文件是否存在（如果存在，覆盖原文件）
		File file = new File(filePath);	
		if (!file.exists()) {		
			file.mkdirs();	
		}
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");	
		// 给要导出的文件起名为 "title_时间.xls"	
		String filePath2 = filePath + title + "_" + fmt.format(new Date()) + ".xls";	
		WritableWorkbook wb = null;
		
		try {		
			File file2 = new File(filePath2);		
			if (!file2.exists()) {
				//不存在，创建			
				file2.createNewFile();		
			}		
			wb = Workbook.createWorkbook(file2);//创建xls表格文件
			
			//标题样式
			WritableCellFormat titleStyle = new WritableCellFormat();
			titleStyle.setAlignment(Alignment.CENTRE);// 水平居中	
			titleStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 垂直居中	
			titleStyle.setWrap(true);
			titleStyle.setFont(new WritableFont(WritableFont.TIMES,16, WritableFont.BOLD));// 表头字体 加粗 16号		
		
			// 表头样式
			WritableCellFormat headerStyle = new WritableCellFormat();		
			headerStyle.setAlignment(Alignment.CENTRE);// 水平居中		
			headerStyle.setWrap(true);
			headerStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 垂直居中	
			headerStyle.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号		
			//wcf.setBackground(jxl.format.Colour.PERIWINKLE);//背景颜色
			
			// 内容显示		
			WritableCellFormat contentStyle = new WritableCellFormat();		
			contentStyle.setWrap(true);//设置单元格可以换行		
			contentStyle.setAlignment(Alignment.CENTRE);//水平居中		
			contentStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 垂直居中		
			contentStyle.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号
	 
			//导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
			WritableSheet ws = wb.createSheet(title, 0);		
			//WritableSheet ws2 = wb.createSheet("sheet2", 1);//第2个sheet页		
			
			//两行合并生成标题栏
			ws.mergeCells(0,0,header.length-1,1);
			//插入标题
			ws.addCell(new Label(0,0,title,titleStyle));
			//设置每一列的宽度
			for(int i =0;i<header.length;i++) {
				ws.setColumnView(i, 30);
			};
	 
			// 导出时生成表头		
			for (int i = 0; i < header.length; i++) {
				//i,代表的第几列，2，代表第3行，第三个参数为要显示的内容，第四个参数，为内容格式设置（按照wcf的格式显示）			
				ws.addCell(new Label(i, 2, header[i],headerStyle));//在sheet1中循环加入表头
			}
					
			int k =3 ;//从第4行开始写入数据
			//用来double转换string的,防止入坑
			NumberFormat nf=NumberFormat.getInstance();
			nf.setGroupingUsed(false);
			for (int i = 0; i < list.length; i++) {				
				for(int j=0;j<list[i].length;j++)
					ws.addCell(new Label(j, k, String.valueOf(list[i][j]),contentStyle));
				k++;		
			}		
			wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件	
			return true;
		} catch(IOException e) {
			e.printStackTrace();	
			return false;
		} catch(JxlWriteException e) {
			e.printStackTrace();	
			return false;
		} catch(WriteException e) {
			e.printStackTrace();	
			return false;
		} finally {		
			try {			
				if (wb != null) {
					wb.close();
				}		
			} catch(WriteException e) {
				e.printStackTrace();
				return false;
			} catch(IOException e) {
				e.printStackTrace();
				return false;
			}	
		}	
		
	}

}
