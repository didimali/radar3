package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import radar.Dao.ManagerDao;
import radar.Entity.Manager;
import radar.Entity.RadarType;
import radar.Repository.ManagerRepository;
import radar.Service.ManagerService;

@Service("ManagerServiceImpl")
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	ManagerDao managerDao;
	@Autowired
	ManagerRepository managerRepository;
	/**
	 * 获取部队列表所需数据
	 * @author madi
	 */
	@Override
	public Object[][] getDataForManagerList() {
		Object[][] result = new Object[18][6];
		for(int i=0;i<result.length;i++) {
			String name,location;
			int j = i+1;
			if(j<10) {
				name = "部队00"+j;
				location = "位置00"+j;
			}				
			else {
				name = "radar0"+j;
				location = "位置0"+j;
			}
			int faultCounts = (int) (1+Math.random()*10);
			int healthCounts = (int) (50+Math.random()*100);
			int hCounts = (int) (25+Math.random()*50);
			Object[] o = {j,name,location,faultCounts,healthCounts,hCounts};
			result[i] = o;			
		}
		return result;
	}

	@Override
	public DefaultPieDataset getDataForManagerDetailsHealthStatus() {
		DefaultPieDataset result= new DefaultPieDataset();
		int faultCounts = (int) (1+Math.random()*10);
		int healthCounts = (int) (50+Math.random()*100);
		int hCounts = (int) (25+Math.random()*50);
		result.setValue("健康雷达",healthCounts);
		result.setValue("亚健康雷达",hCounts);
		result.setValue("故障雷达",faultCounts);
		return result;
	}

	@Override
	public DefaultPieDataset getDataForManagerDetailsPartsComsume() {
		DefaultPieDataset result= new DefaultPieDataset();
		for(int i=0;i<8;i++) {
			result.setValue("备件"+(i+1),(int) (1+Math.random()*10));
		}
		return result;
	}

	@Override
	public DefaultCategoryDataset getDataForManagerDetailsHistoryHealthStatus() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();		
		for(int i=1;i<16;i++) {
			String date = "2020-05-";
			if(i<10)
				date = date +"0"+i;
			else
				date = date+i;
			result.addValue((int) (25+Math.random()*100),"健康雷达",date);
			result.addValue((int) (10+Math.random()*10),"亚健康雷达",date);
			result.addValue((int) (2+Math.random()*5),"故障雷达",date);
		}
		return result;
	}
	
	public DefaultCategoryDataset getDataForRadarDynamicDataLine() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();		
		for(int i=1;i<30;i++) {
			String date = "2020-05-";
			if(i<10)
				date = "2020-05-0"+i;
			else if(i>=10 && i< 32)
				date = "2020-05-"+i;
			else
				date = "2020-06-0"+(i-31);
			result.addValue((int) (25+Math.random()*10),"参数1",date);
			result.addValue((int) (10+Math.random()*10),"参数2",date);
			result.addValue((int) (5+Math.random()*10),"参数3",date);
		}
		return result;
	}

	@Override
	public DefaultCategoryDataset getDataForManagerDetailsHistoryPartsComsume() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();		
		for(int i=1;i<8;i++) {
			String date = "2020-05-";
			if(i<10)
				date = date +"0"+i;
			else
				date = date+i;
			for(int j=1;j<9;j++) {
				result.addValue((int)(1+Math.random()*10),"备件"+j,date);
			}
		}
		return result;
	}
