package ricciliao.scheduler.common;

public enum JobEnum {

    SEND_EMAIL_FOR_BSM("send_email", "BSM"),
    UNKNOWN_JOB("", "");

    private final String jobName;
    private final String jobGroup;

    JobEnum(String jobName, String jobGroup) {
        this.jobName = jobName;
        this.jobGroup = jobGroup;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public static JobEnum getByName(String jobName) {
        for (JobEnum jobEnum : JobEnum.list()) {
            if (jobName.equalsIgnoreCase(jobEnum.jobName)) {
                return jobEnum;
            }
        }
        return UNKNOWN_JOB;
    }

    public static JobEnum[] list() {
        JobEnum[] jobEnums = new JobEnum[JobEnum.values().length - 1];

        for (int i = 0; i < JobEnum.values().length; i++) {
            if (!UNKNOWN_JOB.equals(JobEnum.values()[i])) {
                jobEnums[i] = JobEnum.values()[i];
            }
        }

        return jobEnums;
    }

}
