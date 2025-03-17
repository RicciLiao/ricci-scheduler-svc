package ricciliao.scheduler.repository;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ricciliao.scheduler.pojo.po.JobSchedulerControlPo;
import ricciliao.scheduler.pojo.po.JobSchedulerControlPoPK;

import java.util.List;
import java.util.Optional;

public interface JobSchedulerControlRepository extends JpaRepository<JobSchedulerControlPo, JobSchedulerControlPoPK> {
    @Override
    List<JobSchedulerControlPo> findAll();

    @Override
    List<JobSchedulerControlPo> findAll(Sort sort);

    @Override
    List<JobSchedulerControlPo> findAllById(Iterable<JobSchedulerControlPoPK> iterable);

    @Override
    void flush();

    @Override
    void deleteInBatch(Iterable<JobSchedulerControlPo> iterable);

    @Override
    void deleteAllInBatch();

    @Override
    JobSchedulerControlPo getOne(JobSchedulerControlPoPK JobSchedulerControlPoPK);

    @Override
    <S extends JobSchedulerControlPo> List<S> findAll(Example<S> example);

    @Override
    <S extends JobSchedulerControlPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<JobSchedulerControlPo> findAll(Pageable pageable);

    @Override
    boolean existsById(JobSchedulerControlPoPK JobSchedulerControlPoPK);

    @Override
    long count();

    @Override
    void deleteById(JobSchedulerControlPoPK JobSchedulerControlPoPK);

    @Override
    void delete(JobSchedulerControlPo entity);

    @Override
    void deleteAll(Iterable<? extends JobSchedulerControlPo> entities);

    @Override
    void deleteAll();

    @Override
    <S extends JobSchedulerControlPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends JobSchedulerControlPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends JobSchedulerControlPo> long count(Example<S> example);

    @Override
    <S extends JobSchedulerControlPo> boolean exists(Example<S> example);

    @Override
    <S extends JobSchedulerControlPo> List<S> saveAll(Iterable<S> iterable);

    @Override
    <S extends JobSchedulerControlPo> S save(S entity);

    @Override
    <S extends JobSchedulerControlPo> S saveAndFlush(S s);

    @Override
    Optional<JobSchedulerControlPo> findById(JobSchedulerControlPoPK moeJobBasePoPK);

    List<JobSchedulerControlPo> findAllByEnableIsOrderByJobGroup(String flag);

    JobSchedulerControlPo findByJobNameIsAndJobGroupIsAndEnableIs(String jobName, String jobGroup, String flag);

    JobSchedulerControlPo findByJobNameIsAndJobGroupIs(String jobName, String jobGroup);

}
