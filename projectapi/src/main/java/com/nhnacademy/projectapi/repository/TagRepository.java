package com.nhnacademy.projectapi.repository;

import com.nhnacademy.projectapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository  extends JpaRepository<Tag, Long> {

    Optional<List<Tag>> findByProject_ProjectId(Long id);
}
