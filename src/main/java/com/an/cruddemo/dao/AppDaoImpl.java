package com.an.cruddemo.dao;

import com.an.cruddemo.entity.Course;
import com.an.cruddemo.entity.Instructor;
import com.an.cruddemo.entity.InstructorDetail;
import com.an.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {

    private final EntityManager entityManager;

    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(Integer id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(Integer id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        // executing get all courses in session by lazy load
        List<Course> courses = instructor.getCourses();

        courses.forEach(course -> course.setInstructor(null));

        if (null != instructor) {
            entityManager.remove(instructor);
        }
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(Integer id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        instructorDetail.getInstructor().setInstructorDetail(null);
        if (null != instructorDetail) {
            entityManager.remove(instructorDetail);
        }
    }

    @Override
    public List<Course> findCoursesByInstructorId(Integer id) {
        TypedQuery<Course> query = entityManager.createQuery("FROM Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdByJoinFetch(Integer id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id = :data", Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public InstructorDetail findInstructorDetailById(Integer id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    public Course findCourseById(Integer id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(Integer id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(Integer id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c JOIN FETCH c.reviews where c.id = :id", Course.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsByCourseId(Integer id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c JOIN FETCH c.students where c.id = :id", Course.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(Integer id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s JOIN FETCH s.courses where s.id = :id", Student.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(Integer id) {
        entityManager.remove(entityManager.find(Student.class, id));
    }
}
