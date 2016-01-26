package com.home.gol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
 * Any live cell with two or three live neighbours lives on to the next generation.
 * Any live cell with more than three live neighbours dies, as if by over-population.
 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 * <p>
 * Created by prave_000 on 25/01/2016.
 */
public class GameOfLifeTest {

    private GameOfLife gameOfLife;

    @Before
    public void setUp() {
        gameOfLife = new GameOfLife();
    }

    @Test
    public void createDeadMatrixTest() {
        int[][] expectedArray =
                new int[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}};
        Assert.assertArrayEquals(expectedArray, gameOfLife.createGameBox(4));
    }

    @Test
    public void deathByUnderPopulation() {
        int[][] expectedArray =
                new int[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}};
        int[][] gameArray = gameOfLife.createGameBox(4);
        gameArray[2][2] = 1;
        gameOfLife.printOut(gameArray);
        Assert.assertArrayEquals(expectedArray, gameOfLife.getNextGeneration(gameArray));
    }

    @Test
    public void moveToNextGenerationWithHealthyCountOfNeighbours() {
        int[][] expectedArray =
                new int[][]{
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}};
        int[][] gameArray = gameOfLife.createGameBox(4);
        gameArray[0][1] = gameArray[0][2] = gameArray[1][1] = gameArray [1][2] = gameArray[3][2] = 1;
        gameOfLife.printOut(gameArray);
        Assert.assertArrayEquals(expectedArray, gameOfLife.getNextGeneration(gameArray));
    }

    @Test
    public void deathByOverPopulation() {
        int[][] expectedArray =
                new int[][]{
                        {1, 0, 1, 0},
                        {1, 0, 1, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}};
        int[][] gameArray = gameOfLife.createGameBox(4);
        gameArray[0][1] = gameArray[0][2] = gameArray[1][0] = gameArray[1][1] = gameArray [1][2] = gameArray[3][2] = 1;
        gameOfLife.printOut(gameArray);
        Assert.assertArrayEquals(expectedArray, gameOfLife.getNextGeneration(gameArray));
    }

    @Test
    public void reproduceByHealthyNeighbours() {
        int[][] expectedArray =
                new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}};
        int[][] gameArray = gameOfLife.createGameBox(4);
        gameArray[1][0] = gameArray[2][1] = gameArray [1][2] = gameArray[3][2] = 1;
        gameOfLife.printOut(gameArray);
        Assert.assertArrayEquals(expectedArray, gameOfLife.getNextGeneration(gameArray));
    }
}