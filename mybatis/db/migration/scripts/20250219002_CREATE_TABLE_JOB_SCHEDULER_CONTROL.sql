create table `job_scheduler_control`
(
    `job_name`   varchar(50)  not null,
    `job_desc`   varchar(200) not null,
    `freq`       varchar(50)  not null,
    `enable`     varchar(1)   not null,
    `endpoint`   varchar(200) not null,
    `create_by`  bigint       not null,
    `create_dtm` datetime(6)  not null,
    `update_by`  bigint       not null,
    `update_dtm` datetime(6)  not null,
    `job_group`  varchar(50)  not null,
    constraint `job_scheduler_control_pk` primary key (`job_name`, `job_group`)
);

-- //@undo
drop table `job_scheduler_control`;
