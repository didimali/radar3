package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.AnalysisDao;
import radar.Dao.BigDataDao;
import radar.Dao.RepairContentDao;
import radar.Entity.DynamicData;
import radar.Entity.Equip;
import radar.Entity.Manager;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RadarHealth;
import radar.Entity.RepairContent;
import radar.Entity.RepairPlan;
import radar.Entity.System;
import radar.Service.AnalysisService;

@Service("AnalysisServiceImpl")

@SuppressWarnings({ "rawtypes", "unchecked" })
public class AnalysisServiceImpl implements AnalysisService{
	
	@Autowired
	AnalysisDao AnalysisDao;
	@Autowired
	RepairContentDao RepairContentDao;
	
	
	
	@Override
	public Object[][] countRadarType(Object[] params){
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
	public Object[][] getRadar(Object[] params){
		int typeid = (int)params[0];
		int location = (int)params[1];
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
	    defaultPieDataset.setValue("绿", Ghealth);
	    defaultPieDataset.setValue("黄", Yhealth);
	    defaultPieDataset.setValue("红", Rhealth);
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
	
	public Boolean health(int radar) {	
		Random random = new Random();
		int HI=random.nextInt(100);
		int result;
		int fnum=AnalysisDao.countFaultNum(radar);
		if(HI>40) {
			result=0;	
		}else if(HI>20){
			result=1;		
		}else {
			result=2;
		}
		String radarId=String.valueOf(radar);
		AnalysisDao.change1(radarId);     //健康评估      去除旧的
		AnalysisDao.change2(radarId);     //故障预测
		AnalysisDao.change3(radarId);     //器材筹措
		for(int i=0;i<fnum;i++) {
			int n=random.nextInt(10);
			if(n>7) {
			AnalysisDao.faultForecast(radarId,i+1); //故障预测
			}
		}
		AnalysisDao.save1(radarId,result);  //健康评估     添加新的
		AnalysisDao.save2(radarId,result);  //雷达状态
		List<RadarHealth> health=AnalysisDao.gethealthID(radarId);
		int healthid=health.get(0).getHealthResultId();
		for(int i=1;i<13;i++) {
		int sysint=random.nextInt(100);
		int sysHI=0;
		if(sysint<10) {
			sysHI=2;
		}else if(sysint<35) {
			sysHI=1;
		}
		AnalysisDao.save3(sysHI,healthid,i); //分系统健康评估
		}
		AnalysisDao.save4(radarId);         //器材筹措
		List<RepairPlan> repair=AnalysisDao.getRepairID(radarId);
		int repairid=repair.get(0).getRepairPlanId();
		List<Object> list = AnalysisDao.getPartsForecast(radarId);  
		Iterator it  = list.iterator(); 
		ArrayList token = new ArrayList();
		while(it.hasNext()) {
			Object[] o = (Object[]) it.next();
            token.add(o[0]);
            token.add(o[1]);
		}
		for(int i=0;i<list.size()*2;i=i+2) {
			AnalysisDao.saveRepairContent(Integer.parseInt(token.get(i).toString()),Integer.parseInt(token.get(i+1).toString()),repairid);
		}
		return true;
		}
	
	public String[] countNum() {
		String [] number = new String[6];
	    number[0]=AnalysisDao.countNum(0,1);
	    number[1]=AnalysisDao.countNum(1,1);
	    number[2]=AnalysisDao.countNum(2,1);
	    number[3]=AnalysisDao.countNum(0,2);
	    number[4]=AnalysisDao.countNum(1,2);
	    number[5]=AnalysisDao.countNum(2,2);
	    return number;
	}
	
	public DefaultPieDataset getPartConsumePie(Object[] params) {
		String radarType = (String) params[0];
		String sDate = (String) params[1];
		String eDate = (String) params[2];
		List<Parts> parts=AnalysisDao.getParts(radarType);
		DefaultPieDataset result = new DefaultPieDataset();
		for(int i=0;i<parts.size();i++) {
			Parts part=parts.get(i);
			List<PartConsume> consume=AnalysisDao.getPartsConsume(part.getPartsId(), sDate, eDate);			
			int num=0;
			for(int j=0;j<consume.size();j++) {
				num=num+consume.get(j).getpConsumeCount();
			}
			if(num >0)
				result.setValue(part.getPartsName(),num);
		}
		return result;
	}
	
	
	public DefaultCategoryDataset getPartConsumeLine(Object[] params) {
		String radarType = (String) params[0];
		String sDate = (String) params[1];
		String eDate = (String) params[2];
		List<Parts> parts=AnalysisDao.getParts(radarType);
		DefaultCategoryDataset result = new DefaultCategoryDataset();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<parts.size();i++) {
			Parts part=parts.get(i);
			List<PartConsume> consume=AnalysisDao.getPartsConsume(part.getPartsId(), sDate, eDate);			
			for(int j=0;j<consume.size();j++) {
				PartConsume c=consume.get(j);
				result.addValue(c.getpConsumeCount(),c.getPartsId().getPartsName(),sdf.format(c.getConsumeDate()));
			}			
		}	
		return result;		
	}
	
	public Object[][] getPartsConsumeData(Object[] params){
		String radarType = (String) params[0];
		String sDate = (String) params[1];
		String eDate = (String) params[2];
		List<Parts> parts=AnalysisDao.getParts(radarType);
		Object[][] result = new Object[parts.size()][];
		for(int i=0;i<parts.size();i++) {
			Parts part=parts.get(i);
			List<PartConsume> consume=AnalysisDao.getPartsConsume(part.getPartsId(), sDate, eDate);			
			int num=0;
			for(int j=0;j<consume.size();j++) {
				num=num+consume.get(j).getpConsumeCount();
			}	
			Object[] o = {i+1,part.getPartsName(),num};
			result[i] =o;
		}
		return result;
	}
	
	public Object[][] getRepairPlanContent(Object[] params){
		String radarType = (String) params[0];
		if(radarType=="2") {
			return new Object[0][0];
		}
		List<Radar> radar=AnalysisDao.getRadar(radarType);
		List<Parts> parts=AnalysisDao.getParts(radarType);
		
		Object[][] result = new Object[parts.size()][];
		int[] partsnum=new int[parts.size()];
		for(int i=0;i<radar.size();i++) {
			List<RepairContent> list = RepairContentDao.getRadarRepairContent(radar.get(i).getRadarId());
			if(list.size()>1) {
			for(int j=0;j<list.size();j++) {
				int partid=Integer.parseInt(list.get(j).getPartsId().getPartsId().toString());
				int num=list.get(j).getPartsCount();
				switch(partid) {
		    	case(0):partsnum[0]=partsnum[0]+num;break;
		    	case(1):partsnum[1]=partsnum[1]+num;break;
		    	case(2):partsnum[2]=partsnum[2]+num;break;
		    	case(3):partsnum[3]=partsnum[3]+num;break;
		    	case(4):partsnum[4]=partsnum[4]+num;break;
		    	case(5):partsnum[5]=partsnum[5]+num;break;
		    	case(6):partsnum[6]=partsnum[6]+num;break;
		    	case(7):partsnum[7]=partsnum[7]+num;break;
		    	case(8):partsnum[8]=partsnum[8]+num;break;
		    	case(9):partsnum[9]=partsnum[9]+num;break;
		    	case(10):partsnum[10]=partsnum[10]+num;break;
		    	case(11):partsnum[11]=partsnum[11]+num;break;
		    	case(12):partsnum[12]=partsnum[12]+num;break;
		    	case(13):partsnum[13]=partsnum[13]+num;break;
		    	case(14):partsnum[14]=partsnum[14]+num;break;
		    	case(15):partsnum[15]=partsnum[15]+num;break;
		    	case(16):partsnum[16]=partsnum[16]+num;break;
		    	case(17):partsnum[17]=partsnum[17]+num;break;
		    	case(18):partsnum[18]=partsnum[18]+num;break;
		    	}; 		
			}		
		}
		}
		int n=0;
		for(int i=0;i<parts.size();i++) {
			if(partsnum[i]>0) {
				Object[] o = {n+1,parts.get(i).getPartsName(),partsnum[i]};
				result[n] =o;
				n++;
			}	
		}
        return result;
	}
	
	public Boolean doBigDataAnalysis() {		
		List<Radar> radar=AnalysisDao.getRadarID();
		for(int i=0;i<radar.size();i++) {
			int radarid=radar.get(i).getRadarId();
			if(AnalysisDao.countDynamicData(radarid)>0){
				health(radarid);
			}
		}
		return true;
	}
	
	
}
