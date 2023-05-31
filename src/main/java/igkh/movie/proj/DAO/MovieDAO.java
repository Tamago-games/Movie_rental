package igkh.movie.proj.DAO;

import igkh.movie.proj.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> index(){
        return jdbcTemplate.query("SELECT * FROM movie", new MovieMapper());
    }

    public Movie show(int id){
        return jdbcTemplate.query("SELECT * FROM movie WHERE id=?", new Object[]{id}, new MovieMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Movie movie){
        jdbcTemplate.update("INSERT INTO movie(title, director, yearofproduction) VALUES (?, ?, ?)",
                movie.getTitle(), movie.getDirector(), movie.getYearOfProduction());
    }

    public void update(int id, Movie movie){
        jdbcTemplate.update("UPDATE movie SET title=?, director=?, yearofproduction=? WHERE id=?",
                movie.getTitle(), movie.getDirector(), movie.getYearOfProduction(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM movie WHERE id?", id);
    }
}
