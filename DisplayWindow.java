import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class commandDisplayWindow {

    public static void display(String heading, String subheading) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(heading);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(subheading);
        Button close = new Button("close");
        close.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}


///Display Box for command line interface 
//TODO: connect the CLI with the GUI in their respective classes and initialize here

