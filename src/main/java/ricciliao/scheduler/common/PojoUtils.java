package ricciliao.scheduler.common;


import ricciliao.scheduler.pojo.dto.JobSchedulerControlDto;
import ricciliao.scheduler.pojo.po.JobSchedulerControlPo;

public class PojoUtils {


    PojoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static JobSchedulerControlDto convertToMoeJobSchedulerControlDto(JobSchedulerControlPo po) {
        JobSchedulerControlDto dto = new JobSchedulerControlDto();
        dto.setJobName0(po.getJobName());
        dto.setJobDesc0(po.getJobDesc());
        dto.setFreq0(po.getFreq());
        dto.setEnable0(po.getEnable());
        dto.setEndpoint0(po.getEndpoint());
        dto.setJobGroup0(po.getJobGroup());
        dto.setCreateDtm0(po.getCreateDtm());
        dto.setCreateBy0(po.getCreateBy());
        dto.setUpdateDtm0(po.getUpdateDtm());
        dto.setUpdateBy0(po.getUpdateBy());

        return dto;
    }

    public static JobSchedulerControlPo convertToJobSchedulerControlPo(JobSchedulerControlDto dto) {
        JobSchedulerControlPo po = new JobSchedulerControlPo();
        po.setJobName(dto.getJobName0());
        po.setJobGroup(dto.getJobGroup0());
        po.setJobDesc(dto.getJobDesc0());
        po.setFreq(dto.getFreq0());
        po.setEnable(dto.getEnable0());
        po.setEndpoint(dto.getEndpoint0());
        po.setCreateDtm(dto.getCreateDtm0());
        po.setCreateBy(dto.getCreateBy0());
        po.setUpdateBy(dto.getUpdateBy0());
        po.setUpdateDtm(dto.getUpdateDtm0());

        return po;
    }

}
