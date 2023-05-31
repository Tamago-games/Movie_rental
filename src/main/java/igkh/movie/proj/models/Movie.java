package igkh.movie.proj.models;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Movie {
    private int id;
    private int person_id;

    @NotEmpty(message = "Title shouldn't be empty.")
    @Size(min=2, max=100, message = "Title should be between 2 and 30 characters.")
    private String title;

    @NotEmpty(message = "Title shouldn't be empty.")
    @Size(min=2, max=30, message = "Director's name should be between 2 and 30 characters.")
    private String director;

    @Min(value = 1895, message = "Year should be between 1895 and 2023.")
    @Max(value = 2023, message = "Year should be between 1895 and 2023.")
    private int yearOfProduction;

    public Movie() {
    }

    public Movie(int id, int person_id, String title, String director, int yearOfProduction) {
        this.id = id;
        this.person_id = person_id;
        this.title = title;
        this.director = director;
        this.yearOfProduction = yearOfProduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
}
