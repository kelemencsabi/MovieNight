package org.fasttrackit.MovieNight.service;

import org.fasttrackit.MovieNight.domain.TvShow;
import org.fasttrackit.MovieNight.exception.ResourceNotFoundException;
import org.fasttrackit.MovieNight.persistence.TvShowRepository;
import org.fasttrackit.MovieNight.transfer.tvShow.GetTvShowsRequest;
import org.fasttrackit.MovieNight.transfer.tvShow.SaveTvShowRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TvShowService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowService.class);

    private final TvShowRepository tvShowRepository;

    @Autowired
    public TvShowService(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    public TvShow getTvShow(long id) {
        LOGGER.info("Retrieving tvShow {}", id);
        return tvShowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TvShow not found"));
    }

    public Page<TvShow> getTvShows(GetTvShowsRequest request, Pageable pageable) {
        LOGGER.info("Retrieving tvShows {}", request);
        if (request != null && request.partialName != null && request.releaseYear != null) {
            return tvShowRepository.findByNameContainingAndReleaseYear(request.partialName, request.releaseYear, pageable);
        } else if (request != null && request.partialName != null) {
            return tvShowRepository.findByNameContaining(request.partialName, pageable);
        } else if (request != null && request.releaseYear != null) {
            return tvShowRepository.findByReleaseYear(request.releaseYear, pageable);
        } else return tvShowRepository.findAll(pageable);
    }

    public TvShow createTvShow(SaveTvShowRequest request) {
        LOGGER.info("Creating tvShow: {}", request);
        TvShow tvShow = new TvShow();
        tvShow.setName(request.getName());
        tvShow.setDescription(request.getDescription());
        tvShow.setReleaseYear(request.getReleaseYear());
        tvShow.setImagePath(request.getImagePath());

        return tvShowRepository.save(tvShow);
    }

    public TvShow updateTvShow(long id, SaveTvShowRequest request) {
        LOGGER.info("Updating tvShow {}: {}", id, request);
        TvShow tvShow = getTvShow(id);
        BeanUtils.copyProperties(request, tvShow);
        return tvShowRepository.save(tvShow);
    }

    public void deleteTvShow(long id) {
        LOGGER.info("Deleting tvShow {}", id);
        tvShowRepository.deleteById(id);
    }
}
