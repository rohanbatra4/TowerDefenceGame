package org.game;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static org.game.TowerDefenseGameApp.score;

public class UINodes extends Application {
    private int height;
    private int width;
    private Label enter;
    private Button proceed;
    private Button buy;
    private int priceBase;
    private Label title;
    private Text head;
    private TextField name;
    private ComboBox difficultyDrop;
    private ComboBox towerDrop;
    private Button play;
    private Button quit;
    private Button options;
    private Button back;
    private Button combat;
    private Button restart;
    private Button upgrade;
    private int money;
    private int health;
    private Label dispMoney;
    private Label dispHealth;
    private Label dispScore;

    public boolean isBossDead() {
        return isBossDead;
    }

    private boolean isBossDead = true;
    private boolean isFirstScreenSelected = false;
    private boolean isSecondScreenSelected = false;

    public boolean isWinGameShown() {
        return isWinGameShown;
    }

    private boolean isWinGameShown = false;
    private boolean isThirdScreenSelected = false;
    private boolean alertChecker = false;
    private Rectangle fire;
    private Rectangle water;
    private Rectangle air;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score = 20;

    public int getCount() {
        return count;
    }



    private final int count = 10;
    private final int firepositionx = 300;
    private final int firepositiony = 300;
    private final int waterpositionx = 200;
    private final int waterpositiony = 200;
    private final int airpositionx = 150;
    private final int airpositiony = 150;
    private final Font titleFont = Font.loadFont(getClass().
            getResourceAsStream("/assets/fonts/Freedom-10eM.ttf"), 32);
    private final Font numberFont = new Font(22);

    public String getGreenButtonStyle() {
        return greenButtonStyle;
    }
    public Rectangle getFire() {
        fire = new Rectangle(50,100);
        fire.setTranslateX(220);
        fire.setTranslateY(250);
        fire.setFill(Color.TRANSPARENT);
        return fire;
    }
    public Rectangle getWater() {
        water = new Rectangle(50, 100);
        water.setTranslateX(250);
        water.setTranslateY(250);
        water.setFill(Color.TRANSPARENT);
        return water;
    }
    public Rectangle getAir() {
        air = new Rectangle(50, 100);
        air.setTranslateX(200);
        air.setTranslateY(250);
        air.setFill(Color.TRANSPARENT);
        return air;
    }
    private final String greenButtonStyle = " -fx-background-color:\n"
            + "            linear-gradient(#5bff71, #17e600),\n"
            + "            linear-gradient(#88cf56, #96cb26),\n"
            + "            linear-gradient(#6aff79, #37ef22),\n"
            + "            linear-gradient(#57ff6d 0%, #27f802 50%, #0bee22 100%),\n"
            + "            linear-gradient(from 0% 0% to 15% 50%,"
            + " rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
            + "    -fx-background-radius: 50;\n"
            + "    -fx-background-insets: 0,1,2,3,0;\n"
            + "    -fx-text-fill: #000000;\n"
            + "    -fx-font-weight: bold;\n"
            + "    -fx-font-size: 14px;\n"
            + "    -fx-padding: 10 20 10 20;";

    public String getYellowButtonStyle() {
        return yellowButtonStyle;
    }

    private final String yellowButtonStyle = " -fx-background-color:\n"
            + "            linear-gradient(#ffc85b, #e68400),\n"
            + "            linear-gradient(#ffef84, #f2ba44),\n"
            + "            linear-gradient(#ffea6a, #efaa22),\n"
            + "            linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
            + "            linear-gradient(from 0% 0% to 15% 50%,"
            + " rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
            + "    -fx-background-radius: 50;\n"
            + "    -fx-background-insets: 0,1,2,3,0;\n"
            + "    -fx-text-fill: #654b00;\n"
            + "    -fx-font-weight: bold;\n"
            + "    -fx-font-size: 14px;\n"
            + "    -fx-padding: 10 20 10 20;";

    public String getRedButtonStyle() {
        return redButtonStyle;
    }

    private final String redButtonStyle = " -fx-background-color:\n"
            + "            linear-gradient(#de213c, #e60000),\n"
            + "            linear-gradient(#ff84a7, #f24444),\n"
            + "            linear-gradient(#ac0e1a, #ef2222),\n"
            + "            linear-gradient(#910a12 0%, #f80202 50%, #ee0b0b 100%),\n"
            + "            linear-gradient(from 0% 0% to 15% 50%,"
            + " rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
            + "    -fx-background-radius: 50;\n"
            + "    -fx-background-insets: 0,1,2,3,0;\n"
            + "    -fx-text-fill: #f6f5f1;\n"
            + "    -fx-font-weight: bold;\n"
            + "    -fx-font-size: 14px;\n"
            + "    -fx-padding: 10 20 10 20;";

    public Font getSecondScreenFont() {
        return secondScreenFont;
    }

    private final Font secondScreenFont = Font.loadFont(getClass().
            getResourceAsStream("/assets/fonts/TheConfessionFullRegular-8qGz.ttf"), 22);

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Label getEnter() {
        return enter;
    }

    public void setEnter(Label enter) {
        this.enter = enter;
    }

    public Button getProceed() {
        return proceed;
    }

    public Button getBuy() {
        return buy;
    }
    public Button getCombat() {
        return combat;
    }
    public void setCombat(Button combat) {
        this.combat = combat;
    }
    public Button getRestart() {
        return restart;
    }
    public void setRestart(Button restart) {
        this.restart = restart;
    }

