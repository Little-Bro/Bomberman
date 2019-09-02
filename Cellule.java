import java.awt.*;

public class Cellule
{
    int i,j;
    int x,y;
    int larg;
    int tempsExplo;
    boolean interditTerre;
    String symb;
    String[][] tab;
    Image  mur, terre, bombe, explosion;

    public Cellule(int i, int j,String[][] tab)
    {
        larg = 50;
        tempsExplo = 0;
        this.i = i;
        this.j = j;
        this.tab = tab;
        interditTerre = false;
        symb = tab[i][j];
        x = larg + j*larg;
        y = larg + i*larg;
        mur = Toolkit.getDefaultToolkit().getImage("mur.png");
        terre = Toolkit.getDefaultToolkit().getImage("terre.png");
        bombe = Toolkit.getDefaultToolkit().getImage("bombe.png");
        explosion = Toolkit.getDefaultToolkit().getImage("explosion.png");
    }

    public void dessine(Graphics g, FenetreJeu fen)
    {
        //sans bombe
        if (symb == "0")
        {
            dessineCadrillage(g);
        }
        //avec bombe
        if (symb == "b")
        {
            dessineCadrillage(g);
            g.drawImage(bombe,x+10,y+10,fen);                      
        }
                    
        else if (symb == "#")
        {
            g.drawImage(mur, x,y, fen);
        }

        else if (symb == "o")
        {
            if (i%2 == j%2)
            {
                g.setColor(new Color(0,204,0));
                g.fillRect(x,y,larg,larg);
                g.drawImage(explosion,x+10,y+10,fen);
            }

            else 
            {
                g.setColor(new Color(0,153,0));
                g.fillRect(x,y,larg,larg);
            }
            g.setColor(Color.red); 
            g.drawImage(explosion,x+10,y+10,fen);
        }

        else if (symb == "t")
        {
            g.drawImage(terre, x,y, fen);
        }
    }

    public void dessineCadrillage(Graphics g)
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
}