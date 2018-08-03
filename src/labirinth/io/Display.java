package labirinth.io;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

// @author yvesmedhard

public class Display extends JFrame {
  private GraphicsDevice graphicsDevice;
  private DisplayMode oldDisplayMode;
  private DisplayMode newDisplayMode;
  private BufferStrategy bufferStrategy;
  
  private void initialize(){
    graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    oldDisplayMode = graphicsDevice.getDisplayMode();
    newDisplayMode = new DisplayMode(850, 850, 32, 60);
    this.setLayout(null);
    this.setBounds((oldDisplayMode.getWidth()/2) - 480, 0, 850, 850);
    this.setUndecorated(true);
  }

  public Display() throws HeadlessException {
    initialize();
  }

  public void setBStrategy() {
    this.createBufferStrategy(2);
    bufferStrategy = this.getBufferStrategy();
  }

  public void setKeyListener(InputMethods keyListener) {
    this.addKeyListener(keyListener);
  }

  public void visibleDisplay(boolean visible) {
    this.setVisible(visible);
  }

  public boolean displayChangeSuported() {
    return graphicsDevice.isDisplayChangeSupported();
  }

  public void toFullscreen8x6() {
    try {
        graphicsDevice.setFullScreenWindow(this);
        graphicsDevice.setDisplayMode(newDisplayMode);
    } catch (Exception ex) {
      System.out.println("Error on fullscreen mode 800 x 600: " + ex);
      toWindow();
    }
  }

  public void resetScreen() {
    try {
      graphicsDevice.setDisplayMode(oldDisplayMode);
    } catch (Exception ex) {
      System.out.println("Error on fullscreen mode native resolution: " + ex);
    }
  }

  public void toWindow() {
    try {
      graphicsDevice.setFullScreenWindow(null);
    } catch (Exception ex) {
      System.out.println("Error on exiting fullscreen mode : " + ex);
    }
  }

  public void setBounds() {
    this.setBounds(
      0, 
      0, 
      graphicsDevice.getDisplayMode().getWidth(), 
      graphicsDevice.getDisplayMode().getHeight()
    );
  }

  public BufferStrategy getBstrategy() {
    return bufferStrategy;
  }

  public GraphicsDevice getGd() {
    return graphicsDevice;
  }
}
