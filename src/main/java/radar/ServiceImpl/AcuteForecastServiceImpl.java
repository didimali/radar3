package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.EquipDao;
import radar.Dao.ManagerDao;
import radar.Dao.PartConsumeDao;
import radar.Dao.RadarDao;
import radar.Dao.RadarForecastDao;
import radar.Dao.RadarHealthDao;
import radar.Dao.RadarTypeDao;
import radar.Dao.RepairContentDao;
import radar.Dao.RepairPlanDao;
import radar.Dao.SysOrEquipHealthDao;
import radar.Dao.SystemDao;
import radar.Entity.Equip;
import radar.Entity.Manager;
import radar.Entity.PartConsume;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RadarHealth;
import radar.Entity.RadarType;
import radar.Entity.RepairContent;
import radar.Entity.RepairPlan;
import radar.Entity.SysOrEquipHealth;

@Service("AcuteForecastServiceImpl")
public class AcuteForecastServiceImpl {
	
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
	RadarHealthDao radarHealthDao;
	
	@Autowired
	SysOrEquipHealthDao sysOrEquipHealthDao;
	
	@Autowired
	RepairPlanDao repairPlanDao;
	
	@Autowired
	SystemDao systemDao;
	
	@Autowired
	EquipDao equipDao;
	
	@Autowired
	RepairContentDao repairContentDao;
	
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
			Object[] o = {n.managerId,i+1,n.managerName,n.locationType,n.radarTypeName,n.radarCount,n.rCount,n.yCount,n.gCount,n.pcCount};
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
		int managerId = (int) params[2];
		String radarTypeName = (String)params[1];
		List<Radar> list = radarDao.getRadarDetails(managerId,radarTypeName);
		List<RadarForecast> list1 = radarForecastDao.getRadarForecastResult(managerId,radarTypeName);
		List<RepairPlan> list2  = repairPlanDao.getRepairPlanResult(managerId,radarTypeName);
		String[] a = {"绿","黄","红"};
		String[] b = {"未预测","已预测"};
		String[] c = {"暂缺","已生成"};
		Object[][] result = new Object[list.size()][];
		for(int i=0;i<result.length;i++) {
			Radar r = list.get(i);
			int bIndex = 0;
			int cIndex = 0;
			Object[] o = {r.getRadarId(),i+1,r.getRadarName(),a[r.getRadarHealth()],null,null};
			for(int j=0;j<list1.size();j++) {
				RadarForecast rf = list1.get(j);
				if(rf.getRadarId().getRadarId() == r.getRadarId()) {
					bIndex = 1;
					break;
				}
			}
			o[4] = b[bIndex];
			for(int k=0;k<list2.size();k++) {
				RepairPlan rp = list2.get(k);
				if(rp.getRadarId().getRadarId() == r.getRadarId()) {
					cIndex = 1;
					break;
				}
			}
			o[5] = c[cIndex];			
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
		int managerId = (int) params[4];
		String radarType = (String) params[1];
		String sDate = (String) params[2];
		String eDate = (String) params[3];
		List<Node1> list = getPartsConsumeDetails(managerId,radarType,sDate,eDate);		
		Object[][] result = new Object[list.size()][];
		for(int i=0;i<result.length;i++) {
			Node1 n = list.get(i);
			Object[] o = {i+1,n.partsName,n.pcCount};
			result[i] =o;
		}		
		return result;		
	}
	
	/**
	 * 获取某雷达健康评估结果表格的数据
	 * @param params
	 * @return
	 */
	public Object[][] getDataForRadarHealthResultTable(Object[] params){
		int radarId = (int) params[0];
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		//获取整机雷达的健康评估结果
		List<RadarHealth> list1 = radarHealthDao.getTotalRadarHealthResult(radarId);
		if(list1.size() == 0)
			return null ;
		int healthResultId = list1.get(0).getHealthResultId();
		String date = ft.format(list1.get(0).getAssessDate());
		List<SysOrEquipHealth> list2 = sysOrEquipHealthDao.getRadarHealthResultContent(healthResultId);
		int length = list1.size()+list2.size();
		Object[][] result = new Object[length][];
		String[] health = {"绿","黄","红"};
		Object[] t = {1,"整机",health[Integer.parseInt(list1.get(0).getAssessResult())],date};
		result[0] = t;
		for(int i=1;i<length;i++) {
			SysOrEquipHealth s = list2.get(i-1);
			Object[] o = {i+1,s.getSystemId().getSystemName(),health[Integer.parseInt(s.getAssResult())],date};
			result[i] = o;
		}
		return result;
		
	}
	/**
	 * 获取某雷达整机健康变化趋势图 数据
	 * @param params
	 * @return
	 */
	public DefaultCategoryDataset getDataForRadarHiLine(Object[] params) {
		int radarId = (int) params[0];
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		List<RadarHealth> list = radarHealthDao.getDataForRadarHiLine(radarId);
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		int[] h = {2,1,0};
		for(int i=list.size()-1;i>-1;i--) {
			RadarHealth r = list.get(i);
			String data = ft.format(r.getAssessDate());
			int assessResult = Integer.parseInt(r.getAssessResult());
			result.addValue(h[assessResult],"HI",data);			
		}
		return result;		
	}
	
