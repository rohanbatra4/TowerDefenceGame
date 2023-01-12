package org.game;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import javafx.application.Platform;
import javafx.event.Event;
import org.game.collision.BulletHandler;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;
import org.game.event.EnemyReachedMonumentEvent;
import org.game.event.*;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;
import static org.game.components.EnemyComponent.gone;

public class TowerDefenseGameApp extends GameApplication {
    public static int score = 0;
    public static boolean finalboss = false;
    private static UINodes nodes = new UINodes();
    private double startX;
    private double startY;
    //Change X and Y coordinates to Path Starting.
    private Point2D enemySpawnPoint = new Point2D(50, 0);
    private static List<Point2D> waypoints = new ArrayList<>();
    private static List<Entity>  allEntities = new ArrayList<>();
    public static int measure = 0;
    private static int count = 0;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(700);
        settings.setHeight(500);
        settings.setTitle("Tower Defense Game");
        settings.setVersion("2.1");
        settings.setFontUI("TheConfessionFullRegular-8qGz.ttf");
        settings.setFontGame("Freedom-10eM.ttf");
        settings.setIntroEnabled(false);
        settings.setProfilingEnabled(false);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);

    }
    @Override
    protected void onPreInit() {
        FXGL.getSettings().setGlobalMusicVolume(0.5);
        FXGL.getSettings().setGlobalSoundVolume(0.5);
    }

    @Override
    protected void initInput() {

    }
    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new BulletHandler());
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("money", 0);
        vars.put("health", 0);
        vars.put("numEnemies", 0);

    }
    @Override
    protected void initUI() {
        startingScreen();
    }


    @Override
    protected void initGame() {
        setUIProperties();
        FXGL.getGameWorld().addEntityFactory(new TowerDefenseFactory());

        waypoints.addAll(Arrays.asList(
                new Point2D(40, 13),
                new Point2D(145, 13),
                new Point2D(145, 90),
                new Point2D(270, 90),
                new Point2D(270, 20),
                new Point2D(385, 20),
                new Point2D(385, 197),
                new Point2D(275, 197),
                new Point2D(275, 390),
                new Point2D(400, 390),
                new Point2D(400, 313),
                new Point2D(510, 313),
                new Point2D(510, 387),
                new Point2D(600, 387)

                //Add 10-20 points along our path.
        ));

        BooleanProperty healthLeft = new SimpleBooleanProperty();
        healthLeft.bind(FXGL.getip("health").greaterThan(0));

        BooleanProperty enemiesLeft = new SimpleBooleanProperty();
        enemiesLeft.bind(FXGL.getip("numEnemies").greaterThan(0));

        FXGL.getGameTimer().runAtIntervalWhile(this::spawnEnemy, Duration.seconds(1), enemiesLeft);

        FXGL.getEventBus().addEventHandler(KilledEvent.ANY, this::onEnemyKilled);
    }

    private void setUIProperties() {
        nodes.setHeight(700);
        nodes.setWidth(500);
        nodes.setPlay(new Button("Play"));
        nodes.getPlay().setStyle(nodes.getYellowButtonStyle());
        nodes.setQuit(new Button("Quit"));
        nodes.getQuit().setStyle(nodes.getYellowButtonStyle());
        nodes.setOptions(new Button("Options"));
        nodes.getOptions().setStyle(nodes.getYellowButtonStyle());
        nodes.setHead(new Text("Login"));
        nodes.setTitle(new Label("Tower Defense Game"));
        nodes.getTitle().setFont(nodes.getTitleFont());
        nodes.setProceed(new Button("Continue"));
        nodes.getProceed().setStyle(nodes.getGreenButtonStyle());
        nodes.setName(new TextField());
        nodes.getName().setStyle("-fx-background-color: #b8b7b7 ,"
                + " #000000 , #e8e8e9; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
        nodes.getName().setFont(nodes.getSecondScreenFont());
        nodes.setEnter(new Label("Enter Name:"));
        nodes.getEnter().setFont(nodes.getSecondScreenFont());
        nodes.setDifficultyDrop(new ComboBox(FXCollections.
                observableArrayList("Easy", "Medium", "Hard")));
        nodes.getDifficultyDrop().setStyle(nodes.getYellowButtonStyle());
        nodes.getDifficultyDrop().setPromptText("Difficulty");
        nodes.setBack(new Button("Back"));
        nodes.getBack().setStyle(nodes.getRedButtonStyle());
        nodes.setMoney(0);
        nodes.setHealth(0);
        nodes.setDispHealth(new Label("Health:"));
        nodes.getDispHealth().setFont(nodes.getNumberFont());
        nodes.setDispMoney(new Label("Money:"));
        nodes.getDispMoney().setFont(nodes.getNumberFont());
        nodes.setDispScore(new Label("Enemies Killed:"));
        nodes.getDispScore().setFont(nodes.getNumberFont());
    }
    private void startingScreen() {
        FXGL.getGameScene().clearUINodes();
        FXGL.getGameScene().setBackgroundRepeat("adobespark.jpeg");
        nodes.setTitle(new Label("Tower Defense Game"));
        nodes.getTitle().setFont(nodes.getTitleFont());
        FXGL.addUINode(nodes.getTitle(), 100, 100);
        FXGL.addUINode(nodes.getPlay(), 100, 250);
        FXGL.addUINode(nodes.getOptions(), 300, 250);
        FXGL.addUINode(nodes.getQuit(), 500, 250);
        nodes.getPlay().setOnAction(e -> initSecondScreen());
        nodes.getQuit().setOnAction(e -> Platform.exit());
    }
    private void initSecondScreen() {
        FXGL.getGameScene().clearUINodes();
        FXGL.addUINode(nodes.getEnter(), 30, 50);
        FXGL.addUINode(nodes.getName(), 140, 43);
        FXGL.addUINode(nodes.getDifficultyDrop(), 270, 250);
        FXGL.addUINode(nodes.getProceed(), 570, 450);
        FXGL.addUINode(nodes.getBack(), 30, 450);
        nodes.getBack().setOnAction(e -> startingScreen());
        nodes.getProceed().setOnAction(e -> {
            String nullName = nodes.getName().getText();
            if ((String) nodes.getDifficultyDrop().getValue() == null) {
                nodes.getDifficultyDrop().setValue("Easy");
            }
            if (nullName.isEmpty() || nullName == null || nullName.trim().length() == 0) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Invalid Type");
                a.setContentText("This is not a valid name.");
                a.initModality(Modality.APPLICATION_MODAL);
                a.showAndWait();
            } else {
                initThirdScreen();
            }
        });
    }
    private void initThirdScreen() {
        FXGL.getGameScene().clearUINodes();
        FXGL.getGameScene().setBackgroundRepeat("pathfinal.png");
        Text moneyText = getUIFactoryService().newText("", Color.BLACK, 22);
        Text healthText = getUIFactoryService().newText("", Color.BLACK, 22);
        //FXGL.getGameScene().setBackgroundRepeat("pathfinal.png");
        moneyText.setTranslateX(145);
        moneyText.setTranslateY(400);

        healthText.setTranslateX(145);
        healthText.setTranslateY(450);

        moneyText.textProperty().bind(FXGL.getWorldProperties().intProperty("money").asString());
        healthText.textProperty().bind(FXGL.getWorldProperties().intProperty("health").asString());
        PropertyMap state = FXGL.getWorldProperties();
        FXGL.getGameScene().addUINodes(moneyText, healthText);
        switch ((String) nodes.getDifficultyDrop().getValue()) {
            case "Easy" :
                state.setValue("health", 200);
                state.setValue("money", 200);
                nodes.setPriceBase(0);
                break;
            case "Medium" :
                state.setValue("health", 125);
                state.setValue("money", 125);
                nodes.setPriceBase(10);
                break;
            case "Hard" :
                state.setValue("health", 90);
                state.setValue("money", 90);
                nodes.setPriceBase(20);
                break;
            default:
                state.setValue("health", 200);
                state.setValue("money", 200);
        }
        nodes.setTowerDrop(new ComboBox(FXCollections.
                observableArrayList("Fire Tower (Blasts Fire) $"
                                + (40 + nodes.getPriceBase()), "Water Tower (Throws Water) $"
                                + (25 + nodes.getPriceBase()),
                        "Air Tower (Blows Air) $" + (10 + nodes.getPriceBase()), "None")));
        nodes.getTowerDrop().setStyle(nodes.getYellowButtonStyle());
        FXGL.addUINode(nodes.getTowerDrop(), 250, 445);
        nodes.getTowerDrop().setPromptText("Shop: Tower Menu");
        nodes.setBuy(new Button("Buy"));
        nodes.getBuy().setStyle(nodes.getGreenButtonStyle());
        FXGL.addUINode(nodes.getBuy(), 570, 453);
        nodes.getBuy().setOnAction(e -> {
            if (nodes.getTowerDrop().getValue() != "None") {
                createTower();
            }
        });
        nodes.setCombat(new Button("Start Combat"));
        nodes.getCombat().setStyle(nodes.getGreenButtonStyle());
        nodes.getCombat().setOnAction(e -> onCombatStart());
        nodes.setUpgrade(new Button("Upgrade Towers"));
        nodes.getUpgrade().setStyle(nodes.getGreenButtonStyle());
        nodes.getUpgrade().setOnAction(e -> onUpgrade());
        FXGL.addUINode(nodes.getCombat(), 110, 215);
        FXGL.addUINode(nodes.getUpgrade(), 110, 275);
    }

    private void onUpgrade() {
        PropertyMap state = FXGL.getWorldProperties();
        if (state.getInt("health") <= 70) {
            measure = 1;
            FXGL.removeUINode(nodes.getUpgrade());
        }
    }

    protected void createTower() {
        PropertyMap state = FXGL.getWorldProperties();
        if (nodes.getTowerDrop().getValue() != null) {
            boolean broke = false;
            String temp = (String) nodes.getTowerDrop().getValue();
            if (temp.equals("Fire Tower (Blasts Fire) $" + (40 + nodes.getPriceBase()))) {
                state.setValue("money", state.getInt("money") - (40 + nodes.getPriceBase()));

                if (state.getInt("money") < 0) {


                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText("Insufficient Funds");
                    a.setContentText("You don't have enough money to buy that item.");
                    a.initModality(Modality.APPLICATION_MODAL);
                    a.showAndWait();


                    state.setValue("money", state.getInt("money") + (40 + nodes.getPriceBase()));
                    return;
                }
                Rectangle dummy = nodes.getFire();
                FXGL.addUINode(dummy);
                Entity e = spawnFireTower((int) dummy.getTranslateX(), (int) dummy.getTranslateY());
                allEntities.add(e);
                makeDraggable(dummy, e);
            }
            if (temp.equals("Water Tower (Throws Water) $" + (25 + nodes.getPriceBase()))) {
                state.setValue("money", state.getInt("money") - (25 + nodes.getPriceBase()));
                if (state.getInt("money") < 0) {


                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText("Insufficient Funds");
                    a.setContentText("You don't have enough money to buy that item.");
                    a.initModality(Modality.APPLICATION_MODAL);
                    a.showAndWait();

                    state.setValue("money", state.getInt("money") + (25 + nodes.getPriceBase()));
                    return;
                }
                Rectangle dummy = nodes.getWater();
                FXGL.addUINode(dummy);
                Entity e = spawnWaterTower((int) dummy.getTranslateX(),
                        (int) dummy.getTranslateY());
                allEntities.add(e);
                makeDraggable(dummy, e);
            }
            if (temp.equals("Air Tower (Blows Air) $" + (10 + nodes.getPriceBase()))) {
                state.setValue("money", state.getInt("money") - (10 + nodes.getPriceBase()));
                if (state.getInt("money") < 0) {


                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText("Insufficient Funds");
                    a.setContentText("You don't have enough money to buy that item.");
                    a.initModality(Modality.APPLICATION_MODAL);
                    a.showAndWait();

                    state.setValue("money", state.getInt("money") + (10 + nodes.getPriceBase()));
                    return;
                }
                Rectangle dummy = nodes.getAir();
                FXGL.addUINode(dummy);
                Entity e = spawnAirTower((int) dummy.getTranslateX(), (int) dummy.getTranslateY());
                allEntities.add(e);
                makeDraggable(dummy, e);
            }

        }
    }

    private void makeDraggable(Node node, Entity tower) {
        node.setOnMousePressed(e -> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
        });
        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() - startX);
            node.setTranslateY(e.getSceneY() - startY);
            tower.setX(e.getSceneX() - startX);
            tower.setY(e.getSceneY() - startY);

        });
        node.setOnMouseDragReleased(e -> {
            invalidPosition(node, tower);
        });
        node.setOnMouseReleased(e -> {
            invalidPosition(node, tower);
        });
    }
    public void invalidPosition(Node node, Entity tower) {
        boolean wrong = false;
        if (0 < node.getTranslateX() && node.getTranslateX() < 209
                && 0 < node.getTranslateY() && node.getTranslateY() < 67) {
            wrong = true;
        } else if (159 < node.getTranslateX() && node.getTranslateX() < 209
                && 67 < node.getTranslateY() && node.getTranslateY() < 124) {
            wrong = true;
        } else if (202 < node.getTranslateX() && node.getTranslateX() < 314
                && 123 < node.getTranslateY() && node.getTranslateY() < 150) {
            wrong = true;
        } else if (530 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 678
                && 260 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 292) {
            wrong = true;
        } else if (680 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 773
                && 358 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 400) {
            wrong = true;
        } else if (680 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 773
                && 358 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 400) {
            wrong = true;
        } else if (650 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 806
                && 177 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 211) {
            wrong = true;
        } else if (769 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 806
                && 212 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 396) {
            wrong = true;
        } else if (658 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 709
                && 360 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 396) {
            wrong = true;
        } else if (650 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 695
                && 400 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 586) {
            wrong = true;
        } else if (695 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 814
                && 556 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 586) {
            wrong = true;
        } else if (777 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 814
                && 450 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 553) {
            wrong = true;
        } else if (814 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 923
                && 467 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 499) {
            wrong = true;
        } else if (891 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 926
                && 500 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 550) {
            wrong = true;
        } else if (897 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 1068
                && 550 < node.getTranslateY() + 144 && node.getTranslateY() + 144 < 584) {
            wrong = true;
        } else if (644 < node.getTranslateX() + 369 && node.getTranslateX() + 369 < 681
                && 186 < node.getTranslateY() + 152 && node.getTranslateY() + 152 < 306) {
            wrong = true;
        }
        if (wrong) {
            node.setTranslateX(250);
            node.setTranslateY(250);
            tower.setX(250);
            tower.setY(250);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Invalid Tower Position");
            a.setContentText("Can't block the path with your tower.");
            a.initModality(Modality.APPLICATION_MODAL);
            a.showAndWait();
        }
    }

    public UINodes getNodes() {
        return nodes;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public List<Point2D> getWaypoints() {
        return new ArrayList<>(waypoints);
    }

    private void onCombatStart() {
        FXGL.removeUINode(nodes.getCombat());
        PropertyMap state = FXGL.getWorldProperties();
        BooleanProperty healthLeft = new SimpleBooleanProperty();
        healthLeft.bind(FXGL.getip("health").greaterThan(0));
        FXGL.getGameTimer().runAtIntervalWhile(this::spawnEnemy, Duration.seconds(1), healthLeft);
        if (state.getInt("health") <= 0) {
            FXGL.getEventBus().addEventHandler(EnemyReachedMonumentEvent.ANY, e -> gameOver());
        }
    }
    private Entity spawnAirTower(int x, int y) {
        PropertyMap state = FXGL.getWorldProperties();
        if (state.getInt("health") >= 0) {
            Entity e = FXGL.spawn("AirTower", new SpawnData(x, y)
                    .put("color", FXGLMath.randomColor())
                    .put("index", 0)
            );
            return e;
        }
        return null;
    }
    private Entity spawnWaterTower(int x, int y) {
        PropertyMap state = FXGL.getWorldProperties();
        if (state.getInt("health") >= 0) {
            Entity e = FXGL.spawn("WaterTower", new SpawnData(x, y)
                    .put("color", FXGLMath.randomColor())
                    .put("index", 0)
            );
            return e;
        }
        return null;
    }
    private Entity spawnFireTower(int x, int y) {
        PropertyMap state = FXGL.getWorldProperties();
        if (state.getInt("health") >= 0) {
            Entity e = FXGL.spawn("FireTower", new SpawnData(x, y)
                    .put("color", FXGLMath.randomColor())
                    .put("index", 0)
            );
            return e;
        }
        return null;
    }
    private void spawnMega() {
        PropertyMap state = FXGL.getWorldProperties();
        state.setValue("numEnemies", state.getInt("numEnemies") + 1);
        finalboss = true;
        Entity e = FXGL.spawn("Enemy", enemySpawnPoint.getX(), enemySpawnPoint.getY());
    }
    public static void winGame() {
        for (Entity e : allEntities) {
            FXGL.despawnWithScale(e, Duration.millis(0));
            e.removeFromWorld();
        }
        allEntities.clear();
        waypoints.clear();
        score = 0;
        count = 0;
        gone = 0;
        finalboss = false;
        FXGL.getGameScene().clearUINodes();
        FXGL.getGameScene().setBackgroundRepeat("winscreen_adobespark.png");
        nodes.setRestart(new Button("Restart"));
        nodes.getRestart().setStyle(nodes.getYellowButtonStyle());
        nodes.getRestart().setOnAction(e -> {
            FXGL.getGameController().startNewGame();});

        FXGL.addUINode(nodes.getRestart(), 160, 350);
        FXGL.addUINode(nodes.getQuit(), 450, 350);
        Text moneyText = getUIFactoryService().newText("", Color.BLACK, 22);
        Text healthText = getUIFactoryService().newText("", Color.BLACK, 22);
        moneyText.setTranslateX(145);
        moneyText.setTranslateY(448);
        healthText.setTranslateX(267);
        healthText.setTranslateY(448);
        moneyText.textProperty().bind(FXGL.getWorldProperties().intProperty("money").asString());
        healthText.textProperty().bind(FXGL.getWorldProperties().intProperty("health").asString());
        Text numEnemies = getUIFactoryService().newText("", Color.BLACK, 22);
        numEnemies.setTranslateX(478);
        numEnemies.setTranslateY(448);
        numEnemies.textProperty().bind(FXGL.getWorldProperties().intProperty("numEnemies").asString());
        FXGL.getGameScene().addUINodes(moneyText, healthText, numEnemies);
        FXGL.addUINode(nodes.getDispMoney(), 70, 426);
        FXGL.addUINode(nodes.getDispHealth(), 195, 426);
        FXGL.addUINode(nodes.getDispScore(),340,426);
    }
    private void spawnEnemy() {
        PropertyMap state = FXGL.getWorldProperties();
        if (state.getInt("health") >= 0 && score <= 10) {
            Entity e = FXGL.spawn("Enemy", enemySpawnPoint.getX(), enemySpawnPoint.getY());
            allEntities.add(e);
            count++;
        } else if (count - score - gone <= 0) {
            spawnMega();
            FXGL.getGameTimer().clear();
        }
        setCount(count);
    }
    private void onEnemyKilled(KilledEvent event) {
        PropertyMap state = FXGL.getWorldProperties();
        score++;
        Entity enemy = event.getEnemy();
        state.setValue("numEnemies", state.getInt("numEnemies") + 1);
        Point2D position = enemy.getPosition();
    }

    public void gameOver() {
        //FXGL.showMessage("Demo Over. Thanks for playing!", FXGL.getGameController()::exit);
        for (Entity e : allEntities) {
            FXGL.despawnWithScale(e, Duration.millis(0));
            e.removeFromWorld();
        }
        allEntities.clear();
        waypoints.clear();
        score = 0;
        count = 0;
        gone = 0;
        finalboss = false;
        onGameOver();
    }
    private void onGameOver() {
        FXGL.getGameScene().clearUINodes();
        FXGL.getGameScene().setBackgroundRepeat("img_3.png");
        nodes.setRestart(new Button("Restart"));
        nodes.getRestart().setStyle(nodes.getYellowButtonStyle());
        nodes.getRestart().setOnAction(e -> FXGL.getGameController().startNewGame());
        FXGL.addUINode(nodes.getRestart(), 160, 250);
        FXGL.addUINode(nodes.getQuit(), 450, 250);
        Text moneyText = getUIFactoryService().newText("", Color.BLACK, 22);
        Text healthText = getUIFactoryService().newText("", Color.BLACK, 22);
        moneyText.setTranslateX(145);
        moneyText.setTranslateY(478);
        healthText.setTranslateX(267);
        healthText.setTranslateY(478);
        moneyText.textProperty().bind(FXGL.getWorldProperties().intProperty("money").asString());
        healthText.textProperty().bind(FXGL.getWorldProperties().intProperty("health").asString());
        Text numEnemies = getUIFactoryService().newText("", Color.BLACK, 22);
        numEnemies.setTranslateX(478);
        numEnemies.setTranslateY(478);
        numEnemies.textProperty().bind(FXGL.getWorldProperties().intProperty("numEnemies").asString());
        FXGL.getGameScene().addUINodes(moneyText, healthText, numEnemies);
        FXGL.addUINode(nodes.getDispMoney(), 70, 456);
        FXGL.addUINode(nodes.getDispHealth(), 195, 456);
        FXGL.addUINode(nodes.getDispScore(),340,456);
    }

    public void setCount(int number) {
        this.count = number;
    }

    public int getCount() {
        return count;
    }

    public static List<Entity> getAllEntities() {
        return allEntities;
    }

    public static void setAllEntities(List<Entity> allEntities) {
        TowerDefenseGameApp.allEntities = allEntities;
    }
}