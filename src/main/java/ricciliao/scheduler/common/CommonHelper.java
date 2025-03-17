package ricciliao.scheduler.common;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.core.annotation.AnnotationUtils;
import ricciliao.scheduler.annotation.JobTarget;
import ricciliao.scheduler.job.ScheduleJob;
import ricciliao.scheduler.pojo.bo.JobInitializationBo;
import ricciliao.scheduler.pojo.po.JobSchedulerControlPo;

import java.util.Date;
import java.util.Objects;

public class CommonHelper {

    private CommonHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static JobKey generateJobKey(String name, String group) {

        return new JobKey(name, group);
    }

    public static TriggerKey generateTriggerKey(String name, String group) {

        return new TriggerKey(name, group);
    }

    public static JobInitializationBo jobInitialize(JobSchedulerControlPo controlPo, Class<? extends ScheduleJob> jobClazz) {
        JobKey jobKey = CommonHelper.generateJobKey(controlPo.getJobName(), controlPo.getJobGroup());
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(Constants.ENDPOINT, controlPo.getEndpoint());
        JobDetail jobDetail = JobBuilder.newJob(jobClazz)
                .withIdentity(jobKey)
                .withDescription(controlPo.getJobDesc())
                .setJobData(jobDataMap)
                .build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(controlPo.getFreq())
                .withMisfireHandlingInstructionDoNothing();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(CommonHelper.generateTriggerKey(jobKey.getName(), jobKey.getGroup()))
                .startAt(new Date())
                .withSchedule(scheduleBuilder)
                .forJob(jobDetail)
                .build();

        return new JobInitializationBo(jobDetail, trigger);
    }

    public static Class<? extends ScheduleJob> findJobClazzByJobKey(JobKey jobKey) {
        Reflections reflections = new Reflections("ricciliao.scheduler.job", Scanners.TypesAnnotated);
        for (Class<?> aClass : reflections.getTypesAnnotatedWith(JobTarget.class)) {
            if (ScheduleJob.class.isAssignableFrom(aClass)) {
                JobTarget jobTarget = AnnotationUtils.findAnnotation(aClass, JobTarget.class);
                if (Objects.nonNull(jobTarget)) {
                    if (jobTarget.job().getJobGroup().equalsIgnoreCase(jobKey.getGroup())
                            && jobTarget.job().getJobName().equalsIgnoreCase(jobKey.getName())) {

                        return (Class<? extends ScheduleJob>) aClass;
                    }
                }
            }
        }

        return ScheduleJob.class;
    }

}
