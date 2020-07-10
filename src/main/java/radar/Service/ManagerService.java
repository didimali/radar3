package radar.Service;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 部队管理接口
 * @author madi
 *
 */
public interface ManagerService {
	
	/**
	 * 获取部队下拉框数据
	 */
	Object[] getDataForManagerComboBox(Object[] params) ;
	
	/**
	 * 获取部队位置下拉框
	 */
	Object[] getDataForManagerLocationComboBox() ;

	/**
	 * 获取部队列表所需数据
	 * @return
	 */
	Object[][] getDataForManagerList();
	
	/**
	 * 获取某部队详情-各状态雷达数量饼图数据
	 * @return
	 */
	DefaultPieDataset getDataForManagerDetailsHealthStatus();
	
	/**
	 * 获取某部队详情-各备件消耗数量饼图数据
	 * @return
	 */
	DefaultPieDataset getDataForManagerDetailsPartsComsume();
	
	/**
	 * 获取某部队详情-各状态雷达历史数量折线图数据
	 * @return
	 */
	DefaultCategoryDataset getDataForManagerDetailsHistoryHealthStatus();
	
	/**
	 * 获取某部队详情-各备件历史消耗数量折线图数据
	 * @return
	 */
	DefaultCategoryDataset getDataForManagerDetailsHistoryPartsComsume();
	
	/**
	 * 获取部队故障信息表格数据
	 * @return
	 */
	Object[][] getDataForManagerFaultTable();
	//删除部队信息
	boolean deleteManager(String managerName);
}
