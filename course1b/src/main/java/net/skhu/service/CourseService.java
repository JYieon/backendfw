package net.skhu.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import net.skhu.entity.Course;
import net.skhu.model.CourseEdit;
import net.skhu.model.Pagination;
import net.skhu.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired CourseRepository courseRepository;
    ModelMapper modelMapper = new ModelMapper();

    public CourseEdit findOne(int id) {
        Course courseEntity = courseRepository.findById(id).get();
        return toEditModel(courseEntity);
    }

    public Course findByCode(String code) {
        return courseRepository.findByCode(code);
    }

    public List<Course> findAll(Pagination pagination) {
        PageRequest pageRequest = PageRequest.of(pagination.getPg() - 1,
                pagination.getSz(),
                Sort.Direction.ASC, "id");
        Page<Course> page = courseRepository.findAll(pageRequest);
        pagination.setRecordCount((int)page.getTotalElements());
        return page.getContent();
    }

    public void insert(CourseEdit courseEdit, BindingResult bindingResult,
            Pagination pagination) throws Exception {
        if (hasErrors(courseEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Course course = toEntity(courseEdit);
        courseRepository.save(course);
        int lastPage = (int)Math.ceil((double)courseRepository.count() / pagination.getSz());
        pagination.setPg(lastPage);
    }

    public void update(CourseEdit courseEdit,
            BindingResult bindingResult) throws Exception {
        if (hasErrors(courseEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Course course = toEntity(courseEdit);
        courseRepository.save(course);
    }

    public void delete(int id) {
        courseRepository.deleteById(id);
    }

    public Course toEntity(CourseEdit courseEdit) {
        return modelMapper.map(courseEdit, Course.class);
    }

    public CourseEdit toEditModel(Course courseEntity) {
        return modelMapper.map(courseEntity, CourseEdit.class);
    }

    public boolean hasErrors(CourseEdit courseEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return true;
        Course course2 = findByCode(courseEdit.getCode());
        if (course2 != null && course2.getId() != courseEdit.getId()) {
            bindingResult.rejectValue("code", null, "과목코드가 중복됩니다.");
            return true;
        }
        return false;
    }
}

