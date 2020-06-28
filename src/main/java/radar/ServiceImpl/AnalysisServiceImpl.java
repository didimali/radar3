package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.AnalysisDao;
import radar.Dao.BigDataDao;
import radar.Entity.DynamicData;
import radar.Entity.Equip;
import radar.Entity.Manager;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.System;
import radar.Service.AnalysisService;

@Service("AnalysisServiceImpl")

@SuppressWarnings({ "rawtypes", "unchecked" })
public class AnalysisServiceImpl implements AnalysisService{
	
	@Autowired
	AnalysisDao AnalysisDao;

	@Override
	public Object[][] countRadarType(){
		Object[][] data  = new Object[10][7];
		int typeid = 1;
		int location = 0;
        for(int i=0;i<10;i++) {
        	switch(i) {
        	case (0):typeid=1;location=0;break;
        	case (1):typeid=1;location=1;break;
        	case (2):typeid=1;location=2;break;
        	case (3):typeid=1;location=3;break;
        	case (4):typeid=1;location=4;break;
        	case (5):typeid=2;location=0;break;
        	case (6):typeid=2;location=1;break;
        	case (7):typeid=2;location=2;break;
        	case (8):typeid=2;location=3;break;
        	case (9):typeid=2;location=4;break;   	
        	};
        	data[i][0]=i+1;
        	if(typeid==1) {
        		data[i][1]="I型雷达";
        	}else {
        		data[i][1]="II型雷达";
        	}
        	switch(location) {
        	case(0):data[i][2]="高原";break;
        	case(1):data[i][2]="山地";break;
        	case(2):data[i][2]="平原";break;
        	case(3):data[i][2]="沿海";break;
        	case(4):data[i][2]="沙漠";break;	
        	};       	
        	data[i][3]=AnalysisDao.countRadarByTL(typeid, location,null);
        	data[i][4]=AnalysisDao.countRadarByTL(typeid, location,0);
        	data[i][5]=AnalysisDao.countRadarByTL(typeid, location,1);
        	data[i][6]=AnalysisDao.countRadarByTL(typeid, location,2);
        }	
        return data;   
	}
	
	@Override
	public Object[][] getRadar(int typeid,int location){
		List<Object> list=AnalysisDao.getRadarListByTL(typeid, location);		
		Object[][] data = new Object[list.size()][];
		Iterator it  = list.iterator(); 
		ArrayList token = new ArrayList();
		String health=null;
		while(it.hasNext()) {
			Object[] o = (Object[]) it.next();
            token.add(o[0]);
            token.add(o[1]);
            token.add(o[2]);
		}
		int j=0;
		for(int i=0;i<list.size()*3;i=i+3) {
			int healthid= Integer.parseInt(token.get(i+2).toString());			
			switch(healthid) {
        	case(0):health="绿";break;
        	case(1):health="黄";break;
        	case(2):health="红";break;
        	}; 			
			Object[] o = {j+1,token.get(i).toString(),token.get(i+1).toString(),health};
			data[j] = o;
			j++;
		}
		return data;
	}
	@Override
	public PieDataset createPieData(int typeid,int location) {
	    DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
		int Ghealth=AnalysisDao.countRadarByTL(typeid, location,0);
		int Yhealth=AnalysisDao.countRadarByTL(typeid, location,1);
		int Rhealth=AnalysisDao.countRadarByTL(typeid, location,2);
	    defaultPieDataset.setValue("健康", Ghealth);
	    defaultPieDataset.setValue("不良", Yhealth);
	    defaultPieDataset.setValue("较差", Rhealth);
	    return (PieDataset)defaultPieDataset;
	  }
	@Override
	public String[] titleName(int typeid,int location) {
	    String [] title=new String[2];
		if (typeid==1){
			title[0]="I型雷达";
		}else {
			title[0]="II型雷达";
		}
		switch(location) {
    	case(0):title[1]="高原";break;
    	case(1):title[1]="山地";break;
    	case(2):title[1]="平原";break;
    	case(3):title[1]="沿海";break;
    	case(4):title[1]="沙漠";break;	
    	}; 	
	    return title;
	  }
	@Override
	public int[] getTL(int r) {
	    int [] TL=new int[2]; 
		switch(r) {
    	case(0):TL[0]=1;TL[1]=0;break;
    	case(1):TL[0]=1;TL[1]=1;break;
    	case(2):TL[0]=1;TL[1]=2;break;
    	case(3):TL[0]=1;TL[1]=3;break;
    	case(4):TL[0]=1;TL[1]=4;break;
    	case(5):TL[0]=2;TL[1]=0;break;
    	case(6):TL[0]=2;TL[1]=1;break;
    	case(7):TL[0]=2;TL[1]=2;break;
    	case(8):TL[0]=2;TL[1]=3;break;
    	case(9):TL[0]=2;TL[1]=4;break;   	   	
    	}; 	
	    return TL;
	  }
	
	@Override
	public Object[][] getFaultList(int typeid,int location,String startDate, String endDate){
		List<Object> list = AnalysisDao.getFaultLocationType(typeid,location,startDate,endDate);
		Object[][] data = new Object[list.size()][3];
		for(int i=0;i<list.size();i++) {
			String searchKey = String.valueOf(list.get(i));
			String symbol=String.valueOf(searchKey.charAt(0));
			String symbolID=String.valueOf(searchKey.charAt(1));
			if(symbol.contains("s")) {				
				List<System> S = AnalysisDao.getSystemName(symbolID);
				int num=AnalysisDao.countFaultLocationType(typeid,location,startDate,endDate,searchKey);
				Object[] o = {i+1,S.get(0).getSystemName(),num};
				data[i] = o;
			}else if (symbol.contains("e")){
				List<Equip> E = AnalysisDao.getEquipName(symbolID);
				int num=AnalysisDao.countFaultLocationType(typeid,location,startDate,endDate,searchKey);
				Object[] o = {i+1,E.get(0).getEquipName(),num};
				data[i] = o;
			}		
		}
		return data;		
	}
	@Override
	public PieDataset createPieData2(int typeid,int location,String startDate, String endDate) {
	    DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
		List<Object> list = AnalysisDao.getFaultLocationType(typeid,location,startDate,endDate);
		for(int i=0;i<list.size();i++) {
			String searchKey = String.valueOf(list.get(i));
			String symbol=String.valueOf(searchKey.charAt(0));
			String symbolID=String.valueOf(searchKey.charAt(1));
			if(symbol.contains("s")) {				
				List<System> S = AnalysisDao.getSystemName(symbolID);
				int num=AnalysisDao.countFaultLocationType(typeid,location,startDate,endDate,searchKey);
				defaultPieDataset.setValue(S.get(0).getSystemName(),num);
			}else if (symbol.contains("e")){
				List<Equip> E = AnalysisDao.getEquipName(symbolID);
				int num=AnalysisDao.countFaultLocationType(typeid,location,startDate,endDate,searchKey);
				defaultPieDataset.setValue(E.get(0).getEquipName(),num);
			}		
		}
	    return (PieDataset)defaultPieDataset;
	  }
}
