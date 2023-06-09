package igkh.movie.proj.DAO;

import igkh.movie.proj.models.Movie;
import igkh.movie.proj.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public List<Movie> personsBook(int id){
        return jdbcTemplate.query("SELECT * FROM movie WHERE person_id=?", new Object[]{id}, new MovieMapper());
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(name, yearofbirth) VALUES (?, ?)",
                person.getName(), person.getYearOfBirth());
    }

    public void update(int id, Person person){
        jdbcTemplate.update("UPDATE person SET name=?, yearofbirth=? WHERE id=?",
                person.getName(), person.getYearOfBirth(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public Optional<Person> getPersonName(String name){
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?", new Object[]{name}, new PersonMapper())
                .stream().findAny();
    }
}
