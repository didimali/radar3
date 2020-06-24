package radar.Service;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public interface TestService {
	
	/**获取表格数据方法示例
	 * @param params
	 * @return
	 */
	Object[][] getManagers(Object[] params);
	
	/**获取下拉框数据方法示例
	 * @param params
	 * @return
	 */
	Object[] getRadars(Object[] params);	
	/**获取饼图数据方法示例
	 * @param params
	 * @return
	 */
	DefaultPieDataset getPieData(Object[] params);
	/**获取折线图数据方法示例
	 * @param params
	 * @return
	 */
	DefaultCategoryDataset getLineData(Object[] params);
	
	/**获取折线图数据方法示例
	 * @param params
	 * @return
	 */
	DefaultCategoryDataset getBarData(Object[] params);

}
