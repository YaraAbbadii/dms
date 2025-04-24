package org.example.database_management.database.service;


import lombok.RequiredArgsConstructor;
import org.example.database_management.database.DTO.ProjectDTO;
import org.example.database_management.database.entity.ProjectEntity;
import org.example.database_management.database.infrastructure.data.GeneralResponse;
import org.example.database_management.database.mapper.ProjectMapper;
import org.example.database_management.database.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public GeneralResponse getAllProjects() {
        return new GeneralResponse().success(projectRepository.findAll());
    }

    public GeneralResponse createNewProject(ProjectDTO projectDTO) {
        ProjectEntity entity= ProjectMapper.toProjectEntity(projectDTO);
        return new GeneralResponse().success(projectRepository.save(entity));
    }

    public GeneralResponse updateProject(Long id, ProjectDTO projectDTO) {
        return projectRepository.findById(id).map(projectDTODetails -> {
            projectDTODetails.setName(projectDTO.getName());
            projectDTODetails.setBudget(projectDTO.getBudget());
            projectDTODetails.setDeadline(projectDTO.getDeadline());
            return new GeneralResponse().success(projectRepository.save(projectDTODetails));
        }).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
