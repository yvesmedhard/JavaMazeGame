package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import labirinth.engine.Core;

// @author yvesmedhard

public class InputMethods implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
  private Core gameCore;
  
  public InputMethods(Core gameCore){
    this.gameCore = gameCore;
  }
  
  public void setGameCore(Core gameCore) {
    this.gameCore = gameCore;
  }
    
  @Override
  public void keyTyped(KeyEvent e) {
    gameCore.stopGame();
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
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
}