package com.legalease.controller;

import com.legalease.model.response.RankingResponse;
import com.legalease.service.RankingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.openMocks;

class RankingControllerTest {

    @Mock
    private RankingService rankingService;

    @InjectMocks
    private RankingController rankingController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }


    @Test
    void should_delegate_on_service() {

        // Given
        int regionId = 170;
        int pageNumber = 0;
        int pageSize = 2;
        RankingResponse response = RankingResponse.builder().build();

        given(rankingService.getRankingsByRegion(regionId, pageNumber, pageSize)).willReturn(response);

        // When
        final ResponseEntity<RankingResponse> expected = rankingController.getRankings(regionId, pageNumber, pageSize);

        // Then
        assertThat(expected).isNotNull().isEqualTo(ResponseEntity.ok(response));
        then(rankingService).should().getRankingsByRegion(regionId, pageNumber, pageSize);
    }

}