import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;
//import javax.imageio.ImageIO;
import java.io.File;

public class Board extends JFrame implements KeyListener, MouseListener {
    public static final int X_SIZE= 8;
    public static final int Y_SIZE= 8;
    public static final int Z_SIZE= 7;
    public static final char TIE = 'T';
    public static final char P_RED = 'R';
    public static final char P_BLUE = 'B';
    public static final char EMPTY  = '-';
    public static final char PLAYING = '-';
    public Player p1, p2;
    Scanner k = new Scanner(System.in);



    BufferedImage buffer = null;
    Graphics bg;


    private char[][][] board = new char[X_SIZE][Y_SIZE][Z_SIZE];
    private char winner;

    public Board()
    {
        super("                   Connect 5 ");
        buffer = new BufferedImage(300,800,BufferedImage.TYPE_4BYTE_ABGR);

        winner = PLAYING;
        for(int x = 0; x< X_SIZE; x++)
        {
            for(int y = 0; y< Y_SIZE; y++)
            {
                for(int z = 0; z< Z_SIZE; z++)
                {
                    board[x][y][z]= EMPTY;
                }
            }
        }
        setSize(300, 800);
        addMouseListener(this);
        setVisible(true);


    }
    public Board(Board b)
    {
        super();
        buffer = new BufferedImage(300,800,BufferedImage.TYPE_4BYTE_ABGR);

        winner = PLAYING;
        for(int x = 0; x< X_SIZE; x++)
        {
            for(int y = 0; y< Y_SIZE; y++)
            {
                for(int z = 0; z< Z_SIZE; z++)
                {
                    board[x][y][z]= b.getBoard()[x][y][z];
                }
            }
        }
        setSize(300, 800);
        addMouseListener(this);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {

        int xpos1=0, xpos2=0, xpos3=0, xpos4=0;
        int ypos1=0, ypos2=0, ypos3=0, ypos4=0;
        int x_width = 20;
        int y_height = 10;
        int x_align = 300;
        int gap = 0;
        int zpos;
        int xpos[] = new int[4];
        int ypos[] = new int[4];

        bg =  buffer.getGraphics();

        bg.setColor(Color.BLACK);
        bg.fillRect(0, 0, getWidth(), getHeight());


        for (int z= Z_SIZE-1; z >=0; z-- )
        {
            for (int x=0; x< X_SIZE; x++)
            {

                    xpos1 = x*(x_width+gap) + 100  + gap ;
                    xpos2 = x*(x_width+gap) + 100 - 8;
                    xpos3 = xpos2 + x_width - gap;
                    xpos4 = xpos1 + x_width - gap;




                for (int y =0; y<Y_SIZE; y++)
                {
                    zpos = z*90 + 130;
                    ypos1 = y*(y_height+gap) + zpos;
                    ypos2 = y*(y_height+gap) + zpos -gap  + y_height;
                    ypos3 = ypos2;
                    ypos4 = ypos1;

                    xpos[0]=xpos1 - (8*y) ;
                    xpos[1]=xpos2- (8*y);
                    xpos[2]=xpos3- (8*y);
                    xpos[3]=xpos4- (8*y);
                    ypos[0]=ypos1;ypos[1]=ypos2;ypos[2]=ypos3;ypos[3]=ypos4;

                    bg.setColor(Color.WHITE);
                    bg.drawPolygon(xpos,ypos,4);


                    if(board[x][y][z] == 'R') {
                        bg.setColor(Color.RED);
                        bg.fillOval(xpos[0], ypos[0]+2, x_width-6, y_height-4);
                    }
                    if(board[x][y][z] == 'B') {
                        bg.setColor(Color.BLUE);
                        bg.fillOval(xpos[0], ypos[0]+2, x_width-6, y_height-4);
                    }

                }

            }
        }

        g.drawImage(buffer,0,0,null);
    }


    public boolean makeMove(Move m, char p)
    {
        int n = 0;
        int x=m.getRow();
        int y=m.getCol();
        for (int z = Z_SIZE-1; z >=0 ; z--) {
             if (board[x][y][z] == EMPTY)
             {
                 board[x][y][z] = p;
                 return true;
             }
        }
        return false;
    }

    public boolean isFull() {
        int n = 0;
        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < Y_SIZE; y++) {
                for (int z = 0; z < Z_SIZE; z++) {
                    if (board[x][y][z] == EMPTY) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

     private void setLocation(Location l, char p)
     {
        /* for(int x = 0; x< l.getX(); x++)
         {
             for(int y = 0; y< l.getY(); y++)
             {
                 for(int z = 0; z< l.getZ(); z++)
                 {
                     board[x][y][z]= p;
                 }
             }
         }*/
         board[l.getX()][l.getY()][l.getZ()] = p;
     }



     public char getWinner()
     {
         if (check_winner(P_RED)) return P_RED;
         if (check_winner(P_BLUE)) return P_BLUE;
         return PLAYING;
     }
     boolean check_winner (char p) {
         int cnt = 0;

         int x, y, z;
         int x1, y1, z1;
         int checkColumn = 1;
         int checkRow = 1;
         int checkUp = 1;
         boolean flag = false;
         boolean check = false;

         for (z = 0; z < Z_SIZE; z++) {
             for (x = 0; x < X_SIZE; x++) {
                 cnt = 0;
                 for (y = 0; y < Y_SIZE; y++) {
                     if (board[x][y][z] == p)
                         cnt++;
                     else cnt = 0;
                     if (cnt >= 5)
                         return true;
                 }
             }
         }

         cnt = 0;

         for (z = 0; z < Z_SIZE; z++) {
             for (y = 0; y < Y_SIZE; y++) {
                 cnt = 0;
                 for (x = 0; x < X_SIZE; x++) {
                     if (board[x][y][z] == p)
                         cnt++;
                     else cnt = 0;
                     if (cnt >= 5)
                         return true;
                 }
             }
         }
         cnt = 0;

         for (x = 0; x < X_SIZE; x++) {
             for (z = 0; z < Z_SIZE; z++) {
                 cnt = 0;
                 for (y = 0; y < Y_SIZE; y++) {
                     if (board[x][y][z] == p)
                         cnt++;
                     else cnt = 0;
                     if (cnt >= 5)
                         return true;
                 }
             }
         }
         cnt = 0;

         for (x = 0; x < X_SIZE; x++) {
             for (y = 0; y < Y_SIZE; y++) {
                 cnt = 0;
                 for (z = 0; z < Z_SIZE; z++) {
                     if (board[x][y][z] == p)
                         cnt++;
                     else cnt = 0;
                     if (cnt >= 5)
                         return true;
                 }
             }
         }
         cnt = 0;

         for (y = 0; y < Y_SIZE; y++) {
             for (x = 0; x < X_SIZE; x++) {
                 cnt = 0;
                 for (z = 0; z < Z_SIZE; z++) {
                     if (board[x][y][z] == p)
                         cnt++;
                     else cnt = 0;
                     if (cnt >= 5)
                         return true;
                 }
             }
         }
         cnt = 0;

         for (y = 0; y < Y_SIZE; y++) {
             for (z = 0; z < Z_SIZE; z++) {
                 cnt = 0;
                 for (x = 0; x < X_SIZE; x++) {
                     if (board[x][y][z] == p)
                         cnt++;
                     else cnt = 0;
                     if (cnt >= 5)
                         return true;
                 }
             }
         }
         cnt = 0;

         System.out.println("aaaaaaaaa");
         //x-y
        //diagonal forward
         for (z = 0; z < Z_SIZE; z++) {
             cnt = 0;
             checkColumn = 1;
             checkRow = 1;
             flag = false;
             check = false;
             while (!flag) {
                 for (x = 0; x < X_SIZE; x++) {
                     for (y = 0; y < Y_SIZE; y++) {
                         if (board[x][y][z] == p) {
                             cnt++;
                             check = true;
                             while (check) {
                                 if (checkColumn + x < X_SIZE && checkRow + y < Y_SIZE) {
                                     if (board[x + checkColumn][y + checkRow][z] == p) {
                                         cnt++;
                                     } else {
                                         cnt = 0;
                                         checkColumn = 1;
                                         checkRow = 1;
                                         break;
                                     }
                                 }
                                 checkColumn += 1;
                                 checkRow += 1;

                                 if (checkColumn == X_SIZE - 1 || checkRow == Y_SIZE - 1) {
                                     check = false;
                                     break;

                                 }
                                 if (cnt >= 5) return true;
                             }
                         }
                         if (cnt >= 5) return true;
                         cnt = 0;
                         checkColumn = 1;
                         checkRow = 1;
                     }
                 }
                 break;
             }
         }
         System.out.println("bbbbbbbbbb");
         //diagonal backward
         for (z = 0; z < Z_SIZE; z++) {
             cnt = 0;
             checkColumn = 1;
             checkRow = 1;
             flag = false;
             check = false;
             while (!flag) {
                 for (x = 0; x < X_SIZE; x++) {
                     for (y = 0; y < Y_SIZE; y++) {
                         if (board[x][y][z] == p) {
                             cnt++;
                             check = true;
                             while (check) {
                                 if (x - checkColumn >=0 && checkRow + y < Y_SIZE) {
                                     if (board[x - checkColumn][y + checkRow][z] == p) {
                                         cnt++;
                                     } else {
                                         cnt = 0;
                                         checkColumn = 1;
                                         checkRow = 1;
                                         break;
                                     }
                                 }
                                 checkColumn += 1;
                                 checkRow += 1;

                                 if (checkColumn == 0 || checkRow == Y_SIZE - 1) {
                                     check = false;
                                     break;

                                 }
                                 if (cnt >= 5) return true;
                             }
                         }
                         if (cnt >= 5) return true;
                         cnt = 0;
                         checkColumn = 1;
                         checkRow = 1;
                     }
                 }
                 break;
             }
         }


         System.out.println("cccccccccccccc");
         //z-y
         //diagonal forward
         for (x = 0; x < X_SIZE; x++) {
             cnt = 0;
             checkColumn = 1;
             checkRow = 1;
             flag = false;
             check = false;
             while (!flag) {
                 for (z = 0; z < Z_SIZE; z++) {
                     for (y = 0; y < Y_SIZE; y++) {
                         if (board[x][y][z] == p) {
                             cnt++;
                             check = true;
                             while (check) {
                                 if (checkColumn + z < Z_SIZE && checkRow + y < Y_SIZE) {
                                     if (board[x][y + checkRow][z+checkColumn] == p) {
                                         cnt++;
                                     } else {
                                         cnt = 0;
                                         checkColumn = 1;
                                         checkRow = 1;
                                         break;
                                     }
                                 }
                                 checkColumn += 1;
                                 checkRow += 1;

                                 if (checkColumn == Z_SIZE - 1 || checkRow == Y_SIZE - 1) {
                                     check = false;
                                     break;

                                 }
                                 if (cnt >= 5) return true;
                             }
                         }
                         if (cnt >= 5) return true;
                         cnt = 0;
                         checkColumn = 1;
                         checkRow = 1;
                     }
                 }
                 break;
             }
         }
         System.out.println("dddddddddddd");
         //diagonal backward
         for (x = 0; x < X_SIZE; x++) {
             cnt = 0;
             checkColumn = 1;
             checkRow = 1;
             flag = false;
             check = false;
             while (!flag) {
                 for (z = 0; z < Z_SIZE; z++) {
                     for (y = 0; y < Y_SIZE; y++) {
                         if (board[x][y][z] == p) {
                             cnt++;
                             check = true;
                             while (check) {
                                 if (z - checkColumn >= 0 && checkRow + y < Y_SIZE) {
                                     if (board[x][y + checkRow][z-checkColumn] == p) {
                                         cnt++;
                                     } else {
                                         cnt = 0;
                                         checkColumn = 1;
                                         checkRow = 1;
                                         break;
                                     }
                                 }
                                 checkColumn += 1;
                                 checkRow += 1;

                                 if (checkColumn == 0 || checkRow == Y_SIZE - 1) {
                                     check = false;
                                     break;

                                 }
                                 if (cnt >= 5) return true;
                             }
                         }
                         if (cnt >= 5) return true;
                         cnt = 0;
                         checkColumn = 1;
                         checkRow = 1;
                     }
                 }
                 break;
             }
         }

         System.out.println("eeeeeeeeeeeee");
         //x-z
         //diagonal forward
         for (y = 0; y < Y_SIZE; y++) {
             cnt = 0;
             checkColumn = 1;
             checkRow = 1;
             flag = false;
             check = false;
             while (!flag) {
                 for (x = 0; x < X_SIZE; x++) {
                     for (z = 0; z < Z_SIZE; z++) {
                         if (board[x][y][z] == p) {
                             cnt++;
                             check = true;
                             while (check) {
                                 if (checkColumn + x < X_SIZE && checkRow + z < Z_SIZE) {
                                     if (board[x+ checkColumn][y][z+checkRow] == p) {
                                         cnt++;
                                     } else {
                                         cnt = 0;
                                         checkColumn = 1;
                                         checkRow = 1;
                                         break;
                                     }
                                 }
                                 checkColumn += 1;
                                 checkRow += 1;

                                 if (checkColumn == X_SIZE - 1 || checkRow == Z_SIZE - 1) {
                                     check = false;
                                     break;

                                 }
                                 if (cnt >= 5) return true;
                             }
                         }
                         if (cnt >= 5) return true;
                         cnt = 0;
                         checkColumn = 1;
                         checkRow = 1;
                     }
                 }
                 break;
             }
         }

         System.out.println("fffffffffff");
         //diagonal backward
         for (y = 0; y < Y_SIZE; y++) {
             cnt = 0;
             checkColumn = 1;
             checkRow = 1;
             flag = false;
             check = false;
             while (!flag) {
                 for (x = 0; x < X_SIZE; x++) {
                     for (z = 0; z < Z_SIZE; z++) {
                         if (board[x][y][z] == p) {
                             cnt++;
                             check = true;
                             while (check) {
                                 if (x - checkColumn >=0 && checkRow + z < Z_SIZE) {
                                     if (board[x- checkColumn][y][z+checkRow] == p) {
                                         cnt++;
                                     } else {
                                         cnt = 0;
                                         checkColumn = 1;
                                         checkRow = 1;
                                         break;
                                     }
                                 }
                                 checkColumn += 1;
                                 checkRow += 1;

                                 if (checkColumn == 0 || checkRow == Z_SIZE - 1) {
                                     check = false;
                                     break;

                                 }
                                 if (cnt >= 5) return true;
                             }
                         }
                         if (cnt >= 5) return true;
                         cnt = 0;
                         checkColumn = 1;
                         checkRow = 1;
                     }
                 }
                 break;
             }
         }



        // diagnal across  - 1
         System.out.println("hhhhhhhhhhh");
             cnt = 0;
             checkColumn = 1;
             checkRow = 1;
             checkUp = 1;
             flag = false;
             check = false;
                 for (z = 0; z < Z_SIZE; z++) {

                    for (x = 0; x < X_SIZE; x++) {

                     for (y = 0; y < Y_SIZE; y++) {

                         if (board[x][y][z] == p) {
                             cnt++;
                             check = true;
                             System.out.println("loop");
                             while (check) {
                                 if (checkColumn + x < X_SIZE  && checkRow + y < Y_SIZE && checkUp + z < Z_SIZE) {
                                     if (board[x + checkColumn][y + checkRow][z + checkUp] == p) {
                                         cnt++;
                                         System.out.println("poop");
                                     } else {
                                         cnt = 0;
                                         checkColumn = 1;
                                         checkRow = 1;
                                         checkUp = 1;
                                         break;
                                     }
                                 }
                                 checkColumn += 1;
                                 checkRow += 1;
                                 checkUp += 1;

                                 if (checkColumn == X_SIZE - 1 || checkRow == Y_SIZE - 1 || checkUp == Z_SIZE - 1) {
                                     check = false;
                                     break;

                                 }
                                 if (cnt >= 5) return true;
                             }
                         }
                         if (cnt >= 5) return true;
                         cnt = 0;
                         checkColumn = 1;
                         checkRow = 1;
                         checkUp = 1;
                     }
                        if (cnt >= 5) return true;
                        cnt = 0;
                        checkColumn = 1;
                        checkRow = 1;
                        checkUp = 1;
                 }
             }


         // diagnal across  - 2
         System.out.println("IIIIIIIIIIIIIII");
         cnt = 0;
         checkColumn = 1;
         checkRow = 1;
         checkUp = 1;
         flag = false;
         check = false;

             for (z = 0; z < Z_SIZE; z++) {
                 for (x = 0; x < X_SIZE; x++) {
                     for (y = 0; y < Y_SIZE; y++) {
                         if (board[x][y][z] == p) {
                             cnt++;
                             check = true;
                             while (check) {
                                 if (x - checkColumn >=0 && checkRow + y < Y_SIZE && checkUp + z < Z_SIZE) {
                                     if (board[x - checkColumn][y + checkRow][z + checkUp] == p) {
                                         cnt++;
                                     } else {
                                         cnt = 0;
                                         checkColumn = 1;
                                         checkRow = 1;
                                         checkUp = 1;
                                         break;
                                     }
                                 }
                                 checkColumn += 1;
                                 checkRow += 1;
                                 checkUp += 1;

                                 if (checkColumn == 0  || checkRow == Y_SIZE - 1 || checkUp == Z_SIZE - 1) {
                                     check = false;
                                     break;

                                 }
                                 if (cnt >= 5) return true;
                             }
                         }
                         if (cnt >= 5) return true;
                         cnt = 0;
                         checkColumn = 1;
                         checkRow = 1;
                         checkUp = 1;
                     }
                     if (cnt >= 5) return true;
                     cnt = 0;
                     checkColumn = 1;
                     checkRow = 1;
                     checkUp = 1;
                 }
             }



         // diagnal across  - 3
         System.out.println("JJJJJJJJJJJJJ");
         cnt = 0;
         checkColumn = 1;
         checkRow = 1;
         checkUp = 1;
         flag = false;
         check = false;
             for (z = 0; z < Z_SIZE; z++) {
                 for (x = 0; x < X_SIZE; x++) {
                     for (y = 0; y < Y_SIZE; y++) {
                         if (board[x][y][z] == p) {
                             cnt++;
                             check = true;
                             while (check) {
                                 if (checkColumn + x < X_SIZE && y - checkRow >=0  && checkUp + z < Z_SIZE) {
                                     if (board[x + checkColumn][y - checkRow][z + checkUp] == p) {
                                         cnt++;
                                     } else {
                                         cnt = 0;
                                         checkColumn = 1;
                                         checkRow = 1;
                                         checkUp = 1;
                                         break;
                                     }
                                 }
                                 checkColumn += 1;
                                 checkRow += 1;
                                 checkUp += 1;

                                 if (checkColumn == X_SIZE-1  || checkRow == 0 || checkUp == Z_SIZE - 1) {
                                     check = false;
                                     break;

                                 }
                                 if (cnt >= 5) return true;
                             }
                         }
                         if (cnt >= 5) return true;
                         cnt = 0;
                         checkColumn = 1;
                         checkRow = 1;
                         checkUp = 1;
                     }
                     if (cnt >= 5) return true;
                     cnt = 0;
                     checkColumn = 1;
                     checkRow = 1;
                     checkUp = 1;
                 }
             }

         // diagnal across  - 4
         System.out.println("kkkkkkkkkkkkkkk");
         cnt = 0;
         checkColumn = 1;
         checkRow = 1;
         checkUp = 1;
         flag = false;
         check = false;
             for (z = 0; z < Z_SIZE; z++) {
                 for (x = 0; x < X_SIZE; x++) {
                     for (y = 0; y < Y_SIZE; y++) {
                         if (board[x][y][z] == p) {
                             cnt++;
                             check = true;
                             while (check) {
                                 if (x-checkColumn  >=0 && y-checkRow + y >=0  && z+checkUp < Z_SIZE) {
                                     if (board[x - checkColumn][y - checkRow][z + checkUp] == p) {
                                         cnt++;
                                     } else {
                                         cnt = 0;
                                         checkColumn = 1;
                                         checkRow = 1;
                                         checkUp = 1;
                                         break;
                                     }
                                 }
                                 checkColumn += 1;
                                 checkRow += 1;
                                 checkUp += 1;

                                 if (checkColumn == 0  || checkRow == 0 || checkUp == Z_SIZE-1) {
                                     check = false;
                                     break;

                                 }
                                 if (cnt >= 5) return true;
                             }
                         }
                         if (cnt >= 5) return true;
                         cnt = 0;
                         checkColumn = 1;
                         checkRow = 1;
                         checkUp = 1;
                     }
                     if (cnt >= 5) return true;
                     cnt = 0;
                     checkColumn = 1;
                     checkRow = 1;
                     checkUp = 1;
                 }
             }


         System.out.println("ggggggggg");
        return false;

     }


    private char[][][] getBoard() {
        return board;
    }

    public char getLocation(int x, int y, int z)
    {
        return board[z][y][x];
    }
    public char getLocation(Location l)
    {
        return board[l.getZ()][l.getY()][l.getX()];
    }
    public void reset()
    {
        winner = PLAYING;
          for(int x = 0; x< X_SIZE; x++)
        {
            for(int y = 0; y< Y_SIZE; y++)
            {
                for(int z = 0; z< Z_SIZE; z++)
                {
                    board[x][y][z]= EMPTY;
                }
            }
        }
    }
    public void draw(Graphics bg)
    {
            repaint();

    }




    public void paintPuzzle()
    {
        int p=0;
        int q=0;

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

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

    public boolean checkWin()
    {
        return false;
    }


}
