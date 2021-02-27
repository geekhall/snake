package com.geekhall.main;

import com.geekhall.bean.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import com.geekhall.bean.Node;
import com.geekhall.utils.Constants;
import com.geekhall.utils.Direction;

/**
 * @author yiny
 * @Type MainFrame.java
 * @Desc
 * @date 2/27/21 9:20 PM
 */
public class MainFrame extends JFrame {
    private JPanel jPanel;
    private Snake snake;
    private Node food;
    private Timer timer;

    public MainFrame() throws HeadlessException{

        // init frame.
        initFrame();

        // init game panel.
        initGamePanel();

        // init snake.
        initSnake();

        // init food
        initFood();

        // init timer.
        initTimer();

        // key event listener.
        setKeyListener();


    }
    private void initFood(){
        food = new Node();
        food.random();
    }

    private void setKeyListener(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        if (snake.getDirection() != Direction.DOWN){
                            snake.setDirection(Direction.UP);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (snake.getDirection() != Direction.UP) {
                            snake.setDirection(Direction.DOWN);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (snake.getDirection() != Direction.RIGHT) {
                            snake.setDirection(Direction.LEFT);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (snake.getDirection() != Direction.LEFT) {
                            snake.setDirection(Direction.RIGHT);
                        }
                        break;

                }
//                System.out.println("Key pressed: " + e.getKeyCode());
            }
        });
    }
    private void initTimer(){
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                snake.move();

                Node snakeHead = snake.getBody().getFirst();
                if ( snakeHead.getX() == food.getX() && snakeHead.getY() == food.getY() ){
                    snake.eat(food);
                    food.random();
                }
                jPanel.repaint();

            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 100);
    }

    /**
     * Initiation Frame.
     */
    private void initFrame(){

        // set width and height of the frame.
        setSize(620, 650);

        // set position of the frame.
        setLocation(400, 400);

        // set the function of close button.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

    }

    /**
     * initiation of game panel.
     */
    private void initGamePanel(){

        jPanel = new JPanel(){

            // draw content of panel.
            public void paint(Graphics g) {
                g.clearRect(0,0, Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
                // draw 40 horizon lines.
                for (int x = 10; x<=610; x+=15){
                    g.drawLine(10,x, 610,x);
                }

                // draw 40 vertical lines.
                for (int y=10; y<=610; y+=15){
                    g.drawLine(y, 10, y, 610);
                }

                // draw snake.
                LinkedList<Node> body = snake.getBody();
                for (Node node : body){
                    g.fillRect(node.getX()* Constants.NODE_PIXEL + Constants.PANEL_MARGIN, node.getY()*Constants.NODE_PIXEL+Constants.PANEL_MARGIN, Constants.NODE_PIXEL,Constants.NODE_PIXEL);
                }

                // draw food.
                g.fillRect(food.getX()*Constants.NODE_PIXEL+Constants.PANEL_MARGIN,food.getY()*Constants.NODE_PIXEL+Constants.PANEL_MARGIN, Constants.NODE_PIXEL,Constants.NODE_PIXEL);
            }
        };

        add(jPanel);
    }

    /**
     * init snake.
     */
    private void initSnake(){
        snake = new Snake();
    }
    /**
     *
     * @param args
     */
    public static void main (String [] args){
        new MainFrame().setVisible(true);
    }
}
