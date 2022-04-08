package com.muhsin.watchtogether.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movie", schema = "public")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieId;
    @Column(name = "title")
    private String title;
    @Column(name = "director")
    private String director;
    @Column(name = "yearreleased")
    private int yearReleased;
    @Column(name = "synopsis")
    private String synopsis;

    @ManyToMany(cascade = CascadeType.ALL) //(mappedBy = "movie",
    Set<User> userSet;

    public Movie(){};
    public Movie(String title, String director, int yearReleased, String synopsis) {
        this.title = title;
        this.director = director;
        this.yearReleased = yearReleased;
        this.synopsis = synopsis;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long id) {
        this.movieId = id;
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

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
