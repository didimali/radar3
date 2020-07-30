package radar.Service;


public interface BigDataService {

	String getRadarName(String id);	

	Object[][] getHealth(String id);

	Object[][] getHistory(String id);

	Object[][] getFault(String id);

	Object[][] getPartsNum(String id);

	Integer dataVerify(String id);

}
