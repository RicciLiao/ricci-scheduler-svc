package ricciliao.scheduler.service.impl;


import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.scheduler.common.CommonHelper;
import ricciliao.scheduler.pojo.dto.JobSchedulerControlDto;
import ricciliao.scheduler.pojo.po.JobSchedulerControlPo;
import ricciliao.scheduler.repository.JobSchedulerControlRepository;
import ricciliao.scheduler.service.JobInitializationService;
import ricciliao.scheduler.service.JobSchedulerControlService;
import ricciliao.scheduler.common.PojoUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("moeJobSchedulerControlService")
public class JobSchedulerControlServiceImpl implements JobSchedulerControlService {

    private JobSchedulerControlRepository jobSchedulerControlRepository;
    private JobInitializationService jobInitializationService;

    @Autowired
    public void setJobInitializationService(JobInitializationService jobInitializationService) {
        this.jobInitializationService = jobInitializationService;
    }

    @Autowired
    public void setJobSchedulerControlRepository(JobSchedulerControlRepository jobSchedulerControlRepository) {
        this.jobSchedulerControlRepository = jobSchedulerControlRepository;
    }

    @Override
    public List<JobSchedulerControlPo> findAllByEnableIsOrderByJobGroup(String flag) {

        return jobSchedulerControlRepository.findAllByEnableIsOrderByJobGroup(flag);
    }

    @Override
    public JobSchedulerControlPo findByJobNameIsAndJobGroupIsAndEnableIs(JobKey jobKey, String flag) {

        return jobSchedulerControlRepository.findByJobNameIsAndJobGroupIsAndEnableIs(jobKey.getName(), jobKey.getGroup(), flag);
    }

    @Override
    public JobSchedulerControlDto findByJobNameIsAndJobGroupIs(JobKey jobKey) {
        JobSchedulerControlPo po = jobSchedulerControlRepository.findByJobNameIsAndJobGroupIs(jobKey.getName(), jobKey.getGroup());

        return PojoUtils.convertToMoeJobSchedulerControlDto(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(JobSchedulerControlDto dto) throws SchedulerException {
        dto.setUpdateDtm0(LocalDateTime.now());
        JobSchedulerControlPo save = PojoUtils.convertToJobSchedulerControlPo(dto);
        jobSchedulerControlRepository.save(save);
        jobInitializationService.reset(CommonHelper.generateJobKey(save.getJobName(), save.getJobGroup()));

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(List<JobSchedulerControlDto> dtoList) throws SchedulerException {
        List<JobSchedulerControlPo> savingList = new ArrayList<>();
        dtoList.forEach(dto -> {
            dto.setUpdateDtm0(LocalDateTime.now());
            savingList.add(PojoUtils.convertToJobSchedulerControlPo(dto));
        });
        List<JobSchedulerControlPo> updatedList = jobSchedulerControlRepository.saveAll(savingList);
        for (JobSchedulerControlPo updated : updatedList) {
            jobInitializationService.reset(CommonHelper.generateJobKey(updated.getJobName(), updated.getJobGroup()));
        }

        return true;
    }

    @Override
    public List<JobSchedulerControlDto> findAll() {
        List<JobSchedulerControlPo> poList = jobSchedulerControlRepository.findAll();
        List<JobSchedulerControlDto> dtoList = new ArrayList<>();
        poList.forEach(controlPo -> dtoList.add(PojoUtils.convertToMoeJobSchedulerControlDto(controlPo)));

        return dtoList;
    }

}
