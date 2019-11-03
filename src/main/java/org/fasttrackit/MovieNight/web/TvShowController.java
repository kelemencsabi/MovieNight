package org.fasttrackit.MovieNight.web;

import org.fasttrackit.MovieNight.domain.TvShow;
import org.fasttrackit.MovieNight.persistence.TvShowRepository;
import org.fasttrackit.MovieNight.service.TvShowService;
import org.fasttrackit.MovieNight.transfer.tvShow.GetTvShowsRequest;
import org.fasttrackit.MovieNight.transfer.tvShow.SaveTvShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tvShows")
public class TvShowController {
    private final TvShowService tvShowService;
    private final TvShowRepository tvShowRepository;

    @Autowired
    public TvShowController(TvShowService tvShowService, TvShowRepository tvShowRepository) {
        this.tvShowService = tvShowService;
        this.tvShowRepository = tvShowRepository;
    }

    @PostMapping
    public ResponseEntity<TvShow> createTvShow(@RequestBody @Valid SaveTvShowRequest request) {
        TvShow tvShow = tvShowService.createTvShow(request);
        return new ResponseEntity<>(tvShow, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TvShow> updateTvShow(@PathVariable("id") long id, @RequestBody SaveTvShowRequest request) {
        TvShow tvShow = tvShowService.updateTvShow(id, request);
        return new ResponseEntity<>(tvShow, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<TvShow> deleteTvShow(long id) {
        tvShowService.deleteTvShow(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvShow> getTvShow(@PathVariable("id") long id) {
        TvShow tvShow = tvShowService.getTvShow(id);
        return new ResponseEntity<>(tvShow, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<TvShow>> getTvShows(GetTvShowsRequest request, Pageable pageable) {
        Page<TvShow> tvShows = tvShowService.getTvShows(request, pageable);
        return new ResponseEntity<>(tvShows, HttpStatus.OK);
    }
}
