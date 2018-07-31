package labirinth.engine;

import io.Display;
import io.InputMethods;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
// @author yvesmedhard
public class Core {
  Display display;
  InputMethods inputMethods;
  boolean stopGame;
  
  public Core(){
    setup();
  }
  
  private void setup(){
    stopGame = false;
    display = new Display();
    inputMethods = new InputMethods(this);
    
    //Sets the input listeners on display
    display.setKeyListener(inputMethods);
    display.addMouseListener(inputMethods);
    display.addMouseMotionListener(inputMethods);
    display.addMouseWheelListener(inputMethods);
  }
  
  public void start() throws InterruptedException{
    display.setVisible(true);
    display.toFullscreen8x6();
    display.setBStrategy();
    
    gameLoop();
  }
  
  public void stopGame(){
    this.stopGame = true;
    display.toWindow();
    display.dispose();
  }
  
  public void gameLoop(){
    while (!stopGame) {
      // Updates game state
      updateGame();
      //Draws game on Display
      drawGame();
      // Wait's till next frame
      try {
        Thread.sleep(42);
      } catch (InterruptedException ex) {
        Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public void updateGame(){
    
  }
  
  public void drawGame(){
    
  }
}
