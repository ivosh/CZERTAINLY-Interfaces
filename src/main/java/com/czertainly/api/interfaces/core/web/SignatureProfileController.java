package com.czertainly.api.interfaces.core.web;

import com.czertainly.api.exception.*;
import com.czertainly.api.interfaces.AuthProtectedController;
import com.czertainly.api.model.client.sign.SignatureProfileCreateRequestDto;
import com.czertainly.api.model.core.sign.SignatureProfileDto;
import com.czertainly.api.model.client.sign.SignatureProfileTsaDetailResponseDto;
import com.czertainly.api.model.client.sign.SignatureProfileUpdateRequestDto;
import com.czertainly.api.model.common.BulkActionMessageDto;
import com.czertainly.api.model.common.ErrorMessageDto;
import com.czertainly.api.model.common.UuidDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/v1/signatureProfiles")
@Tag(name = "Signature Profile Management", description = "Signature Profile Management API")
@ApiResponses(
        value = {
                @ApiResponse(
                        responseCode = "404",
                        description = "Not Found",
                        content = @Content(schema = @Schema(implementation = ErrorMessageDto.class))
                ),
                @ApiResponse(
                        responseCode = "502",
                        description = "Connector Error",
                        content = @Content(schema = @Schema(implementation = ErrorMessageDto.class))
                ),
                @ApiResponse(
                        responseCode = "503",
                        description = "Connector Communication Error",
                        content = @Content(schema = @Schema(implementation = ErrorMessageDto.class))
                ),
        })
public interface SignatureProfileController extends AuthProtectedController {
    @Operation(summary = "List of available Signature Profiles")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Signature Profiles retrieved")})
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    List<SignatureProfileDto> listSignatureProfiles(@RequestParam Optional<Boolean> enabled);

