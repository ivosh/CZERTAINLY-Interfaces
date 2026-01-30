package com.czertainly.api.model.client.sign;

import com.czertainly.api.model.client.attribute.RequestAttribute;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SignatureProfileUpdateRequestDto {
    @Schema(description = "Description of the Signature Profile", requiredMode = Schema.RequiredMode.NOT_REQUIRED, examples = {"Signature Profile 1 description..."})
    private String description;

    @Schema(description = "List of Custom Attributes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<RequestAttribute> customAttributes = new ArrayList<>();

    @Schema(description = "Enabled flag - true = enabled; false = disabled")
    private Boolean enabled;
}
