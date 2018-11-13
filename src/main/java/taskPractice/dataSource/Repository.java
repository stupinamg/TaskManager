package taskPractice.dataSource;

import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.springframework.stereotype.Component;
import taskPractice.models.TaskItem;

@Component
public class Repository {
    private ObservableList<TaskItem> allTasks = FXCollections.observableArrayList(item ->
                                                                        new Observable[]{item.completedProperty()});

    private FilteredList<TaskItem> completed = new FilteredList<TaskItem>(allTasks, TaskItem::isCompleted);

    private FilteredList<TaskItem> active = new FilteredList<TaskItem>(allTasks, taskItem -> !taskItem.isCompleted());

    private ListProperty<TaskItem> tasksProperty = new SimpleListProperty<>(allTasks);


    public ListProperty<TaskItem> getTasksProperty() {
        return tasksProperty;
    }

    public void setTasksProperty(ObservableList<TaskItem> tasksProperty) {
        this.tasksProperty.set(tasksProperty);
    }

    public void showAllTasks(){
        tasksProperty.set(allTasks);
    }

    public void showCompletedTasks(){
        tasksProperty.set(completed);
    }

    public void showActiveTasks(){
        tasksProperty.set(active);
    }

    public void addTask(TaskItem item){
        allTasks.add(item);
    }

    public void removeTask(TaskItem item){
        allTasks.remove(item);
    }
}
