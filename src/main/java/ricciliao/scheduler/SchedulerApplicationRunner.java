package ricciliao.scheduler;


import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ricciliao.scheduler.service.JobInitializationService;

@Component("moeSchedulerApplicationRunner")
public class SchedulerApplicationRunner implements ApplicationRunner {

    private JobInitializationService jobInitializationService;

    @Autowired
    public void setJobInitializationService(JobInitializationService jobInitializationService) {
        this.jobInitializationService = jobInitializationService;
    }

    @Override
    public void run(ApplicationArguments args) throws SchedulerException {
        jobInitializationService.resetAll();
    }
}
