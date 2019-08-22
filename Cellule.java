import java.awt.*;

public class Cellule
{
    int i,j;
    int x,y;
    int larg;
    int tempsExplo;
    String symb;
    String[][] tab;

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
         
    }

    public void dessine(Graphics g)
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
                g.setColor(Color.black);
                g.fillOval(x+10,y+10,30,30);
            }

            else 
            {
                g.setColor(new Color(0,153,0));
                g.fillRect(x,y,larg,larg);
                g.setColor(Color.black);
                g.fillOval(x+10,y+10,30,30);
            }            
        }
                    
        else if (symb == "#")
        {
            g.setColor(new Color(160,160,160));
            g.fillRect(x,y,larg,larg);
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
            g.setColor(new Color(150,80,0));
            g.fillRect(x,y,larg,larg);
        }
    }
}