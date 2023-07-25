package com.legalease.controller;

import com.legalease.config.AbstractContainerBaseTest;
import com.legalease.config.DatabaseTestExecutionListener;
import com.legalease.model.response.RankingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//TODO if the endpoint is secured, we can either add security to restTemplate or override the security config.

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@TestExecutionListeners(mergeMode = MERGE_WITH_DEFAULTS, listeners = {DatabaseTestExecutionListener.class})
class RankingControllerITest extends AbstractContainerBaseTest {

    @LocalServerPort
    private Integer randomServerPort;

    private static final String ENDPOINT = "/rankings";

    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
    }

    @ParameterizedTest(name = "Check BadRequest when regionId is \"{0}\"")
    @ValueSource(ints = {0, -3, 175, Integer.MAX_VALUE})
    void shouldReturnBadRequestWhenRegionIdIs(Integer regionId) {

        // Given
        int pageNumber = 0;
        int pageSize = 2;

        // When
        final Throwable throwable = catchThrowable(() -> restTemplate.exchange(getUrl(regionId, pageNumber, pageSize),
                GET, new HttpEntity<>(new HttpHeaders()), RankingResponse.class));

        // Then
        assertThat(throwable).isNotNull()
                .isInstanceOf(HttpClientErrorException.class)
                .hasMessageContaining(String.valueOf(BAD_REQUEST.value()));
    }

    private String getUrl(int regionId, int pageNumber, int pageSize) {
        return "http://localhost:"+randomServerPort+"/" + ENDPOINT + "?regionId="+regionId+"&pageNumber="+pageNumber+"&pageSize=" + pageSize;
    }
}
