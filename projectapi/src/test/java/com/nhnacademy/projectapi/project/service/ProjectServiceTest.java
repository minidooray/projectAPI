//package com.nhnacademy.projectapi.project.service;
//
//import com.nhnacademy.projectapi.project.dto.ProjectResponseDTO;
//import com.nhnacademy.projectapi.project.entity.Project;
//import com.nhnacademy.projectapi.project.repository.ProjectRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ProjectServiceTest {
//
//    @InjectMocks
//    ProjectService projectService;
//
//    @SpyBean
//    ProjectRepository projectRepository;
//
//    @Test
//    void getProjects() {
//        List<ProjectResponseDTO> list = new ArrayList<>();
//        list.add(new ProjectResponseDTO(1L,"admin","프로젝트1","설명","ACTIVE"));
//
//        Project project = new Project().builder()
//                .projectId(1L)
//                .projectAdminId("관리자")
//                .projectDescription("설명")
//                .projectName("프로젝트 이름")
//                .projectStatus("ACTIVE")
//                .build();
//
//        when(projectRepository.findAll())
//                .thenReturn(List.of(project));
//        when(projectService.getProjects())
//                .thenReturn(list);
//
//        List<ProjectResponseDTO> expect = projectService.getProjects();
//
//        verify(projectRepository, times(1))
//                .findAll();
//    }
//
//    @Test
//    void getProject() {
//        ProjectResponseDTO dto = new ProjectResponseDTO(1L,"admin","프로젝트1","설명","ACTIVE");
////        Project project = new Project().builder()
////                .projectAdminId("관리자")
////                .projectDescription("설명")
////                .projectName("프로젝트 이름")
////                .projectStatus("ACTIVE")
////                .build();
////        projectRepository.save(project);
//
//        given(projectService.getProject(anyLong()))
//                .willReturn(dto);
//
//        projectService.getProject(anyLong());
//
//        verify(projectRepository, times(1))
//                .findById(anyLong());
//    }
//
//    @Test
//    void createProject() {
//    }
//
//    @Test
//    void updateProject() {
//    }
//}