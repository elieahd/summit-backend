package summit.ports.outbound;

import summit.models.TrackerTemplate;

import java.util.Optional;

public interface TrackerTemplateInventory {

    Optional<TrackerTemplate> findById(Integer id);

    TrackerTemplate create(TrackerTemplate trackerTemplate);

}
