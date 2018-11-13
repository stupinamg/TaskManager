package taskPractice.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import taskPractice.loaderProvider.FXMLLoaderProvider;
import taskPractice.models.TaskItem;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Component
public class TaskListController implements Initializable{
    private MainController mainController;
    private FXMLLoaderProvider loaderProvider;
    private String pathToTaskFXML = "/task.fxml";
    private Map<TaskItem, Node> taskNodeCache = new HashMap<>();

    @FXML
    ListView<TaskItem> tasksList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tasksList.setItems(mainController.getListProperty());

        tasksList.setCellFactory(new Callback<ListView<TaskItem>, ListCell<TaskItem>>() {
            @Override
            public ListCell<TaskItem> call(ListView<TaskItem> param) {

                return new ListCell<TaskItem>(){
                    @Override
                    protected void updateItem(TaskItem item, boolean empty){
                        super.updateItem(item, empty);
                        if(empty){
                            setText(null);
                            setGraphic(null);
                        }else{
                            setText(null);

                            if(!taskNodeCache.containsKey(item)){
                                final Node parent = loadTaskFXML(item);
                                taskNodeCache.put(item, parent);
                            }
                            Node node = taskNodeCache.get(item);
                            Node currentNode =  getGraphic();
                            if(currentNode == null || !currentNode.equals(node)){
                                setGraphic(node);
                            }
                        }
                    }

                };
            }
        });
    }

    private Node loadTaskFXML(TaskItem item){
        FXMLLoader loader = loaderProvider.getLoader(pathToTaskFXML);

        try {
            Node node = loader.load();
            TaskController taskController = loader.getController();
            taskController.setTask(item);
            return node;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Autowired
    public void setLoaderProvider(FXMLLoaderProvider loaderProvider) {
        this.loaderProvider = loaderProvider;
    }
}

