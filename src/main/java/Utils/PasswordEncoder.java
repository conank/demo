package Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by Pikachu on 9/1/15.
 */
public class PasswordEncoder {
    @Autowired
    private Environment env;

    public String encodePassword(String rawPassword) {
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder(env.getProperty("encoder.key"));
        return standardPasswordEncoder.encode(rawPassword);
    }

}
