package ricciliao.scheduler.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import ricciliao.common.component.response.ResponseData;

import java.io.Serial;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobStatusDto implements ResponseData {
    @Serial
    private static final long serialVersionUID = -2262171915056643090L;
    private String jobName;
    private String jobGroup;
    private String jobStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date previousFireTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nextFireTime;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Date getPreviousFireTime() {
        return previousFireTime;
    }

    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }
}
