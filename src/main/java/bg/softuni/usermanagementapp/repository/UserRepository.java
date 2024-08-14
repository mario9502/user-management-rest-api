package bg.softuni.usermanagementapp.repository;

import bg.softuni.usermanagementapp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findById(UUID id);

    @Transactional
    void deleteByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    @Query(value = "FROM UserEntity ORDER BY lastName, dateOfBirth")
    List<UserEntity> findAllUsersOrderByFirstNameAndDateOfBirth();

    @Query(value = "FROM UserEntity WHERE YEAR(dateOfBirth) < :year")
    List<UserEntity> findAllByDateOfBirthYearBefore(int year);
}
