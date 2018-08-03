package labirinth.engine;
// @author yvesmedhard

import java.util.ArrayList;
import labirinth.players.Hero;


public class Cell {
  String type;
  int row;
  int col;
  boolean visited;
  ArrayList<Hero> visitedBy;
  
  public Cell(String type, int rowIndex, int colIndex) {
    this.type = type;
    this.row = rowIndex;
    this.col =  colIndex;
    visitedBy = new ArrayList<>();
  }
  
  public String getType(){
    String result = null;
    switch (type) {
      case "p":
        result = "path";
        break;
      case "w":
        result = "wall";
        break;
      case "s":
        result = "start";
        break;
      case "e":
        result = "exit";
        break;
    }
    return result;
  }
  
  public String getConsoleChar(){
    String result = null;
    switch (type) {
      case "p":
        result = " ";
        break;
      case "w":
        result = "\u25A0"; // 
        break;
      case "s":
        result = "S";
        break;
      case "e":
        result = "\u2668";
        break;
    }
    return result;
  }
  
  public boolean isVisitedBy(Hero hero){
    return visitedBy.contains(hero);
  }
  
  public void visit(Hero hero){
    visitedBy.add(hero);
    visited = true;
  }
  
  public String getRawType(){
    return type;
  }
  
  public boolean isWall(){
    return type.equals("w");
  }
  
  public boolean isExit(){
    return type.equals("e");
  }
  
  public boolean isPath(){
    return type.equals("p");
  }
  
  public boolean isStart(){
    return type.equals("s");
  }
  
  public int getRow(){
    return row;
  }
  public int getCol(){
    return col;
  }

  boolean isVisited() {
    return visited;
  }
}
