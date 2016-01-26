package com.home.gol;


/**
 * Rule 1: Any live cell with fewer than two live neighbours dies, as if caused by under-population.
 * Rule 2: Any live cell with two or three live neighbours lives on to the next generation.
 * Rule 3: Any live cell with more than three live neighbours dies, as if by over-population.
 * Rule 4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 * <p>
 * <p>
 * Created by prave_000 on 25/01/2016.
 */
public class GameOfLife {

    /**
     * Method to create an empty array with all elements initialised to 0.
     *
     * @param size the size of the cube to create
     * @return the integer 2d array.
     */
    public int[][] createGameBox(int size) {
        int[][] newArray = new int[size][size];
        printOut(newArray);
        return newArray;
    }

    /**
     * Method to printout the 2d array.
     *
     * @param array the incoming array to be printed.
     */
    public void printOut(int[][] array) {
        System.out.println("");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method computes the next generation of the incoming
     * 2d array, based on conway's 4 laws.
     *
     * @param gameArray the array for which the next generation needs to be calculated.
     * @return the next generation of the current array after applying conway's law.
     */
    public int[][] getNextGeneration(int[][] gameArray) {

        int[][] returnArray = new int[gameArray.length][gameArray.length];

        for (int row = 0; row < gameArray.length; row++) {
            for (int column = 0; column < gameArray.length; column++) {
                int numNeighbours = getNumOfLiveNeighbours(gameArray, row, column);
                if (gameArray[row][column] == 1) {
                    if (numNeighbours < 2) { //Applying rule 1
                        //make the cell dead since he is lonely
                        returnArray[row][column] = 0;
                    } else if (numNeighbours > 3) {//Applying Rule 3
                        //make the cell dead since he is crowded
                        returnArray[row][column] = 0;
                    } else {
                        //Rule 2 implicitly applied.
                        returnArray[row][column] = gameArray[row][column];
                    }
                } else if (numNeighbours == 3) { //Applying rule 4
                    returnArray[row][column] = 1;
                } else {
                    returnArray[row][column] = gameArray[row][column];
                }
            }
        }
        System.out.println("returning array");
        printOut(returnArray);
        return returnArray;
    }

    /**
     * This method finds the number of live neighbours (with cell value as 1) around it.
     * This is done by checking up, down, left, right, and diagonally as well.
     *
     * @param gameArray the incoming game array.
     * @param row       the row of the element for which the live neighbours are to be found.
     * @param column    the column of the element for which the live neighbours are to be found.
     * @return the number of live neighbours.
     */
    private int getNumOfLiveNeighbours(int[][] gameArray, int row, int column) {
        int numNeighbours = 0;
        int rowMax = gameArray.length - 1, columnMax = gameArray.length - 1;
        int rowMin = 0, columnMin = 0;

        //Same row neighbours
        if (column + 1 < columnMax) {
            if (gameArray[row][column + 1] == 1) {
                numNeighbours++;
            }
        }
        if (column - 1 >= columnMin) {
            if (gameArray[row][column - 1] == 1) {
                numNeighbours++;
            }
        }
        //Down row neighbours
        if ((row + 1) < rowMax) {
            if (gameArray[row + 1][column] == 1) {
                numNeighbours++;
            }
            if (column + 1 < columnMax) {
                if (gameArray[row + 1][column + 1] == 1) {
                    numNeighbours++;
                }
            }
            if (column - 1 >= columnMin) {
                if (gameArray[row + 1][column - 1] == 1) {
                    numNeighbours++;
                }
            }
        }
        //Upper row neighbours
        if ((row - 1) >= rowMin) {
            if (gameArray[row - 1][column] == 1) {
                numNeighbours++;
            }
            if (column + 1 < columnMax) {
                if (gameArray[row - 1][column + 1] == 1) {
                    numNeighbours++;
                }
            }
            if (column - 1 >= columnMin) {
                if (gameArray[row - 1][column - 1] == 1) {
                    numNeighbours++;
                }
            }
        }
        return numNeighbours;
    }

    /**
     * Main method to do a Blinker oscillator using conway's law.
     *
     * @param args the main arguments.
     */
    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        int[][] gameBox = gameOfLife.createGameBox(5);
        gameBox[2][1] = gameBox[2][2] = gameBox[2][3] = 1;
        gameOfLife.printOut(gameBox);
        for (int i = 0; i < 6; i++) {
            gameBox = gameOfLife.getNextGeneration(gameBox);
            // gameOfLife.printOut(gameBox);
        }
    }
}
