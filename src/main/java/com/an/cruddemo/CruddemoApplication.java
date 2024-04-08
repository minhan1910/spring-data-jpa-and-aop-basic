package com.an.cruddemo;

import com.an.cruddemo.dao.AccountDao;
import com.an.cruddemo.dao.AppDao;
import com.an.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao, AppService appService, AccountDao accountDao) {
        return runner -> {
//            deleteInstructor(appDao);
//            showInstructorDetail(appDao);
//            createNewInstructorWithCourses(appDao);
//            deletetIDtail(appDao);
//            findInstructorWithCoursesInInstructorDetailPage_LazyLoad_JoinFetch(appDao);
//            updateInst(appDao);
//            updateCourse(appDao);
//            deleteCourse(appDao);
//            createNewInstructorWithCourses(appDao);

//            createCourseAndStudents(appDao);

//            Student student = appDao.findStudentAndCoursesByStudentId(2);
//            Course course = appDao.findCourseById(11);
//            student.addCourse(course);
//            appDao.updateStudent(student);

//            appDao.deleteStudentById(1);

            appService.addAccount();
            System.out.println();
            accountDao.addAccount();
            System.out.println();
            accountDao.updateAccount();
        };
    }

    private void createCourseAndStudents(AppDao appDao) {
        var course = new Course("new course 10");

        var std1 = new Student("an", "minh", "minhanStd@gmail.com");
        var std2 = new Student("an 2", "minh 2", "minhanStd2@gmail.com");

        course.addStudent(std1);
        course.addStudent(std2);

        appDao.saveCourse(course);

        System.out.println(course);
        System.out.println(course.getStudents());
    }

    private void updateCourse(AppDao appDao) {
        Course course = appDao.findCourseById(10);
        course.setTitle("New Course Java");
        appDao.updateCourse(course);
    }

    private void deletetIDtail(AppDao appDao) {
        appDao.deleteInstructorDetailById(6);
    }

    private void showInstructorDetail(AppDao appDao) {
        InstructorDetail instructorDetailById = appDao.findInstructorDetailById(2);

        System.out.println(instructorDetailById);
        System.out.println(instructorDetailById.getInstructor());
    }

    private void findInstructorWithCoursesEager(AppDao appDao) {
        Instructor instructor = appDao.findInstructorById(1);

        System.out.println(instructor);
//        System.out.println(instructor.getCourses());
    }

    private void updateInst(AppDao appDao) {
        Instructor instructor = appDao.findInstructorById(1);
        instructor.setFirstName("Minh An 2002");
        appDao.update(instructor);
    }

    private void findInstructorWithCoursesInInstructorDetailPage_LazyLoad_JoinFetch(AppDao appDao) {
        Instructor instructor = appDao.findInstructorByIdByJoinFetch(1);

        System.out.println(instructor);
        System.out.println(instructor.getInstructorDetail());
        System.out.println(instructor.getCourses());
    }

    private void findInstructorWithCoursesInInstructorDetailPage_LazyLoad(AppDao appDao) {
        Instructor instructor = appDao.findInstructorById(1);
        System.out.println(instructor);

        List<Course> coursesByInstructorId = appDao.findCoursesByInstructorId(instructor.getId());
        instructor.setCourses(coursesByInstructorId);

        System.out.println(instructor.getCourses());
//        System.out.println(coursesByInstructorId);
    }

    private void createNewInstructorWithCourses(AppDao appDao) {
        var instructor = new Instructor("an 2", "minh 2", "minhan@gmail.com");
        var instructorDetail = new InstructorDetail("minhanChannel 2", "coding");
        instructor.setInstructorDetail(instructorDetail);

        var course1 = new Course("Java Spring");
        var course2 = new Course("C# Dotnet 8");

        instructor.addCourse(course1);
        instructor.addCourse(course2);

        var commentCourse1 = new Review("comment 1");
        var comment1Course1 = new Review("comment 2");

        course1.addReview(commentCourse1);
        course1.addReview(comment1Course1);

        appDao.save(instructor);

        System.out.println("Create Successfully!");

        System.out.println("Show");

        System.out.println(appDao.findInstructorById(instructor.getId()));
    }

    private void deleteCourse(AppDao appDao) {
        var id = 10;
        appDao.deleteCourseById(id);
    }

    private void deleteInstructor(AppDao appDao) {
		var id = 1;
		appDao.deleteCourseById(id);
		System.out.println("Delete successfully!");
    }
}
