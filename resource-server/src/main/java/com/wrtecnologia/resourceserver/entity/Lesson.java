package com.wrtecnologia.resourceserver.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
// @AllArgsConstructor - Desabilitado por incompatibilidade (não funcional)
public class Lesson {
    private String title;
    private String description;
    private String instructor;
    private LocalDateTime schedule;

    public Lesson(String title, String description, String instructor, LocalDateTime schedule) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.schedule = schedule;
    }
}

