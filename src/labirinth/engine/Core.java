package labirinth.engine;

import labirinth.players.Hero;
import labirinth.io.Display;
import labirinth.io.InputMethods;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import labirinth.players.Npc;

// @author yvesmedhard

public class Core {
  Display display;
  Renderer renderer;
  State state;
  InputMethods inputMethods;
  ArrayList<Hero> heroes;
  ArrayList<Npc> npcs;
  ArrayList<String> mazesFiles;
  ArrayList<TimeTrial> timeTrials;
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
    
    heroes = new ArrayList<>();
    npcs = new ArrayList<>();
    
    Hero hero = new Hero();
    heroes.add(hero);
    
    Npc npc = new Npc();
    npcs.add(npc);
    
    timeTrials = new ArrayList<>();
    
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
    heroes.forEach((hero) -> hero.setPosition(startCell.row, startCell.col));
    npcs.forEach((npc) -> npc.setPosition(startCell.row, startCell.col));
    startTimeTrial();
  }
  
  public void startTimeTrial(){
    TimeTrial timeTrial = new TimeTrial(maze);
    timeTrials.add(timeTrial);    
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
    renderer.setHeroes(heroes);
    renderer.setNpcs(npcs);
    renderer.setTimeTrials(timeTrials);
    renderer.setMaze(maze);
  }
  
  public void stateLoad(){
    state = new State();
    state.setHeroes(heroes);
    state.setNpcs(npcs);
    state.setTimeTrials(timeTrials);
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
    while (!isStopGame()) {
      // Updates game state
      updateGame();    
      // Draws game on Display
      drawGame();
      // Wait till next frame
      try {
        Thread.sleep(42);
      } catch (InterruptedException ex) {
        Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    while(!isQuitGame()){
      renderer.drawGameOver(display);
      try {
        Thread.sleep(42);
      } catch (InterruptedException ex) {
        Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
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
  
  private void drawGame(){
    if(state.isMaze()){
      renderer.drawMaze(display);
      renderer.drawHeroes(display);
      renderer.drawNpcs(display);
    }else if(mazesFiles.size() > 0){
      renderer.drawLoadScreen(display);
    }
    // Draws game time
    renderer.drawTimeTrials(display);
    display.getBstrategy().show();
  }
  
  private ArrayList<String> loadMazesFiles(){
    ArrayList<String> results = new ArrayList<>();
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
