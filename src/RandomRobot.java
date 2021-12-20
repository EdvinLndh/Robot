package src;

import java.util.ArrayList;

    /**
     * Class: RandomRobot
     * Description: Makes a robot moving through a maze to find a goal, marked 'G'.
     *
     * Author: Edvin Lindholm (c19elm@cs.umu.se)
     * Date: 2020-04-16
     * Version: v1.1
     */

    public class RandomRobot implements Robot {

        // Attributes
        private Position position;
        private Position previousPosition;
        private Maze maze;


        // Constructor
        public RandomRobot (Maze m){
            this.maze = m;
            this.position = maze.getStart();
            this.previousPosition = new Position(0,0);

        }

        // Methods

        /**
         * Method: move
         * Description: Moves the robot one step to either north, east, west or south. If the robot is surrounded by 3
         *              stars '*' it will back up. Otherwise it wont step to its previous position.
         *
         */
        public void move(){
            double random;
            ArrayList<Position> posList = new ArrayList<>();

            //Check which positions are currently movable
            if(checkPosition(position.getPosToNorth())){
                posList.add(position.getPosToNorth());
            }

            if(checkPosition(position.getPosToEast())) {
                posList.add(position.getPosToEast());
            }

            if(checkPosition(position.getPosToWest())){
                posList.add(position.getPosToWest());
            }

            if(checkPosition(position.getPosToSouth())){
                posList.add(position.getPosToSouth());
            }

            //Random which posisition to move too.
            random = Math.random() * posList.size();
            // If there is not position which is movable,
            if(posList.size() == 0){
                Position tempPos = position;
                position = previousPosition;
                previousPosition = tempPos;
            }
            else{
                previousPosition = position;
                position = posList.get((int)random);
            }

        }

        /**
         * @param pos position to check
         * @return if maze is movable or not.
         */
        private boolean checkPosition(Position pos){
            return maze.isMovable(pos) && !previousPosition.equals(pos);
        }

        /**
         * Method: getPosition
         * Description: Gets the robots current position.
         * Returns: Robots current position.
         *
         */
        public Position getPosition(){
            return this.position;
        }

        /**
         * Method: hasReachedGoal
         * Description: Checks if the robot has reached the goal.
         * Returns: Boolean
         *
         */
        public Boolean hasReachedGoal(){

            return maze.isGoal(getPosition());
        }
    }

