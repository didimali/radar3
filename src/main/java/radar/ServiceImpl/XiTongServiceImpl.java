package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Entity.Equip;
import radar.Entity.System;
import radar.Repository.XiTongRepository;
import radar.Dao.XiTongDao;
import radar.Service.XiTongService;

@Service("XiTongServiceImpl")
public class XiTongServiceImpl implements XiTongService{
	@Autowired
	XiTongDao xiTongDao;
	@Autowired
	XiTongRepository xiTongRepository;
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

}
