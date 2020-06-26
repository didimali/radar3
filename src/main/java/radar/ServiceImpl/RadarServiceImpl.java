package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.RadarDao;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarType;
import radar.Repository.PartConsumeRepository;
import radar.Repository.PartsRepository;
import radar.Repository.RadarRepository;
import radar.Service.RadarService;

@Service("RadarServiceImpl")

public class RadarServiceImpl implements RadarService {
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
		public Object[] getDataForRadarComboBox() {
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
}