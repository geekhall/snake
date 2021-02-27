package com.geekhall.bean;

import com.geekhall.utils.Constants;
import com.geekhall.utils.Direction;

import java.util.LinkedList;

/**
 * @author yiny
 * @Type Snake.java
 * @Desc
 * @date 2/27/21 9:59 PM
 */
public class Snake {

    private LinkedList<Node> body;
    // default move left;
    private Direction direction = Direction.LEFT;

    private boolean isAlive = true;

    public Snake() {
        initSnakeBody();
    }

    /**
     *
     */
    private void initSnakeBody(){
        //
        body = new LinkedList<Node>();

        for (int i = Constants.SNAKE_INIT_POSITION_X; i< Constants.SNAKE_INIT_POSITION_X+Constants.SNAKE_INIT_LENGTH; i++){
            body.add(new Node(i, Constants.SNAKE_INIT_POSITION_y));
        }
    }

    /**
     * move function.
     */
    public void move(){
        if (isAlive) {
            Node head = body.getFirst();


            switch (direction){
                case UP:
                    body.addFirst(new Node(head.getX(), head.getY()-1));
                    break;
                case DOWN:
                    body.addFirst(new Node(head.getX(), head.getY() + 1));
                    break;
                case LEFT:
                    body.addFirst(new Node(head.getX() - 1, head.getY()));
                    break;
                case RIGHT:
                    body.addFirst(new Node(head.getX() + 1, head.getY()));
                    break;
            }

            body.removeLast();

            // alive judge.
            head = body.getFirst();
            if (head.getX() < 0 || head.getY() < 0 || head.getX()>=Constants.WIDTH_COUNT || head.getY() >= Constants.HEIGHT_COUNT){
                isAlive = false;
            }

            // judge the snake bite itself.
            for (int i = 1; i< body.size(); i++){
                Node node = body.get(i);
                if (head.getX() == node.getX() && head.getY() == node.getY()) {
                    isAlive = false;
                }
            }
        } else {
//            System.out.println("dead!!!!!!!!!!");
        }
    }

    /**
     *
     * @param food
     */
    public void eat(Node food){

        Node head = body.getFirst();
        switch (direction){
            case UP:
                body.addFirst(new Node(head.getX(), head.getY()-1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getX(), head.getY() + 1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getX() - 1, head.getY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getX() + 1, head.getY()));
                break;
        }
    }

    /**
     *
     * @return
     */
    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
