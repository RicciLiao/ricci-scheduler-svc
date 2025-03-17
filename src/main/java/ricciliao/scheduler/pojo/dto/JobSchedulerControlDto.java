package ricciliao.scheduler.pojo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ricciliao.common.component.serialisation.LocalDateTime2TimestampSerializer;
import ricciliao.common.component.serialisation.Timestamp2LocalDateTimeDeserializer;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class JobSchedulerControlDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 2637166190582470811L;
    private String jobName0;
    private String jobDesc0;
    private String freq0;
    private String enable0;
    private String endpoint0;
    private String jobGroup0;
    @JsonSerialize(using = LocalDateTime2TimestampSerializer.class)
    @JsonDeserialize(using = Timestamp2LocalDateTimeDeserializer.class)
    private LocalDateTime createDtm0;
    private Long createBy0;
    @JsonSerialize(using = LocalDateTime2TimestampSerializer.class)
    @JsonDeserialize(using = Timestamp2LocalDateTimeDeserializer.class)
    private LocalDateTime updateDtm0;
    private Long updateBy0;

    public String getJobName0() {
        return jobName0;
    }

    public void setJobName0(String jobName0) {
        this.jobName0 = jobName0;
    }

    public String getJobDesc0() {
        return jobDesc0;
    }

    public void setJobDesc0(String jobDesc0) {
        this.jobDesc0 = jobDesc0;
    }

    public String getFreq0() {
        return freq0;
    }

    public void setFreq0(String freq0) {
        this.freq0 = freq0;
    }

    public String getEnable0() {
        return enable0;
    }

    public void setEnable0(String enable0) {
        this.enable0 = enable0;
    }

    public String getEndpoint0() {
        return endpoint0;
    }

    public void setEndpoint0(String endpoint0) {
        this.endpoint0 = endpoint0;
    }

    public String getJobGroup0() {
        return jobGroup0;
    }

    public void setJobGroup0(String jobGroup0) {
        this.jobGroup0 = jobGroup0;
    }

    public LocalDateTime getCreateDtm0() {
        return createDtm0;
    }

    public void setCreateDtm0(LocalDateTime createDtm0) {
        this.createDtm0 = createDtm0;
    }

    public Long getCreateBy0() {
        return createBy0;
    }

    public void setCreateBy0(Long createBy0) {
        this.createBy0 = createBy0;
    }

    public LocalDateTime getUpdateDtm0() {
        return updateDtm0;
    }

    public void setUpdateDtm0(LocalDateTime updateDtm0) {
        this.updateDtm0 = updateDtm0;
    }

    public Long getUpdateBy0() {
        return updateBy0;
    }

    public void setUpdateBy0(Long updateBy0) {
        this.updateBy0 = updateBy0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobSchedulerControlDto)) return false;
        JobSchedulerControlDto that = (JobSchedulerControlDto) o;
        return Objects.equals(jobName0, that.jobName0) &&
                Objects.equals(jobDesc0, that.jobDesc0) &&
                Objects.equals(freq0, that.freq0) &&
                Objects.equals(enable0, that.enable0) &&
                Objects.equals(endpoint0, that.endpoint0) &&
                Objects.equals(jobGroup0, that.jobGroup0) &&
                Objects.equals(createDtm0, that.createDtm0) &&
                Objects.equals(createBy0, that.createBy0) &&
                Objects.equals(updateDtm0, that.updateDtm0) &&
                Objects.equals(updateBy0, that.updateBy0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName0, jobDesc0, freq0, enable0, endpoint0, jobGroup0, createDtm0, createBy0, updateDtm0, updateBy0);
    }
}
