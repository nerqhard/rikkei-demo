package vn.rikkeisoft.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private long id;

    @NotEmpty(message = "{field.empty}")
    @Size(min = 8, max = 32, message = "{field.size.user}")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,32}", message = "{register.field.user}")
    private String username;

    @NotEmpty(message = "{field.empty}")
    private String fullName;

    @NotEmpty(message = "{field.empty}")
    @Size(min = 8, message = "{field.size.password}")
    private String password;

    private boolean active;

    private Set<Long> roles;
}
