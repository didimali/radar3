package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.PartsDao;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.Service.PartsService;

@Service("PartsServiceImpl")

public class PartsServiceImpl implements PartsService{
	@Autowired
	PartsDao partsDao;
	//备件种类表格数据获取
	@Override
	public Object[][] getPartsType(Object[] params) {
		List<Parts> list = new ArrayList<Parts>();
		list = partsDao.getPartsType();
		Object[][] data  = new Object[list.size()][];
		for(int i=0;i<list.size();i++) {
			Parts p = list.get(i);
			String radarType="";
			if(p.getRadarTypeId()!=null) {
				radarType=p.getRadarTypeId().getRadarTypeName();
			}
			Object[] o = {p.getPartsId(),p.getPartsName(),radarType};
			data[i] = o;
		}
		return data;
	}
	//备件消耗表格数据获取
		@Override
		public Object[][] getPartsConsume(Object[] params) {
			List<PartConsume> list = new ArrayList<PartConsume>();
			list = partsDao.getPartsConsume();
			Object[][] data  = new Object[list.size()][];
			for(int i=0;i<list.size();i++) {
				PartConsume p = list.get(i);
				String partName = "";
				if(p.getPartsId()!=null) {
					partName=p.getPartsId().getPartsName();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Object[] o = {i+1,partName,p.getpConsumeCount(),sdf.format(p.getConsumeDate()),p.getManagerId().getManagerName()};
				data[i] = o;
			}
			return data;
		}
		//获取备件下拉列表
		@Override
		public Object[] getDataForPartsComboBox(Object[] params) {
			List<Parts> list = new ArrayList<Parts>();
			list =  partsDao.getPartsType();
			Object[] result = new Object[list.size()];
			for(int i=0;i<list.size();i++) {
				Parts P = list.get(i);
				result[i] =P.getPartsName();
			}
			return result;
		}
}
