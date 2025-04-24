package org.example.database_management.database.mapper;

import org.example.database_management.database.DTO.ProjectDTO;
import org.example.database_management.database.entity.ProjectEntity;

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
}
