package com.example.dms.controller;


import com.example.dms.DTO.ProjectDTO;
import com.example.dms.data.GeneralResponse;
import com.example.dms.service.ProjectService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("get/{id}")
    public GeneralResponse getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id);
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
