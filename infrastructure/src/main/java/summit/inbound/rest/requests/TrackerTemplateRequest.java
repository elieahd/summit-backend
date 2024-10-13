package summit.inbound.rest.requests;

import summit.models.TrackerInterval;

public record TrackerTemplateRequest(String name,
                                     String description,
                                     TrackerInterval interval) {
}
