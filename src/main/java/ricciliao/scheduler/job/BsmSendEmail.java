package ricciliao.scheduler.job;


import org.quartz.JobExecutionContext;
import ricciliao.scheduler.annotation.JobTarget;
import ricciliao.scheduler.common.JobEnum;

@JobTarget(job = JobEnum.SEND_EMAIL_FOR_BSM)
public class BsmSendEmail extends ScheduleJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        jobExecutorService.generateDrugListToHAPharmacy(jobExecutionContext.getJobDetail());
    }

}
