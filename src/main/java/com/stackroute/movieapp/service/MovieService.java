package com.stackroute.movieapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistException;
import com.stackroute.movieapp.exception.MovieNotFoundException;

@Service
public interface MovieService {

	public Movie addMovie(Movie movie) throws MovieAlreadyExistException;
	public Iterable<Movie> getAllMovies();
	public boolean deleteMovie(int id) throws MovieNotFoundException ;
	public Movie updateMovie(Movie movie,int id) throws MovieNotFoundException ;
	public Optional<Movie> getMovieById(int id) throws MovieNotFoundException;
	public List<Movie> getMovieByTitle(String title);

}
