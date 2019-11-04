package vn.rikkeisoft.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.rikkeisoft.demo.common.PasswordMatches;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class PasswordResetDTO {
    @NotEmpty
    private String pass;
    @NotEmpty
    private String confirmPass;
    @NotEmpty
    private String token;
}
