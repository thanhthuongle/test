package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class maintest extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo cây TreeView
        TreeItem<String> rootItem = new TreeItem<>("Root");

        TreeItem<String> nodeA = new TreeItem<>("Node A");

        TreeItem<String> nodeB = new TreeItem<>("Node B");
        TreeItem<String> nodeC = new TreeItem<>("Node C");
        TreeItem<String> nodeD = new TreeItem<>("Node D");
        nodeB.getChildren().addAll(nodeC, nodeD);

        TreeItem<String> nodeE = new TreeItem<>("Node E");
        TreeItem<String> nodeF = new TreeItem<>("Node F");
        nodeE.getChildren().add(nodeF);

        TreeItem<String> nodeG = new TreeItem<>("Node G");

        rootItem.getChildren().addAll(nodeA, nodeG);
        nodeA.getChildren().addAll(nodeB, nodeE);
        nodeB.getChildren().add(nodeE);

        TreeView<String> treeView = new TreeView<>(rootItem);

        // Tạo ComboBox và thiết lập TreeView như là cell factory
        ComboBox<TreeItem<String>> comboBox = new ComboBox<>();
        comboBox.setCellFactory(param -> new TreeCellCombo());
        comboBox.setButtonCell(new TreeCellCombo());

        // Thiết lập dữ liệu cho ComboBox
        comboBox.getItems().addAll(rootItem, nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG);

        // Tạo layout và hiển thị cửa sổ
        StackPane root = new StackPane();
        root.getChildren().add(comboBox);
        primaryStage.setScene(new Scene(root, 200, 100));
        primaryStage.setTitle("TreeView in ComboBox Example");
        primaryStage.show();
    }

    // Lớp tùy chỉnh cho cell của ComboBox, hiển thị giá trị của TreeItem
    private static class TreeCellCombo extends javafx.scene.control.ListCell<TreeItem<String>> {
        @Override
        protected void updateItem(TreeItem<String> item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getValue());
            } else {
                setText(null);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

