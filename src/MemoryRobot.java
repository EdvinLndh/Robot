package src;
import java.util.ArrayList;

/**
 * Class: MemoryRobot
 *
 * Description: Makes a robot moving through a maze to find a goal, marked 'G'.
 *              Creates a list of visited positions, and also the list of positions
 *              taken to its current position.
 *
 * Author: Edvin Lindholm (c19elm@cs.umu.se)
 * Date: 2020-04-21
 * Version: v1.1
 */

public class MemoryRobot implements Robot{

    // Attributes
    private Position position;
    private Maze maze;
    private ArrayList<Position> path;
    private ArrayList<Position> visitedList;

    // Constructor
    public MemoryRobot (Maze m){
        this.maze = m;
        position = maze.getStart();
        path = new ArrayList<>();
        visitedList = new ArrayList<>();
    }



    /**
     * Method: move
     * Description: Store walked path in an array, if robot encounters an alley,
     *              walk back and take the other possible turn.
     *
     *
     */
    @Override
    public void move() {

        ArrayList<Position> posList = new ArrayList<>();

        if(position.equals(maze.getStart())){
            path.add(position);
            visitedList.add(position);
        }

        //Check are not a wall, or has already been visited.
        if(checkPosition(position.getPosToNorth())) {
            posList.add(position.getPosToNorth());
        }

        if(checkPosition(position.getPosToEast())) {
            posList.add(position.getPosToEast());
        }

        if(checkPosition(position.getPosToWest())) {
            posList.add(position.getPosToWest());
        }

        if(checkPosition(position.getPosToSouth())) {
            posList.add(position.getPosToSouth());
        }

        // If no positions had been found, the robot has walked in to an alley, and needs to back up.
        if(posList.size() == 0){
            path.remove(path.size()-1);
            position = path.get(path.size()-1);

        }
        // Move robot.
        else{
            position = posList.get(posList.size()-1);
            path.add(position);
            visitedList.add(position);
        }


    }

    /**
     *
     * @param pos position to check
     * @return if maze is movable or not.
     */
    private boolean checkPosition(Position pos){
        return maze.isMovable(pos) && !visitedList.contains(pos);
    }

    /**
     * Method: getPosition
     * Description: Gets the robots current position.
     * Returns: Robots current position.
     *
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * Method: hasReachedGoal
     * Description: Checks if the robot has reached the goal.
     * Returns: Boolean
     *
     */
    @Override
    public Boolean hasReachedGoal() {
        return maze.isGoal(position);
    }
}
