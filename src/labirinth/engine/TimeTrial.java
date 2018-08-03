/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinth.engine;

// @author yvesmedhard

import labirinth.players.Hero;


public class TimeTrial {
  private Maze maze;
  long totalTime;
  private long lastStart;
  private long lastStop;
  private Hero winner;

  public TimeTrial(Maze maze){
    this.maze = maze;
    totalTime = 0;
    lastStart = 0;
    lastStop = 0;
  }
  
  public String formattedTotalTime(){
    long millis = totalTime % 1000;
    long second = (totalTime / 1000) % 60;
    long minute = (totalTime / (1000 * 60)) % 60;

    return String.format("%02d:%02d.%d", minute, second, millis);
  }
  
  public String formattedMazeTime(){
    return String.format("%s: %s", getMaze().getName(), formattedTotalTime());
  }
  
  public void updateTotalTime(long updateTime){
    if(lastStop < lastStart){
      long elapsedTime = updateTime - lastStart;
      totalTime = totalTime + elapsedTime;
    }
  }

  public void setMaze(Maze maze) {
    this.maze = maze;
  }

  public void setLastStart(long lastStart) {
    this.lastStart = lastStart;
  }

  public void setLastStop(long lastStop) {
    this.lastStop = lastStop;
  }

  public Maze getMaze() {
    return maze;
  }
  
  public Hero getWinner() {
    return winner;
  }
  
  public void setWinner(Hero winner) {
    this.winner = winner;
  }
}
