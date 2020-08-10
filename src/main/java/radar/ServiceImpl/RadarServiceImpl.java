package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.RadarDao;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RadarType;
import radar.Entity.RepairPlan;
import radar.Repository.PartConsumeRepository;
import radar.Repository.PartsRepository;
import radar.Repository.RadarRepository;
import radar.Service.RadarService;

@Service("RadarServiceImpl")

public class RadarServiceImpl implements RadarService{
	
	@Autowired
	RadarDao RadarDao;
	
	@Override
	public Object[][] getRadarList(String id){
		List<Radar> Rlist = RadarDao.getRadarsByType(id);
		Object[][] data  = new Object[Rlist.size()][];
		String state;
		String fault;
		String repair;
		for(int i=0;i<Rlist.size();i++) {
			Radar r=Rlist.get(i);
			if(r.getRadarHealth() == 0) {
				state="健康";
			}else if(r.getRadarHealth()==1) {
				state="良好";
			}else if(r.getRadarHealth()==2) {
				state="较差";
			}else {
				state="未预测";
			}
			List<RadarForecast> Flist=RadarDao.getFaultForecast(r.getRadarId());
			if(Flist.size()==0) {
				fault="未预测";
			}else {
				fault="已预测";
			}
			List<RepairPlan> Plist=RadarDao.getRepairPlan(r.getRadarId());
			if(Plist.size()==0) {
				repair="未预测";
			}else {
				repair="已预测";
			}	
			Object[] o = {i+1,r.getRadarName(),r.getManagerId().getManagerName(),state,fault,repair};
			data[i] = o;
		}
		return data;
	}
	
	@Override
	public String getType(String id){
		List<Radar> Rlist = RadarDao.getRadarsByType(id);
        String type=Rlist.get(0).getRadarTypeId().getRadarTypeName();
		return type;
	}
	
	
	@Override
	public Object[][] countRadarList(){
		int num1 = RadarDao.countRadar(1);
		int num2 = RadarDao.countRadar(2);
		int Rhealth1=RadarDao.countHealth(1,2);
		int Yhealth1=RadarDao.countHealth(1,1);
		int Ghealth1=RadarDao.countHealth(1,0);
		int Rhealth2=RadarDao.countHealth(2,2);
		int Yhealth2=RadarDao.countHealth(2,1);
		int Ghealth2=RadarDao.countHealth(2,0);		
		Object[][] data  = new Object[2][6];
        data[0][0] = 1;
        data[0][1] = "I型雷达";
        data[0][2] = num1;		
        data[0][3] = Rhealth1;	
        data[0][4] = Yhealth1; 		
        data[0][5] = Ghealth1;
        data[1][0] = 2;
        data[1][1] = "II型雷达";
        data[1][2] = num2;		
        data[1][3] = Rhealth2;	
        data[1][4] = Yhealth2; 		
        data[1][5] = Ghealth2;
        return data;   
	}
	
	public PieDataset createDataset1() {
	    DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
		int Rhealth1=RadarDao.countHealth(1,2);
		int Yhealth1=RadarDao.countHealth(1,1);
		int Ghealth1=RadarDao.countHealth(1,0);
		defaultPieDataset.setValue("绿", Ghealth1);
	    defaultPieDataset.setValue("黄", Yhealth1);
	    defaultPieDataset.setValue("红", Rhealth1);
	    return (PieDataset)defaultPieDataset;
	  }
	
