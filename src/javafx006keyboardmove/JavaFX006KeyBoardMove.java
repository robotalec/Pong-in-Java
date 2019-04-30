package javafx006keyboardmove;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Hold down an arrow key to have your ball move around the screen. Hold down
 * the shift key to have the ball move faster.
 */
public class JavaFX006KeyBoardMove extends Application {

    private static final double W = 600, H = 600; // Size of Scene

    public static final double D = 20;

    //private Node ball;
    int x = 580;
    double y;
    int xx = 20;
    double yy;
    int zz = 1;
    int zzz = 1;
    int yyy;
    int xxx;
    int player1Score = 0;
    int player2Score = 0;
    // DoubleProperty xxx = new SimpleDoubleProperty();
    boolean faster, goNorth, goSouth, goEast, goWest;
    boolean faster2, goNorth2, goSouth2, goEast2, goWest2;
    Rectangle ball = new Rectangle(x, y, 20, 40);
    Rectangle ball2 = new Rectangle(xx, yy, 20, 40);
    Rectangle ball3 = new Rectangle(xxx, yyy, D, D);

    @Override
    public void start(Stage stage) throws Exception {

        ball.setFill(Color.BLUE);
        ball2.setFill(Color.RED);
        ball3.setFill(Color.WHITE);
        Label myLabel = new Label("Player 1: " + player1Score);
        myLabel.setTextFill(Color.RED);
        myLabel.setFont(Font.font("Monaco", 20));

        Label myLabel2 = new Label("Player 2: " + player2Score);
        myLabel2.setTextFill(Color.BLUE);
        myLabel2.setFont(Font.font("Monaco", 20));
        myLabel2.setTranslateX(450);

        StackPane root = new StackPane();
        root.getChildren().addAll(myLabel, myLabel2);

        Group player = new Group(ball, ball2, ball3, root);

        moveBallTo2(20, 300); // Put the ball in the center of the Scene
        moveBallTo(580, 300);

        Scene scene = new Scene(player, W, H, Color.BLACK);
       player2Score = player2Score - 1;
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) // Set boolean to true for key pressed
            {
                case UP:
                    goNorth = true;

                    break;
                case DOWN:
                    goSouth = true;
                    break;

                case W:
                    goNorth2 = true;

                    break;
                case S:
                    goSouth2 = true;
                    break;

                //case SHIFT:
                //  faster = true;
                //break;
            }
        } // Event handler what key pressed
        );

        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) { // Set boolean to false so object stops moving {

                case UP:
                    goNorth = false;
                    break;
                case DOWN:
                    goSouth = false;
                    break;
                case W:
                    goNorth2 = false;

                    break;
                case S:
                    goSouth2 = false;
                    break;
            }
        });
        // Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new KeyValue(xxx, 0)), new KeyFrame(Duration.seconds(3), new KeyValue(xxx, W - D)));
        Timeline timeline = new Timeline();
        //timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        AnimationTimer timer;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                int dx = 0, dy = 0;  // set up variables to measure the change in x or y
                int dxx = 0, dyy = 0;

                if (goNorth) {
                    dy -= 5;
                }
                if (goSouth) {
                    dy += 5;
                }
                if (goNorth2) {
                    dyy -= 5;
                }
                if (goSouth2) {
                    dyy += 5;
                }

                moveBallBy(dx, dy);
                moveBallBy2(dxx, dyy);

                ball3.setLayoutX(xxx);
                ball3.setLayoutY(yyy);
                ball3.setFill(Color.WHITE);

                if (yyy < 1) {
                    zz = 1;
                }
                if (yyy >= 580) {
                    zz = -1;
                }
                yyy = yyy + zz;

                if ((xxx + 20 >= x) && ((yyy + 20 > y && (yyy < (y + 40))))) {
                    //             if(xxx + 20 >= x && ( y >= yyy+20 && y+40 <= yyy+20)){
                    //             if (yyy <=(y + 40) && yyy >= y ){

                    zzz = -1;

                }
                if ((xxx == xx) && ((yyy + 20 >= yy && (yyy <= (yy + 40))))) {
                    //             if(xxx + 20 >= x && ( y >= yyy+20 && y+40 <= yyy+20)){
                    //             if (yyy <=(y + 40) && yyy >= y ){
                    System.out.println(yy);
                    System.out.println(xx);

                    zzz = 1;

                }

                if (xxx == 600) {
                    System.out.println(xxx);

                    player1Score = player1Score + 1;
                    System.out.println(player1Score);

                    myLabel.setText("Player1: " + player1Score);

                    xxx = 300;
                    yyy = 300;
                }
                if (xxx == 0) {
                    System.out.println(xxx);

                    player2Score = player2Score + 1;
                    System.out.println(player2Score);

                    myLabel2.setText("Player2: " + player2Score);

                    xxx = 300;
                    yyy = 300;
                }

                yyy = yyy + zz;
                xxx = xxx + zzz;

            }
        };

        stage.setScene(scene);

        stage.show();

        timer.start();
        timeline.play();
    }

    private void moveBallBy(int dx, int dy) {
        if (dx == 0 && dy == 0) {
            return;
        }
        final double cx = ball.getBoundsInLocal().getWidth() / 2;
        final double cy = ball.getBoundsInLocal().getHeight() / 2;

        //double x = cx + ball.getLayoutX() + dx;
        y = cy + ball.getLayoutY() + dy;
        System.out.println(y);
        moveBallTo(580, y);
    }

    private void moveBallBy2(int dxx, int dyy) {
        if (dxx == 0 && dyy == 0) {
            return;
        }

        final double cxx = ball2.getBoundsInLocal().getWidth() / 2;
        final double cyy = ball2.getBoundsInLocal().getHeight() / 2;

        //double x = cx + ball.getLayoutX() + dx;
        yy = cyy + ball2.getLayoutY() + dyy;
        System.out.println(yy);
        moveBallTo2(20, yy);
    }

    private void moveBallTo(double x, double y) {
        final double cx = ball.getBoundsInLocal().getWidth() / 2;
        final double cy = ball.getBoundsInLocal().getHeight() / 2;
        if (x - cx >= 0
                && x + cx <= W
                && y - cy >= 0
                && y + cy <= H) {
            ball.relocate(x - cx, y - cy);
        }
    }

    private void moveBallTo2(double xx, double yy) {
        final double cxx = ball2.getBoundsInLocal().getWidth() / 2;
        final double cyy = ball2.getBoundsInLocal().getHeight() / 2;

        if (xx - cxx >= 0
                && xx + cxx <= W
                && yy - cyy >= 0
                && yy + cyy <= H) {
            ball2.relocate(xx - cxx, yy - cyy);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
