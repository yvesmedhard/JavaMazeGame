package labirinth.engine;

// @author yvesmedhard

import java.awt.Point;


public class State {
  private Hero hero;
  private Maze maze;
  private boolean win;  
  private boolean ongoing;
  
  public void updateGameState(){
    updateHero();
  }
  
  private void updateHero(){
    updateHeroPosition();
  }
  
  private void updateHeroPosition(){
    String direction = hero.getMoveDirection();
    if(direction != null){

      Cell cell = nextCell(direction);
      if(cell != null){
        if(cell.isPath() || cell.isStart()){
          hero.setPosition(cell.row, cell.col);
          cell.visit();
          win = false;
          setOngoing(true);
        }else if(cell.isExit()){
          hero.setPosition(cell.row, cell.col);
          cell.visit();
          win = true;
          setOngoing(false);
        }
      }
    }
  }
  
  private Cell nextCell(String direction){
    Point position = hero.getPosition();
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
  
  public void setHero(Hero hero) {
    this.hero = hero;
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
}
