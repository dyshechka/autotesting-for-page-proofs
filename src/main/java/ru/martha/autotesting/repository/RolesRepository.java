package ru.martha.autotesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.martha.autotesting.entity.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
}
