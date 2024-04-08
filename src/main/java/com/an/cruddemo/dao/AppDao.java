package com.an.cruddemo.dao;

import com.an.cruddemo.entity.Course;
import com.an.cruddemo.entity.Instructor;
import com.an.cruddemo.entity.InstructorDetail;
import com.an.cruddemo.entity.Student;

import java.util.List;

public interface AppDao {

    void save(Instructor instructor);

    void saveCourse(Course course);

    Instructor findInstructorById(Integer id);

    Course findCourseById(Integer id);

    Instructor findInstructorByIdByJoinFetch(Integer id);

    InstructorDetail findInstructorDetailById(Integer id);

    void deleteInstructorById(Integer id);
    void deleteInstructorDetailById(Integer id);

    List<Course> findCoursesByInstructorId(Integer id);

    void update(Instructor instructor);

    void updateCourse(Course course);

    void deleteCourseById(Integer id);

    void deleteStudentById(Integer id);

    Course findCourseAndReviewsByCourseId(Integer id);

    Course findCourseAndStudentsByCourseId(Integer id);
    Student findStudentAndCoursesByStudentId(Integer id);

    void updateStudent(Student student);
}
