package summit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import summit.exceptions.MemberWithSameUsernameAlreadyExistsException;
import summit.models.Member;
import summit.ports.inbound.CreateMember;
import summit.ports.outbound.MembersInventory;
import summit.stubs.InMemoryMembersInventory;
import summit.usecases.MemberUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static utils.DomainRandomizer.randomString;

class CreateMemberTest {

    private CreateMember createMember;
    private MembersInventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new InMemoryMembersInventory();
        createMember = new MemberUseCase(inventory);
    }

    @Test
    void shouldThrowExceptionWhenMemberAlreadyExistsWithSameUsername() {
        // Arrange
        final String name = randomString();
        final String username = randomString();
        inventory.create(new Member(username, name));
        // Act
        final Throwable thrown = catchThrowable(() -> createMember.create(username, name));
        // Assert
        assertThat(thrown)
                .isInstanceOf(MemberWithSameUsernameAlreadyExistsException.class)
                .hasMessageContaining("'" + username + "' already exists");
    }

    @Test
    void shouldCreateMember() throws MemberWithSameUsernameAlreadyExistsException {
        // Arrange
        final String name = randomString();
        final String username = randomString();
        // Act
        final Member actual = createMember.create(username, name);
        // Assert
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getUsername()).isEqualTo(username);
    }

}
