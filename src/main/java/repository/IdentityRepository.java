package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import people.Identity;

/**
 * Created by Pikachu on 9/1/15.
 */

@Repository
@Transactional
public interface IdentityRepository extends JpaRepository<Identity, Long> {
    Identity findOneByUserName(String userName);
}
