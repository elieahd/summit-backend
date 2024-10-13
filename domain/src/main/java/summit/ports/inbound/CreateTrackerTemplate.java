package summit.ports.inbound;

import summit.models.TrackerInterval;
import summit.models.TrackerTemplate;

public interface CreateTrackerTemplate {

    TrackerTemplate create(String name,
                           String description,
                           TrackerInterval interval);

}
