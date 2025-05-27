package com.example.kummiRoom_backend.api.service;

import com.example.kummiRoom_backend.api.dto.responseDto.CourseResponseDto;
import com.example.kummiRoom_backend.api.entity.Course;
import com.example.kummiRoom_backend.api.repository.CourseRepository;
import com.example.kummiRoom_backend.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseResponseDto> getCoursesBySchoolId(Long schoolId) {
        List<Course> courses = courseRepository.findAllBySchool_SchoolId(schoolId);

        return courses.stream()
                .map(course -> CourseResponseDto.builder()
                        .courseId(course.getCourseId())
                        .courseName(course.getCourseName())
                        .courseType(course.getCourseType())
                        .courseArea(course.getCourseArea())
                        .semester(course.getSemester())
                        .description(course.getDescription())
                        .maxStudents(course.getMaxStudents())
                        .createdAt(course.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());
    }

    public CourseResponseDto getCourseBySchoolIdAndCourseId(Long schoolId, Long courseId) {
        Course course = courseRepository.findBySchool_SchoolIdAndCourseId(schoolId, courseId)
                .orElseThrow(() -> new NotFoundException("해당 과목을 찾을 수 없습니다."));

        return CourseResponseDto.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .courseArea(course.getCourseArea())
                .semester(course.getSemester())
                .description(course.getDescription())
                .maxStudents(course.getMaxStudents())
                .createdAt(course.getUpdatedAt())
                .build();
    }
}