package ricciliao.scheduler.service;

import org.quartz.JobKey;
import org.quartz.SchedulerException;
import ricciliao.scheduler.pojo.dto.JobStatusDto;

public interface JobInitializationService {

    boolean initialize() throws SchedulerException;

    boolean resetAll() throws SchedulerException;

    boolean reset(JobKey jobKey) throws SchedulerException;

    JobStatusDto jobStatus(JobKey jobKey) throws SchedulerException;

}