    public void setProceed(Button proceed) {
        this.proceed = proceed;
    }

    public void setBuy(Button buy) {
        this.buy = buy;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public Text getHead() {
        return head;
    }

    public void setHead(Text head) {
        this.head = head;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public ComboBox getDifficultyDrop() {
        return difficultyDrop;
    }

    public void setTowerDrop(ComboBox towerDrop) {
        this.towerDrop = towerDrop;
    }

    public ComboBox getTowerDrop() {
        return towerDrop;
    }

    public void setDifficultyDrop(ComboBox difficultyDrop) {
        this.difficultyDrop = difficultyDrop;
    }

    public Button getPlay() {
        return play;
    }

    public void setPlay(Button play) {
        this.play = play;
    }

    public Button getQuit() {
        return quit;
    }

    public void setQuit(Button quit) {
        this.quit = quit;
    }

    public Button getOptions() {
        return options;
    }

    public void setOptions(Button options) {
        this.options = options;
    }

    public Button getBack() {
        return back;
    }

    public void setBack(Button back) {
        this.back = back;
    }

    public int getPriceBase() {
        return priceBase;
    }

    public void setPriceBase(int x) {
        priceBase = x;
    }

    public Button getUpgrade() {
        return upgrade;
    }
    public void setUpgrade(Button upgrade) {
        this.upgrade = upgrade;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Label getDispMoney() {
        return dispMoney;
    }

    public Label getDispScore() {
        return dispScore;
    }
    public void setDispScore(Label dispScore) {
        this.dispScore = dispScore;
    }
    public void setDispMoney(Label dispMoney) {
        this.dispMoney = dispMoney;
    }

    public Label getDispHealth() {
        return dispHealth;
    }

    public void setDispHealth(Label dispHealth) {
        this.dispHealth = dispHealth;
    }
    public boolean isFirstScreenSelected() {
        return isFirstScreenSelected;
    }

    public void setFirstScreenSelected(boolean firstScreenSelected) {
        isFirstScreenSelected = firstScreenSelected;
    }

    public boolean isSecondScreenSelected() {
        return isSecondScreenSelected;
    }

    public void setSecondScreenSelected(boolean secondScreenSelected) {
        isSecondScreenSelected = secondScreenSelected;
    }

    public boolean isThirdScreenSelected() {
        return isThirdScreenSelected;
    }

    public void setThirdScreenSelected(boolean thirdScreenSelected) {
        isThirdScreenSelected = thirdScreenSelected;
    }

    public boolean isAlertChecker() {
        return alertChecker;
    }

    public void setAlertChecker(boolean alertChecker) {
        this.alertChecker = alertChecker;
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
    public int getFireTowerX() {
        return firepositionx;
    }
    public int getFireTowerY() {
        return firepositiony;
    }

    public int getAirTowerX() {
        return airpositionx;
    }
    public int getAirTowerY() {
        return airpositiony;
    }
    public int getWaterTowerX() {
        return waterpositionx;
    }
    public int getWaterTowerY() {
        return waterpositiony;
    }
    public Font getTitleFont() {
        return titleFont;
    }
    public Font getNumberFont() {
        return numberFont;
    }

    private final int initialEntityPositionX = 40;
    private final int initialEntityPositionY = 13;

    public int getInitialEntityPositionX() {
        return initialEntityPositionX;
    }
    public int getInitialEntityPositionY() {
        return initialEntityPositionY;
    }
    public int getMonumentInitialHealth() {
        return initialHealth;
    }

    private final int initialHealth = health;
    public String getEntity() {
        return "";
    }

    public boolean isGameOverScreenShown() {
        return isGameOverScreenShown;
    }
    public void setGameOverScreenShown(boolean gameOverScreenShown) {
        isGameOverScreenShown = gameOverScreenShown;
    }
    private boolean isGameOverScreenShown = false;


    private final int initialEnemyHealth = 2;
    private final int damagedEnemyHealth = 1;

    public int getFinalEnemyHealth() {
        return finalEnemyHealth;
    }

    private final int finalEnemyHealth = 0;
    public int getInitialEnemyHealth() {
        return initialEnemyHealth;
    }

    public int getDamagedEnemyHealth() {
        return damagedEnemyHealth;
    }

    public boolean isEnemyDisappeared() {
        return enemyDisappeared;
    }

    private boolean enemyDisappeared = true;

    private final double fireTowerShootRate = 0.9;
    private final double waterTowerShootRate = 1.1;

    public double getFireTowerShootRate() {
        return fireTowerShootRate;
    }

    public double getWaterTowerShootRate() {
        return waterTowerShootRate;
    }

    public double getAirTowerShootRate() {
        return airTowerShootRate;
    }

    private final double airTowerShootRate = 0.6;

    private int initialBossHealth = 200;

    public int getFinalBossHealth() {
        return finalBossHealth;
    }

    private int finalBossHealth = 0;

    public int getInitialBossHealth() {
        return initialBossHealth;
    }

    private int initDamage = 1;

    public int getInitDamage() {
        return initDamage;
    }

    public int getFinalDamage() {
        return finalDamage;
    }

    private int finalDamage = 2;

    public int getEnemyOrientation() {
        return enemyOrientation;
    }

    private int enemyOrientation = 180;

}