package ricciliao.scheduler.pojo.bo;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;

import java.util.Objects;

public class JobInitializationBo {

    public JobInitializationBo() {
    }

    public JobInitializationBo(JobDetail jobDetail, CronTrigger trigger) {
        this.jobDetail = jobDetail;
        this.trigger = trigger;
    }

    private JobDetail jobDetail;
    private CronTrigger trigger;

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }

    public CronTrigger getTrigger() {
        return trigger;
    }

    public void setTrigger(CronTrigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobInitializationBo)) return false;
        JobInitializationBo that = (JobInitializationBo) o;
        return Objects.equals(jobDetail, that.jobDetail) &&
                Objects.equals(trigger, that.trigger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobDetail, trigger);
    }
}
