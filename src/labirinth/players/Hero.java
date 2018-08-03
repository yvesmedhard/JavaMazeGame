package labirinth.players;

// @author yvesmedhard

import java.awt.Point;
import java.awt.Color;

public class Hero {
  protected Point position;
  protected String moveDirection;
  protected String consoleChar;
  protected String trailConsoleChar;
  protected Color drawColor;
  
  public Hero(){
    position = new Point(0,0);
    setup();
  }
  
  protected void setup(){
    setupConsoleChar();
    setupDrawColor(); 
    setupTrailConsoleChar();
  }
  
  protected void setupConsoleChar(){
    setConsoleChar("H");
  }
  
  protected void setupDrawColor(){
    drawColor = new Color(235, 197, 0, 230).brighter();
  }
  
  protected void setupTrailConsoleChar(){
    setTrailConsoleChar("\u30FB");
  }
    
  public String getConsoleChar(){
    return consoleChar;
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
  
  public Color drawColor(){
    return drawColor;
  }

  public String trailConsoleChar() {
    return trailConsoleChar;
  }

  public void setDrawColor(Color drawColor) {
    this.drawColor = drawColor;
  }

  public void setConsoleChar(String consoleChar) {
    this.consoleChar = consoleChar;
  }

  public void setTrailConsoleChar(String trailConsoleChar) {
    this.trailConsoleChar = trailConsoleChar;
  }
}
