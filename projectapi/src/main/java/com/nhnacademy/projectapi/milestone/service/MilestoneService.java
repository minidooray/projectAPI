package com.nhnacademy.projectapi.milestone.service;

import com.nhnacademy.projectapi.exception.NotFoundException;
import com.nhnacademy.projectapi.milestone.entity.Milestone;
import com.nhnacademy.projectapi.project.entity.Project;
import com.nhnacademy.projectapi.milestone.repository.MilestoneRepository;
import com.nhnacademy.projectapi.project.repository.ProjectRepository;
import com.nhnacademy.projectapi.milestone.dto.MilestoneRequestDTO;
import com.nhnacademy.projectapi.milestone.dto.MilestoneResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public List<MilestoneResponseDTO> getMilestones(Long projectId){
        List<Milestone> list = milestoneRepository.findByProject_ProjectId(projectId).orElseThrow();
        return list.stream()
                .map(m -> new MilestoneResponseDTO(m.getMilestoneId(),
                        m.getMilestoneContent(),
                        m.getMilestoneStartAt(),
                        m.getMilestoneEndAt()))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public MilestoneResponseDTO getMilestone(Long milestoneId){
        Milestone milestone = milestoneRepository.findById(milestoneId).orElseThrow(()->new NotFoundException("마일스톤이 없습니다."));
        return new MilestoneResponseDTO(milestone.getMilestoneId(),
                milestone.getMilestoneContent(),
                milestone.getMilestoneStartAt(),
                milestone.getMilestoneEndAt());
    }

    public void createMilestone(Long milestoneId, MilestoneRequestDTO dto){
        Project project = projectRepository.findById(milestoneId).orElseThrow(()->new NotFoundException("프로젝트가 없습니다."));

        Milestone milestone = new Milestone().builder()
                .milestoneContent(dto.getMilestoneContent())
                .milestoneStartAt(dto.getMilestoneStartAt())
                .milestoneEndAt(dto.getMilestoneEndAt())
                .project(project)
                .build();

        milestoneRepository.save(milestone);
    }

    public void updateMilestone(Long milestoneId,MilestoneRequestDTO dto){
        Milestone milestone = milestoneRepository.findById(milestoneId).orElseThrow(()->new NotFoundException("마일스톤이 없습니다."));
        milestone.modify(dto.getMilestoneContent(),
                dto.getMilestoneStartAt(),
                dto.getMilestoneEndAt());
        milestoneRepository.save(milestone);
    }
    public void deleteMilestone(Long milestoneId){
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(()->new NotFoundException("마일스톤이 없습니다."));
        milestoneRepository.delete(milestone);
    }
}
