package net.skhu.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import net.skhu.dto.Course;
import net.skhu.mapper.CourseMapper;
import net.skhu.model.Pagination;
import net.skhu.model.CourseEdit;

@Service
public class CourseService {

    @Autowired CourseMapper courseMapper;
    ModelMapper modelMapper = new ModelMapper();

    public CourseEdit findOne(int id) {
        Course courseDto = courseMapper.findOne(id);
        return toEditModel(courseDto);
    }

    public Course findByCode(String code) {
        return courseMapper.findByCode(code);
    }

    public List<Course> findAll(Pagination pagination) {
        pagination.setRecordCount(courseMapper.getCount());
        return courseMapper.findAll(pagination);
    }

    public void insert(CourseEdit courseEdit, BindingResult bindingResult,
            Pagination pagination) throws Exception {
        if (bindingResult.hasErrors())
            throw new Exception("입력 데이터 오류");
        Course course2 = findByCode(courseEdit.getCode());
        if (course2 != null) {
            bindingResult.rejectValue("code", null, "강좌코드가 중복됩니다");
            throw new Exception("입력 데이터 오류");
        }
        Course course = toDto(courseEdit);
        courseMapper.insert(course);
        int lastPage = (int)Math.ceil((double)courseMapper.getCount() / pagination.getSz());
        pagination.setPg(lastPage);
    }

    public void update(CourseEdit courseEdit,
    		BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            throw new Exception("입력 데이터 오류");
        Course course2 = findByCode(courseEdit.getCode());
        if (course2 != null && course2.getId() != courseEdit.getId()) {
            bindingResult.rejectValue("code", null, "강좌코드가 중복됩니다");
            throw new Exception("입력 데이터 오류");
        }
        Course course = toDto(courseEdit);
        courseMapper.update(course);
    }

    public void delete(int id) {
        courseMapper.delete(id);
    }

    public Course toDto(CourseEdit courseEdit) {
        return modelMapper.map(courseEdit, Course.class);
    }

    public CourseEdit toEditModel(Course courseDto) {
        return modelMapper.map(courseDto, CourseEdit.class);
    }
}

