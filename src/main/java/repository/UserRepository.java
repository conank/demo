package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import people.User;

import java.util.List;

/**
 * Created by Pikachu on 8/8/15.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastName(String lastName);

    User findOneByLastName(String lastName);
}
