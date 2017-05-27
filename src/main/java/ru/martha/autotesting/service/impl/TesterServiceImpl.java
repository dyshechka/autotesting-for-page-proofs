package ru.martha.autotesting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.martha.autotesting.entity.Project;
import ru.martha.autotesting.entity.ProjectVersion;
import ru.martha.autotesting.repository.ProjectsRepository;
import ru.martha.autotesting.service.TesterService;

import java.util.List;

@Service
@Transactional
public class TesterServiceImpl implements TesterService {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectsRepository.findAll();
    }

    @Override
    public List<ProjectVersion> getProjectVersions(Project project) {
        return projectsRepository.getOne(project.getId()).getVersions();
    }

    @Override
    public void createOrUpdateProject(Project project) {
        projectsRepository.save(project);
    }

    @Override
    public void removeProject(Project project) {
        projectsRepository.delete(project);
    }
}
