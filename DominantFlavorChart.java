
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.Alert.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.TableColumn.*;
import javafx.application.*;
import javafx.scene.chart.*;

public class DominantFlavorChart extends Application
{
    private final Flavors flavors = new Flavors();
    private final ObservableList<PieChart.Data> dataList = flavors.getObsList();
    public void start(Stage mainStage)
    {
        // Set the main layout and scene
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 600,600);
        mainStage.setScene(mainScene);
        VBox mainLayout = new VBox();
        root.setCenter(mainLayout);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainStage.setTitle("Dominant Flavor Pie Chart");
        
        // Pie chart to display all flavors
        PieChart chart = new PieChart();
        mainLayout.getChildren().add(chart);
        
        // link list to chart
        chart.setData(dataList);
        
        // chart formatting
        chart.setTitle("Flavor Mapping");
        chart.setLabelsVisible(true);
        chart.setLabelLineLength(50);
        chart.setLegendSide(Side.LEFT);
        
        // Button row to edit flavors
        HBox buttonRow = new HBox();
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(10);
        buttonRow.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
        
        // Buttons to add for adding/removing flavors
        // Add
        Button addButton = new Button("Add Flavor");
        addButton.setOnAction((event)->
        {
            flavors.add_input();
        });
        
        // Remove
        Button removeButton = new Button("Remove Flavor");
        removeButton.setOnAction((event)->
        {
            flavors.remove_input();
        });
        buttonRow.getChildren().addAll(addButton, removeButton);
        mainLayout.getChildren().add(buttonRow);
        
        // Top menu for functions
        MenuBar topMenu = new MenuBar();
        topMenu.setStyle("-fx-font-size: 15px;");
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        // Menu Item - Check Stress Condition
        MenuItem mapItem = new MenuItem("Check Flavor Mapping in %");
        mapItem.setOnAction((e->{
            Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("Current Flavor Mapping");
            info.setHeaderText("Current Flavor Mapping");
            info.setContentText(flavors.getCompositions());
            info.showAndWait();
        }));
        // Menu Item - Reset
        MenuItem resetItem = new MenuItem("Reset");
        resetItem.setOnAction((e->{
            dataList.clear();
        }));
        // Close item
        MenuItem closeItem = new MenuItem("Exit");
        closeItem.setOnAction((e->{
            mainStage.close();
            System.exit(0);
        }));
        // Add all menu items in
        fileMenu.getItems().addAll(mapItem, resetItem, closeItem);
        
        // Show all elements once added
        mainStage.show();
        
        mainStage.show();
    }
}
