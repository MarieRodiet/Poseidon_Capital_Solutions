package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;
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
public class CurvePointServiceTests {

    @InjectMocks
    private CurvePointService curvePointService;

    @Mock
    private CurvePointRepository curvePointRepository;

    @Test
    public void findAllCurvePointsTest() {
        // GIVEN
        CurvePoint curvePoint1 = createCurvePoint(1);
        CurvePoint curvePoint2 = createCurvePoint(2);
        List<CurvePoint> expectedCurvePoints = Arrays.asList(curvePoint1, curvePoint2);
        when(curvePointRepository.findAll()).thenReturn(expectedCurvePoints);

        // WHEN
        List<CurvePoint> actualCurvePoints = curvePointService.findAll();

        // THEN
        assertThat(actualCurvePoints).isEqualTo(expectedCurvePoints);
    }

    @Test
    public void saveCurvePointTest() {
        // GIVEN
        CurvePoint curvePoint = createCurvePoint(1);

        // WHEN
        curvePointService.save(curvePoint);

        // THEN
        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    public void deleteByIdCurvePointTest() {
        // GIVEN
        int curvePointId = 1;

        // WHEN
        curvePointService.deleteById(curvePointId);

        // THEN
        verify(curvePointRepository, times(1)).deleteById(curvePointId);
    }

    @Test
    public void findByIdCurvePointTest() {
        // GIVEN
        int curvePointId = 1;
        CurvePoint expectedCurvePoint = createCurvePoint(curvePointId);
        when(curvePointRepository.findById(curvePointId)).thenReturn(Optional.of(expectedCurvePoint));

        // WHEN
        CurvePoint actualCurvePoint = curvePointService.findById(curvePointId);

        // THEN
        assertThat(actualCurvePoint).isEqualTo(expectedCurvePoint);
    }

    @Test
    public void findByIdCurvePointNotFoundTest() {
        // GIVEN
        int curvePointId = 1;
        when(curvePointRepository.findById(curvePointId)).thenReturn(Optional.empty());

        // WHEN
        CurvePoint actualCurvePoint = curvePointService.findById(curvePointId);

        // THEN
        assertThat(actualCurvePoint).isNull();
    }

    private CurvePoint createCurvePoint(int id) {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(id);
        // Set other curvePoint properties as needed
        return curvePoint;
    }
}
