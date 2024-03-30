package net.skhu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import net.skhu.dto.Genre;
import net.skhu.dto.Movie;
import net.skhu.mapper.GenreMapper;
import net.skhu.mapper.MovieMapper;

@Controller
@RequestMapping("movie")
public class MovieController {

    @Autowired MovieMapper movieMapper;
    @Autowired GenreMapper genreMapper;

    @GetMapping("list")
    public String list(Model model) {
        List<Movie> movies = movieMapper.findAll();
        model.addAttribute("movies", movies);
        return "movie/list";
    }

    @GetMapping("create")
    public String create(Model model) {
        Movie movie = new Movie();
        List<Genre> genres = genreMapper.findAll();
        model.addAttribute("movie", movie);
        model.addAttribute("genres", genres);
        return "movie/edit";
    }

    @PostMapping("create")
    public String create(Model model, Movie movie) {
        movieMapper.insert(movie);
        return "redirect:list";
    }

    @GetMapping("edit")
    public String edit(Model model, int id) {
        Movie movie = movieMapper.findOne(id);
        List<Genre> genres = genreMapper.findAll();
        model.addAttribute("movie", movie);
        model.addAttribute("genres", genres);
        return "movie/edit";
    }

    @PostMapping("edit")
    public String edit(Model model, Movie movie) {
        movieMapper.update(movie);
        return "redirect:list";
    }

    @GetMapping("delete")
    public String delete(Model model, int id) {
        movieMapper.delete(id);
        return "redirect:list";
    }
}

