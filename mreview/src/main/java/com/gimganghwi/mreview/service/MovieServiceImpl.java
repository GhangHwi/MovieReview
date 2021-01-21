package com.gimganghwi.mreview.service;

import com.gimganghwi.mreview.dto.MovieDTO;
import com.gimganghwi.mreview.entity.Movie;
import com.gimganghwi.mreview.entity.MovieImage;
import com.gimganghwi.mreview.repository.MovieImageRepository;
import com.gimganghwi.mreview.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements  MovieService{

    private final MovieRepository movieRepository;

    private final MovieImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {

        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie)entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>)entityMap.get("imgList");

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            imageRepository.save(movieImage);
        });

        return movie.getMno();
    }
}
