package com.kamil.courseerpbackend.model.entity;

import com.kamil.courseerpbackend.model.enums.CourseStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "courses")
public class Course extends BaseEntity{
    String name;
    @Enumerated(EnumType.STRING)
    CourseStatus status;
}
