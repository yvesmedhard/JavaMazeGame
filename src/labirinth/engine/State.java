package labirinth.engine;

// @author yvesmedhard

import labirinth.players.Hero;
import java.awt.Point;
import java.util.ArrayList;
import labirinth.players.Npc;


public class State {
  private ArrayList<Hero> heroes;
  private ArrayList<Npc> npcs;
  private ArrayList<TimeTrial> timeTrials;
  private Maze maze;
  private boolean win;  
  private boolean ongoing;
  private String currentState;
  
  public void updateGameState(){
    updateHeroes();
    updateNpcs();
    updateCurrentTimeTrial();
  }
  
  private void updateHeroes(){
    heroes.forEach((hero) -> this.updateHeroPosition(hero));
  }
  
  private void updateNpcs(){
    npcs.forEach((npc) -> this.updateHeroPosition(npc));
  }
  
  private void updateHeroPosition(Hero hero){
    String direction = hero.getMoveDirection();
    if(direction != null){
      checkWin(hero);
      Cell cell = nextCell(hero.getPosition(), direction);
      if(cell != null){
        if(cell.isPath() || cell.isStart() || cell.isExit()){
          hero.setPosition(cell.row, cell.col);
          cell.visit(hero);
        }
      }
    }
  }
  
  private void checkWin(Hero hero){
    Cell cell = maze.getCell((int) hero.getPosition().getX(), (int) hero.getPosition().getY());
    if(cell.isExit()){
      win = true;
      currentTimeTrial().setWinner(hero);
      setOngoing(false);
    }
  }
  
  private Cell nextCell(Point position, String direction){
    int posX = (int) position.getX();
    int posY = (int) position.getY();    
    Cell cell = null;
    switch(direction){
      case ("left"):
        cell = maze.getCell(posX, posY - 1);
        break;
      case ("right"):
        cell = maze.getCell(posX, posY + 1);
        break;
      case ("up"):
        cell = maze.getCell(posX - 1, posY);
        break;
      case ("down"):
        cell = maze.getCell(posX + 1, posY);
        break;
    }
    return cell;
  }
  
  private TimeTrial currentTimeTrial(){
    TimeTrial result = null;
    for (TimeTrial timeTrial : timeTrials) {
      if (maze == timeTrial.getMaze()) {
        result = timeTrial;
      }
    }
    return result;
  }
  
  private void updateCurrentTimeTrial(){
    TimeTrial timeTrial = currentTimeTrial();
    long updateTime = System.currentTimeMillis();
    if(ongoing){
      timeTrial.updateTotalTime(updateTime);
      timeTrial.setLastStart(updateTime);
    }else{
      timeTrial.updateTotalTime(updateTime);
      timeTrial.setLastStop(updateTime);
    }
  }
  
  public void setHeroes(ArrayList<Hero> heroes) {
    this.heroes = heroes;
  }
  
  public void setNpcs(ArrayList<Npc> npcs) {
    this.npcs = npcs;
  }

  public void setMaze(Maze maze) {
    this.maze = maze;
  }
  
  public boolean isMaze(){
    return ongoing && !win;
  }
  
  public void setWin(boolean win) {
    this.win = win;
  }

  public void setOngoing(boolean ongoing) {
    this.ongoing = ongoing;
  }
  
  public String currentState() {
    return currentState;
  }

  public void setTimeTrials(ArrayList<TimeTrial> timeTrials) {
    this.timeTrials = timeTrials;
  }
}
