package com.nhnacademy.projectapi.repository;

import com.nhnacademy.projectapi.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskTagRepository  extends JpaRepository<TaskTag, Long> {

    Optional<List<TaskTag>> findByPk_TaskId(Long id);
    Optional<TaskTag> findByPk_TagId(Long id);

}
