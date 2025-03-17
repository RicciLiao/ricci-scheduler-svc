package ricciliao.scheduler.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.common.component.response.ResponseData;
import ricciliao.common.component.response.ResponseUtils;
import ricciliao.common.component.response.ResponseVo;
import ricciliao.scheduler.common.CommonHelper;
import ricciliao.scheduler.common.Constants;
import ricciliao.scheduler.common.JobEnum;
import ricciliao.scheduler.pojo.dto.InnerFreqDto;
import ricciliao.scheduler.pojo.dto.InnerMateDataDto;
import ricciliao.scheduler.pojo.dto.JobSchedulerControlDto;
import ricciliao.scheduler.service.JobSchedulerControlService;

import java.util.List;

@Tag(name = "Moe JobScheduler Controller")
@RestController
public class MoeJobSchedulerController {

    public static final String CRON_TIP = "常用表达式例子\n\n<b>0/2 * * * * ?</b>&nbsp;------>&nbsp;每2秒触发\n<b>0 0/2 * * * ?</b>&nbsp;------>&nbsp;每2分钟触发\n <b>0 0 2 1 * ?</b>&nbsp;------>&nbsp;在每月的1日的凌晨2点触发\n<b>0 15 10 ? * MON-FRI</b>&nbsp;------>&nbsp;周一到周五每天上午10:15触发\n<b>0 15 10 ? 6L 2002-2006</b>&nbsp;------>&nbsp;2002-2006年的每个月的最后一个星期五上午10:15触发\n<b>0 0 10,14,16 * * ?</b>&nbsp;------>&nbsp;每天上午10点，下午2点，4点触发 \n<b>0 0/30 9-17 * * ?</b>&nbsp;------>&nbsp;朝九晚五工作时间内每半小时触发 \n<b>0 0 12 ? * WED</b>&nbsp;------>&nbsp;每个星期三中午12点触发 \n<b>0 0 12 * * ?</b>&nbsp;------>&nbsp;每天中午12点触发 \n<b>0 15 10 ? * *</b>&nbsp;------>&nbsp;每天上午10:15触发 \n<b>0 15 10 * * ?</b>&nbsp;------>&nbsp;每天上午10:15触发 \n<b>0 15 10 * * ?</b>&nbsp;------>&nbsp;每天上午10:15触发 \n<b>0 15 10 * * ? 2005</b>&nbsp;------>&nbsp;2005年的每天上午10:15触发\n<b>0 * 14 * * ?</b>&nbsp;------>&nbsp; 在每天下午2点到下午2:59期间的每1分钟触发\n<b>0 0/5 14 * * ?</b>&nbsp;------>&nbsp;在每天下午2点到下午2:55期间的每5分钟触发 \n<b>0 0/5 14,18 * * ?</b>&nbsp;------>&nbsp; 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发\n<b>0 0-5 14 * * ?</b>&nbsp;------>&nbsp;在每天下午2点到下午2:05期间的每1分钟触发\n<b>0 10,44 14 ? 3 WED</b>&nbsp;------>&nbsp;每年三月的星期三的下午2:10和2:44触发\n<b>0 15 10 ? * MON-FRI</b>&nbsp;------>&nbsp;周一至周五的上午10:15触发\n<b>0 15 10 15 * ?</b>&nbsp;------>&nbsp;每月15日上午10:15触发\n<b>0 15 10 L * ?</b>&nbsp;------>&nbsp;每月最后一日的上午10:15触发\n<b>0 15 10 ? * 6L</b>&nbsp;------>&nbsp;每月的最后一个星期五上午10:15触发\n<b>0 15 10 ? * 6L 2002-2005</b>&nbsp;------>&nbsp;2002年至2005年的每月的最后一个星期五上午10:15触发\n<b>0 15 10 ? * 6#3</b>&nbsp;------>&nbsp;每月的第三个星期五上午10:15触发";

    private JobSchedulerControlService jobSchedulerControlService;

    @Autowired
    public void setJobSchedulerControlService(JobSchedulerControlService moeJobSchedulerControlService) {
        this.jobSchedulerControlService = moeJobSchedulerControlService;
    }

