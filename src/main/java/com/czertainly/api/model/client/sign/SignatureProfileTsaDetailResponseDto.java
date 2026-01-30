package com.czertainly.api.model.client.sign;

import com.czertainly.api.model.common.NameAndUuidDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignatureProfileTsaDetailResponseDto extends NameAndUuidDto {
    @Schema(description = "TSA Protocol active?", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean active;

    @Schema(description = "TSA Protocol URL")
    private String url;
}
