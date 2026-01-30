package com.czertainly.api.model.core.sign;

import com.czertainly.api.model.common.NameAndUuidDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TsaProfileDto extends NameAndUuidDto {

    @Schema(
            description = "Enabled flag - true = enabled; false = disabled",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private boolean enabled;

    @Schema(
            description = "TSA Profile description",
            examples = {"Sample text description"}
    )
    private String description;

    @Schema(
            description = "TSA URL",
            examples = {"https://your-domain.com/api/v1/protocols/tsa/tsaProfile"}
    )
    private String cmpUrl;
}
