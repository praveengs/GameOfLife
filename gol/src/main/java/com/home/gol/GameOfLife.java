package com.home.gol;


/**
 * Created by prave_000 on 25/01/2016.
 */
public class GameOfLife {
    public int[][] createGameBox(int size) {
        int[][] newArray = new int[size][size];
        printOut(newArray);
        return newArray;
    }

    public void printOut(int[][] array) {
        System.out.println("");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getNextGeneration(int[][] gameArray) {

        int[][] returnArray = new int[gameArray.length][gameArray.length];

        for (int row = 0; row < gameArray.length; row++) {
            for (int column = 0; column < gameArray.length; column++) {
                int numNeighbours = getNumOfLiveNeighbours(gameArray, row, column);
                if (gameArray[row][column] == 1) {
                    if (numNeighbours < 2) {
                        //make the cell dead since he is lonely
                        returnArray[row][column] = 0;
                    } else if (numNeighbours > 3 ){
                        //make the cell dead since he is crowded
                        returnArray[row][column] = 0;
                    }
                    else {
                        returnArray[row][column] = gameArray[row][column];
                    }
                } else if (numNeighbours == 3) {
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
            if (gameArray[row+1][column] == 1) {
                numNeighbours++;
            }
            if (column + 1 < columnMax) {
                if (gameArray[row+1][column + 1] == 1) {
                    numNeighbours++;
                }
            }
            if (column - 1 >= columnMin) {
                if (gameArray[row+1][column - 1] == 1) {
                    numNeighbours++;
                }
            }
        }
        //Upper row neighbours
        if ((row - 1) >= rowMin) {
            if (gameArray[row-1][column] == 1) {
                numNeighbours++;
            }
            if (column + 1 < columnMax) {
                if (gameArray[row-1][column + 1] == 1) {
                    numNeighbours++;
                }
            }
            if (column - 1 >= columnMin) {
                if (gameArray[row-1][column - 1] == 1) {
                    numNeighbours++;
                }
            }
        }
        return numNeighbours;
    }

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        int[][] gameBox = gameOfLife.createGameBox(5);
        gameBox[2][1] = gameBox[2][2] = gameBox[2][3] = 1;
        gameOfLife.printOut(gameBox);
        for (int i = 0; i < 6 ; i++) {
            gameBox = gameOfLife.getNextGeneration(gameBox);
           // gameOfLife.printOut(gameBox);
        }
    }
}
