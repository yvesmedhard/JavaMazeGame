package labirinth.engine;

// @author yvesmedhard

import labirinth.players.Hero;
import labirinth.io.Display;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import labirinth.players.Npc;

public class Renderer {
  private Display display;
  private ArrayList<Hero> heroes;
  private ArrayList<Npc> npcs;
  private ArrayList<TimeTrial> timeTrials;
  private Maze maze;
  
  public void drawMaze(Display diaplay){
    Graphics2D g2d = (Graphics2D) display.getBstrategy().getDrawGraphics();
    setFont(g2d);
    g2d.fillRect(0, 0, display.getWidth(), display.getHeight());
    g2d.setColor(Color.white);        
    for (Cell[] row: maze.getCells()) {
      for (Cell cell: row) {
        drawCell(g2d, cell);
      }
    }
  }
  
  public void drawCell(Graphics2D g2d, Cell cell){
    if(cell.isVisited()){ 
      heroes.forEach((hero) -> {
        if(cell.isVisitedBy(hero)){
          g2d.setColor(hero.drawColor()); 
          g2d.drawString(hero.trailConsoleChar(), cell.getCol() * positionMultiplier(), (cell.getRow() + 1) * positionMultiplier()); 
        }
      });
      npcs.forEach((npc) -> {
        if(cell.isVisitedBy(npc)){
          g2d.setColor(npc.drawColor()); 
          g2d.drawString(npc.trailConsoleChar(), cell.getCol() * positionMultiplier(), (cell.getRow() + 1) * positionMultiplier()); 
        }
      });
    }else{
      g2d.setColor(Color.WHITE.darker()); 
      g2d.drawString(cell.getConsoleChar(), cell.getCol() * positionMultiplier(), (cell.getRow() + 1) * positionMultiplier()); 
    }
  }
  
  public void drawHeroes(Display display){
    heroes.forEach((hero) -> drawHero(hero, display));
  }
  
  public void drawNpcs(Display display){
    npcs.forEach((npc) -> drawHero(npc, display));
  }
  
  public void drawHero(Hero hero, Display display) {
    Graphics2D g2d = (Graphics2D) display.getBstrategy().getDrawGraphics();
    setFont(g2d);
    g2d.setColor(hero.drawColor());
    int x = (int) hero.getPosition().getX();    
    int y = (int) hero.getPosition().getY();
    g2d.fillRect(y * positionMultiplier(), x * positionMultiplier(), positionMultiplier(), positionMultiplier());
  }
  
  public void drawLoadScreen(Display display){
    Graphics2D g2d = (Graphics2D) display.getBstrategy().getDrawGraphics();
    setFont(g2d);
    g2d.setColor(new Color(130, 130, 130, 1));
    g2d.fillRect(0, 0, display.getWidth(), display.getHeight());
    g2d.setColor(Color.red);
    g2d.drawString("Loading", 380, 290);
  }
  
  public void drawTimeTrials(Display display){
    Graphics2D g2d = (Graphics2D) display.getBstrategy().getDrawGraphics();
    int i = 0;
    for(TimeTrial timeTrial: timeTrials){
      if(timeTrial.getWinner() != null){
        g2d.setColor(timeTrial.getWinner().drawColor());
      }else{
        g2d.setColor(Color.GREEN);
      }
      g2d.drawString(timeTrial.formattedMazeTime(), display.getWidth() - (display.getWidth() - mazeCanvasSize()), 20 * (i + 1));
      i++;
    }
  }
  
  public void drawGameOver(Display display){
    Graphics2D g2d = (Graphics2D) display.getBstrategy().getDrawGraphics();
    g2d.setColor(Color.RED.brighter());
    g2d.drawString("Game Over",  display.getWidth() - (display.getWidth() - mazeCanvasSize()), 20 * (timeTrials.size() + 1));
    g2d.drawString("Press ESC to exit",  display.getWidth() - (display.getWidth() - mazeCanvasSize()), 20 * (timeTrials.size() + 3));
    display.getBstrategy().show();

  }
  
  public void setFont(Graphics2D g2d){
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, positionMultiplier())); 
  }
  
  public int positionMultiplier(){
    return mazeCanvasSize()/maze.size;
  }
  
  public int mazeCanvasSize(){
    return display.getWidth() - 130;
  }

  public void setDisplay(Display display) {
    this.display = display;
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
  
  public void setTimeTrials(ArrayList<TimeTrial> timeTrials) {
    this.timeTrials = timeTrials;
  }
  
}
