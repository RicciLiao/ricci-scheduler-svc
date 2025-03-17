package ricciliao.scheduler.service;

import org.quartz.JobDetail;
import ricciliao.common.component.response.ResponseSimpleData;
import ricciliao.common.component.response.ResponseVo;

public interface JobExecutorService {

    ResponseVo<ResponseSimpleData.Bool> generateDrugListToHAPharmacy(JobDetail job);

}
