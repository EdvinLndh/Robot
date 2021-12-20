package src;

/**
 * Class: Position
 * Description: Creating a 2D positioning system. Each position has a position in the x-axis and the y-axis.
 *
 *
 * Author: Edvin Lindholm (c19elm@cs.umu.se)
 * Date: 2020-04-07
 * Version: v1.0
 */
public class Position {

    // Attributes
    private int xPos;
    private int yPos;

    //Constructor
    /*
     * param: @xPos: x-axis coordinate
     *        @yPos: y-axis coordinate
     *
     */
    public Position(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // Methods

    //Returns current x-axis coordinate
    public int getX() {
        return this.xPos;

    }
    //Returns current y-axis coordinate
    public int getY() {
        return this.yPos;
    }

    // Creates and returns a new position 1 unit south of current position.
    public Position getPosToSouth(){
        return new Position(this.xPos, (this.yPos + 1));
    }
    // Creates and returns a new position 1 unit north of current position.
    public Position getPosToNorth(){
        return new Position(this.xPos, (this.yPos - 1));
    }
    // Creates and returns a new position 1 unit west of current position.
    public Position getPosToWest(){
        return new Position((this.xPos - 1), this.yPos);
    }
    // Creates and returns a new position 1 unit east of current position.
    public Position getPosToEast(){
        return new Position((this.xPos + 1), this.yPos);
    }

    /**
    * Method: equals
    * Description: Checks if the xPos and yPos variables are the same, if so they're equal.
    * param:    @0: Object
    * returns:  Boolean
    */
@Override
    public boolean equals(Object o) {
        if(o instanceof Position){

            Position p2 = (Position) o;

            return p2.xPos == xPos && p2.yPos == yPos;
        }
        else
            return false;
    }

    //Hashcode function.
    public int hashCode(){
        int x = 10;
        int y = 5;



        return (x + y) % 40;
    }
}