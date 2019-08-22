import java.awt.*;

public class Cellule
{
    int i,j;
    int x,y;
    int larg;
    int tempsExplo;
    String symb;
    String[][] tab;
<<<<<<< HEAD
    Image  mur, terre, bombe;
=======
>>>>>>> 576c437943b296b973a3debcc3501572de894b84

    public Cellule(int i, int j,String[][] tab)
    {
        larg = 50;
        tempsExplo = 0;
        this.i = i;
        this.j = j;
        this.tab = tab;
        symb = tab[i][j];
        x = larg + j*larg;
        y = larg + i*larg;
<<<<<<< HEAD
        mur = Toolkit.getDefaultToolkit().getImage("mur.png");
        terre = Toolkit.getDefaultToolkit().getImage("terre.png");
        bombe = Toolkit.getDefaultToolkit().getImage("bombe.png");
    }

    public void dessine(Graphics g, FenetreJeu fen)
=======
         
    }

    public void dessine(Graphics g)
>>>>>>> 576c437943b296b973a3debcc3501572de894b84
    {
        //sans bombe
        if (symb == "0")
        {
            if (i%2 == j%2)
            {
                g.setColor(new Color(0,204,0));
                g.fillRect(x,y,larg,larg);
            }

            else 
            {
                g.setColor(new Color(0,153,0));
                g.fillRect(x,y,larg,larg);
            }
        }
        //avec bombe
        if (symb == "b")
        {
            if (i%2 == j%2)
            {
                g.setColor(new Color(0,204,0));
                g.fillRect(x,y,larg,larg);
<<<<<<< HEAD
                g.drawImage(bombe,x+10,y+10,fen);
                //g.setColor(Color.black);
                //g.fillOval(x+10,y+10,30,30);
=======
                g.setColor(Color.black);
                g.fillOval(x+10,y+10,30,30);
>>>>>>> 576c437943b296b973a3debcc3501572de894b84
            }

            else 
            {
                g.setColor(new Color(0,153,0));
                g.fillRect(x,y,larg,larg);
<<<<<<< HEAD
                g.drawImage(bombe,x+10,y+10,fen);
                //g.setColor(Color.black);
                //g.fillOval(x+10,y+10,30,30);
=======
                g.setColor(Color.black);
                g.fillOval(x+10,y+10,30,30);
>>>>>>> 576c437943b296b973a3debcc3501572de894b84
            }            
        }
                    
        else if (symb == "#")
        {
<<<<<<< HEAD
            g.drawImage(mur, x,y, fen);
=======
            g.setColor(new Color(160,160,160));
            g.fillRect(x,y,larg,larg);
>>>>>>> 576c437943b296b973a3debcc3501572de894b84
        }

        else if (symb == "o")
        {
            if (i%2 == j%2)
            {
                g.setColor(new Color(0,204,0));
                g.fillRect(x,y,larg,larg);
            }

            else 
            {
                g.setColor(new Color(0,153,0));
                g.fillRect(x,y,larg,larg);
            }
            g.setColor(Color.red);
            g.fillOval(x+10,y+10,30,30);
        }

        else if (symb == "t")
        {
<<<<<<< HEAD
            g.drawImage(terre, x,y, fen);
            //g.setColor(new Color(150,80,0));
            //g.fillRect(x,y,larg,larg);
=======
            g.setColor(new Color(150,80,0));
            g.fillRect(x,y,larg,larg);
>>>>>>> 576c437943b296b973a3debcc3501572de894b84
        }
    }
}