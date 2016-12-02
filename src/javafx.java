import javafx.application.Application;
02
import javafx.geometry.Pos;
03
import javafx.scene.Scene;
04
import javafx.scene.layout.StackPane;
05
import javafx.scene.paint.Color;
06
import javafx.scene.shape.Rectangle;
07
import javafx.scene.text.Text;
08
import javafx.stage.Stage;
09
 
10
public class FxLayoutStackPaneExample extends Application
11
{
12
    public static void main(String[] args)
13
    {
14
        Application.launch(args);
15
    }
16
     
17
    @Override
18
    public void start(Stage stage)
19
    {      
20
        // Create a Rectangle with Width 200 and Height 100
21
        Rectangle rect = new Rectangle(200,100);
22
        // Set the color of the Rectangle to Lavendar
23
        rect.setFill(Color.LAVENDER);
24
        // Create the Text
25
        Text text = new Text("A Text inside a Rectangle");
26
         
27
        // Create the StackPane
28
        StackPane root = new StackPane();      
29
        // Add the children to the StackPane
30
        root.getChildren().addAll(rect, text);
31
        // Set the Alignment of the Children to top center
32
        root.setAlignment(Pos.TOP_CENTER);
33
        // Set the size of the StackPane
34
        root.setPrefSize(300, 200);
35
        // Set the padding of the StackPane
36
        root.setStyle("-fx-padding: 10;");
37
        // Set the border-style of the StackPane
38
        root.setStyle("-fx-border-style: solid inside;");
39
        // Set the border-width of the StackPane
40
        root.setStyle("-fx-border-width: 2;");
41
        // Set the border-insets of the StackPane
42
        root.setStyle("-fx-border-insets: 5;");
43
        // Set the border-radius of the StackPane
44
        root.setStyle("-fx-border-radius: 5;");
45
        // Set the border-color of the StackPane
46
        root.setStyle("-fx-border-color: blue;");
47
         
48
        // Create the Scene
49
        Scene scene = new Scene(root);
50
        // Add the scene to the Stage
51
        stage.setScene(scene);
52
        // Set the title of the Stage
53
        stage.setTitle("A StackPane Example");
54
        // Display the Stage
55
        stage.show();
56
    }
57
}
