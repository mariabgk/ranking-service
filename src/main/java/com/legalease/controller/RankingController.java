package com.legalease.controller;

import com.legalease.errorhandling.ErrorResponse;
import com.legalease.model.mapper.RankingMapper;
import com.legalease.model.response.RankingResponse;
import com.legalease.service.RankingService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Log4j2
@Validated
@RestController
@Tag(name = "Ranking Controller | Allows operations over rankings")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
})
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @Autowired
    private RankingMapper mapper;

    //TODO this would need an auth server somewhere (either ours or external) -and ideally a gateway too
    //@PreAuthorize("hasAnyAuthority('LEGALEASE_READ','SCOPE_MICROSERVICE')")
    @GetMapping(value = "/ranking", produces = "application/com.com.legalease.api.v1+json")
    ResponseEntity<RankingResponse> getRankings(@RequestParam(required = false) Integer regionId, @RequestParam @NotNull @Min(0) Integer pageNumber,
                                              @RequestParam @NotNull @Min(1) Integer pageSize){

        log.info("Request received to list rankings with regionId {}, pageNumber {} and pageSize {}", regionId, pageNumber, pageSize);
        return ResponseEntity.ok(rankingService.getRankingsByRegion( regionId, pageNumber, pageSize));
    }

}
