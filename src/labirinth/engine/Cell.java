package labirinth.engine;
// @author yvesmedhard

import java.awt.Point;

public class Cell {
  char type;
  int row;
  int col;
  
  public Cell(char type, int rowIndex, int colIndex) {
    this.type = type;
    this.row = rowIndex;
    this.col =  colIndex;
  }
  
  public String getType(){
    String result = null;
    switch (type) {
      case 'p':
        result = "path";
        break;
      case 'w':
        result = "wall";
        break;
      case 's':
        result = "start";
        break;
      case 'e':
        result = "exit";
        break;
    }
    return result;
  }
  
  public char getRawType(){
    return type;
  }
  
  public boolean isWall(){
    return type == 'w';
  }
  
  public boolean isExit(){
    return type == 'e';
  }
  
  public boolean isPath(){
    return type == 'p';
  }
  
  public boolean isStart(){
    return type == 's';
  }
  
  public int getRow(){
    return row;
  }
  public int getCol(){
    return col;
  }
}
