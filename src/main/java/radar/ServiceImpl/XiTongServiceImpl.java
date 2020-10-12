package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radar.Entity.DynamicData;
import radar.Entity.Equip;
import radar.Entity.FaultRecord;
import radar.Entity.Radar;
import radar.Entity.System;
import radar.Repository.DynamicDataRepository;
import radar.Repository.FaultRepository;
import radar.Repository.XiTongRepository;
import radar.Dao.XiTongDao;
import radar.Service.XiTongService;
import radar.Tools.Faults;
import radar.Tools.Records;

@Service("XiTongServiceImpl")
public class XiTongServiceImpl implements XiTongService{
	@Autowired
	XiTongDao xiTongDao;
	@Autowired
	XiTongRepository xiTongRepository;
	
	@Autowired
	DynamicDataRepository dynamicDataRepository;
	
	@Autowired
	FaultRepository faultRepository;
	
	//系统表格数据获取
	@SuppressWarnings("rawtypes")
	@Override
	public Object[][] getXiTongInfo(Object[] paras) {
//		List<System> list = new ArrayList();
		@SuppressWarnings("unchecked")
		List<System> list = new ArrayList();
		list = xiTongDao.getXiTongInfo();
		Object[][] data  = new Object[list.size()][];
		for(int i=0;i<list.size();i++) {
			System s = list.get(i);
			String radarType = "";
			if(s.getRadarTypeId()!=null) {
				radarType=s.getRadarTypeId().getRadarTypeName();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Object[] o = {s.getSystemId(),s.getSystemName(),radarType};
			data[i] = o;
		}
		return data;
	}
	public Object[][] getEquipmentInfo(Object[] paras){
		List<Equip> list = new ArrayList<Equip>();
		list = xiTongDao.getEquipmentInfo();
		Object[][] data  = new Object[list.size()][];
		for(int i=0;i<list.size();i++) {
			Equip e = list.get(i);
			String xiTongName="";
			if(e.getSystemId()!=null) {
				xiTongName=e.getSystemId().getSystemName();
			}
			Object[] o = {e.getEquipId(),e.getEquipName(),xiTongName};
			data[i] = o;
		}
		return data;
		
	}
	public boolean add(System xiTong) {
		try {
			xiTongRepository.save(xiTong);
			return true;

		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<System> getXiTonglist() {
		List<System> list = new ArrayList<System>();
		list=xiTongDao.getXiTongInfo();
		return list;
	}
	public boolean add(Equip equip) {
		try {
			xiTongRepository.save(equip);
			return true;

		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 添加雷达运行状态数据
	 * @param radarId
	 * @param list1
	 */
	public void AddDynamicData(int radarId, List<Records> list1) {
		if(list1 != null && list1.size()>0) {
			for(int i=0;i<list1.size();i++) {
				DynamicData d = new DynamicData();
				Radar r = new Radar();
				r.setRadarId(radarId);
				d.setRadarId(r);
				d.setDataVaule(list1.get(i).getInfo());
				d.setCollectDate(list1.get(i).getTime());
				d.setDev(list1.get(i).getDev());
				dynamicDataRepository.save(d);
			}
		}
	}
	/**
	 * 添加雷达运行时的故障记录数据
	 * @param radarId
	 * @param list2
	 */
	public void AddFaultRecord(int radarId, List<Faults> list2) {
		if(list2 != null && list2.size() >0) {
			for(int i=0;i<list2.size();i++) {
				FaultRecord fr = new FaultRecord();
				Radar r = new Radar();
				r.setRadarId(radarId);
				fr.setRadarId(r);
				fr.setFaultReason(list2.get(i).getInfo());
				fr.setFaultDate(list2.get(i).getTime());
				fr.setDev(list2.get(i).getDev());
				faultRepository.save(fr);
			}
		}
	}

}
