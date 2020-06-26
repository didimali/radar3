package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.ManagerDao;
import radar.Dao.PartConsumeDao;
import radar.Dao.RadarDao;
import radar.Dao.RadarForecastDao;
import radar.Dao.RadarTypeDao;
import radar.Dao.RepairPlanDao;
import radar.Entity.Manager;
import radar.Entity.PartConsume;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RadarType;
import radar.Entity.RepairPlan;
import radar.Service.AcuteForecastService;

@Service("AcuteForecastServiceImpl")
public class AcuteForecastServiceImpl implements AcuteForecastService{
	
	@Autowired
	RadarTypeDao radarTypeDao;
	
	@Autowired
	ManagerDao managerDao;
	
	@Autowired
	PartConsumeDao partConsumeDao;
	
	@Autowired
	RadarDao radarDao;
	
	@Autowired
	RadarForecastDao radarForecastDao;
	
	@Autowired
	RepairPlanDao repairPlanDao;
	/**
	 * 获取部队驻地类型
	 * @return
	 */
	public Object[] getLocationType(Object[] params) {
		Object[] result = {"All","高原","山地","平原","沿海","沙漠"};
		return result;		
	}
	
	/**
	 * 获取雷达型号
	 * @return
	 */
	public Object[] getRadarType(Object[] params) {
		Object[] result;
		List<RadarType> list = radarTypeDao.getRadarType();
		result = new Object[list.size()+1];
		result[0] = "All";
		for(int i=1;i<result.length;i++)
			result[i] = list.get(i-1).getRadarTypeName();
		return result;
		
	}
	
	/**
	 * 获取精准预测第一张总表的数据
	 * @param params
	 * @return
	 */
	public Object[][] getAcuteForecastTable1Data(Object[] params){
			
		List<Node> r = getRData();
		List<Node> pc = getPCData(r);
		List<Node> m = getMData(pc);
		Object[][] result = new Object[m.size()][];	
		
		for(int i=0;i<m.size();i++) {
			Node n = m.get(i);
			Object[] o = {i+1,n.managerName,n.locationType,n.radarTypeName,n.radarCount,n.rCount,n.yCount,n.gCount,n.pcCount};
			result[i] = o;
		}
		return result;		
	}
	/**
	 * 获取精准预测评第二张表的数据
	 * @param params
	 * @return
	 */
	public Object[][] getAcuteForecastTable2Data(Object[] params){
		String managerName = (String) params[0];
		String radarTypeName = (String)params[1];
		List<Radar> list = radarDao.getRadarDetails(managerName,radarTypeName);
		List<RadarForecast> list1 = radarForecastDao.getRadarForecastResult(managerName,radarTypeName);
		List<RepairPlan> list2  = repairPlanDao.getRepairPlanResult(managerName,radarTypeName);
		String[] a = {"绿","黄","红"};
		String[] b = {"未预测","已预测"};
		String[] c = {"暂缺","已生成"};
		Object[][] result = new Object[list.size()][];
		for(int i=0;i<result.length;i++) {
			Radar r = list.get(i);
			int bIndex = 0;
			int cIndex = 0;
			Object[] o = {i+1,r.getRadarName(),a[r.getRadarHealth()],null,null};
			for(int j=0;j<list1.size();j++) {
				RadarForecast rf = list1.get(j);
				if(rf.getRadarId().getRadarId() == r.getRadarId()) {
					bIndex = 1;
					break;
				}
			}
			o[3] = b[bIndex];
			for(int k=0;k<list2.size();k++) {
				RepairPlan rp = list2.get(k);
				if(rp.getRadarId().getRadarId() == r.getRadarId()) {
					cIndex = 1;
					break;
				}
			}
			o[4] = c[cIndex];			
			result[i] = o;
		}
		return result;		
	}
	
	/**
	 * 获取精准预测的第三张表格数据
	 * @param params
	 * @return
	 */
	public Object[][] getAcuteForecastTable3Data(Object[] params){
		String managerName = (String) params[0];
		String radarType = (String) params[1];
		String sDate = (String) params[2];
		String eDate = (String) params[3];
		List<Node1> list = getPartsConsumeDetails(managerName,radarType,sDate,eDate);		
		Object[][] result = new Object[list.size()][];
		for(int i=0;i<result.length;i++) {
			Node1 n = list.get(i);
			Object[] o = {i+1,n.partsName,n.pcCount};
			result[i] =o;
		}		
		return result;		
	}
	
	private List<Node1> getPartsConsumeDetails(String managerName, String radarType, String sDate, String eDate) {
		List<PartConsume> list = partConsumeDao.getPartsConsumeDetails(managerName,radarType,sDate,eDate);
		List<Node1> result = new ArrayList<Node1>();
		for(PartConsume pc:list) {
			Node1 node = new Node1(pc.getPartsId().getPartsName(),pc.getpConsumeCount());
			if(result.size() == 0) {
				result.add(node);
				continue;
			}				
			boolean added = true;
			for(int i=0;i<result.size();i++) {
				Node1 n = result.get(i);
				if(n.partsName.equals(node.partsName)) {
					result.get(i).pcCount = n.pcCount + node.pcCount;
					added = false;
					break;
				}
			}
			if(added) {
				result.add(node);
			}
		}
		return result;
	}

	/**
	 * 获取某部队各型号雷达饼图数据
	 * @return
	 */
	public DefaultPieDataset getDataForPie(Object[] params) {
		String managerName = (String) params[0];
		DefaultPieDataset result = new DefaultPieDataset();
		List<Object> list = radarDao.getRadarCountByRadarType(managerName);
		Iterator it  = list.iterator();
		while(it.hasNext()) {
			Object[] o = (Object[]) it.next();
			String name = (String) o[1];
			int value = Integer.parseInt(o[0].toString());
			result.setValue(name,value);
		}
		return result;
		
	}
	
