package ricciliao.scheduler.common;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SYSTEM_NAME = "brain storming market";
    public static final String ENDPOINT = "endpoint";
    public static final String DB_FLAG_TRUE = "Y";
    public static final String DB_FLAG_FALSE = "N";
    public static final String ALL = "ALL";
    public static final String JOB_SPLIT = "#";
    

    public static final String JOB_GROUP_FOR_BSM = "BSM";
    public static final String JOB_NAME_FOR_BSM_SEND_EMAIL = "SEND_EMAIL";
    

    public static final String JOB_SELECTOR_FOR_STH_CMN =
            JOB_GROUP_FOR_BSM + JOB_SPLIT + JOB_NAME_FOR_BSM_SEND_EMAIL + ","
            ;

    public static final String JOB_SELECTOR_VALUES =
            JOB_SELECTOR_FOR_STH_CMN
            ;
}
