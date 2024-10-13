package summit.inbound.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import summit.exceptions.MemberNotFoundByIdException;
import summit.exceptions.MemberWithSameUsernameAlreadyExistsException;
import summit.inbound.rest.requests.CreateMemberRequest;
import summit.models.Member;
import summit.ports.inbound.CreateMember;
import summit.ports.inbound.GetMember;

import java.net.URI;

@RestController
@Tag(name = "Members")
@RequestMapping("api/v1/members")
public class MembersRestApi {

    private final CreateMember createMember;
    private final GetMember getMember;

    public MembersRestApi(CreateMember createMember,
                          GetMember getMember) {
        this.createMember = createMember;
        this.getMember = getMember;
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get member by id",
            description = "Get member by id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found exception will be thrown when no member found with given id"
                    )
            }
    )
    public ResponseEntity<Member> getById(@PathVariable("id") Integer id) throws MemberNotFoundByIdException {
        Member member = getMember.getById(id);
        return ResponseEntity.ok(member);
    }

    @PostMapping
    @Operation(
            summary = "Create a new member",
            description = "Create a new member, username should be unique",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Username is unique, so if trying to create with an already existing username, an exception will be thrown"
                    )
            }
    )
    public ResponseEntity<Member> create(@RequestBody CreateMemberRequest request) throws MemberWithSameUsernameAlreadyExistsException {
        Member member = createMember.create(request.username(), request.name());
        return ResponseEntity
                .created(URI.create("v1/members/" + member.getId()))
                .body(member);
    }

}
