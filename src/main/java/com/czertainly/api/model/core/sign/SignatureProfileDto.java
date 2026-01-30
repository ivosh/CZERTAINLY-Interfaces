package com.czertainly.api.model.core.sign;

import com.czertainly.api.model.client.attribute.ResponseAttribute;
import com.czertainly.api.model.common.NameAndUuidDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * Represents a Signature Profile.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SignatureProfileDto extends NameAndUuidDto {

    @Schema(description = "Description of the Signature Profile",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Schema(description = "Enabled flag - true = enabled; false = disabled",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean enabled;

    @Schema(description = "List of Custom Attributes",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<ResponseAttribute> customAttributes;

    @Schema(description = "List of protocols enabled",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> enabledProtocols;
}
