package labirinth.engine;
// @author yvesmedhard

public class Cell {
  String type;
  int row;
  int col;
  boolean visited;
  
  public Cell(String type, int rowIndex, int colIndex) {
    this.type = type;
    this.row = rowIndex;
    this.col =  colIndex;
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
        result = "#";
        break;
      case "s":
        result = "S";
        break;
      case "e":
        result = "E";
        break;
    }
    return result;
  }
  
  public void visit(){
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
