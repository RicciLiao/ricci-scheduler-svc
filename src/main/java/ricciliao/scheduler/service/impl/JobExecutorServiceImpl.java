package ricciliao.scheduler.service.impl;


import org.quartz.JobDetail;
import org.springframework.stereotype.Service;
import ricciliao.common.component.response.ResponseSimpleData;
import ricciliao.common.component.response.ResponseVo;
import ricciliao.scheduler.service.JobExecutorService;

@Service("jobExecutorService")
public class JobExecutorServiceImpl implements JobExecutorService {

    @Override
    public ResponseVo<ResponseSimpleData.Bool> generateDrugListToHAPharmacy(JobDetail job) {

        return new ResponseVo<>(0L, "", new ResponseSimpleData.Bool(true));
    }

}