	public PieDataset createDataset2() {
	    DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
		int Rhealth1=RadarDao.countHealth(2,2);
		int Yhealth1=RadarDao.countHealth(2,1);
		int Ghealth1=RadarDao.countHealth(2,0);
	    defaultPieDataset.setValue("绿", Ghealth1);
	    defaultPieDataset.setValue("黄", Yhealth1);
	    defaultPieDataset.setValue("红", Rhealth1);
	    return (PieDataset)defaultPieDataset;
	  }
	@Autowired
	RadarDao radarDao;
	@Autowired
	RadarRepository radarRepository;
	@Autowired
	PartsRepository partsRepository;
	@Autowired
	PartConsumeRepository partConsumeRepository;
	//获取雷达下拉列表
		@SuppressWarnings("rawtypes")
		@Override
		public Object[] getDataForRadarComboBox(Object[] params) {
			@SuppressWarnings("unchecked")
			List<Radar> list = new ArrayList();
			list = radarDao.getRadars();
			Object[] result = new Object[list.size()];
			for(int i=0;i<list.size();i++) {
				Radar r = list.get(i);
				result[i] = r.getRadarName();
			}
			return result;
		}
		@SuppressWarnings("unchecked")
		public List<RadarType> selectRadarType(String name1) {
			// TODO Auto-generated method stub
			@SuppressWarnings("rawtypes")
			List<RadarType> list = new ArrayList();
			list = radarDao.selectRadarType(name1);
			return list;
		}
		public Boolean addRadar(Radar radar) {
			try {
				radarRepository.save(radar);
				return true;
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		public boolean deleteRadar(String radarName) {
			// TODO Auto-generated method stub
			return radarDao.deleteRadar(radarName);
		}
		
		@SuppressWarnings("rawtypes")
		public List<RadarType> getRadarTypes() {
			@SuppressWarnings("unchecked")
			List<RadarType> list = new ArrayList();
			list = radarDao.getRadarTypes();
			return list;
		}
		public boolean add(Parts part) {
			try {
				partsRepository.save(part);
				return true;

			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		public List<Parts> getParts() {
			List<Parts> list = new ArrayList();
			list = radarDao.getParts();
			return list;
		}
		public boolean add(PartConsume partConsume) {
			try {
				partConsumeRepository.save(partConsume);
				return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
}
		public Object[] selectLocationByRadarName(String choosenRadarName) {
			List<Radar>list =radarDao.selectRadar(choosenRadarName);
			Radar r= list.get(0);
			Object[] o = new Object[1];
			String radarType="";
			if(r.getRadarTypeId()!=null) {
				radarType=r.getRadarTypeId().getRadarTypeName();
			}
		
			o[0]=radarType;

			return o;
		}
		public Object[] selectManagerNameByRadarName(String choosenRadarName) {
			List<Radar>list =radarDao.selectRadar(choosenRadarName);
			Radar r= list.get(0);
			Object[] o = new Object[1];
			String managerName="";
			if(r.getManagerId()!=null) {
				managerName=r.getManagerId().getManagerName();
			}
		
			o[0]=managerName;

			return o;
		}
		public Object[] selectHealthByRadarName(String choosenRadarName) {
			List<Radar>list =radarDao.selectRadar(choosenRadarName);
			Radar r= list.get(0);
			Object[] o = new Object[1];
			String health="";
			if(Integer.toString(r.getRadarHealth()).equals("0")) {
				health="绿";
			}else if(Integer.toString(r.getRadarHealth()).equals("1")) {
				health="黄";

			}else if(Integer.toString(r.getRadarHealth()).equals("2")) {
				health="蓝";

			}
		
			o[0]=health;

			return o;
		}
		public  List<Radar> getAllRadars() {
			// TODO Auto-generated method stub
			return radarDao.getRadars();
		}
		@Override
		public Object[][] getRadars(Object[] params) {
			Object[][] result = {};
			return result;
		}

		@Override
		public Object[][] getRadarsByManagerId(Object[] params) {
			int managerId = (int) params[0];
			List<Radar> list = radarDao.getRadarsByManagerId(managerId);
			int N = list.size();
			Object[][] result = new Object[N+1][2];
			if(N == 0)
				return result;
			
			for(int i=0;i<N;i++) {
				result[i+1][1] = list.get(i).getRadarName();
				result[i+1][0] = list.get(i).getRadarId();
			}
			return result;	
		}
}
