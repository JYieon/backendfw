package net.skhu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.skhu.dto.Student;
import net.skhu.mapper.StudentMapper;
import net.skhu.model.StudentEdit;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public StudentEdit findOne(int id) {
        Student studentDto = studentMapper.findOne(id);
        return toEditModel(studentDto);
    }

    public Student findByStudentNo(String studentNo) {
        return studentMapper.findByStudentNo(studentNo);
    }

    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    public void insert(StudentEdit studentEdit) {
        Student student = toDto(studentEdit);
        studentMapper.insert(student);
    }

    public void update(StudentEdit studentEdit) {
        Student student = toDto(studentEdit);
        studentMapper.update(student);
    }

    public void delete(int id) {
        studentMapper.delete(id);
    }

    public Student toDto(StudentEdit studentEdit) {
        Student studentDto = new Student();
        studentDto.setId(studentEdit.getId());
        studentDto.setStudentNo(studentEdit.getStudentNo());
        studentDto.setName(studentEdit.getName());
        studentDto.setDepartmentId(studentEdit.getDepartmentId());
        studentDto.setEmail(studentEdit.getEmail());
        studentDto.setPhone(studentEdit.getPhone());
        studentDto.setSex(studentEdit.getSex());
        return studentDto;
    }

    public StudentEdit toEditModel(Student studentDto) {
        StudentEdit studentEdit = new StudentEdit();
        studentEdit.setId(studentDto.getId());
        studentEdit.setStudentNo(studentDto.getStudentNo());
        studentEdit.setName(studentDto.getName());
        studentEdit.setDepartmentId(studentDto.getDepartmentId());
        studentEdit.setEmail(studentDto.getEmail());
        studentEdit.setPhone(studentDto.getPhone());
        studentEdit.setSex(studentDto.getSex());
        return studentEdit;
    }
}

