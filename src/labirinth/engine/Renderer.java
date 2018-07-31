package labirinth.engine;

// @author yvesmedhard

import io.Display;
import java.awt.Color;
import java.awt.Graphics2D;

public class Renderer {
  protected Display display;
  
  public Renderer(Display display){
    this.display =  display;
  }
  
  public void drawMaze(Maze maze, Display diaplay){
    Graphics2D g2d = (Graphics2D) display.getBstrategy().getDrawGraphics();
    for (Cell[] row: maze.getCells()) {
      for (Cell cell: row) {
        g2d.drawString(String.valueOf(cell.getRawType()), cell.getRow() * 10, cell.getCol() * 10);
      }
    }
  }
  
  public Graphics2D graphics(){
    return (Graphics2D) display.getBstrategy().getDrawGraphics();
  }
}
