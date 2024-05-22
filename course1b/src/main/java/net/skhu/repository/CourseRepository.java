package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.skhu.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>  {

    Course findByCode(String code);
}
