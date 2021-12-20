package src;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Class: Maze
 * Description: Builds a maze, from the input .txt file where '*' represents a wall, a blank space ' ' represents a
 *              movable spot. The maze needs an 'S' to represent the starting position and a 'G' to represent the goal.
 *
 * Author: Edvin Lindholm (c19elm@cs.umu.se)
 * Date: 2020-04-16
 * Version: v1.0
 */

public class Maze {

    // Attributes
    private Position start;
    private int numOfCols;
    private int numOfRows;
    private ArrayList<String> mazeData;


    /**
     * Maze constructor. 
     * @param r Reader to file.  
     */
    public Maze(java.io.Reader r){
        Scanner sc = new Scanner(r);
        int hasStart = 0;

        this.mazeData = new ArrayList<>();

        // Read every line and store in an arraylist.
        while (sc.hasNextLine()){
            String s = sc.nextLine();
            if (s.length() > this.numOfCols){
                this.numOfCols = s.length();
            }

            // Confirm that Maze contains a starting position.
            if (s.contains("S")) {
                start = new Position(this.numOfRows, s.indexOf('S'));
                ++hasStart;
            }

            mazeData.add(s);
            this.numOfRows++;
        }

        // Close scanner
        sc.close();

        // If no or too many starts, print error. 
        if ((hasStart == 0 || hasStart >= 2)){
            throw new IllegalArgumentException("Error: No or too many starting positions.");

        }
    }

    /**
     * Method: isMovable
     * @Description: Checks if a position is movable, in this case, isn't a '*'.
     * @param pos Position to check movability.
     * Returns: Bool
     *
     */
    public boolean isMovable(Position pos){

        int column = pos.getX();
        int row = pos.getY();
        if(row >= 0 && row < numOfRows){
            String s = this.mazeData.get(row);
            return pos.getX() >= 0 && pos.getX() < s.length() && s.charAt(column) != '*';
        }

        return false;
    }
    /**
     * Method: isGoal
     * Description: Checks if a position is the goal, in this case, is the 'G' character.
     * Param: @pos: Position
     * Returns: Bool
     *
     */
    public boolean isGoal(Position pos){
        int column = pos.getX();
        int row = pos.getY();
        if(row >= 0 && row < numOfRows){
            String s = this.mazeData.get(row);
            return s.charAt(column) == 'G';
        }
        return false;

    }
    /**
     * Method: getStart
     * Description: Gets the position of the start of the maze. The position containing 'S'.
     * Param:
     * Returns: Position
     *
     */
    public Position getStart(){
        Position pos = new Position(0,0);
        for(int i = 0; i < this.mazeData.size(); i++){
            String s = this.mazeData.get(i);

            if(s.contains("S")){
                pos = new Position(s.indexOf('S'), i);
            }
        }
        return pos;
    }

    /**
     * Method: getNumOfCols
     * Description: Gets the maximum number of columns in the maze.
     * Param:
     * Returns: int
     *
     */
    public int getNumColumns() {
        return numOfCols;
    }

    /**
     * Method: getNumOfRows
     * Description: Gets the maximum number of rows in the maze.
     * Param:
     * Returns: int
     *
     */
    public int getNumRows() {
        return numOfRows;
    }

    public ArrayList<String> getMazeData() {
        return mazeData; 
    }
}


