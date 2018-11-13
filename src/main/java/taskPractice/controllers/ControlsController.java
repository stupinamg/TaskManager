package taskPractice.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlsController {
    private MainController mainController;

    @FXML
    void showActive(ActionEvent event) {
        mainController.showActiveTasks();
    }

    @FXML
    void showAll(ActionEvent event) {
        mainController.showAllTasks();
    }

    @FXML
    void showCompleted(ActionEvent event) {
        mainController.showCompletedTasks();
    }

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
