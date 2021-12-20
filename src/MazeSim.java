package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

/**
 * Class: MazeSim
 * Description: Creates graphical interface for simulation robots. 
 *
 *
 * Author: Edvin Lindholm (c19elm@cs.umu.se)
 * Date: 2021-12-20
 * Version: v1.0
 */
public class MazeSim {

    public static final int MAX_STEPS=100;
    
    private ArrayList<String> mazeDisplay;
    private Maze maze;
    private JTextField messageField;
    private JTextArea mazePanel;
    private String path; 

    /**
     * Constructor
     * @param filepath Path to file. 
     */
    public MazeSim(String filepath) {

        path = filepath; 
        JFrame window= setupMainWindow();
        JMenuBar menuBar = createMenuBar();
        
        mazePanel = new JTextArea();
        messageField = new JTextField();
        
        window.setJMenuBar(menuBar);
        window.add(mazePanel,BorderLayout.CENTER);
        window.add(messageField,BorderLayout.SOUTH);
        Font f = new Font("Courier New", Font.BOLD, 20);
        mazePanel.setFont(f);
        readMaze();
    }

    
    /**
     * Create and configure the menubar for the application
     * @return The configured menu.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar=new JMenuBar();
        JMenu file=new JMenu("File");
        JMenuItem quit=new JMenuItem("Quit");
        quit.addActionListener(e->System.exit(0));
        file.add(quit);
        
        JMenu robot=new JMenu("Robot");
        JMenuItem random = new JMenuItem("RandomRobot");
        JMenuItem rHandRule = new JMenuItem("RightHandRuleRobot");
        JMenuItem memory = new JMenuItem("MemoryRobot");
        ActionListener randomListener=new RobotMenuListener("Random");
        ActionListener rHandRuleListener=new RobotMenuListener("RightHand");
        ActionListener memoryListener=new RobotMenuListener("Memory");
        random.addActionListener(randomListener);
        rHandRule.addActionListener(rHandRuleListener);
        memory.addActionListener(memoryListener);
        
        robot.add(random);
        robot.add(rHandRule);
        robot.add(memory);
        menuBar.add(file);
        menuBar.add(robot);
        return menuBar;
    }



    /**
     * Reads maze from input file in path attribute. 
     */
    protected void readMaze() {
        File file=new File(path);

        try {
            maze=(new Maze(new FileReader(file)));
            mazeDisplay = maze.getMazeData();
        } catch (FileNotFoundException e) {
            updateMessage("Unable to open maze file");
        }
        catch (Exception e) {
            updateMessage("Something wrong with map");
        }
        
        setupMazePanel();
    }

    /**
     * Create and configure the main window
     * @return the window
     */
    protected JFrame setupMainWindow() {
        JFrame theWindow = new JFrame("RoboSim");
        //Setup main window 
        theWindow.setSize(300, 300);
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.setBackground(Color.WHITE);
        theWindow.setVisible(true);

        return theWindow;
    }

    /**
     * Display maze in window
     */
    private void setupMazePanel() {

        for(int i = 0; i < maze.getNumRows(); i++) {
            mazePanel.append(mazeDisplay.get(i));
            mazePanel.append("\n");
        }
    }


    /**
     * Simulate a robot moving in the maze
     * @param r the robot to simulate
     * @throws BadLocationException
     */
    public void roboTic(Robot r) throws BadLocationException {

        Position robotPos = r.getPosition();
        displayChar(robotPos, "X");
        int i=0;
        
        do { //Simulate the robot moving
            displayChar(r.getPosition(), " ");
            // Add removed start symbol.
            if(maze.getStart().equals(r.getPosition())) {
                displayChar(r.getPosition(), "S");
            }
            
            r.move();
            
            displayChar(r.getPosition(), "X");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                return;
            }
            i++;
        } while(!r.hasReachedGoal() && i < MAX_STEPS);
        if(r.hasReachedGoal()) {
            displayChar(r.getPosition(), "G");
            updateMessage("Robot reached goal after "+ i +" steps");
        }
        else
            updateMessage("Robot did not reach goal before reaching maximum number of steps");
    }

    /**
     * Update messagebox. 
     * @param string message
     */
    private void updateMessage(final String string) {
        SwingUtilities.invokeLater(()->messageField.setText(string));
    }

    /**
     * Display a char in the given position.
     * @param robotPos position of robot
     * @param disp char to display. 
     * @throws BadLocationException
     */
    private void displayChar(final Position robotPos, String disp) throws BadLocationException {
        int xPos = robotPos.getX();
        int yPos = robotPos.getY();
        final int offset = mazePanel.getLineStartOffset(yPos) + xPos;
        SwingUtilities.invokeLater(
                () -> mazePanel.replaceRange(new String(disp), offset , offset + 1));
    }

    class RobotMenuListener implements ActionListener {
 
        String robot; 
        /**
         * @param robot String containing which robot to be run.
         */
        public RobotMenuListener(String robot) {
            this.robot = robot;
        }
    
        /* 
         * Run robot. 
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            new Thread(new Runnable(){
    
                @Override
                public void run() {
                    try {
                        switch(robot) {
                            case "Memory":
                                roboTic(new MemoryRobot(maze));
                            break;
                            case "RightHand":
                                roboTic( new RightHandRuleRobot(maze));
                            break;
                            case "Random":
                                roboTic(new RandomRobot(maze));
                            break;
                        }
                    } catch(Exception e) {
                        updateMessage("Simulation failed because of "+
                            e.getClass().getName());
                    }
                }}).start();
        }
    }

    /**
     * @param args Should contain filepath to txt file containing maze. 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new MazeSim(args[0]));
    }
}