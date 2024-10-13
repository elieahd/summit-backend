package summit.stubs;

import summit.models.TrackerTemplate;
import summit.ports.outbound.TrackerTemplateInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static utils.DomainRandomizer.randomInteger;

public class InMemoryTrackerTemplateInventory implements TrackerTemplateInventory {

    private final List<TrackerTemplate> trackerTemplates;

    public InMemoryTrackerTemplateInventory() {
        this.trackerTemplates = new ArrayList<>();
    }

    @Override
    public Optional<TrackerTemplate> findById(Integer id) {
        return trackerTemplates.stream()
                .filter(trackerTemplate -> trackerTemplate.getId().equals(id))
                .findFirst();
    }

    @Override
    public TrackerTemplate create(TrackerTemplate trackerTemplate) {
        trackerTemplate.setId(randomInteger());
        trackerTemplates.add(trackerTemplate);
        return trackerTemplate;
    }

}
