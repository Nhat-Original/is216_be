package com.github.nhatoriginal.spring.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
  @JsonProperty("access_token")
  private String accessToken;
}
