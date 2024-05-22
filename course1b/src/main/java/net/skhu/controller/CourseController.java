package net.skhu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import net.skhu.entity.Course;
import net.skhu.entity.Professor;
import net.skhu.model.Pagination;
import net.skhu.model.CourseEdit;
import net.skhu.service.ProfessorService;
import net.skhu.service.CourseService;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired CourseService courseService;
    @Autowired ProfessorService professorService;

    @GetMapping("list")
    public String list(Model model, Pagination pagination) {
        List<Course> courses = courseService.findAll(pagination);
        model.addAttribute("courses", courses);
        return "course/list";
    }

    @GetMapping("create")
    public String create(Model model, Pagination pagination) {
    	CourseEdit courseEdit = new CourseEdit();
        List<Professor> professors = professorService.findAll();
        model.addAttribute("courseEdit", courseEdit);
        model.addAttribute("professors", professors);
        return "course/edit";
    }

    @PostMapping("create")
    public String create(Model model, Pagination pagination,
            @Valid CourseEdit courseEdit, BindingResult bindingResult) {
        try {
        	courseService.insert(courseEdit, bindingResult, pagination);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("professors", professorService.findAll());
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            return "course/edit";
        }
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id,
            Pagination pagination) {
    	CourseEdit courseEdit = courseService.findOne(id);
        List<Professor> professors = professorService.findAll();
        model.addAttribute("courseEdit", courseEdit);
        model.addAttribute("professors", professors);
        return "course/edit";
    }

    @PostMapping(value="edit", params="cmd=save")
    public String edit(Model model, Pagination pagination,
            @Valid CourseEdit courseEdit, BindingResult bindingResult) {
        try {
        	courseService.update(courseEdit, bindingResult);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("professors", professorService.findAll());
            bindingResult.rejectValue("", null, "수정할 수 없습니다.");
            return "course/edit";
        }
    }

    @PostMapping(value="edit", params="cmd=delete")
    public String delete(Model model, Pagination pagination,
    		CourseEdit courseEdit, BindingResult bindingResult) {
        try {
        	courseService.delete(courseEdit.getId());
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("professors", professorService.findAll());
            bindingResult.rejectValue("", null, "삭제할 수 없습니다.");
            return "course/edit";
        }
    }
}

