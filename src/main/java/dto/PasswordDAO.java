package dto;


import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class PasswordDAO {

    @Valid
    private String username;

    @Valid
    private String password;

    PasswordEncoder encoder = new BCryptPasswordEncoder();


    public @Valid String getUsername() {
        return username;
    }

    public void setUsername(@Valid String username) {
        this.username = username;
    }

    public @Valid String getPassword() {
        return password;
    }

    public void setPassword(@Valid String password) {
        this.password = password;
    }
}
