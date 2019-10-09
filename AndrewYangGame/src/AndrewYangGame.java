
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URISyntaxException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @Date 9/27/2019
 * @author Alfster
 */
public class AndrewYangGame extends Application {

	final int WIDTH = 600;
	final int HEIGHT = 400;
	String[] dollars = { "resources/1.png", "resources/5.bmp", "resources/20.jpg", "resources/1000.jpg" };
	int dollarIndex = 1;
	int collected = -1;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		// Basic Things to start the JavaFX
		stage.setTitle("Andrew Yang Game");
		final Group root = new Group();
		final Scene scene = new Scene(root, WIDTH, HEIGHT); // Keep the
															// resolution of the
															// scene at 320x200.
															// Make the polygons
															// roughly 24x24.

		// ImageView winScreen = new ImageView();
		for (int i = 0; i < 6; i++) {
			final ImageViewAdam p = new ImageViewAdam(getClass().getResource("resources/1.PNG").toURI().toString(), 0); // p
																														// for
																														// polygon
																														// (They
																														// were
																														// polygons
																														// in
																														// an
																														// earlier
																														// version
																														// they
																														// are
																														// dollars
																														// now)
			p.setPreserveRatio(true);
			p.setFitHeight(24);
			p.setX(Math.random() * WIDTH - 24);
			p.setY(Math.random() * HEIGHT - 24);
			p.setOnMouseMoved(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent t) {
					collected++;
					if (collected == 6) {
						collected = 0;
						dollarIndex++;
					}
					if (dollarIndex == 4) { // Star disapperaing instead of
											// respawing
						root.getChildren().remove(p);
						p.setId(p.getAdamID() + 1);
						if (collected == 5) {
							ImageView l = new ImageView();
							try {
								l = new ImageView(getClass().getResource("resources/Slide2.JPG").toURI().toString()); // resources/Slide2.JPG
							} catch (URISyntaxException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// getClass().getResource("za.jpg").toExternalForm()
							// "resources/Slide2.JPG"
							root.getChildren().add(l);
							l.setPreserveRatio(true);
							l.setFitHeight(HEIGHT);
							scene.setCursor(Cursor.DEFAULT);
						}
					} else {
						try {
							p.setImage(new Image(getClass().getResource(dollars[dollarIndex]).toURI().toString()));
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // getClass().getResource(dollars[dollarIndex]).toURI().toString()
						p.setId(p.getAdamID() + 1);
						p.setX(Math.random() * WIDTH - 24);
						p.setY(Math.random() * HEIGHT - 24);

					}
					// Have Here the sening for if u grabbed the wrong dollar
					if (p.getAdamID() != dollarIndex) {
						ImageView l = new ImageView();
						try {
							l = new ImageView(getClass().getResource("resources/Slide3.JPG").toURI().toString());
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						root.getChildren().add(l);
						l.setX(-50);
						l.setPreserveRatio(true);
						l.setFitHeight(HEIGHT);
						scene.setCursor(Cursor.DEFAULT);
					}

				}
			});
			root.getChildren().add(p);
		}

		final ImageView a = new ImageView(getClass().getResource("resources/YangRunning.gif").toURI().toString()); // a
																													// for
																													// andrew
																													// //resources/YangRunning.gif
		a.setMouseTransparent(true);
		a.setPreserveRatio(true);
		a.setFitHeight(60);
		root.getChildren().add(a);

		final ImageView e = new ImageView(getClass().getResource("resources/advisary.jpg").toURI().toString()); // e
																												// enemy
																												// //resources/advisary.jpg
		e.setX(WIDTH - 100);
		e.setY(HEIGHT - 100);
		e.setPreserveRatio(true);
		e.setFitHeight(40);
		e.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				ImageView l = new ImageView();
				try {
					l = new ImageView(getClass().getResource("resources/Slide3.JPG").toURI().toString());
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // file:resources/Slide3.JPG
					// getClass().getResource("za.jpg").toExternalForm()
				root.getChildren().add(l);
				l.setX(-50);
				l.setPreserveRatio(true);
				l.setFitHeight(HEIGHT);
				scene.setCursor(Cursor.DEFAULT);
			}
		});
		root.getChildren().add(e);

		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				a.setX(t.getX() - 20);// -20 //Puts yang on the mouse
				a.setY(t.getY() - 30);// -30

			}
		});

		final ImageView t = new ImageView(getClass().getResource("resources/Slide1.JPG").toURI().toString());// (getClass().getResource("file:resources/Slide1.JPG").toExternalForm());
		root.getChildren().add(t);
		t.setX(-50);
		t.setPreserveRatio(true);
		t.setFitHeight(HEIGHT);
		t.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent m) {
				root.getChildren().remove(t);
				scene.setCursor(Cursor.NONE);// No mouse since yangs running
												// animation will be used as a
												// mouse

			}
		});

		// Animation Timer needed to make news consistantly move toward you
		AnimationTimer animation = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// Move enemy closer on the x axis
				if (a.getX() < e.getX()) {
					e.setX(e.getX() - 2);
				} else if (a.getX() > e.getX()) {
					e.setX(e.getX() + 2);
				}
				// Move enemy closer on the y axis
				if (a.getY() < e.getY()) {
					e.setY(e.getY() - 2);
				} else if (a.getY() > e.getY()) {
					e.setY(e.getY() + 2);
				}

			}
		};

		animation.start();

		// Basic Things to End javaFX
		stage.setScene(scene);

		stage.show();
	}

}
