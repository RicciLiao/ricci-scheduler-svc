package ricciliao.scheduler.pojo.po;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "job_scheduler_control")
@IdClass(JobSchedulerControlPoPK.class)
public class JobSchedulerControlPo {
    private String jobName;
    private String jobGroup;
    private String jobDesc;
    private String freq;
    private String enable;
    private String endpoint;
    private Long createBy;
    private LocalDateTime createDtm;
    private Long updateBy;
    private LocalDateTime updateDtm;

    @Id
    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "job_desc")
    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    @Basic
    @Column(name = "freq")
    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    @Basic
    @Column(name = "enable")
    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "endpoint")
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Basic
    @Column(name = "create_by")
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "create_dtm")
    public LocalDateTime getCreateDtm() {
        return createDtm;
    }

    public void setCreateDtm(LocalDateTime createDtm) {
        this.createDtm = createDtm;
    }

    @Basic
    @Column(name = "update_by")
    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "update_dtm")
    public LocalDateTime getUpdateDtm() {
        return updateDtm;
    }

    public void setUpdateDtm(LocalDateTime updateDtm) {
        this.updateDtm = updateDtm;
    }

    @Id
    @Column(name = "job_group")
    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobSchedulerControlPo that = (JobSchedulerControlPo) o;
        return Objects.equals(jobName, that.jobName) && Objects.equals(jobDesc, that.jobDesc) && Objects.equals(freq, that.freq) && Objects.equals(enable, that.enable) && Objects.equals(endpoint, that.endpoint) && Objects.equals(createBy, that.createBy) && Objects.equals(createDtm, that.createDtm) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateDtm, that.updateDtm) && Objects.equals(jobGroup, that.jobGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName, jobDesc, freq, enable, endpoint, createBy, createDtm, updateBy, updateDtm, jobGroup);
    }
}
