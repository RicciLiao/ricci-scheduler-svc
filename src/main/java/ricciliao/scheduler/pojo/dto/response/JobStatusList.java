package ricciliao.scheduler.pojo.dto.response;

import ricciliao.common.component.response.ResponseData;
import ricciliao.scheduler.pojo.dto.JobStatusDto;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JobStatusList implements ResponseData {
    @Serial
    private static final long serialVersionUID = 5836909775161800670L;

    public JobStatusList() {
    }

    public JobStatusList(List<JobStatusDto> jobStatusList) {
        this.jobStatusList.addAll(jobStatusList);
    }

    private List<JobStatusDto> jobStatusList = new ArrayList<>();

    public List<JobStatusDto> getJobStatusList() {
        return jobStatusList;
    }

    public void setJobStatusList(List<JobStatusDto> jobStatusList) {
        this.jobStatusList = jobStatusList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobStatusList that)) return false;
        return Objects.equals(getJobStatusList(), that.getJobStatusList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobStatusList());
    }
}
