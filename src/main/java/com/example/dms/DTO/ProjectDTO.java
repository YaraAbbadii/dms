package com.example.dms.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectDTO {

    private Long id;
    private String name;
    private String budget;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date deadline;
}
