package com.legalease.service;

import com.legalease.errorhandling.exception.InvalidRequestException;
import com.legalease.model.Tier;
import com.legalease.model.Trend;
import com.legalease.model.mapper.RankingMapper;
import com.legalease.model.response.FirmDto;
import com.legalease.model.response.RankingDto;
import com.legalease.model.response.RankingResponse;
import com.legalease.repository.RankingRepository;
import com.legalease.repository.entities.FirmEntity;
import com.legalease.repository.entities.RankingEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.openMocks;

class RankingServiceTest {

    @Mock
    private RankingMapper mapper;

    @Mock
    private RankingRepository rankingRepository;

    @InjectMocks
    private RankingService rankingService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("blankOrNullStrings")
    void should_Throw_Exception( Integer regionId){
        // Given
        int pageNumber = 0;
        int pageSize = 2;
        final Throwable expected = new InvalidRequestException("Wrong regionId was " + regionId + ", but 170 was expected");
        given(rankingRepository.findByFirm_FirmRegions_Region_Id(any(), any())).willReturn(Page.empty());

        // When
        Throwable throwable = catchThrowable(() -> rankingService.getRankingsByRegion(regionId, pageNumber, pageSize));

        // Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).usingRecursiveComparison().isEqualTo(expected);
        then(rankingRepository).shouldHaveNoInteractions();
    }

    private static Stream<Integer> blankOrNullStrings() {
        return Stream.of(0, 19, -1, null);
    }

    @Test
    void should_Return_No_Rankings(){
        // Given
        int regionId = 170;
        int pageNumber = 0;
        int pageSize = 2;
        Page<RankingEntity> page = new PageImpl<>(List.of(), PageRequest.of(0, 1), 0);
        RankingResponse expected = RankingResponse.builder().rankings(List.of()).totalPages(0).totalRecords(0).page(0).hasNext(false).build();
        given(rankingRepository.findByFirm_FirmRegions_Region_Id(any(), any())).willReturn(page);

        // When
        RankingResponse actual = rankingService.getRankingsByRegion(regionId, pageNumber, pageSize);

        // Then
        then(rankingRepository).should().findByFirm_FirmRegions_Region_Id(170, PageRequest.of(0, 2));
        then(mapper).shouldHaveNoInteractions();
        assertThat(expected).isNotNull();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    void should_Return_Rankings(){
        // Given
        int regionId = 170;
        int pageNumber = 0;
        int pageSize = 2;
        List<RankingEntity> rankingEntities = createRankingEntityList();
        List<RankingDto> rankingDtos = createRankingDtoList();
        Page<RankingEntity> page = new PageImpl<>(rankingEntities, PageRequest.of(pageNumber, pageSize), 0);

        given(rankingRepository.findByFirm_FirmRegions_Region_Id(any(), any())).willReturn(page);
        given(mapper.toDto(rankingEntities.get(0))).willReturn(rankingDtos.get(0));
        given(mapper.toDto(rankingEntities.get(1))).willReturn(rankingDtos.get(1));

        RankingResponse expected = RankingResponse.builder().rankings(rankingDtos).totalPages(1).totalRecords(2).page(0).hasNext(false).build();

        // When
        RankingResponse actual = rankingService.getRankingsByRegion(regionId, pageNumber, pageSize);

        // Then
        then(rankingRepository).should().findByFirm_FirmRegions_Region_Id(regionId, PageRequest.of(pageNumber, pageSize));
        assertThat(expected).isNotNull();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }

    private List<RankingEntity> createRankingEntityList(){
        return List.of(
                new RankingEntity(1, new FirmEntity(1, "Firm", "slug", "imageurl", "website",
                        Set.of(), List.of(), List.of()), Trend.DOWN, true, Tier.T_1, 0),

                new RankingEntity(2, new FirmEntity(2, "Firm2", "slug2", "imageurl2", "website2",
                        Set.of(), List.of(), List.of()), Trend.STABLE_1, true, Tier.T_2, 0));
    }

    private List<RankingDto> createRankingDtoList(){
        return List.of(
                new RankingDto(1, new FirmDto(1, "Firm", "slug", "imageurl", "website",
                        Set.of(), List.of(), List.of()), Trend.DOWN, true, Tier.T_1, 0),
                new RankingDto(2, new FirmDto(2, "Firm2", "slug2", "imageurl2", "website2",
                        Set.of(), List.of(), List.of()), Trend.STABLE_1, true, Tier.T_2, 0));
    }

}