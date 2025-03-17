package ricciliao.scheduler.job;


import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import ricciliao.scheduler.service.JobExecutorService;

public abstract class ScheduleJob implements Job {

    protected JobExecutorService jobExecutorService;

    @Autowired
    public void setJobExecutorService(JobExecutorService jobExecutorService) {
        this.jobExecutorService = jobExecutorService;
    }

}
