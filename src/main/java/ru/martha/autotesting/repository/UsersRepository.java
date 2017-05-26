package ru.martha.autotesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.martha.autotesting.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
