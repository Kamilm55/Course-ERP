package com.kamil.courseerpbackend.service.course;

import com.kamil.courseerpbackend.model.entity.Course;
import com.kamil.courseerpbackend.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    @Override
    public void insert(Course course) {
        courseRepository.save(course);
    }
}
