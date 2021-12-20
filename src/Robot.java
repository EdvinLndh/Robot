package src;

/**
 * Interface: Robot
 * Description: Defines methods for Robots.
 *
 * Author: Edvin Lindholm (c19elm@cs.umu.se)
 * Date: 2020-04-21
 * Version: v1.0
 *
 */

 interface Robot {

    void move();

    Position getPosition();

    Boolean hasReachedGoal();


}
