package model;

/**
 * Created by Pikachu on 8/9/15.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {
    public User findByLastName(String email);
}
