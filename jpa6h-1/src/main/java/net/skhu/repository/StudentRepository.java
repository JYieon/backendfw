package net.skhu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>  {

    Student findByStudentNo(String studentNo);

    Page<Student> findByStudentNoOrNameStartsWithOrDepartmentNameStartsWith(
            String studentNo, String name, String departmentName, Pageable pageable);
}

