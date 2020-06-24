package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.TestDao;
import radar.Entity.DynamicData;
import radar.Entity.Manager;
import radar.Entity.Radar;
import radar.Service.TestService;

@Service("TestServiceImpl")

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestServiceImpl implements TestService{
	
	@Autowired
	TestDao testDao;

	//表格数据获取
	@Override
	public Object[][] getManagers(Object[] params) {
		List<Manager> list = new ArrayList();
		list = testDao.getManagers();
		Object[][] data  = new Object[list.size()][];
		for(int i=0;i<list.size();i++) {
			Manager m = list.get(i);
			Object[] o = {m.getManagerId(),m.getManagerName(),m.getManagerLocation(),m.getManagerStatus()};
			data[i] = o;
		}
		return data;
	}

	//下拉框数据获取
	public Object[] getRadars(Object[] params) {
		System.out.println("我来表演获取UI传参了");
		//UI设置的传参：Object[] params = {"pigan","皮干",1};
		String p1 = (String) params[0];
		String p2 = (String) params[1];
		int p3 = (int) params[2];
		System.out.println("UI传参分别为："+p1+" "+p2+" "+p3);
		List<Radar> list = new ArrayList();
		list = testDao.getRadars();
		Object[] result = new Object[list.size()];
		for(int i=0;i<list.size();i++) {
			Radar r = list.get(i);
			result[i] = r.getRadarName();
		}
		return result;
	}

	//获取饼图数据
	@Override
	public DefaultPieDataset getPieData(Object[] params) {
		List<Object> list = new ArrayList();
		list = testDao.getPieData();
		DefaultPieDataset data = new DefaultPieDataset();
		Iterator it  = list.iterator();
		while(it.hasNext()) {
			Object[] o = (Object[]) it.next();
			data.setValue(String.valueOf(o[0]),Integer.parseInt(o[1].toString()));
		}
		return data;
	}

	//获取折线图数据
	@Override
	public DefaultCategoryDataset getLineData(Object[] params) {
		List<DynamicData> list = new ArrayList();
		list = testDao.getLineData();
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		for(int i=0;i<list.size();i++) {
			DynamicData data = list.get(i);
			result.setValue(Float.valueOf(data.getDataVaule()),null,data.getCollectDate());
		}
		return result;
	}

	@Override
	public DefaultCategoryDataset getBarData(Object[] params) {
		return null;
	}
}
