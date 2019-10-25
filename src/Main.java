import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    static Scene scene;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Laboratoire 6");
        primaryStage.setFullScreen(true);
        scene = new Scene(new Group());
        BorderPane root = rootSetUp();
        scene.setRoot(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public BorderPane rootSetUp() {

        // Images
        Image cat = new Image("image1.jpg");
        Image cat2 = new Image("image2.jpg");
        Image cat3 = new Image("image3.jpg");

        // Default image
        final ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(500);
        imageView.setFitWidth(500);
        try {
            Image Defimage = new Image("default.jpg");
            imageView.setImage(Defimage);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Effects
        ColorAdjust colorAdjust = new ColorAdjust();
        imageView.setEffect(colorAdjust);


        //Sliders
        Label label = new Label("Brightness");
        Label label1 = new Label("Contrast");
        Label label2 = new Label("Hue");
        Label label3 = new Label("Saturation");
        Slider slider = new Slider(-1, 1, 0);
        Slider slider1 = new Slider(-1, 1, 0);
        Slider slider2 = new Slider(-1, 1, 0);
        Slider slider3 = new Slider(-1, 1, 0);
        VBox vBox = new VBox(label, slider, label1, slider1, label2, slider2, label3, slider3);

        // Slider actions
        slider.valueProperty().addListener((observable -> {
            colorAdjust.setBrightness(slider.getValue());
        }));
        slider1.valueProperty().addListener((observable -> {
            colorAdjust.setContrast(slider1.getValue());
        }));
        slider2.valueProperty().addListener((observable -> {
            colorAdjust.setHue(slider2.getValue());
        }));
        slider3.valueProperty().addListener((observable -> {
            colorAdjust.setSaturation(slider3.getValue());
        }));

        //Hbox
        HBox hBox = new HBox(imageView, vBox);

        //Tooltip
        slider.setTooltip(new Tooltip("Makes the image brighter or darker"));
        slider1.setTooltip(new Tooltip("Adds or decreases the amount of color of the image"));
        slider2.setTooltip(new Tooltip("Changes the hue of the image"));
        slider3.setTooltip(new Tooltip("Adds or decreases the saturation of the image"));

        // Rectangle bottom screen
        Rectangle rectangle = new Rectangle(0, 1000, 2000, 20);
        rectangle.setFill(Color.LIGHTGRAY);
        Label label4 = new Label("Test");

        //Menu
        Menu files = new Menu("Files");
        Menu actions = new Menu("Actions");
        Menu images = new Menu("Images");
        CheckMenuItem image1 = new CheckMenuItem("Image 1");
        CheckMenuItem image2 = new CheckMenuItem("Image 2");
        CheckMenuItem image3 = new CheckMenuItem("Image 3");
        CheckMenuItem reset = new CheckMenuItem("Reset");

        files.getItems().addAll(images);
        images.getItems().addAll(image1, image2, image3);
        actions.getItems().addAll(reset);

        MenuBar menuBar = new MenuBar(files, actions);
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(hBox);
        StackPane stackPane = new StackPane(rectangle, label4);
        root.setBottom(stackPane);
        stackPane.setAlignment(Pos.BOTTOM_LEFT);
        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        // Right Click
        ContextMenu contextMenu = new ContextMenu(actions, files);
        scene.setOnContextMenuRequested(event -> {
            contextMenu.show(scene.getWindow(), event.getScreenX(), event.getScreenY());
        });

        //CheckMenuItem image 1 set on action
        image1.setOnAction(event -> {
                    imageView.setImage(cat);

                }
        );

        image2.setOnAction(event -> {
                    imageView.setImage(cat2);
                }
        );

        image3.setOnAction(event -> {
                    imageView.setImage(cat3);
                }
        );

        reset.setOnAction(event -> {
            slider.setValue(0);
            slider1.setValue(0);
            slider2.setValue(0);
            slider3.setValue(0);
        });

        return root;
    }

}

