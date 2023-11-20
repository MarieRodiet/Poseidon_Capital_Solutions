package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class RatingServiceTests {

    @InjectMocks
    private RatingService ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @Test
    public void findAllRatingsTest() {
        // GIVEN
        Rating rating1 = createRating(1);
        Rating rating2 = createRating(2);
        List<Rating> expectedRatings = Arrays.asList(rating1, rating2);
        when(ratingRepository.findAll()).thenReturn(expectedRatings);

        // WHEN
        List<Rating> actualRatings = ratingService.findAll();

        // THEN
        assertThat(actualRatings).isEqualTo(expectedRatings);
    }

    @Test
    public void saveRatingTest() {
        // GIVEN
        Rating rating = createRating(1);

        // WHEN
        ratingService.save(rating);

        // THEN
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    public void deleteByIdRatingTest() {
        // GIVEN
        int ratingId = 1;

        // WHEN
        ratingService.deleteById(ratingId);

        // THEN
        verify(ratingRepository, times(1)).deleteById(ratingId);
    }

    @Test
    public void findByIdRatingTest() {
        // GIVEN
        int ratingId = 1;
        Rating expectedRating = createRating(ratingId);
        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(expectedRating));

        // WHEN
        Rating actualRating = ratingService.findById(ratingId);

        // THEN
        assertThat(actualRating).isEqualTo(expectedRating);
    }

    @Test
    public void findByIdRatingNotFoundTest() {
        // GIVEN
        int ratingId = 1;
        when(ratingRepository.findById(ratingId)).thenReturn(Optional.empty());

        // WHEN
        Rating actualRating = ratingService.findById(ratingId);

        // THEN
        assertThat(actualRating).isNull();
    }

    private Rating createRating(int id) {
        Rating rating = new Rating();
        rating.setId(id);
        // Set other rating properties as needed
        return rating;
    }
}

