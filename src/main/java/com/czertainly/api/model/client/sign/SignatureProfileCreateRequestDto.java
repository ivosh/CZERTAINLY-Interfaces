package com.czertainly.api.model.client.sign;

import com.czertainly.api.model.client.attribute.RequestAttribute;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SignatureProfileCreateRequestDto {
    @NotNull
    @NotBlank
    @Schema(description = "Name of the Signature Profile", requiredMode = Schema.RequiredMode.REQUIRED, examples = {"Signature Profile 1"})
    private String name;

    @Schema(description = "Description of the Signature Profile", examples = {"This Signature Profile provides..."})
    private String description;

    @Schema(description = "Enabled flag - true = enabled; false = disabled", defaultValue = "false")
    private boolean enabled = false;

    @Schema(description = "List of Custom Attributes")
    private List<RequestAttribute> customAttributes;
}
