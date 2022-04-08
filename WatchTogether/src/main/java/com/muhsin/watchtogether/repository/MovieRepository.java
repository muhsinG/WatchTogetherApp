package com.muhsin.watchtogether.repository;

import com.muhsin.watchtogether.model.Movie;
import com.muhsin.watchtogether.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("movie")
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT * FROM d2q60ocmgllri7.public.movie", nativeQuery = true)
    List<Movie> getAllMovies();

    @Query(value = "SELECT * FROM d2q60ocmgllri7.public.movie WHERE movieid = ?1", nativeQuery = true)
    Movie findMovieById(Integer id);



}