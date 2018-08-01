package labirinth.engine;

import labirinth.io.Display;
import labirinth.io.InputMethods;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// @author yvesmedhard

public class Core {
  Display display;
  Renderer renderer;
  State state;
  InputMethods inputMethods;
  Hero hero;
  ArrayList<String> mazesFiles;
  Maze maze;
  private boolean stopGame;  
  private boolean quitGame;
  String gameState;
  
  public Core(){
    quitGame = false;
    setup();
  }
  
  private void setup(){
    display = new Display();
    inputMethods = new InputMethods();
    hero = new Hero();
    
    mazesFiles = loadMazesFiles();
    loadMaze(mazesFiles.remove(0));
    
    // Sets the game components into inputMethods
    inputMethods.setGameCore(this);
    inputMethods.setHero(hero);
    
    // Sets the input listeners on display
    display.setKeyListener(inputMethods);
    display.addMouseListener(inputMethods);
    display.addMouseMotionListener(inputMethods);
    display.addMouseWheelListener(inputMethods);
  }
  
  public void loadMaze(String filename){
    maze = new Maze(filename);
    // Sets Hero into first start position on maze
    Cell startCell = maze.getStartCell();
    hero.setPosition(startCell.row, startCell.col);
  }
  
  public void displayStart(){
    display.setVisible(true);
    // display.toFullscreen8x6();
    display.setBStrategy();
  }
  private void displayStop(){
    display.toWindow();
    display.dispose();
  }
  
  public void rendererLoad(){
    renderer = new Renderer();
    renderer.setDisplay(display);
    renderer.setHero(hero);
    renderer.setMaze(maze);
  }
  
  public void stateLoad(){
    state = new State();
    state.setHero(hero);
    state.setMaze(maze);
    state.setWin(false);
    state.setOngoing(true);
  }
  
  public void start() throws InterruptedException{
    resumeGame();
    displayStart();
    rendererLoad();
    stateLoad();
    gameLoop();
  }
  
  public void stopGame(){
    stopGame = true;
  }
  
  public void resumeGame(){
    stopGame = false;
  }
  
  public void quitGame(){
    quitGame = true;
  }
  
  public void gameLoop() {
    long startTime = System.currentTimeMillis();
    long currentTime = 0;
    while (!isStopGame()) {
      currentTime = System.currentTimeMillis() - startTime;
      // Updates game state
      updateGame();    
      // Draws game on Display
      drawGame(currentTime);
      // Wait till next frame
      try {
        Thread.sleep(42);
      } catch (InterruptedException ex) {
        Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
//    while(!isQuitGame()){
//    }
    System.out.println(currentTime);
    displayStop();
  }
  
  private void updateGame() {
    if(state.isMaze()){
      state.updateGameState();
    }else if(mazesFiles.size() > 0){
      loadMaze(mazesFiles.remove(0));
      rendererLoad();
      stateLoad();
    } else{
      stopGame();
    }
  }
  
  private void drawGame(long currentTime){
    if(state.isMaze()){
      renderer.drawMaze(display);
      renderer.drawHero(display);
    }else if(mazesFiles.size() > 0){
      renderer.drawLoadScreen(display);
    }
    // Draws game time
    drawGameTime(currentTime);
    display.getBstrategy().show();
  }
  
  private void drawGameTime(long currentTime){
    Graphics2D g2d = (Graphics2D) display.getBstrategy().getDrawGraphics();
    g2d.setColor(Color.white);
    g2d.drawString(Long.toString(currentTime), 750, 10);
  }
  
  private ArrayList<String> loadMazesFiles(){
    ArrayList<String> results = new ArrayList<String>();
    File[] files = new File("assets\\mazes").listFiles();
    for (File file : files) {
      if (file.isFile()) {
        results.add(file.getName());
      }
    }
    return results;
  }

  public boolean isStopGame() {
    return stopGame;
  }

  public boolean isQuitGame() {
    return quitGame;
  }
}
