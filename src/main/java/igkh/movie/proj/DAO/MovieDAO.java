package igkh.movie.proj.DAO;

import igkh.movie.proj.models.Movie;
import igkh.movie.proj.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        jdbcTemplate.update("DELETE FROM movie WHERE id=?", id);
    }

    public Optional<Person> getMovie(int id){
        return jdbcTemplate.query("SELECT person.* FROM movie JOIN person ON movie.person_id=person.id WHERE movie.id=?",
                new Object[]{id}, new PersonMapper()).stream().findAny();
    }

    public void freeBook(int id){
        jdbcTemplate.update("UPDATE movie SET person_id=null WHERE id=?", id);
    }

    public void giveABook(int id, Person person){
        jdbcTemplate.update("UPDATE movie SET person_id=? WHERE id=?", person.getId(), id);
    }
}
