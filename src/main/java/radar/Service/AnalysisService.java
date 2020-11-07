package radar.Service;

import org.jfree.data.general.PieDataset;

public interface AnalysisService {
    //统计分析页面表格数据获取
	Object[][] countRadarType(Object[] params);
    //统计分析→适应性分析页面表格数据获取
	Object[][] getRadar(Object[] params);
    //统计分析→适应性分析页面饼图数据获取
	PieDataset createPieData(int typeid, int location);
    //统计分析及其子页面top栏标题
	String[] titleName(int typeid, int location);
    //统计分析页面点击行进行radartype及location的选择
	int[] getTL(int r);
    //统计分析→故障分析页面表格数据获取
	Object[][] getFaultList(int typeid, int location, String startDate, String endDate);
    //统计分析→故障分析页面饼图数据获取
	PieDataset createPieData2(int typeid, int location, String startDate, String endDate);
	



}
