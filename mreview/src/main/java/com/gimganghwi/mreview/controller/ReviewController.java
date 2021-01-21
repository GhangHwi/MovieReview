package com.gimganghwi.mreview.controller;

import com.gimganghwi.mreview.dto.ReviewDTO;
import com.gimganghwi.mreview.service.ReviewService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno){
        log.info("------------list-------------");
        log.info("MNO: " + mno);

        List<ReviewDTO> reviewDTOList = reviewService.getListOMovie(mno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO movieReviewDTO){
        log.info("------------add MovieReview-------------");
        log.info("reviewDTO: " + movieReviewDTO);

        Long reviewnum = reviewService.register(movieReviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);

    }

    @PutMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody ReviewDTO movieREviewDTO){
        reviewService.modify(movieREviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewnum){
        reviewService.remove(reviewnum);

        return new ResponseEntity<>(reviewnum,HttpStatus.OK);
    }
}
