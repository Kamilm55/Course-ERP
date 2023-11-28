package com.kamil.courseerpbackend.model.mapper;

import com.kamil.courseerpbackend.model.entity.Course;
import com.kamil.courseerpbackend.model.payload.auth.register.RegisterPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseEntityMapper {
    CourseEntityMapper INSTANCE = Mappers.getMapper(CourseEntityMapper.class);

    @Mapping(source = "courseName" , target = "name")
    @Mapping(target = "status" , constant = "ACTIVE")
    Course fromRegisterPayloadToCourse(RegisterPayload payload);
}
