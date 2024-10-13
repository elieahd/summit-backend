package summit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import summit.exceptions.TrackerTemplateNotFoundByIdException;
import summit.models.TrackerInterval;
import summit.models.TrackerTemplate;
import summit.ports.inbound.GetTrackerTemplate;
import summit.ports.outbound.TrackerTemplateInventory;
import summit.stubs.InMemoryTrackerTemplateInventory;
import summit.usecases.TrackerTemplateUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static utils.DomainRandomizer.random;
import static utils.DomainRandomizer.randomInteger;
import static utils.DomainRandomizer.randomString;

class GetTrackerTemplateTest {

    private GetTrackerTemplate getTrackerTemplate;
    private TrackerTemplateInventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new InMemoryTrackerTemplateInventory();
        getTrackerTemplate = new TrackerTemplateUseCase(inventory);
    }

    @Test
    void shouldThrowExceptionWhenTrackerTemplateNotFoundById() {
        // Arrange
        final Integer id = randomInteger();
        // Act
        final Throwable thrown = catchThrowable(() -> getTrackerTemplate.getById(id));
        // Assert
        assertThat(thrown)
                .isInstanceOf(TrackerTemplateNotFoundByIdException.class)
                .hasMessageContaining("Tracker template '" + id + "' not found");
    }

    @Test
    void shouldReturnTrackerTemplateById() throws TrackerTemplateNotFoundByIdException {
        // Arrange
        final String name = randomString();
        final String description = randomString();
        final TrackerInterval interval = random(TrackerInterval.class);
        final TrackerTemplate trackerTemplate = inventory.create(new TrackerTemplate(name, description, interval));
        final Integer id = trackerTemplate.getId();
        // Act
        final TrackerTemplate actual = getTrackerTemplate.getById(id);
        // Assert
        assertThat(actual.getId()).isEqualTo(id);
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getDescription()).isEqualTo(description);
        assertThat(actual.getInterval()).isEqualTo(interval);
    }

}