	/**
	 * 获取某雷达故障预测结果
	 * @param params
	 * @return
	 */
	public Object[][] getDataForRadarForecastTable(Object[] params){
		int radarId = (int) params[0];
		List<RadarForecast> list =  radarForecastDao.getRadarForecastResult(radarId);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		List<radar.Entity.System> s = systemDao.getAllSystem();
		List<Equip> e = equipDao.getAllEquip();
		Object[][] result = new Object[list.size()][];
		for(int i=0;i<list.size();i++) {
			RadarForecast rf = list.get(i);
			String date = sf.format(rf.getForecastDate());
			String name = null;
			String key = rf.getFaultTypeId().getFaultLocaltion();
			int id = Integer.parseInt(key.substring(1,key.length()));
			if(key.contains("s")) 
				for(int j=0;j<s.size();j++) {
					radar.Entity.System item = s.get(j);
					if(item.getSystemId() == id) {
						name = item.getSystemName();
						break;
					}						
				}
			else
				for(int j=0;j<e.size();j++) {
					Equip item = e.get(j);
					if(item.getEquipId() == id) {
						name = item.getEquipName();
						break;
					}						
				}
			Object[] o = {i+1,name,date};
			result[i] = o;
		}
		return result;		
	}
	
	/**
	 * 获取某部队维修计划内容
	 * @param params
	 * @return
	 */
	public Object[][] getDataForRadarRepairPlanContent(Object[] params){
		int radarId = (int) params[0];
		List<RepairContent> list = repairContentDao.getRadarRepairContent(radarId);
		int length = list.size();
		Object[][] result = new Object[length][];
		for(int i=0;i<length;i++) {
			RepairContent rc = list.get(i);
			Object[] o = {i+1,rc.getPartsId().getPartsName(),rc.getPartsCount()};
			result[i] = o;
		}
		return result;		
	}
	
	private List<Node1> getPartsConsumeDetails(int managerId, String radarType, String sDate, String eDate) {
		List<PartConsume> list = partConsumeDao.getPartsConsumeDetails(managerId,radarType,sDate,eDate);
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
		int managerId = (int) params[2];
		DefaultPieDataset result = new DefaultPieDataset();
		List<Object> list = radarDao.getRadarCountByRadarType(managerId);
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
		int managerId = (int) params[2];
		String radarTypeName = (String) params[1];
		DefaultPieDataset result = new DefaultPieDataset();
		List<Object> list = radarDao.getRadarCountByRadarHeath(managerId,radarTypeName);
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
		int managerId = (int) params[4];
		String radarType = (String) params[1];
		String sDate = (String) params[2];
		String eDate = (String) params[3];		
		List<Node1> list = getPartsConsumeDetails(managerId,radarType,sDate,eDate);		
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
		int managerId = (int) params[4];
		String radarType = (String) params[1];
		String sDate = (String) params[2];
		String eDate = (String) params[3];	
		List<PartConsume> list = partConsumeDao.getPartsConsumeDetails(managerId,radarType,sDate,eDate);
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<list.size();i++) {
			PartConsume item = list.get(i);
			result.addValue(item.getpConsumeCount(),item.getPartsId().getPartsName(),formatter.format(item.getConsumeDate()));
		}
		return result;		
	}
	
	/**
	 * 获取某雷达备件维修计划时间
	 * @param params
	 * @return
	 */
	public String getDataForRadarRepairPlanDate(Object[] params) {
		int radarId = (int) params[0];
		List<RepairPlan> list = repairPlanDao.geRadarRepairPlanDate(radarId);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if(list.size() == 0)
			return null;
		String result = sf.format(list.get(0).getRepairPlanDate());
		return result;
		
	}
	
	/**
	 * 获取雷达组织结构表的数据
	 * @param params
	 * @return
	 */
	public Object[][] getDataForRadarStructTable(Object[] params){
		List<Equip> list  = equipDao.getAllEquip();
		int length = list.size();
		if(length == 0)
			return null;
		Object[][] result = new Object[length][];
		for(int i=0;i<length;i++) {
			Equip e = list.get(i);
			Object[] o = {i+1,e.getSystemId().getRadarTypeId().getRadarTypeName(),
					e.getSystemId().getSystemName(),e.getEquipName()};
			result[i] = o;
		}
		return result;
		
	}


	private List<Node> getRData() {
		List<Radar> r = radarDao.getRadarCountByHealth();	//部队，型号，雷达状态，数量
		List<Node> result = new ArrayList<Node>();
		for(int i=0;i<r.size();i++) {
			Radar radar = r.get(i);
			Node node = new Node(radar.getManagerId().getManagerId(),radar.getManagerId().getManagerName(),radar.getRadarTypeId().getRadarTypeName());
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
			Node n = new Node(p.getManagerId().getManagerId(),p.getManagerId().getManagerName(),p.getPartsId().getRadarTypeId().getRadarTypeName());
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
		private int managerId;
		private String managerName;
		private String locationType;
		private String radarTypeName;
		private int radarCount = 0;
		private int rCount = 0;
		private int yCount = 0;
		private int gCount = 0;
		private int pcCount = 0;
		
		public Node(int managerId,String managerName, String radarTypeName) {
			this.managerId = managerId;
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
