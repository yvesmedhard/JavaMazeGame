package labirinth.engine;

// @author yvesmedhard

import labirinth.io.Display;
import java.awt.Color;
import java.awt.Graphics2D;

public class Renderer {
  private Display display;
  private Hero hero;
  private Maze maze;
  
  public Renderer(){
  }
  
  public void drawMaze(Display diaplay){
    Graphics2D g2d = (Graphics2D) display.getBstrategy().getDrawGraphics();
    g2d.setColor(new Color(0f, 0f, 0f, 0.3f));
    g2d.fillRect(0, 0, 800, 600);
    g2d.setColor(Color.white);        
    for (Cell[] row: maze.getCells()) {
      for (Cell cell: row) {
        drawCell(g2d, cell);
      }
    }
  }
  
  public void drawCell(Graphics2D g2d, Cell cell){
    if(cell.isVisited()){
      g2d.setColor(Color.yellow); 
      g2d.drawString("*", cell.getCol() * 10, (cell.getRow() + 1) * 10); 
    }else{
      g2d.setColor(Color.white);        
      g2d.drawString(cell.getConsoleChar(), cell.getCol() * 10, (cell.getRow() + 1) * 10); 
    }
  }
  
  public void drawHero(Display display) {
    Graphics2D g2d = (Graphics2D) display.getBstrategy().getDrawGraphics();
    g2d.setColor(Color.yellow);
    int x = (int) hero.getPosition().getX();    
    int y = (int) hero.getPosition().getY();
    g2d.fillRect(y * 10, x * 10, 10, 10);
  }
  
  public void drawLoadScreen(Display display){
    Graphics2D g2d = (Graphics2D) display.getBstrategy().getDrawGraphics();
    g2d.setColor(new Color(130, 130, 130, 1));
    g2d.fillRect(0, 0, 800, 600);
    g2d.setColor(Color.red);
    g2d.drawString("Loading", 380, 290);

  }
  
 
  /**
   * @param display the display to set
   */
  public void setDisplay(Display display) {
    this.display = display;
  }

  /**
   * @param hero the hero to set
   */
  public void setHero(Hero hero) {
    this.hero = hero;
  }

  /**
   * @param maze the maze to set
   */
  public void setMaze(Maze maze) {
    this.maze = maze;
  }
  
}
