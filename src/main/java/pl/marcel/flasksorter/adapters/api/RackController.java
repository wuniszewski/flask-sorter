package pl.marcel.flasksorter.adapters.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marcel.flasksorter.domain.RackFacade;
import pl.marcel.flasksorter.domain.model.FlaskID;

import java.util.UUID;

@RestController
@RequestMapping("/api/rack")
@RequiredArgsConstructor
public class RackController {

    private final RackFacade rackFacade;

    @Operation(summary = "Assign flask to rack.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns 200 code, when requested 'flaskSampleID' was successfully assigned to existing rack.",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Returns 404 response when requested 'flaskSampleID' points to already assigned flask.",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Returns 404 response when requested 'flaskSampleID' points to not existing flask, " +
                                    "or there are no racks fitting assigning specifications. Please check body message for reason.",
                            content = @Content()
                    )}
    )
    @PutMapping(path = "/flask/{flaskSampleID}")
    public ResponseEntity assignFlask(@PathVariable UUID flaskSampleID) {
        rackFacade.assignFlask(new FlaskID(flaskSampleID));
        return ResponseEntity.ok().build();
    }
}
