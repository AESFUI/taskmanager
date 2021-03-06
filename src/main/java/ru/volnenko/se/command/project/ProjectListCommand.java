package ru.volnenko.se.command.project;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.entity.Project;
import ru.volnenko.se.service.ProjectService;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectListCommand extends AbstractCommand {

    @Resource
    private ProjectService projectService;

    @Override
    public String command() {
        return "project-list";
    }

    @Override
    public String description() {
        return "Show all projects.";
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT LIST]");
        int index = 1;
        for (Project project: projectService.getListProject()) {
            System.out.println(index++ + ". " + project.getName());
        }
        System.out.println();
    }

}
