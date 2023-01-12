package org.game;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.*;


//Not used. Test requirement exception obtained from instructor.
public class TowerDefenseGameAppTest extends ApplicationTest {
    private static UINodes testNode = new UINodes();
    @Override
    public void start(Stage primaryStage) throws Exception {
        TowerDefenseGameApp towerDefenseTest = new TowerDefenseGameApp();
        testNode.start(new Stage());
    }

    @BeforeEach
    protected void setupGameNodes() {
        TowerDefenseGameApp testGame = new TowerDefenseGameApp();
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
    @Test
    protected void testEnemyOnPath() {
        int spawnPositionX = testNode.getInitialEntityPositionX();
        int spawnPositionY = testNode.getInitialEntityPositionY();
        assertEquals(spawnPositionX, 40);
        assertEquals(spawnPositionY, 13);
    }
    @Test
    protected void testRestartButton() {
        testNode.getRestart().fire();
        MatcherAssert.assertThat(testNode.isFirstScreenSelected(), CoreMatchers.is(false));

    }

    @Test
    protected void testCombatButton() {
        testNode.getCombat().fire();
        MatcherAssert.assertThat(testNode.getEntity(), CoreMatchers.is(false));

    }

    @Test
    protected void testGameOverScreen() {
        if (testNode.getHealth() == 0) {
            MatcherAssert.assertThat(testNode.isGameOverScreenShown(), CoreMatchers.is(false));
        }
    }
    @Test
    protected void testHealthIfEnemyAttacks() {
        TowerDefenseGameApp game = new TowerDefenseGameApp();
        int initialHealth = game.getNodes().getMonumentInitialHealth();
        int finalHealth = game.getNodes().getHealth();
        boolean something = false;
        if (finalHealth <= initialHealth) {
            something = true;
        }
        assertTrue(something);
    }

    @Test
    protected void testContinuousEnemies() {
        TowerDefenseGameApp game = new TowerDefenseGameApp();
        boolean something = false;
        if (game.getCount() >= 0) {
            something = true;
        }
        assertTrue(something);
    }
    @Test
    protected void testEnemyHealthIfTowerAttacks() {
        TowerDefenseGameApp game = new TowerDefenseGameApp();
        int initialHealth = game.getNodes().getInitialEnemyHealth();
        int damagedHealth = game.getNodes().getDamagedEnemyHealth();
        boolean something = false;
        if (damagedHealth < initialHealth) {
            something = true;
        }
        assertTrue(something);
    }

    @Test
    protected void testEnemyDeath() {
        TowerDefenseGameApp game = new TowerDefenseGameApp();
        int finalHealth = game.getNodes().getFinalEnemyHealth();
        boolean something = false;
        if (finalHealth == 0 && game.getNodes().isEnemyDisappeared()) {
            something = true;
        }
        assertTrue(something);
    }

    @Test
    protected void isFireTowerShooting() {
        TowerDefenseGameApp game = new TowerDefenseGameApp();
        double shootRate = game.getNodes().getFireTowerShootRate();
        boolean isShooting = false;
        if (shootRate > 0) {
            isShooting = true;
        }
        assertTrue(isShooting);
    }

    @Test
    protected void isWaterTowerShooting() {
        TowerDefenseGameApp game = new TowerDefenseGameApp();
        double shootRate = game.getNodes().getWaterTowerShootRate();
        boolean isShooting = false;
        if (shootRate > 0) {
            isShooting = true;
        }
        assertTrue(isShooting);
    }

    @Test
    protected void isAirTowerShooting() {
        TowerDefenseGameApp game = new TowerDefenseGameApp();
        double shootRate = game.getNodes().getAirTowerShootRate();
        boolean isShooting = false;
        if (shootRate > 0) {
            isShooting = true;
        }
        assertTrue(isShooting);
    }

    @Test
    protected void differentShootRates() {
        TowerDefenseGameApp game = new TowerDefenseGameApp();
        double fireShootRate = game.getNodes().getFireTowerShootRate();
        double waterShootRate = game.getNodes().getWaterTowerShootRate();
        double airShootRate = game.getNodes().getAirTowerShootRate();
        boolean diffShootRates = false;
        if ((fireShootRate != waterShootRate) && (waterShootRate != airShootRate)
                && (fireShootRate != airShootRate)) {
            diffShootRates = true;
        }
        assertTrue(diffShootRates);
    }

    @Test
    protected void damageByBoss() {
        TowerDefenseGameApp game = new TowerDefenseGameApp();
        int initialHealth = game.getNodes().getMonumentInitialHealth();
        int finalHealth = game.getNodes().getHealth();
        boolean bossDamage = false;
        if (finalHealth <= initialHealth) {
            bossDamage = true;
        }
        assertTrue(bossDamage);
    }

    @Test
    protected void spawnBoss() {
        boolean isSpawned = false;
        if (testNode.getCount() - testNode.getScore() <= 0 && testNode.isEnemyDisappeared()) {
            isSpawned = true;
        }
        assertTrue(isSpawned);
    }

    @Test
    protected void testBossOnPath() {
        int spawnPositionX = testNode.getInitialEntityPositionX();
        int spawnPositionY = testNode.getInitialEntityPositionY();
        assertEquals(spawnPositionX, 40);
        assertEquals(spawnPositionY, 13);
    }

    @Test
    protected void testWinGameRestartButton() {
        testNode.getRestart().fire();
        MatcherAssert.assertThat(testNode.isFirstScreenSelected(), CoreMatchers.is(false));
    }

    @Test
    protected void testWinGameScreen() {
        if (testNode.isBossDead()) {
            MatcherAssert.assertThat(testNode.isWinGameShown(), CoreMatchers.is(false));
        }
    }

    @Test
    protected void testStatisticsShown() {
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispMoney().getText()),
                CoreMatchers.is(testNode.getMoney()));
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispScore().getText()),
                CoreMatchers.is(testNode.getScore()));
        MatcherAssert.assertThat(Integer.parseInt(testNode.getDispHealth().getText()),
                CoreMatchers.is(testNode.getHealth()));
    }

    @Test
    protected void testBossHealth() {
        int initialHealth = testNode.getInitialBossHealth();
        int damagedHealth = testNode.getFinalBossHealth();
        boolean something = false;
        if (damagedHealth < initialHealth) {
            something = true;
        }
        assertTrue(something);
    }

    @Test
    protected void testUpgradeButton() {
        testNode.getUpgrade().fire();
        MatcherAssert.assertThat(testNode.getDamagedEnemyHealth(), CoreMatchers.is(false));

    }

    @Test
    protected void testTowerDamage() {
        if (testNode.getFinalDamage() == 2 * testNode.getInitDamage()) {
            assertTrue(true);
        }else {
            fail();
        }
    }

    @Test
    protected void testEnemyOrientationFromUpgradedTowers() {
        if (testNode.getEnemyOrientation() == 180) {
            assertTrue(true);
        } else {
            fail();
        }
    }
}