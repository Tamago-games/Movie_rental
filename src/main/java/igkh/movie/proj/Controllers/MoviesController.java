package igkh.movie.proj.Controllers;

import igkh.movie.proj.DAO.MovieDAO;
import igkh.movie.proj.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/movies")
public class MoviesController {
    private final MovieDAO movieDAO;

    @Autowired
    public MoviesController(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("movies", movieDAO.index());
        return "/movies/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id){
        model.addAttribute("movie", movieDAO.show(id));
        return "/movies/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("movie") Movie movie){
        return "/movies/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("movie") @Valid Movie movie, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/movies/new";
        movieDAO.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("movie", movieDAO.show(id));
        return "/movies/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("movie") @Valid Movie movie, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "/movies/edit";
        movieDAO.update(id, movie);
        return "redirect:/movies";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        movieDAO.delete(id);
        return "redirect:/movies";
    }
}
