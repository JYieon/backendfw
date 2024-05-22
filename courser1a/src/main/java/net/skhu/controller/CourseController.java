package net.skhu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import net.skhu.entity.Course;
import net.skhu.entity.Professor;
import net.skhu.repository.CourseRepository;
import net.skhu.repository.ProfessorRepository;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired CourseRepository courseRepository;
    @Autowired ProfessorRepository professorRepository;

    @GetMapping("list")
    public String list(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "course/list";
    }

    @GetMapping("create")
    public String create(Model model) {
        Course course = new Course();
        List<Professor> professors = professorRepository.findAll();
        model.addAttribute("course", course);
        model.addAttribute("professors", professors);
        return "course/edit";
    }

    @PostMapping("create")
    public String create(Model model, Course course) {
        courseRepository.save(course);
        return "redirect:list";
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id) {
        Course course = courseRepository.findById(id).get();
        List<Professor> professors = professorRepository.findAll();
        model.addAttribute("course", course);
        model.addAttribute("professors", professors);
        return "course/edit";
    }

    @PostMapping("edit")
    public String edit(Model model, Course course) {
        courseRepository.save(course);
        return "redirect:list";
    }

    @GetMapping("delete")
    public String delete(Model model, @RequestParam("id") int id) {
        courseRepository.deleteById(id);
        return "redirect:list";
    }
}
