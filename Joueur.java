import java.awt.*;
import java.util.*;

public class Joueur
{
    int x,y;
    int numero;
    int colonne, ligne;
    int xdir, ydir;
    int larg;
    int vitesse;
    boolean peutPoser;
    public LinkedList<Bombe> listeBombes;
    enum direction {HAUT, BAS, GAUCHE, DROITE};
    direction dir;
    //joueur 1
    Image perso1face, perso1gauche, perso1droite, perso1dos;
    Image perso2face, perso2gauche, perso2droite, perso2dos;
    Image perso1d0, perso1d1, perso1f0, persof1, perso1g0, perso1g1;

    //joueur 2 
    //Image perso2;

    public Joueur(int x, int y, int numero)
    {
        this.x = x;
        this.y = y;
        this.numero = numero;
        xdir = 0;
        ydir = 0;
        vitesse = 3;
        larg = 30;
        peutPoser = true;
        dir = direction.BAS;
        listeBombes = new LinkedList<Bombe>();
        //perso1 = Toolkit.getDefaultToolkit().getImage("perso1.png");


        perso1face = Toolkit.getDefaultToolkit().getImage("perso1face.png");
        perso1dos = Toolkit.getDefaultToolkit().getImage("perso1dos.png");
        perso1gauche = Toolkit.getDefaultToolkit().getImage("perso1gauche.png");
        perso1droite = Toolkit.getDefaultToolkit().getImage("perso1droite.png");

        perso2face = Toolkit.getDefaultToolkit().getImage("perso2face.png");
        perso2dos = Toolkit.getDefaultToolkit().getImage("perso2dos.png");
        perso2gauche = Toolkit.getDefaultToolkit().getImage("perso2gauche.png");
        perso2droite = Toolkit.getDefaultToolkit().getImage("perso2droite.png");
    }
    
    public void bouge()
    {
        colonne = ((x+larg/2)/50)-1;
        ligne = ((y+larg/2)/50)-1;
        x += vitesse*xdir;
        y += vitesse*ydir;

        //horizontal
        if (x < 50)
            x = 50;
        else if (x + larg > 700)
            x = 700 - larg;
        
        //vertical
        if (y < 50)
            y = 50;
        else if (y + larg> 600)
            y = 600 - larg;
    }

    public void dessine(Graphics g, FenetreJeu fen)
    {
        //joueur 1
        if (numero == 1)
        {
            switch (dir)
            {
                case HAUT:
                    g.drawImage(perso1dos,x,y,fen);
                break;
                case BAS:
                    g.drawImage(perso1face,x,y,fen);
                break;
                case GAUCHE:
                if (ydir == 0)
                    g.drawImage(perso1gauche,x,y,fen);
                break;
                case DROITE:
                    g.drawImage(perso1droite,x,y,fen);
                break;
            }
        }
            
        
        //joueur 2
        else if (numero == 2)
        {
            switch (dir)
            {
                case HAUT:
                    g.drawImage(perso2dos,x,y,fen);
                break;
                case BAS:
                    g.drawImage(perso2face,x,y,fen);
                break;
                case GAUCHE:
                if (ydir == 0)
                    g.drawImage(perso2gauche,x,y,fen);
                break;
                case DROITE:
                    g.drawImage(perso2droite,x,y,fen);
                break;
            }
        }
    }
    

