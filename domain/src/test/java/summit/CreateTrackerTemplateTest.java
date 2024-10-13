package summit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import summit.models.TrackerInterval;
import summit.models.TrackerTemplate;
import summit.ports.inbound.CreateTrackerTemplate;
import summit.ports.outbound.TrackerTemplateInventory;
import summit.stubs.InMemoryTrackerTemplateInventory;
import summit.usecases.TrackerTemplateUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.DomainRandomizer.random;
import static utils.DomainRandomizer.randomString;

class CreateTrackerTemplateTest {

    private CreateTrackerTemplate createTrackerTemplate;
    private TrackerTemplateInventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new InMemoryTrackerTemplateInventory();
        createTrackerTemplate = new TrackerTemplateUseCase(inventory);
    }

    @Test
    void shouldCreateTrackerTemplate() {
        // Arrange
        final String name = randomString();
        final String description = randomString();
        final TrackerInterval interval = random(TrackerInterval.class);
        inventory.create(new TrackerTemplate(name, description, interval));
        // Act
        final TrackerTemplate actual = createTrackerTemplate.create(name, description, interval);
        // Assert
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getDescription()).isEqualTo(description);
        assertThat(actual.getInterval()).isEqualTo(interval);
    }

}
