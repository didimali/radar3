package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Entity.FaultRecord;
import radar.Repository.FaultRepository;


@Service("FaultRecordServiceImpl")
public class FaultRecordServiceImpl {
	
	@Autowired
	FaultRepository faultRepository;

	public  Boolean add(FaultRecord fault) {
		try {
			faultRepository.save(fault);
			return true;

		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
