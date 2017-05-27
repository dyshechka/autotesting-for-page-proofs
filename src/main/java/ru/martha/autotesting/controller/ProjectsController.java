package ru.martha.autotesting.controller;

import lombok.Getter;
import lombok.Setter;
import ru.martha.autotesting.entity.Project;
import ru.martha.autotesting.service.TesterService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class ProjectsController implements Serializable {

    @ManagedProperty("#{testerServiceImpl}")
    @Setter
    private TesterService testerService;

    @Getter
    private List<Project> projects;

    @Getter
    @Setter
    private Project selectedProject;

    @Setter
    @Getter
    private boolean isEditMode;

    @Setter
    @Getter
    private boolean isNewProject;

    @PostConstruct
    private void init() {
        projects = testerService.getAllProjects();
    }

    public void onProjectSelected() {
        isEditMode = false;
    }

    public void createProject() {
        selectedProject = new Project();
        selectedProject.setCreationDate(new Date());
        projects.add(selectedProject);
        isNewProject = true;
        isEditMode = true;
    }

    public void cancelCreateProject() {
        selectedProject = null;
        projects = testerService.getAllProjects();
        isEditMode = false;
        isNewProject = false;
    }

    public void saveProject() {
        try {
            testerService.createOrUpdateProject(selectedProject);
            projects = testerService.getAllProjects();
            isEditMode = false;
            isNewProject = false;
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Произошла ошибка при сохранении");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void removeProject() {
        try {
            testerService.removeProject(selectedProject);
            selectedProject = null;
            projects = testerService.getAllProjects();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Произошла ошибка при удалении");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
