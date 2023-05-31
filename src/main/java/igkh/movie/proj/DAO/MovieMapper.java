package igkh.movie.proj.DAO;

import igkh.movie.proj.models.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setPerson_id(resultSet.getInt("person_id"));
        movie.setTitle(resultSet.getString("title"));
        movie.setDirector(resultSet.getString("director"));
        movie.setYearOfProduction(resultSet.getInt("yearofproduction"));
        return movie;
    }
}
