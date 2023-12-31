package pl.marcel.flasksorter.adapters.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marcel.flasksorter.domain.FlaskFacade;
import pl.marcel.flasksorter.domain.model.FlaskID;

import java.util.UUID;

@RestController
@RequestMapping("/api/flask")
@RequiredArgsConstructor
public class FlaskController {

    private final FlaskFacade flaskFacade;

    @Operation(summary = "Flask with given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns flask JSON object, provided 'flaskSampleID' pointing to existing flask. " +
                                    "'flaskSampleID' can be NULL, if flask is not yet assigned.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FlaskResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Returns 404 response code given 'flaskSampleID' pointing to not existing flask.",
                            content = @Content()
                    )}
    )
    @GetMapping(value = "/{flaskSampleID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlaskResponse> get(@PathVariable UUID flaskSampleID) {
        return ResponseEntity.ok(FlaskResponse.of(flaskFacade.getFlask(new FlaskID(flaskSampleID))));
    }
}
