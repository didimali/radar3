package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.PartsDao;
import radar.Dao.TestDao;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.Service.PartsService;

@Service("PartsServiceImpl")

public class PartsServiceImpl implements PartsService{
	@Autowired
	PartsDao partsDao;
	//备件种类表格数据获取
	@Override
	public Object[][] getPartsType(Object[] paras) {
		List<Parts> list = new ArrayList();
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
		public Object[][] getPartsConsume(Object[] paras) {
			List<PartConsume> list = new ArrayList();
			list = partsDao.getPartsConsume();
			Object[][] data  = new Object[list.size()][];
			for(int i=0;i<list.size();i++) {
				PartConsume p = list.get(i);
				String partName = "";
				if(p.getPartsId()!=null) {
					partName=p.getPartsId().getPartsName();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Object[] o = {p.getConsumeId(),partName,p.getpConsumeCount(),sdf.format(p.getConsumeDate())};
				data[i] = o;
			}
			return data;
		}
}
