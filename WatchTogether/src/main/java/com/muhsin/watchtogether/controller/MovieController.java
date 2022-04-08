package com.muhsin.watchtogether.controller;


import com.muhsin.watchtogether.model.Movie;
import com.muhsin.watchtogether.model.User;
import com.muhsin.watchtogether.repository.MovieRepository;
import com.muhsin.watchtogether.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie/")
@CrossOrigin
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    private UserRepository userRepository;

    @GetMapping("allmovies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<>(this.movieRepository.getAllMovies(), HttpStatus.OK);
    }
    @GetMapping({"{movieId}"})
    public ResponseEntity<Movie> getUser(@PathVariable(value = "movieId") Integer movieId){
        return new ResponseEntity<>(this.movieRepository.findMovieById(movieId), HttpStatus.OK);
    }

    @PostMapping("addmovie")
    public ResponseEntity<?> addMovie(@RequestBody Movie newMovie){
        movieRepository.save(newMovie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("addmovie/{userId}")
    public ResponseEntity<?> addMovieToList(@RequestBody Movie movie, @PathVariable(value="userId") Integer userId){
        User user = userRepository.findUserById(userId);

        if (user!=null){
            user.getMovieList().add(movie);
            userRepository.save(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
