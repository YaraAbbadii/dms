package com.example.dms.service;


import com.example.dms.DTO.ProjectDTO;
import com.example.dms.data.GeneralResponse;
import com.example.dms.entity.EmployeeEntity;
import com.example.dms.entity.ProjectEntity;
import com.example.dms.exception.ResourceNotFoundException;
import com.example.dms.mapper.ProjectMapper;
import com.example.dms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public GeneralResponse getAllProjects() {
        return new GeneralResponse().success(projectRepository.findAll());
    }

    public GeneralResponse getProjectById(Long id) {
        ProjectEntity projectEntity = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        ProjectDTO projectDTO = ProjectMapper.toProjectDTO(projectEntity);
        return new GeneralResponse().success(projectDTO);
    }

    public GeneralResponse createNewProject(ProjectDTO projectDTO) {
        ProjectEntity entity = ProjectMapper.toProjectEntity(projectDTO);
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
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        projectRepository.delete(project);
    }
}
