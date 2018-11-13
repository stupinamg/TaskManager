package taskPractice.controllers;

import javafx.beans.property.ListProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import taskPractice.dataSource.Repository;
import taskPractice.models.TaskItem;

@Component
public class MainController {
    Repository repository;

    public void deleteTask(TaskItem item){
        repository.removeTask(item);
    }

    public void showAllTasks(){
        repository.showAllTasks();
    }

    public void showActiveTasks(){
        repository.showActiveTasks();
    }

    public void showCompletedTasks(){
        repository.showCompletedTasks();
    }

    public void addTask(TaskItem item){
        repository.addTask(item);
    }

    public ListProperty<TaskItem> getListProperty(){
        return repository.getTasksProperty();
    }

    @Autowired
    public void setRepository(Repository repository){
        this.repository = repository;
    }
}
