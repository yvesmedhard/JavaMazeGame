package labirinth.engine;

// @author yvesmedhard

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
  Cell[][] cells;
  int size;
  
  public Maze(Cell[][] cells, int size){
    this.cells = cells;
    this.size = size;
  }
  
  public Maze(String filename){
    this.cells = loadMaze(filename);
    this.size = cells.length;
  }
  
  private Cell[][] loadMaze(String mazeFile){
    // First line is maze size
    // The mazeFile should be in ./assets/mazes folder and must be a txt file.
    // p = way
    // w = wall
    // s = entrance
    // e = exit
    Cell[][] loadMaze = null;
    
    try {
      FileReader fileReader = new FileReader("assets\\mazes\\" + mazeFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = null;
      line = bufferedReader.readLine();
      int mazeSize = Integer.parseInt(line);
      loadMaze = new Cell[mazeSize][mazeSize];
      
      int rowIndex = 0;
      while((line = bufferedReader.readLine()) != null) {
        int colIndex = 0;
        for (char ch: line.toCharArray()) {
          Cell cell = new Cell(String.valueOf(ch), rowIndex, colIndex);
          loadMaze[rowIndex][colIndex] = cell;
          colIndex++;
        }
        rowIndex++;
      }   
      bufferedReader.close();         
    }
    catch(FileNotFoundException ex) {
      System.out.println(
        "Unable to open file '" + 
        mazeFile + "'");                
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file '" 
            + mazeFile + "'");                  
    }
    return loadMaze;
  }
  
  public Cell getCell(int posX, int posY){
    Cell cell = null;
    if(posX >= 0 && posX < size && posY >= 0 && posY < size){
      cell = cells[posX][posY];
    }
    return cell;
  }
  
  public Cell getStartCell(){
    Cell startCell = null;
    for (Cell[] row: cells) {
      for (Cell cell: row) {
        if(cell.isStart()){
          startCell = cell;
        }
      }
    }
    return startCell;
  }
  
  public Cell[][] getCells(){
    return cells;
  }
}