	/**
	 * 获取某部队某型号雷达各状态饼图数据
	 * @return
	 */
	public DefaultPieDataset getDataForPie1(Object[] params) {
		String managerName = (String) params[0];
		String radarTypeName = (String) params[1];
		DefaultPieDataset result = new DefaultPieDataset();
		List<Object> list = radarDao.getRadarCountByRadarHeath(managerName,radarTypeName);
		Iterator it  = list.iterator();
		String[] h = {"绿","黄","红"};
		int[][] data = {{0,0},{1,0},{2,0}};
		while(it.hasNext()) {
			Object[] o = (Object[]) it.next();
			int index = Integer.parseInt(o[1].toString());
			int value = Integer.parseInt(o[0].toString());
			data[index][1] = value;
		}
		for(int i=0;i<data.length;i++) {
			result.setValue(h[data[i][0]], data[i][1]);
		}
		return result;
		
	}
	/**
	 * 获取某部队某型号雷达备件消耗饼图数据
	 * @param params
	 * @return
	 */
	public DefaultPieDataset getDataForPartConsumePie(Object[] params) {
		String managerName = (String) params[0];
		String radarType = (String) params[1];
		String sDate = (String) params[2];
		String eDate = (String) params[3];		
		List<Node1> list = getPartsConsumeDetails(managerName,radarType,sDate,eDate);		
		DefaultPieDataset result = new DefaultPieDataset();
		for(int i=0;i<list.size();i++) {
			Node1 n = list.get(i);
			result.setValue(n.partsName,n.pcCount);
		}	
		return result;
		
	}
	
	/**
	 * 获取某部队某型号雷达备件消耗折线图数据
	 * @param params
	 * @return
	 */
	public DefaultCategoryDataset getDataForPartConsumeLine(Object[] params) {
		String managerName = (String) params[0];
		String radarType = (String) params[1];
		String sDate = (String) params[2];
		String eDate = (String) params[3];	
		List<PartConsume> list = partConsumeDao.getPartsConsumeDetails(managerName,radarType,sDate,eDate);
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<list.size();i++) {
			PartConsume item = list.get(i);
			result.addValue(item.getpConsumeCount(),item.getPartsId().getPartsName(),formatter.format(item.getConsumeDate()));
		}
		return result;		
	}


	private List<Node> getRData() {
		List<Radar> r = radarDao.getRadarCountByHealth();	//部队，型号，雷达状态，数量
		List<Node> result = new ArrayList<Node>();
		for(int i=0;i<r.size();i++) {
			Radar radar = r.get(i);
			Node node = new Node(radar.getManagerId().getManagerName(),radar.getRadarTypeId().getRadarTypeName());
			if(result.size() == 0) {
				result.add(node);
			}
			boolean added = true;
			for(int j=0;j<result.size();j++) {
				Node n = result.get(j);
				if(node.managerName.equals(n.managerName) && node.radarTypeName.equals(n.radarTypeName)) {
					if(radar.getRadarHealth() == 0) {
						result.get(j).gCount++;
					}
					else if(radar.getRadarHealth() == 1) {
						result.get(j).yCount++;
					}
					else {
						result.get(j).rCount++;
					}
					result.get(j).radarCount++;
					added = false;
					break;
				}
			}
			if(added) {
				if(radar.getRadarHealth() == 0) {
					node.gCount++;
				}
				else if(radar.getRadarHealth() == 1) {
					node.yCount++;
				}
				else {
					node.rCount++;
				}
				node.radarCount++;
				result.add(node);
			}
				
		}
		return result;
	}

	private List<Node> getPCData(List<Node> r) {
		List<PartConsume> pc = partConsumeDao.getPartConsume();	//部队，备件消耗数量
		for(int i=0;i<pc.size();i++) {
			PartConsume p = pc.get(i);
			Node n = new Node(p.getManagerId().getManagerName(),p.getPartsId().getRadarTypeId().getRadarTypeName());
			for(int j=0;j<r.size();j++) {
				Node node = r.get(j);
				if(n.managerName.equals(node.managerName) && n.radarTypeName.equals(node.radarTypeName)) {
					r.get(j).pcCount = node.pcCount+p.getpConsumeCount();
					break;
				}						
			}		
		}		
		return r;
	}

	private List<Node> getMData(List<Node> pc) {
		List<Manager> m = managerDao.getManager(); 
		String[] lt = {"高原","山地","平原","沿海","沙漠"};
		for(int i=0;i<pc.size();i++) {
			Node node = pc.get(i);
			for(int j=0;j<m.size();j++) {
				Manager t = m.get(j);
				if(t.getManagerName().equals(node.managerName)) {
					pc.get(i).locationType = lt[t.getManagerLocation()];
				}
					
			}
		}
		return pc;
	}
	
	
	private class Node{
		private String managerName;
		private String locationType;
		private String radarTypeName;
		private int radarCount = 0;
		private int rCount = 0;
		private int yCount = 0;
		private int gCount = 0;
		private int pcCount = 0;
		
		public Node(String managerName, String radarTypeName) {
			this.managerName = managerName;
			this.radarTypeName = radarTypeName;
		}
	}
	
	private class Node1{
		private String partsName;
		private int pcCount;
		
		public Node1(String partsName, Integer pcCount) {
			this.partsName = partsName;
			this.pcCount = pcCount;
		}
	}

}
