package radar.ServiceImpl;

import java.util.List;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.RadarDao;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RepairPlan;
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
	    defaultPieDataset.setValue("健康", Ghealth1);
	    defaultPieDataset.setValue("不良", Yhealth1);
	    defaultPieDataset.setValue("较差", Rhealth1);
	    return (PieDataset)defaultPieDataset;
	  }
	
	public PieDataset createDataset2() {
	    DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
		int Rhealth1=RadarDao.countHealth(2,2);
		int Yhealth1=RadarDao.countHealth(2,1);
		int Ghealth1=RadarDao.countHealth(2,0);
	    defaultPieDataset.setValue("健康", Ghealth1);
	    defaultPieDataset.setValue("不良", Yhealth1);
	    defaultPieDataset.setValue("较差", Rhealth1);
	    return (PieDataset)defaultPieDataset;
	  }
}