    public void collision(Cellule liste[][])
    {
        for (Bombe b : listeBombes)
        {
            if ((b.colonne != colonne || b.ligne != ligne) && b.chrono <= 100)
            {
                liste[b.ligne][b.colonne].symb = "b";
            }
        }
        //TERRE : BORDURE VERTICALES
        if (colonne == 0 || colonne == 12)
        {
            //terre
            if (ligne > 0)
            {
                if (liste[ligne-1][colonne].symb == "t" || liste[ligne-1][colonne].symb == "b")
                {
                    if (y < (liste[ligne-1][colonne].y+liste[ligne-1][colonne].larg))
                        y = liste[ligne-1][colonne].y+liste[ligne-1][colonne].larg;
                }
            }
            if (ligne <10)
            {
                if (liste[ligne+1][colonne].symb == "t" || liste[ligne+1][colonne].symb == "b")
                {
                    if (y+larg > liste[ligne+1][colonne].y)
                    y = liste[ligne+1][colonne].y - larg;
                }
            }
        }

        //TERRE : BORDURES HORIZONTALES
        if (ligne == 0 || ligne == 10)
        {   
            if (colonne > 0)
            {
                if (liste[ligne][colonne-1].symb == "t" || liste[ligne][colonne-1].symb == "b")
                {
                    if (x < (liste[ligne][colonne-1].x+liste[ligne][colonne-1].larg))
                        x = liste[ligne][colonne-1].x+liste[ligne][colonne-1].larg;
                }
            }

            if (colonne <10)
            {
                if (liste[ligne][colonne+1].symb == "t" || liste[ligne][colonne+1].symb == "b")
                {
                    if (x+larg > liste[ligne][colonne+1].x)
                        x = liste[ligne][colonne+1].x - larg;
                }
            }
        }

       //MUR : BORDURES VERTICALES
        if (ligne == 0)
        {
            if (liste[1][colonne].symb == "#" || liste[1][colonne].symb == "t" || liste[1][colonne].symb == "b")
            {
                if (y+larg > liste[1][colonne].y)
                    y = liste[1][colonne].y - larg;
            }
        }
        else if (ligne == 10)
        {
            if (liste[9][colonne].symb == "#"|| liste[9][colonne].symb == "t"|| liste[9][colonne].symb == "b")
            {
                if (y < (liste[9][colonne].y+liste[9][colonne].larg))
                    y = (liste[9][colonne].y+liste[9][colonne].larg);
            }
        }

        //MUR : BORDURES HORIZONTALES
        if(colonne == 0)
        {
            //mur
            if (liste[ligne][1].symb == "#"|| liste[ligne][1].symb == "t"|| liste[ligne][1].symb == "b")
            {
                if (x+larg > liste[ligne][1].x)
                    x = liste[ligne][1].x - larg;
            }
        }

        else if (colonne == 12)
        {
            if (liste[ligne][11].symb == "#"|| liste[ligne][11].symb == "t"|| liste[ligne][11].symb == "b")
            {
                if (x < (liste[ligne][11].x + liste[ligne][11].larg))
                    x = (liste[ligne][11].x + liste[ligne][11].larg);
            }
        }
        
        //MUR ET TERRE : RESTE DU PLATEAU
        if (ligne-1 >=0 && colonne-1 >=0 && ligne+1 <=10 && colonne +1 <= 12)
        {
            //collision verticale
            if (liste[ligne-1][colonne].symb == "#"|| liste[ligne-1][colonne].symb == "t"|| liste[ligne-1][colonne].symb == "b")
            {
                if (y < (liste[ligne-1][colonne].y+liste[ligne-1][colonne].larg))
                    y = (liste[ligne-1][colonne].y+liste[ligne-1][colonne].larg);
            }

            if (liste[ligne+1][colonne].symb == "#"|| liste[ligne+1][colonne].symb == "t"|| liste[ligne+1][colonne].symb == "b")
            {
                if (y+larg > liste[ligne+1][colonne].y)
                    y = liste[ligne+1][colonne].y - larg;
            }

            //collision horizontale
            if (liste[ligne][colonne-1].symb == "#"|| liste[ligne][colonne-1].symb == "t"|| liste[ligne][colonne-1].symb == "b")
            {
                if (x < (liste[ligne][colonne-1].x + liste[ligne][colonne-1].larg))
                    x = (liste[ligne][colonne-1].x + liste[ligne][colonne-1].larg);
            }

            if (liste[ligne][colonne+1].symb == "#"|| liste[ligne][colonne+1].symb == "t"|| liste[ligne][colonne+1].symb == "b")
            {
                if (x+larg > liste[ligne][colonne+1].x)
                    x = liste[ligne][colonne+1].x - larg;
            }
        }
    }

    public void poseBombe(Cellule liste[][])
    {
        if (peutPoser)
        {
            listeBombes.add(new Bombe(liste[ligne][colonne].x+10, liste[ligne][colonne].y+10));
            peutPoser = false;
        }
    }

    public boolean checkMort(Cellule liste[][])
    {
        if (liste[ligne][colonne].symb == "o")
        {
            return true;
        }

        else
            return false;
    }
}