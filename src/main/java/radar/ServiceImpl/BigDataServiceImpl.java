package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import radar.Dao.BigDataDao;
import radar.Entity.Equip;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RadarHealth;
import radar.Entity.SysOrEquipHealth;
import radar.Entity.System;
import radar.Service.BigDataService;

@Service("BigDataServiceImpl")

public class BigDataServiceImpl implements BigDataService{
	
	@Autowired
	BigDataDao BigDataDao;
	
	
	@Override
	public String getRadarName(String id) {
		List<Radar> list=BigDataDao.getRadarByID(id);
		String name=list.get(0).getRadarName();
		return name;
	}
	
	@Override
	public Integer dataVerify(String id) {
		List<RadarHealth> Rlist = BigDataDao.getRadarHealth(id);
		Integer num=Rlist.size();
		return num;
	}
	
	@Override
	public Object[][] getHealth(String id){
		List<RadarHealth> Rlist = BigDataDao.getRadarHealth(id);
		List<SysOrEquipHealth> SElist = BigDataDao.getSEHealth(Rlist.get(0).getHealthResultId().toString());
		Object[][] data  = new Object[SElist.size()+1][];
		String state;
		String name;
		int HI;
		RadarHealth rh=Rlist.get(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date =sdf.format(rh.getAssessDate());
		for(int i=0;i<SElist.size()+1;i++) {		
			if(i==0) {
			HI = Integer.parseInt(rh.getAssessResult());
			name="雷达总系统";
			}else {			
			SysOrEquipHealth seh = SElist.get(i-1);
			HI=Integer.parseInt(seh.getAssResult());
			String sysid=seh.getSystemId().getSystemId().toString();
			List<System> system = BigDataDao.getSystemName(sysid);						
			name=system.get(0).getSystemName();		
			}
			if(HI==0) {
				state="健康";
			}else if(HI==1) {
				state="良好";
			}else {
				state="较差";
			}						
			Object[] o = {i+1,name,state,date};
			data[i] = o;
		}
		return data;
	}
	
	@Override
	public Object[][] getHistory(String id) {
		List<RadarHealth> list = BigDataDao.getRadarHistory(id);
		Object[][] state = new Object[list.size()][2];
	    int j=0;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=(list.size()-1);i>(-1);i--) {			
			if(Integer.parseInt(list.get(i).getAssessResult())==0) {
				state[j][0]=2;				
			}else if(Integer.parseInt(list.get(i).getAssessResult())==2) {
				state[j][0]=0;
			}else {
				state[j][0]=1;
			}			
			state[j][1]=sdf.format(list.get(i).getAssessDate());
			j++;
		}
		return state;		
	}
	
	@Override
	public Object[][] getFault(String id){
		List<RadarForecast> list = BigDataDao.getFaultForecast(id);
		Object[][] data = new Object[list.size()][];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String location;
		for(int i=0;i<list.size();i++) {
			String symbol=String.valueOf(list.get(i).getFaultTypeId().getFaultLocaltion().charAt(0));
			String symbolID=String.valueOf(list.get(i).getFaultTypeId().getFaultLocaltion().charAt(1));
			if(symbol.contains("s")) {
				List<System> S = BigDataDao.getSystemName(symbolID);
				location=S.get(0).getSystemName();
			}else if(symbol.contains("e")) {
				List<Equip> E = BigDataDao.getEquipName(symbolID);
				location=E.get(0).getEquipName();
			}else {
				location=null;
			}
			Object[] o = {list.get(i).getFaultTypeId().getFaultName(),location
					,list.get(i).getFaultTypeId().getFaultPrinciple(),sdf.format(list.get(i).getForecastResultDate())};
			data[i] = o;
		}
		return data;
		
	}
	
	@Override
	public Object[][] getPartsNum(String id) {	
		List<Object> list=BigDataDao.getPartsID(id);
		Object[][] data = new Object[list.size()][2];
		for(int i=0;i<list.size();i++) {
			String partid =list.get(i).toString();
			List<Parts> part=BigDataDao.getPartsName(partid);			
			data[i][0]=part.get(0).getPartsName();
			Integer partnum=BigDataDao.getPartsNum(id,partid);
			data[i][1]=partnum;	
		}
			return data;		
		}		
}
