package com.example.dms.mapper;

import com.example.dms.DTO.ProjectDTO;
import com.example.dms.entity.ProjectEntity;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {

    public static ProjectDTO toProjectDTO(ProjectEntity projectEntity) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(projectEntity.getProjectId());
        projectDTO.setName(projectEntity.getName());
        projectDTO.setBudget(projectEntity.getBudget());
        projectDTO.setDeadline(projectEntity.getDeadline());
        return projectDTO;
    }

    public static ProjectEntity toProjectEntity(ProjectDTO projectDTO) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectId(projectDTO.getId());
        projectEntity.setName(projectDTO.getName());
        projectEntity.setBudget(projectDTO.getBudget());
        projectEntity.setDeadline(projectDTO.getDeadline());
        return projectEntity;
    }

    public static List<ProjectDTO> toProjectDTOList(List<ProjectEntity> projectEntities) {
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntities) {
            ProjectDTO projectDTO = toProjectDTO(projectEntity);
            projectDTOList.add(projectDTO);
        }
        return projectDTOList;
    }

    public static List<ProjectEntity> toProjectEntityList(List<ProjectDTO> projectDTOList) {
        List<ProjectEntity> projectEntities = new ArrayList<>();
        for (ProjectDTO projectDTO : projectDTOList) {
            ProjectEntity project = toProjectEntity(projectDTO);
            projectEntities.add(project);
        }
        return projectEntities;
    }
}