    @Operation(description = "Change cron", summary = CRON_TIP)
    @Parameters({
            @Parameter(
                    name = "job",
                    description = "Please select one of the job",
                    in = ParameterIn.PATH,
                    schema = @Schema(allowableValues = {Constants.ALL + "," + Constants.JOB_SELECTOR_VALUES}, implementation = String.class),
                    required = true
            )
    })
    @PostMapping("/freq/{job}")
    public ResponseVo<ResponseData> freq(@PathVariable(name = "job") String job,
                                         @RequestBody InnerFreqDto freqDto) throws SchedulerException {
        if (Constants.ALL.equalsIgnoreCase(job)) {
            List<JobSchedulerControlDto> dtoList = jobSchedulerControlService.findAll();
            dtoList.forEach(dto -> dto.setFreq0(freqDto.getFreq()));
            jobSchedulerControlService.update(dtoList);
        } else {
            JobEnum jobEnumFreq = JobEnum.getByName(job.split("]")[1]);
            JobKey jobKeyFreq = CommonHelper.generateJobKey(jobEnumFreq.getJobName(), jobEnumFreq.getJobGroup());
            JobSchedulerControlDto freq = jobSchedulerControlService.findByJobNameIsAndJobGroupIs(jobKeyFreq);
            freq.setFreq0(freqDto.getFreq());
            jobSchedulerControlService.update(freq);
        }

        return ResponseUtils.successResponse();
    }

    @Operation(description = "Enable job")
    @Parameters({
            @Parameter(
                    name = "job",
                    description = "Please select one of the job",
                    in = ParameterIn.PATH,
                    schema = @Schema(allowableValues = {Constants.ALL + "," + Constants.JOB_SELECTOR_VALUES}),
                    required = true
            ),
            @Parameter(
                    name = "enable",
                    description = "enable / disable job",
                    in = ParameterIn.PATH,
                    schema = @Schema(allowableValues = {Constants.DB_FLAG_TRUE + "," + Constants.DB_FLAG_FALSE}, implementation = String.class),
                    required = true
            )
    })
    @PostMapping("/enable/{job}/{enable}")
    public ResponseVo<ResponseData> enable(@PathVariable(name = "job") String job, @PathVariable(name = "enable") String enable) throws SchedulerException {
        if (Constants.ALL.equalsIgnoreCase(job)) {
            List<JobSchedulerControlDto> dtoList = jobSchedulerControlService.findAll();
            dtoList.forEach(dto -> dto.setEnable0(enable));
            jobSchedulerControlService.update(dtoList);
        } else {
            JobEnum jobEnum = JobEnum.getByName(job.split("]")[1]);
            JobKey jobKey = CommonHelper.generateJobKey(jobEnum.getJobName(), jobEnum.getJobGroup());
            JobSchedulerControlDto moeJobSchedulerControlDto = jobSchedulerControlService.findByJobNameIsAndJobGroupIs(jobKey);
            moeJobSchedulerControlDto.setEnable0(enable);
            jobSchedulerControlService.update(moeJobSchedulerControlDto);
        }

        return ResponseUtils.successResponse();
    }

    @Operation(description = "Change mate data")
    @Parameters({
            @Parameter(
                    name = "job",
                    description = "Please select one of the job",
                    in = ParameterIn.PATH,
                    schema = @Schema(allowableValues = {Constants.ALL + "," + Constants.JOB_SELECTOR_VALUES}, implementation = String.class),
                    required = true
            )
    })
    @PostMapping("/mateData/{job}")
    public ResponseVo<ResponseData> mateData(@PathVariable(name = "job") String job,
                                             @RequestBody InnerMateDataDto mateDataDto) throws SchedulerException {
        JobEnum jobEnumMateData = JobEnum.getByName(job.split("]")[1]);
        JobKey jobKeyMateData = CommonHelper.generateJobKey(jobEnumMateData.getJobName(), jobEnumMateData.getJobGroup());
        JobSchedulerControlDto mateData = jobSchedulerControlService.findByJobNameIsAndJobGroupIs(jobKeyMateData);
        mateData.setEndpoint0(mateDataDto.getEndpoint());
        mateData.setJobDesc0(mateDataDto.getDescription());
        jobSchedulerControlService.update(mateData);

        return ResponseUtils.successResponse();
    }

}
