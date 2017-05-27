package ru.martha.autotesting.service;

import ru.martha.autotesting.entity.Project;
import ru.martha.autotesting.entity.ProjectVersion;

import java.util.List;

public interface TesterService {
    List<Project> getAllProjects();
    List<ProjectVersion> getProjectVersions(Project project);
    void createOrUpdateProject(Project project);
    void removeProject(Project project);
}
