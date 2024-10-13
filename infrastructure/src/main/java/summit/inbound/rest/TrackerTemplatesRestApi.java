package summit.inbound.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import summit.exceptions.TrackerTemplateNotFoundByIdException;
import summit.inbound.rest.requests.TrackerTemplateRequest;
import summit.models.TrackerTemplate;
import summit.ports.inbound.CreateTrackerTemplate;
import summit.ports.inbound.GetTrackerTemplate;

import java.net.URI;

@RestController
@Tag(name = "Tracker Templates")
@RequestMapping("api/v1/tracker-templates")
public class TrackerTemplatesRestApi {

    private final CreateTrackerTemplate createTrackerTemplate;
    private final GetTrackerTemplate getTrackerTemplate;

    public TrackerTemplatesRestApi(CreateTrackerTemplate createTrackerTemplate,
                                   GetTrackerTemplate getTrackerTemplate) {
        this.createTrackerTemplate = createTrackerTemplate;
        this.getTrackerTemplate = getTrackerTemplate;
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get tracker template by id",
            description = "Get tracker template by id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found exception will be thrown when no tracker template found with given id"
                    )
            }
    )
    public ResponseEntity<TrackerTemplate> getById(@PathVariable("id") Integer id) throws TrackerTemplateNotFoundByIdException {
        TrackerTemplate trackerTemplate = getTrackerTemplate.getById(id);
        return ResponseEntity.ok(trackerTemplate);
    }

    @PostMapping
    @Operation(
            summary = "Create new tracker template",
            description = "Create new tracker template",
            responses = {
                    @ApiResponse(responseCode = "201")
            }
    )
    public ResponseEntity<TrackerTemplate> create(@RequestBody TrackerTemplateRequest request) {
        TrackerTemplate trackerTemplate = createTrackerTemplate.create(
                request.name(),
                request.description(),
                request.interval()
        );
        return ResponseEntity
                .created(URI.create("v1/tracker-templates/" + trackerTemplate.getId()))
                .body(trackerTemplate);
    }

}
