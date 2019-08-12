package pl.nogacz.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.nogacz.tasks.domain.Task;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Dawid Nogacz on 10.07.2019
 */
@Transactional
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);

    @Override
    long count();
}
