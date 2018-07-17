package com.stackroute.movieapp.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.service.MovieService;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
//	@Autowired
//    private Environment env;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		private MovieService movieService;
		
		@Autowired
		public MovieController(MovieService movieService) {
			this.movieService = movieService;
		}
		
		@PostMapping("/movie")
		public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
		try{
			return new ResponseEntity<Movie>(movieService.addMovie(movie),HttpStatus.CREATED);
		}catch(MovieAlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		}
		
		@GetMapping("/movies")
		public ResponseEntity<?> getMovies()
		{
//			System.out.println(env.getProperty("com.stackroute.username"));
			 
			logger.debug("This is a debug message");
			logger.info("This is an info message");
			return new ResponseEntity<Iterable<Movie>>(movieService.getAllMovies(),HttpStatus.OK);
		}
		
		@DeleteMapping("/movie/{id}")
		public ResponseEntity<?> deleteMovie(@PathVariable("id") int id){
		try{
			movieService.deleteMovie(id);
			return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
		}catch(MovieNotFoundException e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		}
		
		
		@PutMapping("/movie/{id}")
		public ResponseEntity<?> updateMovie(@RequestBody Movie movie, @PathVariable("id") int id){
			try {
			movieService.updateMovie(movie, id);
			return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
			}catch(MovieNotFoundException e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			}
		}
		@GetMapping("/movie/{id}")
		public ResponseEntity<?> movieById(@PathVariable("id") int id){
			try {
			return new ResponseEntity<Optional<Movie>>(movieService.getMovieById(id),HttpStatus.OK);
		}catch(MovieNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
					}
		}
		
		@GetMapping("/movieTitle/{title}")
		public ResponseEntity<?> movieByTitle(@PathVariable("title") String title){
			return new ResponseEntity<List<Movie>>(movieService.getMovieByTitle(title),HttpStatus.OK);
		}
		
	}

