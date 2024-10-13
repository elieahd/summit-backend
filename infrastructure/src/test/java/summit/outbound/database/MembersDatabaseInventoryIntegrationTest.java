package summit.outbound.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import summit.models.Member;
import summit.outbound.database.mappers.MemberRowMapper;
import utils.integration.IntegrationTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.Randomizer.randomInteger;
import static utils.Randomizer.randomString;
import static utils.TestData.aMember;

@JdbcTest
@Import({
        MembersDatabaseInventory.class,
        MemberRowMapper.class
})
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
class MembersDatabaseInventoryIntegrationTest extends IntegrationTest {

    @Autowired
    private MembersDatabaseInventory membersInventory;

    @Test
    void shouldCreateMember() {
        // Arrange
        final Member inputMember = aMember();
        inputMember.setId(null);
        // Act
        final Member actual = membersInventory.create(inputMember);
        // Assert
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getUsername()).isEqualTo(inputMember.getUsername());
        final Member memberInDatabase = db.members.selectOne(actual.getId());
        assertThat(memberInDatabase).isNotNull();
        assertThat(memberInDatabase.getUsername()).isEqualTo(inputMember.getUsername());
        assertThat(memberInDatabase.getName()).isEqualTo(inputMember.getName());
    }

    @Test
    void shouldReturnTrueWhenMemberExistsByUsername() {
        // Arrange
        final String username = randomString();
        db.members.insert(username, randomString());
        // Act
        boolean actual = membersInventory.existsByUsername(username);
        // Assert
        assertThat(actual).isTrue();
    }

    @Test
    void shouldReturnFalseWhenMemberDoesNotExistsByUsername() {
        // Arrange
        final String username = randomString();
        // Act
        boolean actual = membersInventory.existsByUsername(username);
        // Assert
        assertThat(actual).isFalse();
    }

    @Test
    void shouldReturnOptionalOfMemberById() {
        // Arrange
        final String username = randomString();
        final String name = randomString();
        final Integer id = db.members.insert(username, name);
        // Act
        final Optional<Member> actual = membersInventory.findById(id);
        // Assert
        assertThat(actual).isPresent();
        assertThat(actual.get().getId()).isEqualTo(id);
        assertThat(actual.get().getUsername()).isEqualTo(username);
        assertThat(actual.get().getName()).isEqualTo(name);
    }

    @Test
    void shouldReturnOptionalOfEmptyWhenNotFoundById() {
        // Arrange
        final Integer id = randomInteger();
        // Act
        final Optional<Member> actual = membersInventory.findById(id);
        // Assert
        assertThat(actual).isNotPresent();
    }

}
