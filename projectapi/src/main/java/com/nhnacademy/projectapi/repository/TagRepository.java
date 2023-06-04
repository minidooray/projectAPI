package com.nhnacademy.projectapi.repository;

import com.nhnacademy.projectapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<Tag, Long> {
}
