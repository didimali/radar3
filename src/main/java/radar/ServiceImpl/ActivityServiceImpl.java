package radar.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.ActivityDao;
import radar.Entity.Activity;


@Service("RecordServiceImpl")

public class ActivityServiceImpl {
	@Autowired
	ActivityDao activityDao;
	public List<Activity> getAllRecords() {
		return activityDao.getAllRecords();
	}
}
