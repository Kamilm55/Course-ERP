package com.kamil.courseerpbackend.model.entity;

import com.kamil.courseerpbackend.model.enums.BranchStatus;
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
@Table(name = "branches")
public class Branch extends BaseEntity{
    String name;
    @Enumerated(EnumType.STRING)
    BranchStatus status;
    String address;
    double lat;
    double lon;
    Long courseId;
    //    name       varchar(50)           not null,
//    status     varchar(30)           not null,
//    address    varchar(255),
//    lat        double precision,
//    lon        double precision,
//    course_id  bigint

}
