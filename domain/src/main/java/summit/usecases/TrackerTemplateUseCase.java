package summit.usecases;

import summit.UseCase;
import summit.exceptions.TrackerTemplateNotFoundByIdException;
import summit.models.TrackerInterval;
import summit.models.TrackerTemplate;
import summit.ports.inbound.CreateTrackerTemplate;
import summit.ports.inbound.GetTrackerTemplate;
import summit.ports.outbound.TrackerTemplateInventory;

@UseCase
public class TrackerTemplateUseCase implements GetTrackerTemplate, CreateTrackerTemplate {

    private final TrackerTemplateInventory inventory;

    public TrackerTemplateUseCase(TrackerTemplateInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public TrackerTemplate getById(Integer id) throws TrackerTemplateNotFoundByIdException {
        return inventory.findById(id)
                .orElseThrow(() -> new TrackerTemplateNotFoundByIdException(id));
    }

    @Override
    public TrackerTemplate create(String name, String description, TrackerInterval interval) {
        TrackerTemplate trackerTemplate = new TrackerTemplate(name, description, interval);
        return inventory.create(trackerTemplate);
    }
}
