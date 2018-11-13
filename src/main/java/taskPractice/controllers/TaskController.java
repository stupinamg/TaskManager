package taskPractice.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import taskPractice.models.TaskItem;

@Component
@Scope("prototype")
public class TaskController {
    MainController mainController;

    private TaskItem taskItem;

    @FXML
    private Label taskHeader;

    @FXML
    private Label status;

    @FXML
    private CheckBox completedCheckbox;

    @FXML
    void deleteTask(ActionEvent event) {
        mainController.deleteTask(taskItem);
    }

    public void setTask(TaskItem task) {
        this.taskItem = task;
        taskHeader.setText(task.getName());
        completedCheckbox.selectedProperty().bindBidirectional(taskItem.completedProperty()); // изменение состояния checkbox
        taskItem.completedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) status.setText("completed");
            else status.setText("active");
        });
    }

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
