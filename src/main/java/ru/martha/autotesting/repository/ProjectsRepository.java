package ru.martha.autotesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.martha.autotesting.entity.Project;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {
}
