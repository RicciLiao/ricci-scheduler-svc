package ricciliao.scheduler.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;

public class JobSchedulerControlPoPK implements Serializable {
    @Serial
    private static final long serialVersionUID = -5848633900377951253L;

    private String jobName;
    private String jobGroup;

    @Id
    @Column(name = "job_group")
    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    @Id
    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

}
