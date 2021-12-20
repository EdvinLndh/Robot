# Maze solving robot simulator 

A few implementations of some simple maze solving robots. 

1. RandomRobot 
Solves maze by randomly determining which way to go. Maybe not the smartest of the bunch. 
2. RightHandRuleRobot 
Solves maze by always keeping his right robot hand on the wall. 
3. MemoryRobot 
Remembers the last intersection, which it will return to if it happens to run into a dead end. 

Needs a .txt file containing maze in order to run. The file should be structured as follows.
* 'S' Determines start position, can only be one. 
* 'G' Determines goal.
* '*' Determines walls.
* ' ' Determines walkable space.

Jar file compiled with version 15.0.1. 

