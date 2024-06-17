package net.skhu.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.skhu.entity.Student;
import net.skhu.model.Pagination;
import net.skhu.model.StudentEdit;
import net.skhu.repository.StudentRepository;

@Service
public class StudentService {

	@Data @AllArgsConstructor
    public static class Order {
        int value;
        String label;
    }


    static Order[] orders = new Order[] {
        new Order(0, "정렬 순서"),
        new Order(1, "학번 오름차순"),
        new Order(2, "학번 내림차순"),
        new Order(3, "이름 오름차순"),
        new Order(4, "학과 오름차순")
    };

    static Sort[] sorts = new Sort[] {
        Sort.by(Sort.Direction.ASC, "id"),
        Sort.by(Sort.Direction.ASC, "studentNo"),
        Sort.by(Sort.Direction.DESC, "studentNo"),
        Sort.by(Sort.Direction.ASC, "name"),
        Sort.by(Sort.Direction.ASC, "department.name"),
    };

    @Autowired StudentRepository studentRepository;
    ModelMapper modelMapper = new ModelMapper();

    public Order[] getOrders() {
        return orders;
    }

    public StudentEdit findOne(int id) {
        Student studentEntity = studentRepository.findById(id).get();
        return toEditModel(studentEntity);
    }

    public Student findByStudentNo(String studentNo) {
        return studentRepository.findByStudentNo(studentNo);
    }

    public List<Student> findAll(Pagination pagination) {
        int orderIndex = pagination.getOd();
        PageRequest pageRequest = PageRequest.of(pagination.getPg() - 1,
                pagination.getSz(), sorts[orderIndex]);
        Page<Student> page;
        if (pagination.getSt().length() == 0)
            page = studentRepository.findAll(pageRequest);
        else
            page = studentRepository.findByStudentNoOrNameStartsWithOrDepartmentNameStartsWith(
                    pagination.getSt(), pagination.getSt(), pagination.getSt(), pageRequest);
        pagination.setRecordCount((int)page.getTotalElements());
        return page.getContent();
    }

    public void insert(StudentEdit studentEdit, BindingResult bindingResult,
            Pagination pagination) throws Exception {
        if (hasErrors(studentEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Student student = toEntity(studentEdit);
        studentRepository.save(student);
          pagination.setSt("");
          pagination.setOd(0);
        int lastPage = (int)Math.ceil((double)studentRepository.count() / pagination.getSz());
        pagination.setPg(lastPage);
    }

    public void update(StudentEdit studentEdit,
            BindingResult bindingResult) throws Exception {
        if (hasErrors(studentEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Student student = toEntity(studentEdit);
        studentRepository.save(student);
    }

    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    public Student toEntity(StudentEdit studentEdit) {
        return modelMapper.map(studentEdit, Student.class);
    }

    public StudentEdit toEditModel(Student studentEntity) {
        return modelMapper.map(studentEntity, StudentEdit.class);
    }

    public boolean hasErrors(StudentEdit studentEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return true;
        Student student2 = findByStudentNo(studentEdit.getStudentNo());
        if (student2 != null && student2.getId() != studentEdit.getId()) {
            bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
            return true;
        }
        return false;
    }
}

