package summit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import summit.exceptions.MemberNotFoundByIdException;
import summit.models.Member;
import summit.ports.inbound.GetMember;
import summit.ports.outbound.MembersInventory;
import summit.stubs.InMemoryMembersInventory;
import summit.usecases.MemberUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static utils.DomainRandomizer.randomInteger;
import static utils.DomainRandomizer.randomString;

class GetMemberTest {

    private GetMember getMember;
    private MembersInventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new InMemoryMembersInventory();
        getMember = new MemberUseCase(inventory);
    }

    @Test
    void shouldThrowExceptionWhenMemberNotFoundById() {
        // Arrange
        final Integer id = randomInteger();
        // Act
        final Throwable thrown = catchThrowable(() -> getMember.getById(id));
        // Assert
        assertThat(thrown)
                .isInstanceOf(MemberNotFoundByIdException.class)
                .hasMessageContaining("Member '" + id + "' not found");
    }

    @Test
    void shouldReturnMemberById() throws MemberNotFoundByIdException {
        // Arrange
        final String username = randomString();
        final String name = randomString();
        final Member member = inventory.create(new Member(username, name));
        final Integer id = member.getId();
        // Act
        final Member actual = getMember.getById(id);
        // Assert
        assertThat(actual.getId()).isEqualTo(id);
        assertThat(actual.getUsername()).isEqualTo(username);
        assertThat(actual.getName()).isEqualTo(name);
    }

}