//获取部队下拉列表
	@Override
	public Object[] getDataForManagerComboBox(Object[] params) {
		List<Manager> list = new ArrayList<Manager>();
		list = managerDao.getManagers();
		Object[] result = new Object[list.size()];
		for(int i=0;i<list.size();i++) {
			Manager m = list.get(i);
			result[i] = m.getManagerName();
		}
		return result;
	}
	
	/**
	 * 获取部队驻地类型
	 * @return
	 */
	public Object[] getLocationType(Object[] params) {
		Object[] result = {"","高原","山地","平原","沿海","沙漠"};
		return result;		
	}
	
	/**
	 * 获取部队驻地类型
	 * @return
	 */
	public Object[] getHealthType(Object[] params) {
		Object[] result = {"", "绿","黄","红"};
		return result;		
	}
	/**
	 * 获取部队驻地类型
	 * @return
	 */
	public Object[] getRadarType(Object[] params) {
		Object[] result = {"", "I型雷达","II型雷达"};
		return result;		
	}

	@Override
	public Object[] getDataForManagerLocationComboBox() {
		Object[] result = new Object[8];
		String name = "位置";
		for(int i=1;i<9;i++)
			result[i-1] = name+i;
		return result;
	}

	@Override
	public Object[][] getDataForManagerFaultTable() {
		Object[][] result = new Object[10][4];
		Object[][] counts = getDataForManagerFault();
		for(int i=0;i<result.length;i++) {
			String name;
			if(i+1<10) {
				name = "部队00"+(i+1);
			}				
			else {
				name = "部队0"+(i+1);
			}
			Object[] o = {i+1,name,counts[i][0],counts[i][1]};
			result[i] = o;
			
		}
		return result;
	}
	
	public DefaultCategoryDataset getDataForManagerFaultBarChart() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();	
		Object[][] counts = getDataForManagerFault();
		for(int i=0;i<10;i++) {
			
			result.addValue((int) (counts[i][0]),"雷达总数","部队"+i+1);
			result.addValue((int) (counts[i][1]),"故障雷达","部队"+i+1);
		}
		return result;
	}
	
	public DefaultPieDataset getDataForManagerFaultPieChart() {
		DefaultPieDataset result= new DefaultPieDataset();
		Object[][] counts = getDataForManagerFault();
		int faultCount = (int) counts[4][1];
		int totalCount = (int) counts[4][0];
		result.setValue("故障雷达",faultCount);
		result.setValue("非故障雷达",totalCount-faultCount);
		return result;
	}
	
	public DefaultCategoryDataset getDataForManagerFaultLineChart() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();	
		Object[][] counts = getDataForManagerFault();
		
		for(int i=1;i<8;i++) {
			String date = "2020-0"+i;
			int faultCount = (int) counts[4][1];
			faultCount = faultCount + (int)(1+Math.random()*10);
			for(int j=1;j<9;j++) {
				result.addValue(faultCount,"故障雷达数量",date);
			}
		}
		return result;
	}
	
	private Object[][] getDataForManagerFault(){
		Object[][] result = {{108,19},{120,17},{100,15},{105,15},{107,14},{109,12},{108,10},{101,5},{120,0},{111,0}};
		return result;
	}
	
	private Object[][] getDataForManagersHealth(){
		Object[][] result = {{108,19,8},{120,17,7},{100,15,6},{105,15,6},{107,14,5},{109,12,4},{108,10,3},{101,5,3},{120,0,2},{111,0,1}};
		return result;
	}
	
	public Object[][] getDataForManagerHealth(){
		Object[][] result = new Object[10][5];
		Object[][] data = getDataForManagersHealth();
		for(int i=0;i<result.length;i++) {
			String name;
			if(i+1<10) {
				name = "部队00"+(i+1);
			}				
			else {
				name = "部队0"+(i+1);
			}
			Object[] o = {i+1,name,data[i][0],data[i][1],data[i][2]};
			result[i] = o;
			
		}
		return result;
	}
	
	public DefaultPieDataset getDataForManagerHealthPie() {
		DefaultPieDataset result = new DefaultPieDataset();
		Object[][] data = getDataForManagersHealth();
		result.setValue("健康雷达", (Number) data[3][0]);
		result.setValue("一般雷达",(Number) data[3][1]);
		result.setValue("注意雷达",(Number) data[3][2]);
		return result;
	}
	
	public DefaultCategoryDataset getDataForManagerHealthLine() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();	
		Object[][] counts = getDataForManagersHealth();
		
		for(int i=1;i<8;i++) {
			String date = "2020-0"+i;
			int hc = (int) counts[3][0];
			hc = hc + (int)(1+Math.random()*5);
			int nc = (int) counts[3][1];
			nc = nc + (int)(1+Math.random()*15);
			int ac = (int) counts[3][2];
			ac = ac + (int)(1+Math.random()*5);
			result.addValue(hc,"健康雷达",date);
			result.addValue(nc,"一般雷达",date);
			result.addValue(ac,"注意雷达",date);
		}
		return result;
	}
	
	public Object[][] getDataForPartsConsumeTable(){
		Object[][] result = new Object[10][5];
		Object[][] data = getDataForPC();
		for(int i=0;i<result.length;i++) {
			int j = i+1;
			String name;
			if(j<10)
				name = "部队00"+j;
			else
				name = "部队0"+j;
			Object[] o = {j,name,data[i][0],data[i][1],data[i][2]};
			result[i] = o;
		}
		return result;
		
	}
	
	private Object[][] getDataForPC(){
		Object[][] o = {{105,"2020-06-29","急需采购"},{80,"2020-07-11","需要采购"},{60,"2020-08-09","需要采购"},
				{50,"2020-10-25","暂不需要采购"},{40,"2020-12-24","暂不需要采购"},
				{30,"2021-03-04","不需要采购"},{20,"2021-04-20","不需要采购"},{18,"2021-04-27","不需要采购"},{17,"2021-05-05","不需要采购"},{5,"2021-07-02","不需要采购"}};
		return o;
	}
	
	public Object[][] getDataForPartsPlan(){
		Object[][] result = new Object[7][4];
		Object[] data = getDataForPP();
		for(int i=0;i<result.length;i++) {
			int j = i+1;
			String name,name1;
			name = "备件00"+j;
			if(j+7 >10)
				name1 = "备件0"+(j+7);
			else
				name1 = "备件00"+(j+7);
			Object[] o = {name,data[i],name1,data[i+7]};
			result[i] = o;
		}
		return result;
	}

	private Object[] getDataForPP() {
		Object[] result = {12,13,10,7,20,32,54,32,34,9,21,53,11,28};
		return result;
	}
	
	public Object[][] getDataForRadarDynamicData(){
		Object[][] result = new Object[10][5];
		for(int i=0;i<result.length;i++) {
			int j = i+1;
			String name;
			if(j<10)
				name = "雷达00"+j;
			else
				name = "雷达0"+j;
			int p = (int) (1+Math.random()*3);
			String param = "参数"+p;
			int value = (int) (20+Math.random()*10);	
			String date = randomDate("2020-05-01 00:00:00","2020-07-01 00:00:00");
			Object[] d = {j,name,param,value,date};
			result[i] = d;			
		}
		return result;
	}
	
	public Object[] getParamForComboBox() {
		Object[] result = new Object[3];
		for(int i=0;i<result.length;i++) {
			result[i] = "参数"+(i+1);
		}
		return result;
	}
	
    public String randomDate(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);
 
            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            Date result = new Date(date);
            return format.format(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }
    
    public Object[][] getDataForRadarType(){
    	Object[][] result = new Object[20][3];
    	for(int i=0;i<result.length;i++) {
    		int j= i+1;
    		String name,level;
    		if(i<10)
    			name = "结构00"+j;
    		else 
    			name = "结构0"+ j;
    		if(j<3)
    			level = "子系统级别";
    		else if(j>= 3 && j< 10)
    			level = "部件级别";
    		else
    			level = "板卡级别";
    		Object[] o = {j,name,level};
    		result[i] = o;
    	}
    	return result;
    }
    
    public Object[][] getParamInfo(){
    	Object[][] result = {{1,"状态1","10~25"},{2,"状态2","0/1"},{3,"状态3","0/1"},{4,"状态4","40~80"},
    						{5,"状态5","0/1"},{6,"状态6","200~250"},{7,"状态7","90~180"},{8,"状态8","41~55"},
    						{9,"状态9","0/1"},{10,"状态10","0/1"}};
    	return result;
    }

	public Boolean addManager(Manager manager) {
		try {
			managerRepository.save(manager);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteManager(String managerName) {
		// TODO Auto-generated method stub
		return managerDao.deleteManager(managerName);
	}

	public List<Manager> selectManager(String name2) {
		List<Manager> list = new ArrayList();
		list = managerDao.selectManager(name2);
		return list;
	}
	public Object[] selectLocationByManagerName(String choosenManagerName) {
			List<Manager>list =	managerDao.selectManager(choosenManagerName);
			Object[] o=null;
			if(list!=null&&list.size()>0) {
				Manager m = list.get(0);
				o = new Object[1];
//				String managerLocation =Integer.toString(m.getManagerLocation());
				String managerLocation ="";
				if(Integer.toString(m.getManagerLocation()).equals("0")) {
					managerLocation="高原";
				}else if(Integer.toString(m.getManagerLocation()).equals("1")) {
					managerLocation="山地";
				}else if(Integer.toString(m.getManagerLocation()).equals("2")) {
					managerLocation="平原";
				}else if(Integer.toString(m.getManagerLocation()).equals("3")) {
					managerLocation="沿海";
				}else if(Integer.toString(m.getManagerLocation()).equals("4")) {
					managerLocation="沙漠";
				}
				o[0]=managerLocation;
			}else {
				o = new Object[5];
				o[0]="高原";

				o[1]="山地";

				o[2]="平原";

				o[3]="沿海";

				o[4]="沙漠";


			}
		

			return o;
	}
	public boolean updateManager(String managerNameEditor, String locationType, String managerName) {
		// TODO Auto-generated method stub
		Integer lt = null;
//		Integer locateType=null;
		if(locationType.equals("高原")) {
			lt=0;
		}else if(locationType.equals("山地")) {
			lt=1;

		}else if(locationType.equals("平原")){
			lt=2;

		}else if(locationType.equals("沿海")) {
			lt=3;

		}else if(locationType.equals("沙漠")) {
			lt=4;

		}
		return managerDao.updateManager(managerNameEditor,lt,managerName);
	}

	public List<Manager> getManagers() {
		// TODO Auto-generated method stub
		return  managerDao.getManagers();
	}
    
	 
}
