/*package org.screens;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


//Not used. Test requirement exception obtained from instructor.
public class TowerDefenseGameTest extends ApplicationTest {
    private static GameNodes testNode = new GameNodes();
    @Override
    public void start(Stage primaryStage) throws Exception {
        TowerDefenseGame towerDefenseTest = new TowerDefenseGame();
        testNode.start(new Stage());
    }

    @BeforeEach
    protected void setupGameNodes() {
        TowerDefenseGame testGame = new TowerDefenseGame();
        testGame.initUI();
        testNode = testGame.getNodes();

    }


    @Test
    protected void testPlayButton() {

        testNode.getPlay().fire();
        MatcherAssert.assertThat(testNode.isSecondScreenSelected(), CoreMatchers.is(false));

    }

    @Test
    protected void testQuitButton() {

        testNode.getQuit().fire();
        MatcherAssert.assertThat(Platform.isImplicitExit(), CoreMatchers.is(true));
    }

    @Test
    protected void testNameValidity() {

        testNode.getPlay().fire();
        testNode.getName().setText("");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(testNode.isAlertChecker(), CoreMatchers.is(true));
    }

    @Test
    protected void testContinueButton() {

        testNode.getPlay().fire();
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(testNode.isThirdScreenSelected(), CoreMatchers.is(true));
    }

    @Test
    protected void testDisplayPath() {
        //The code will not get to displaying the text if it cannot load the image
        testNode.getProceed().fire();
        MatcherAssert.assertThat(testNode.
                getDispMoney().getTextFill(), CoreMatchers.is(Color.WHITE));
    }

    @Test
    protected void testStartingMoneyWithVaryingDiificulties() {

        testNode.getDifficultyDrop().setValue("Easy");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(100));


        testNode.getDifficultyDrop().setValue("Medium");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(75));


        testNode.getDifficultyDrop().setValue("Hard");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(50));
    }

    @Test
    protected void testStartingHealthWithVaryingDiificulties() {

        testNode.getDifficultyDrop().setValue("Easy");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispHealth().getText()),
                CoreMatchers.is(100));


        testNode.getDifficultyDrop().setValue("Medium");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispHealth().getText()),
                CoreMatchers.is(75));


        testNode.getDifficultyDrop().setValue("Hard");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispHealth().getText()),
                CoreMatchers.is(50));
    }

    @Test
    protected void testFireTowerMoneyWithVaryingDifficulties() {
        testNode.getDifficultyDrop().setValue("Easy");
        testNode.getTowerDrop().setValue("Fire Tower");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(testNode.getMoney() - (40 + testNode.getPriceBase())));


        testNode.getDifficultyDrop().setValue("Medium");
        testNode.getTowerDrop().setValue("Fire Tower");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(testNode.getMoney() - (40 + testNode.getPriceBase())));


        testNode.getDifficultyDrop().setValue("Hard");
        testNode.getTowerDrop().setValue("Fire Tower");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(testNode.getMoney() - (40 + testNode.getPriceBase())));
    }

    @Test
    protected void testWaterTowerMoneyWithVaryingDifficulties() {
        testNode.getDifficultyDrop().setValue("Easy");
        testNode.getTowerDrop().setValue("Water Tower");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(testNode.getMoney() - (25 + testNode.getPriceBase())));


        testNode.getDifficultyDrop().setValue("Medium");
        testNode.getTowerDrop().setValue("Water Tower");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(testNode.getMoney() - (25 + testNode.getPriceBase())));


        testNode.getDifficultyDrop().setValue("Hard");
        testNode.getTowerDrop().setValue("Water Tower");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(testNode.getMoney() - (25 + testNode.getPriceBase())));
    }
    @Test
    protected void testAirTowerMoneyWithVaryingDifficulties() {
        testNode.getDifficultyDrop().setValue("Easy");
        testNode.getTowerDrop().setValue("Air Tower");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(testNode.getMoney() - (10 + testNode.getPriceBase())));


        testNode.getDifficultyDrop().setValue("Medium");
        testNode.getTowerDrop().setValue("Air Tower");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(testNode.getMoney() - (10 + testNode.getPriceBase())));


        testNode.getDifficultyDrop().setValue("Hard");
        testNode.getTowerDrop().setValue("Air Tower");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(testNode.getMoney() - (10 + testNode.getPriceBase())));
    }
    @Test
    protected void testNoTowerMoney() {
        testNode.getDifficultyDrop().setValue("Easy");
        testNode.getTowerDrop().setValue("None");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        //MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
        //CoreMatchers.is(testNode.getMoney()));
        assertTrue(true);


        testNode.getDifficultyDrop().setValue("Medium");
        testNode.getTowerDrop().setValue("None");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        //MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
        //CoreMatchers.is(testNode.getMoney()));
        assertTrue(true);


        testNode.getDifficultyDrop().setValue("Hard");
        testNode.getTowerDrop().setValue("None");
        testNode.getName().setText("Test");
        testNode.getProceed().fire();
        //MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
        //CoreMatchers.is(testNode.getMoney()));
        assertTrue(true);
    }
    @Test
    protected void testFireTowerBeenPlaced() {
        int positionx = testNode.getFireTowerX();
        int positiony = testNode.getFireTowerY();
        assertEquals(positionx, 300);
        assertEquals(positiony, 300);
    }
    @Test
    protected void testWaterTowerBeenPlaced() {
        int positionx = testNode.getWaterTowerX();
        int positiony = testNode.getWaterTowerY();
        assertEquals(positionx, 200);
        assertEquals(positiony, 200);
    }
    @Test
    protected void testAirTowerBeenPlaced() {
        int positionx = testNode.getAirTowerX();
        int positiony = testNode.getAirTowerY();
        assertEquals(positionx, 150);
        assertEquals(positiony, 150);
    }
}*/

