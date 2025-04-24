package org.example.database_management.database.controller;


import lombok.RequiredArgsConstructor;
import org.example.database_management.database.DTO.ProjectDTO;
import org.example.database_management.database.entity.ProjectEntity;
import org.example.database_management.database.infrastructure.data.GeneralResponse;
import org.example.database_management.database.service.ProjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/all")
    public GeneralResponse getAll() {
        return projectService.getAllProjects();
    }

    @PostMapping("/create")
    public GeneralResponse createAddress(@RequestBody ProjectDTO projectDTO) {
        return projectService.createNewProject(projectDTO);
    }

    @PutMapping("/{id}")
    public GeneralResponse updateAddress(@PathVariable Long id, @RequestBody ProjectDTO projectDTODetails) {
        return projectService.updateProject(id, projectDTODetails);
    }


    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
