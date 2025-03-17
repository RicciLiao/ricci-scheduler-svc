package ricciliao.scheduler.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.common.component.response.ResponseData;
import ricciliao.common.component.response.ResponseUtils;
import ricciliao.common.component.response.ResponseVo;
import ricciliao.scheduler.common.CommonHelper;
import ricciliao.scheduler.common.Constants;
import ricciliao.scheduler.common.JobEnum;
import ricciliao.scheduler.pojo.dto.JobStatusDto;
import ricciliao.scheduler.pojo.dto.response.JobStatusList;
import ricciliao.scheduler.service.JobInitializationService;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Job Initialization Controller")
@RestController
public class JobInitializationController {

    private JobInitializationService jobInitializationService;

    @Autowired
    public void setJobInitializationService(JobInitializationService jobInitializationService) {
        this.jobInitializationService = jobInitializationService;
    }

    @Operation(description = "Reset job(s), base on control table")
    @Parameters({
            @Parameter(
                    name = "job",
                    description = "Please select one of the job",
                    in = ParameterIn.PATH,
                    schema = @Schema(allowableValues = {Constants.ALL + "," + Constants.JOB_SELECTOR_VALUES}, implementation = String.class),
                    required = true
            )
    })
    @PostMapping("/resetJob/{job}")
    public ResponseVo<ResponseData> resetJob(@PathVariable(name = "job") String job) throws SchedulerException {
        if (Constants.ALL.equalsIgnoreCase(job)) {
            jobInitializationService.resetAll();
        } else {
            JobEnum jobEnum = JobEnum.getByName(job.split("]")[1]);
            jobInitializationService.reset(CommonHelper.generateJobKey(jobEnum.getJobName(), jobEnum.getJobGroup()));
        }

        return ResponseUtils.successResponse();
    }

    @Operation(description = "Get job(s) status")
    @Parameters({
            @Parameter(
                    name = "job",
                    description = "Please select one of the job",
                    in = ParameterIn.PATH,
                    schema = @Schema(allowableValues = {Constants.ALL + "," + Constants.JOB_SELECTOR_VALUES}, implementation = String.class),
                    required = true
            )
    })
    @GetMapping("/jobStatus/{job}")
    public ResponseVo<ResponseData> jobStatus(@PathVariable(name = "job") String job) throws SchedulerException {
        if (Constants.ALL.equalsIgnoreCase(job)) {
            List<JobStatusDto> statusDtoList = new ArrayList<>();
            for (JobEnum jobEnum : JobEnum.list()) {
                statusDtoList.add(jobInitializationService.jobStatus(CommonHelper.generateJobKey(jobEnum.getJobName(), jobEnum.getJobGroup())));
            }

            return ResponseUtils.successResponse(new JobStatusList(statusDtoList));
        } else {
            JobEnum jobEnum = JobEnum.getByName(job.split("]")[1]);
            JobStatusDto statusDto = jobInitializationService.jobStatus(CommonHelper.generateJobKey(jobEnum.getJobName(), jobEnum.getJobGroup()));
            return ResponseUtils.successResponse(statusDto);
        }
    }

}
