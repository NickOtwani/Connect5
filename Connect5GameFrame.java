import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Connect5GameFrame extends JFrame implements KeyListener{

    private long updatesDone=0;
    public static final int UPS = 50;
    Board b = new Board();
    BufferedImage buffer = null;

    public Connect5GameFrame()
    {
        super();
        buffer = new BufferedImage(900,900,BufferedImage.TYPE_4BYTE_ABGR);

        setUndecorated(true);
        addKeyListener(this);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {


        Graphics bg =  buffer.getGraphics();

        b.draw(bg);

        //g.drawImage(buffer,0,0,null);

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
            System.exit(0);
        if(e.getKeyChar()=='w')
        {
            ;
        }
        if(e.getKeyChar()=='d')
        {
            ;
        }
        if(e.getKeyChar()=='s')
        {
            ;       }
        if(e.getKeyChar()=='a')
        {
            ;       }



    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyChar()=='w')
        {
            ;
        }
        if(e.getKeyChar()=='d')
        {
            ;
        }
        if(e.getKeyChar()=='s')
        {
            ;        }
        if(e.getKeyChar()=='a')
        {
            ;       }
    }
}
