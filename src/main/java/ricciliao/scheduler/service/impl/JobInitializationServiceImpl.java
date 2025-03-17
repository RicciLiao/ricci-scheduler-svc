package ricciliao.scheduler.service.impl;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.scheduler.common.CommonHelper;
import ricciliao.scheduler.common.Constants;
import ricciliao.scheduler.pojo.bo.JobInitializationBo;
import ricciliao.scheduler.pojo.dto.JobStatusDto;
import ricciliao.scheduler.pojo.po.JobSchedulerControlPo;
import ricciliao.scheduler.service.JobInitializationService;
import ricciliao.scheduler.service.JobSchedulerControlService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("jobInitializationService")
public class JobInitializationServiceImpl implements JobInitializationService {

    private Scheduler scheduler;
    private JobSchedulerControlService moeJobSchedulerControlService;

    @Autowired
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Autowired
    public void setMoeJobSchedulerControlService(JobSchedulerControlService moeJobSchedulerControlService) {
        this.moeJobSchedulerControlService = moeJobSchedulerControlService;
    }

    @Override
    public boolean initialize() throws SchedulerException {
        List<JobSchedulerControlPo> JobSchedulerControlPoList =
                moeJobSchedulerControlService.findAllByEnableIsOrderByJobGroup(Constants.DB_FLAG_TRUE);
        for (JobSchedulerControlPo JobSchedulerControlPo : JobSchedulerControlPoList) {
            JobKey jobKey = CommonHelper.generateJobKey(JobSchedulerControlPo.getJobName(), JobSchedulerControlPo.getJobGroup());
            if (!scheduler.checkExists(jobKey)) {
                JobInitializationBo jobInitializationBo =
                        CommonHelper.jobInitialize(JobSchedulerControlPo, CommonHelper.findJobClazzByJobKey(jobKey));
                scheduler.scheduleJob(jobInitializationBo.getJobDetail(), jobInitializationBo.getTrigger());
            }
        }

        return true;
    }

    @Override
    public boolean resetAll() throws SchedulerException {
        Set<JobKey> jobKeySet = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
        List<JobKey> jobKeyList = new ArrayList<>(jobKeySet);
        scheduler.deleteJobs(jobKeyList);
        initialize();

        return true;
    }

    @Override
    public boolean reset(JobKey jobKey) throws SchedulerException {
        scheduler.deleteJob(jobKey);
        JobSchedulerControlPo JobSchedulerControlPo =
                moeJobSchedulerControlService.findByJobNameIsAndJobGroupIsAndEnableIs(jobKey, Constants.DB_FLAG_TRUE);
        if (JobSchedulerControlPo != null) {
            JobInitializationBo jobInitializationBo =
                    CommonHelper.jobInitialize(JobSchedulerControlPo, CommonHelper.findJobClazzByJobKey(jobKey));
            scheduler.scheduleJob(jobInitializationBo.getJobDetail(), jobInitializationBo.getTrigger());
        }

        return true;
    }

    public JobStatusDto jobStatus(JobKey jobKey) throws SchedulerException {
        JobStatusDto statusDto = new JobStatusDto();
        TriggerKey triggerKey = TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup());
        Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        statusDto.setJobName(jobKey.getName());
        statusDto.setJobGroup(jobKey.getGroup());
        statusDto.setJobStatus(triggerState.name());
        if (scheduler.checkExists(jobKey)) {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            statusDto.setPreviousFireTime(trigger.getPreviousFireTime());
            statusDto.setNextFireTime(trigger.getNextFireTime());
        }

        return statusDto;
    }

}
