package labirinth.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import labirinth.engine.Core;
import labirinth.engine.Hero;

// @author yvesmedhard

public class InputMethods implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
  private Core gameCore;
  private Hero hero;
  
  public InputMethods(){
  }
    
  @Override
  public void keyTyped(KeyEvent e) { 
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case (KeyEvent.VK_LEFT):
        hero.setMoveDirection("left");
        break;
      case (KeyEvent.VK_RIGHT):
        hero.setMoveDirection("right");
        break;
      case (KeyEvent.VK_UP):
        hero.setMoveDirection("up");
        break;
      case (KeyEvent.VK_DOWN):
        hero.setMoveDirection("down");
        break;
      case (KeyEvent.VK_ESCAPE):
        if(!gameCore.isStopGame()){
          gameCore.stopGame();
        }else{
          gameCore.quitGame();
        }
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case (KeyEvent.VK_LEFT):
        hero.setMoveDirection(null);
        break;
      case (KeyEvent.VK_RIGHT):
        hero.setMoveDirection(null);
        break;
      case (KeyEvent.VK_UP):
        hero.setMoveDirection(null);
        break;
      case (KeyEvent.VK_DOWN):
        hero.setMoveDirection(null);
        break;
   }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mouseDragged(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
  }

  public void setGameCore(Core gameCore) {
    this.gameCore = gameCore;
  }
  
  public void setHero(Hero hero) {
    this.hero = hero;
  }
}