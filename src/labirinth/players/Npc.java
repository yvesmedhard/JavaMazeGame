package labirinth.players;

// @author yvesmedhard

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Npc extends Hero {
  
  @Override
  public String getMoveDirection(){
    //Dumb Strategy
    String[] directions = new String[]{"left", "right", "up", "down"};
    int rnd = new Random().nextInt(directions.length);
    return directions[rnd];
  }
  
  @Override
  protected void setupConsoleChar(){
    setConsoleChar("N");
  }
  
  /**
   *
   */
  @Override
  protected void setupDrawColor(){
    drawColor = new Color(65, 105, 225, 230).brighter();
  }
  
  protected void setupTrailConsoleChar(){
    setTrailConsoleChar("\u30ED");
  }
    
  @Override
  public String getConsoleChar(){
    return consoleChar;
  }
}
