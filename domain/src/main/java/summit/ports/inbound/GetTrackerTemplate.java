package summit.ports.inbound;

import summit.exceptions.TrackerTemplateNotFoundByIdException;
import summit.models.TrackerTemplate;

public interface GetTrackerTemplate {

    TrackerTemplate getById(Integer id) throws TrackerTemplateNotFoundByIdException;

}
