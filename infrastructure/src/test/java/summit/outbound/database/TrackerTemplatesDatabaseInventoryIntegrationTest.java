package summit.outbound.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import summit.models.TrackerInterval;
import summit.models.TrackerTemplate;
import summit.outbound.database.mappers.TrackerTemplateRowMapper;
import utils.integration.IntegrationTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.Randomizer.random;
import static utils.Randomizer.randomInteger;
import static utils.Randomizer.randomString;
import static utils.TestData.aTrackerTemplate;

@JdbcTest
@Import({
        TrackerTemplatesDatabaseInventory.class,
        TrackerTemplateRowMapper.class
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TrackerTemplatesDatabaseInventoryIntegrationTest extends IntegrationTest {

    @Autowired
    private TrackerTemplatesDatabaseInventory trackerTemplatesInventory;

    @Test
    void shouldCreateTrackerTemplate() {
        // Arrange
        final TrackerTemplate trackerTemplateInput = aTrackerTemplate();
        trackerTemplateInput.setId(null);
        // Act
        final TrackerTemplate actual = trackerTemplatesInventory.create(trackerTemplateInput);
        // Assert
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo(trackerTemplateInput.getName());
        assertThat(actual.getDescription()).isEqualTo(trackerTemplateInput.getDescription());
        assertThat(actual.getInterval()).isEqualTo(trackerTemplateInput.getInterval());

        final TrackerTemplate trackerTemplateInDatabase = db.trackerTemplates.selectOne(actual.getId());
        assertThat(trackerTemplateInDatabase).isNotNull();
        assertThat(trackerTemplateInDatabase.getId()).isEqualTo(actual.getId());
        assertThat(trackerTemplateInDatabase.getName()).isEqualTo(trackerTemplateInput.getName());
        assertThat(trackerTemplateInDatabase.getDescription()).isEqualTo(trackerTemplateInput.getDescription());
        assertThat(trackerTemplateInDatabase.getInterval()).isEqualTo(trackerTemplateInput.getInterval());
    }

    @Test
    void shouldReturnOptionalOfTrackerTemplateById() {
        // Arrange
        final String name = randomString();
        final String description = randomString();
        final TrackerInterval interval = random(TrackerInterval.class);
        final Integer id = db.trackerTemplates.insert(name, description, interval);
        // Act
        final Optional<TrackerTemplate> actual = trackerTemplatesInventory.findById(id);
        // Assert
        assertThat(actual).isPresent();
        assertThat(actual.get().getId()).isEqualTo(id);
        assertThat(actual.get().getName()).isEqualTo(name);
        assertThat(actual.get().getDescription()).isEqualTo(description);
        assertThat(actual.get().getInterval()).isEqualTo(interval);
    }

    @Test
    void shouldReturnOptionalOfEmptyWhenNotFoundById() {
        // Arrange
        final Integer id = randomInteger();
        // Act
        final Optional<TrackerTemplate> actual = trackerTemplatesInventory.findById(id);
        // Assert
        assertThat(actual).isNotPresent();
    }

}
