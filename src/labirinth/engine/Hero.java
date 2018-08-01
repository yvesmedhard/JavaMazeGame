package labirinth.engine;

// @author yvesmedhard

import java.awt.Point;

public class Hero {
  private Point position;
  private String moveDirection;
  
  public Hero(){
    position = new Point(0,0);
  }
    
  public String getConsoleChar(){
    return "H";
  }

  public Point getPosition() {
    return position;
  }

  public String getMoveDirection() {
    return moveDirection;
  }

  public void setMoveDirection(String moveDirection) {
    this.moveDirection = moveDirection;
  }

  public void setPosition(int x, int y) {
    this.position.x = x;
    this.position.y = y;
  }
}
