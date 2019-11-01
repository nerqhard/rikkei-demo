package vn.rikkeisoft.demo.service.dto;

import lombok.Data;
import vn.rikkeisoft.demo.common.PasswordMatches;

import javax.validation.constraints.NotEmpty;

@Data
@PasswordMatches
public class PasswordResetDTO {
    @NotEmpty
    private String pass;
    @NotEmpty
    private String confirmPass;
    @NotEmpty
    private String token;
}
