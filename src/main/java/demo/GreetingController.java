package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import people.Identity;
import people.User;
import repository.IdentityRepository;
import repository.UserRepository;

import java.util.concurrent.atomic.AtomicLong;

//import repository.IdentityRepository;


@RestController // Note @RestController will return the return value as JSON by default
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdentityRepository identityRepository;

    @Autowired
    Environment env;

    /**
     * @return
     */
    @RequestMapping(value = "/greeting")
    public User greeting(@RequestParam(value = "lastName", defaultValue = "world") String lastName) {
        User user = userRepository.findOneByLastName(lastName);
        return user;
    }

    @RequestMapping(value = "/addNewUser")
    public void addNewUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                           @RequestParam("userType") int userType) {
        User newUser = new User(firstName, lastName);
        newUser.setUserType(userType);
        userRepository.save(newUser);
    }

    @RequestMapping(value = "/register")
    public Identity registerNewUser(@RequestParam("userName") String userName, @RequestParam("password") String password,
                                    @RequestParam("role") String role) {
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder(env.getProperty("encoder.key"));
        String encodedPassword = standardPasswordEncoder.encode(password);
        Identity newUser = new Identity(userName, encodedPassword, role);
        identityRepository.save(newUser);
        return newUser;
    }

    @RequestMapping(value = "/userLogin")
    public String userLogin() {
        return "You are login as user";
    }

    @RequestMapping(value = "/adminLogin")
    public String adminLogin() {
        return "Your are login as admin";
    }

    @RequestMapping("/fuck")
    public String fuck() {
       return "index";
    }

}
