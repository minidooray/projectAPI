package com.nhnacademy.projectapi.task.repository;

import com.nhnacademy.projectapi.project.entity.Project;
import com.nhnacademy.projectapi.task.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @MockBean
    TaskRepository taskRepository;

    @Test
    void findByProject_ProjectId() {
        List<Task> list = new ArrayList<>();
        Project project = new Project().builder()
                .projectAdminId("관리자")
                .projectDescription("설명")
                .projectName("프로젝트 이름")
                .projectStatus("ACTIVE")
                .build();

        Task task = new Task().builder()
                .milestoneId(1L)
                .taskRegisterId("등록자")
                .taskManagerId("담당자")
                .taskContent("내용")
                .taskTitle("제목")
                .taskStartAt(LocalDate.now())
                .taskEndAt(LocalDate.now())
                .project(project)
                .build();
        list.add(task);

        this.entityManager.merge(project);
        this.entityManager.merge(task);

        given(taskRepository.findByProject_ProjectId(anyLong()))
                .willReturn(Optional.of(list));

        List<Task> actual = taskRepository.findByProject_ProjectId(1L).get();

        assertThat(actual.get(0)).isEqualTo(task);
    }
}