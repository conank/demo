package demo;

import model.User;
import model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pikachu on 8/9/15.
 */
@RestController
public class UserController {
    @RequestMapping("/create")
    public String create(String firstName, String lastName) {
        User user = null;
        try {
            user = new User(firstName, lastName, 0);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    /**
     * /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/delete")
    public String delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * /get-by-email  --> Return the id for the user having the passed email.
     */
    /**
     * /update  --> Update the email and the name for the user in the database
     * having the passed id.
     */
    @RequestMapping("/update")
    public String updateUser(long id, String firstName, String lastName) {
        try {
            User user = userDao.findOne(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }


    // Private fields

    @Autowired
    private UserDao userDao;
}
