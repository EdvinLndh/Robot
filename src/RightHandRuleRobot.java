package src;

/**
 * Class: RightHandRuleRobot
 * Description: Makes a robot moving through a maze to find a goal, marked 'G'.
 *              Robot always keeps its right side("hand") against a wall.
 *
 * Author: Edvin Lindholm (c19elm@cs.umu.se)
 * Date: 2020-04-21
 * Version: v1.1
 */

public class RightHandRuleRobot implements Robot{

    // Attributes
    private Position position;
    private Maze maze;
    private String direction;


    // Constructor
    public RightHandRuleRobot (Maze m){
        this.maze = m;
        this.direction = "initMove";
        position = maze.getStart();
    }

    /**
     * Method: move
     * Description: Move following the rule that the right "hand" of the robot always follows the wall
     *              to the right of the robot.
     */
    @Override
    public void move() {


        switch(direction){
            // Initial move, find the first northern, not movable position and take it from there.
            case "initMove": {
                dirInitMove();
                break;
            }

            // Direction East
            case "East": {
                dirEast();
                break;
            }

            // Direction north
            case "North": {
                dirNorth();
                break;
            }

            //Direction West
            case "West": {
                dirWest();
                break;
            }
            // Direction South
            case "South": {
                dirSouth();
                break;
            }
        }
    }

    /**
     *
     * @param pos position to check
     * @return if maze is movable or not.
     */
    private boolean checkPosition(Position pos){
        return maze.isMovable(pos);
    }

    /**
     * @Description Checks which direction to move next and calls move function.
     */
    private void dirSouth(){
        if (checkPosition(position.getPosToWest())) {
            moveWest();
        }
        else if (checkPosition(position.getPosToSouth())){
            moveSouth();
        }
        else if(checkPosition(position.getPosToEast())){
            moveEast();
        }
        else if (checkPosition(position.getPosToNorth())){
            moveNorth();
        }
    }
    private void dirNorth(){
        if(checkPosition(position.getPosToEast())){
            moveEast();
        }
        else if (checkPosition(position.getPosToNorth())){
            moveNorth();
        }
        else if (checkPosition(position.getPosToWest())){
            moveWest();
        }
        else if (checkPosition(position.getPosToSouth())){
            moveSouth();
        }
    }
    private void dirEast(){
        if (checkPosition(position.getPosToSouth())) {
            moveSouth();
        }
        else if(checkPosition(position.getPosToEast())){
            moveEast();
        }
        else if (checkPosition(position.getPosToNorth())){
            moveNorth();
        }
        else if (checkPosition(position.getPosToWest())){
            moveWest();
        }
    }
    private void dirWest(){
        if(checkPosition(position.getPosToNorth())){
            moveNorth();
        }
        else if (checkPosition(position.getPosToWest())){
            moveWest();
        }
        else if (checkPosition(position.getPosToSouth())){
            moveSouth();
        }
        else if(checkPosition(position.getPosToEast())){
            moveEast();
        }
    }
    private void dirInitMove(){
        while(checkPosition(position.getPosToNorth())){
            if(!checkPosition(position.getPosToEast())){
                moveEast();
                break;
            }
            moveNorth();
        }
        if (checkPosition(position.getPosToWest())){
            moveWest();
        }
        else if(checkPosition(position.getPosToEast())){
            moveEast();
        }
        else{
            moveSouth();
        }
    }

    /**
     *
     * Description: Moves robot, and changes direction.
     */
    private void moveEast(){
        position = position.getPosToEast();
        direction = "East";
    }
    private void moveNorth(){
        position = position.getPosToNorth();
        direction = "North";
    }
    private void moveWest(){
        position = position.getPosToWest();
        direction = "West";
    }
    private void moveSouth(){
        position = position.getPosToSouth();
        direction = "South";
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
        return maze.isGoal(this.position);
    }
}
