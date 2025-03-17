package ricciliao.scheduler.service;


import org.quartz.JobKey;
import org.quartz.SchedulerException;
import ricciliao.scheduler.pojo.dto.JobSchedulerControlDto;
import ricciliao.scheduler.pojo.po.JobSchedulerControlPo;

import java.util.List;

public interface JobSchedulerControlService {

    List<JobSchedulerControlPo> findAllByEnableIsOrderByJobGroup(String flag);

    JobSchedulerControlPo findByJobNameIsAndJobGroupIsAndEnableIs(JobKey jobKey, String flag);

    JobSchedulerControlDto findByJobNameIsAndJobGroupIs(JobKey jobKey);

    boolean update(JobSchedulerControlDto dto) throws SchedulerException;

    boolean update(List<JobSchedulerControlDto> dtoList) throws SchedulerException;

    List<JobSchedulerControlDto> findAll();

}
