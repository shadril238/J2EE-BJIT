package com.devrezaur.course.management.service.controller;

import com.devrezaur.common.module.model.CustomHttpResponse;
import com.devrezaur.common.module.util.ResponseBuilder;
import com.devrezaur.course.management.service.model.Course;
import com.devrezaur.course.management.service.model.CourseEnrollmentInfo;
import com.devrezaur.course.management.service.service.CourseEnrollmentService;
import com.devrezaur.course.management.service.service.CourseService;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final CourseEnrollmentService courseEnrollmentService;

    public CourseController(CourseService courseService, CourseEnrollmentService courseEnrollmentService) {
        this.courseService = courseService;
        this.courseEnrollmentService = courseEnrollmentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomHttpResponse> addCourse(@RequestBody Course course) {
        try {
            courseService.addCourses(course);
        } catch (Exception ex) {
            return ResponseBuilder.buildFailureResponse(HttpStatus.BAD_REQUEST, "400",
                    "Failed to add course! Reason: " + ex.getMessage());
        }
        return ResponseBuilder.buildSuccessResponse(HttpStatus.CREATED,
                Map.of("message", "Successfully added course info"));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<CustomHttpResponse> getAllCourses(@RequestParam @Nullable UUID userId) {
        List<Course> courseList;
        if (userId != null) {
            List<UUID> enrolledCourseIds = courseEnrollmentService.getEnrolledCourseIds(userId);
            courseList = courseService.getCourses(enrolledCourseIds);
        } else {
            courseList = courseService.getAllCourses();
        }
        return ResponseBuilder.buildSuccessResponse(HttpStatus.OK, Map.of("courseList", courseList));
    }

    @GetMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<CustomHttpResponse> getCourseById(@PathVariable UUID courseId) {
        Course course = courseService.getCourseByUserId(courseId);
        if (course == null) {
            return ResponseBuilder.buildFailureResponse(HttpStatus.NOT_FOUND, "404",
                    "No course found for this course id!");
        }
        return ResponseBuilder.buildSuccessResponse(HttpStatus.OK, Map.of("course", course));
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomHttpResponse> updateCourse(@RequestBody Course course) {
        try {
            courseService.updateCourse(course);
        } catch (Exception ex) {
            return ResponseBuilder.buildFailureResponse(HttpStatus.BAD_REQUEST, "400",
                    "Failed to update course! Reason: " + ex.getMessage());
        }
        return ResponseBuilder.buildSuccessResponse(HttpStatus.CREATED,
                Map.of("message", "Successfully updated course info"));
    }

    @PostMapping("/enroll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomHttpResponse> enrollToCourse(@RequestBody CourseEnrollmentInfo courseEnrollmentInfo) {
        try {
            courseEnrollmentService.enrollToCourse(courseEnrollmentInfo);
        } catch (Exception ex) {
            return ResponseBuilder.buildFailureResponse(HttpStatus.BAD_REQUEST, "400",
                    "Failed to enroll to the course! Reason: " + ex.getMessage());
        }
        return ResponseBuilder.buildSuccessResponse(HttpStatus.CREATED,
                Map.of("message", "Successfully enrolled to the course"));
    }

    @GetMapping("/enrolled-users/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<CustomHttpResponse> getEnrolledUsers(@PathVariable UUID courseId) {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            List<UUID> enrolledUserIds = courseEnrollmentService.getEnrolledUserIds(courseId);
            if (!CollectionUtils.isEmpty(enrolledUserIds)) {
                userList = courseEnrollmentService.fetchEnrolledUserInformation(enrolledUserIds);
            }
        } catch (Exception ex) {
            return ResponseBuilder.buildFailureResponse(HttpStatus.EXPECTATION_FAILED, "400",
                    "Failed to get enrolled user information! Reason: " + ex.getMessage());
        }
        return ResponseBuilder.buildSuccessResponse(HttpStatus.OK, Map.of("userList", userList));
    }

}
