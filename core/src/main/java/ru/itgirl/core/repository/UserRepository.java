package ru.itgirl.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itgirl.core.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