    @Operation(summary = "Details of Signature Profile Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Signature Profile details retrieved"),
            @ApiResponse(responseCode = "404", description = "Signature Profile not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
    })
    @GetMapping(path = "/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    SignatureProfileDto getSignatureProfile(@Parameter(description = "Signature Profile UUID") @PathVariable UUID uuid) throws NotFoundException;

    @Operation(summary = "Create Signature Profile")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Signature Profile created", content = @Content(schema = @Schema(implementation = UuidDto.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject(value = "[\"Error Message 1\", \"Error Message 2\"]")}))
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    SignatureProfileDto createSignatureProfile(@RequestBody @Valid SignatureProfileCreateRequestDto request)
            throws AlreadyExistException, AttributeException, NotFoundException, ValidationException;

    @Operation(summary = "Edit Signature Profile")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Signature Profile updated"),
            @ApiResponse(responseCode = "404", description = "Signature Profile not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject(value = "[\"Error Message 1\", \"Error Message 2\"]")}))
    })
    @PutMapping(path = "/{uuid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    SignatureProfileDto updateSignatureProfile(@Parameter(description = "Signature Profile UUID") @PathVariable UUID uuid, @RequestBody SignatureProfileUpdateRequestDto request)
            throws AttributeException, NotFoundException;

    @Operation(summary = "Delete Signature Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Signature Profile deleted"),
            @ApiResponse(responseCode = "404", description = "Signature Profile not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
    })
    @DeleteMapping(path = "/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteSignatureProfile(@Parameter(description = "Signature Profile UUID") @PathVariable UUID uuid) throws NotFoundException;

    @Operation(summary = "Disable Signature Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Signature Profile disabled"),
            @ApiResponse(responseCode = "404", description = "Signature Profile not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
    })
    @PatchMapping(path = "/{uuid}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void disableSignatureProfile(@Parameter(description = "Signature Profile UUID") @PathVariable UUID uuid) throws NotFoundException;

    @Operation(summary = "Enable Signature Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Signature Profile enabled"),
            @ApiResponse(responseCode = "404", description = "Signature Profile not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
    })
    @PatchMapping(path = "/{uuid}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void enableSignatureProfile(@Parameter(description = "Signature Profile UUID") @PathVariable UUID uuid) throws NotFoundException;

    @Operation(summary = "Delete multiple Signature Profiles")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Signature Profiles deleted"),
            @ApiResponse(responseCode = "404", description = "Signature Profile not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject(value = "[\"Error Message 1\", \"Error Message 2\"]")}))
    })
    @DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    List<BulkActionMessageDto> bulkDeleteSignatureProfiles(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Signature Profile UUIDs", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
            examples = {@ExampleObject(value = "[\"c2f685d4-6a3e-11ec-90d6-0242ac120003\", \"b9b09548-a97c-4c6a-a06a-e4ee6fc2da98\"]")}))
                                                           @RequestBody List<UUID> uuids) throws NotFoundException, ValidationException;

    @Operation(summary = "Disable multiple Signature Profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Signature Profiles disabled"),
            @ApiResponse(responseCode = "404", description = "Signature Profile(s) not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
    })
    @PatchMapping(path = "/disable", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    List<BulkActionMessageDto> bulkDisableSignatureProfiles(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Signature Profile UUIDs", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
            examples = {@ExampleObject(value = "[\"c2f685d4-6a3e-11ec-90d6-0242ac120003\", \"b9b09548-a97c-4c6a-a06a-e4ee6fc2da98\"]")}))
                                      @RequestBody List<UUID> uuids) throws NotFoundException;

    @Operation(summary = "Enable multiple Signature Profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Signature Profiles enabled"),
            @ApiResponse(responseCode = "404", description = "Signature Profile(s) not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
    })
    @PatchMapping(path = "/enable", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    List<BulkActionMessageDto> bulkEnableSignatureProfiles(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Signature Profile UUIDs", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
            examples = {@ExampleObject(value = "[\"c2f685d4-6a3e-11ec-90d6-0242ac120003\", \"b9b09548-a97c-4c6a-a06a-e4ee6fc2da98\"]")}))
                                     @RequestBody List<UUID> uuids) throws NotFoundException;

    // -----------------------------------------------------------------------------------------------------------------
    // TSA protocol
    // -----------------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Get TSA Profile details for Signature Profile"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TSA Profile details retrieved"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = String.class)),
                            examples = {@ExampleObject(value = "[\"Error Message 1\",\"Error Message 2\"]")}
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Signature Profile not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
    })
    @GetMapping(path = "/{uuid}/protocols/tsa", produces = {MediaType.APPLICATION_JSON_VALUE})
    SignatureProfileTsaDetailResponseDto getTsaForSignatureProfile(@Parameter(description = "Signature Profile UUID") @PathVariable UUID uuid)
            throws NotFoundException;

    @Operation(
            summary = "Activate TSA Profile for Signature Profile"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TSA Profile activated"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                            examples = {@ExampleObject(value = "[\"Error Message 1\", \"Error Message 2\"]")}
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Signature Profile not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
    })
    @PatchMapping(path = "/{signatureProfileUuid}/protocols/tsa/activate/{tsaProfileUuid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    SignatureProfileTsaDetailResponseDto activateTsaForSignatureProfile(
            @Parameter(description = "Signature Profile UUID") @PathVariable UUID signatureProfileUuid,
            @Parameter(description = "TSA Profile UUID") @PathVariable UUID tsaProfileUuid
    ) throws ConnectorException, AttributeException, NotFoundException;

    @Operation(
            summary = "Deactivate TSA Profile for Signature Profile"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TSA Profile deactivated"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                            examples = {@ExampleObject(value = "[\"Error Message 1\", \"Error Message 2\"]")}
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Signature Profile not found", content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
    })
    @PatchMapping(path = "/{uuid}/protocols/tsa/deactivate", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deactivateTsaForSignatureProfile(@Parameter(description = "Signature Profile UUID") @PathVariable UUID uuid) throws NotFoundException;
}
