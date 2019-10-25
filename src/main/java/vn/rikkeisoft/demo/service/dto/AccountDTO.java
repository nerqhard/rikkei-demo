package vn.rikkeisoft.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.rikkeisoft.demo.entity.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private long id;

    @NotEmpty(message = "Not to empty")
    @Size(min = 8, max = 32, message = "Between 8 to 32 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,32}", message = "Character a-z, A-Z, 0-9")
    private String username;

    @NotEmpty(message = "Not to empty")
    private String fullName;

    @NotEmpty(message = "Not to empty")
    @Size(min = 8, message = "Passwords must be at least 8 characters")
    private String password;

    private Timestamp createDate;

    private Set<Role> roles;
}
