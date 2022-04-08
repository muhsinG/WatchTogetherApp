package com.muhsin.watchtogether.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userId;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    @ManyToMany  (cascade = CascadeType.ALL) //(fetch= FetchType.LAZY,
    @JoinTable(
            name = "user_movie",
            joinColumns = @JoinColumn(name = "user_id"), //, referencedColumnName = ""
            inverseJoinColumns = @JoinColumn(name = "movie_movieid")
    )
    Set<Movie> movieList = new HashSet<>();

    public User(String firstName, String lastName, Set<Movie> movieList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.movieList = movieList;
    }
    public User(){}

    @Column(name = "id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(Set<Movie> movieList) {
        this.movieList = movieList;
    }
}
