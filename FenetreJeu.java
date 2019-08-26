import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.Timer;
import java.util.*;
import java.io.*;
import sun.audio.*;

public class FenetreJeu extends JFrame implements KeyListener, ActionListener
{
    //initialisation du plateau
    // 0 : rien, # : mur, o : explosion, t : terre, b : bombe
    String[][] plateau = {
    {"0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"0","#","0","#","0","#","0","#","0","#","0","#","0"},
    {"0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"0","#","0","#","0","#","0","#","0","#","0","#","0"},
    {"0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"0","#","0","#","0","#","0","#","0","#","0","#","0"},
    {"0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"0","#","0","#","0","#","0","#","0","#","0","#","0"},
    {"0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"0","#","0","#","0","#","0","#","0","#","0","#","0"},
    {"0","0","0","0","0","0","0","0","0","0","0","0","0"}};
    
    ArrayList<Joueur> listeJoueurs; 
    Cellule liste[][];
    Joueur j1, j2;
    Timer tic;
    FenetreDessin fenDessin;
    int optionJoueur, joueurGagnant, chronoAttente;
    boolean lanceChrono;

    public FenetreJeu()
    {
        setTitle("BOMBERMAN");
        setLocation(400,120);
        setSize(750,650);

        //paramètres initiaux
        lanceChrono = false;
        chronoAttente = 0;
        optionJoueur = -1;

        //initialisation de la liste de joueurs
        listeJoueurs = new ArrayList<Joueur>();
        j1 = new Joueur(65,55,1);
        j2 = new Joueur(664,563,2);
        listeJoueurs.add(j1);
        listeJoueurs.add(j2);

        //initialisation de la liste de Cellules
        liste = new Cellule[11][13];
        for (int i=0; i<11; i++)
        {
            for (int j=0; j<13;j++)
            {
                liste[i][j] = new Cellule(i,j, plateau);
            }
        }

        //initialisation du terrain
        //définition des cases interdites (joueurs + alentours direct)
        liste[1][0].interditTerre = true;
        liste[9][12].interditTerre = true;
        for (int i=0; i<=2; i++)
        {
            liste[0][i].interditTerre = true;
            liste[10][10+i].interditTerre = true;
        }

        for (int i=0; i<11; i++)
        {
            for (int j=0; j<13;j++)
            {
                double rng = Math.random();
                if (liste[i][j].symb != "#")
                {
                    if (liste[i][j].interditTerre)
                        continue;
                    if (rng < 0.5)
                    {
                        liste[i][j].symb = "t";
                    }
                }
            }
        }      
        //gestion de l'affichage
        fenDessin = new FenetreDessin(listeJoueurs, liste, this);
        add(fenDessin);

        //initialisation du chrono
        tic = new Timer(1000/60,this);
        tic.start();

        addKeyListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //tic
    public void actionPerformed(ActionEvent e)
    {
        if (lanceChrono)
            chronoAttente++;
        fenetreFinDePartie(chronoAttente);

        //gestion des bombes
        for (int k = 0; k<listeJoueurs.size(); k++)
        {
            for (int i=0;i<listeJoueurs.get(k).listeBombes.size();i++)
            {
                listeJoueurs.get(k).listeBombes.get(i).ticTac();
    
                //la bombe explose
                if (listeJoueurs.get(k).listeBombes.get(i).chrono == 100)
                {
                    jouerSon("explosion.wav");
                    listeJoueurs.get(k).listeBombes.get(i).boum(liste);
                    listeJoueurs.get(k).listeBombes.remove(listeJoueurs.get(k).listeBombes.get(i));
                    listeJoueurs.get(k).peutPoser = true;
                }
            }
        }

        //gestion des explosions
        for (int i=0; i<11; i++)
        {
            for (int j=0; j<13;j++)
            {
                if  (liste[i][j].symb == "o")
                {
                    liste[i][j].tempsExplo++;

                    if (liste[i][j].tempsExplo == 50)
                    {
                        liste[i][j].symb = "0";
                        liste[i][j].tempsExplo = 0;
                    }
                }
            }
        }

        //gestion des joueurs
        for (int i = 0; i<listeJoueurs.size(); i++)
        {
            listeJoueurs.get(i).bouge();
            listeJoueurs.get(i).collision(liste);

            if (listeJoueurs.get(i).checkMort(liste))
            {
                lanceChrono = true;
                gameOver(i);
            }
        }
        
        //partie terminée, le joueur veut rejouer
        if (optionJoueur == 0)
        {
            setVisible(false);
            FenetreJeu fen = new FenetreJeu();
            optionJoueur = -1;
        }
                
        //partie terminée, le joueur veut quitter
        else if (optionJoueur == 2)
        {
            setVisible(false);
        }
        fenDessin.repaint();
    } 

    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        {
            switch(keyCode) 
            { 
                //JOUEUR 1
                case KeyEvent.VK_UP:
                j1.dir = j1.dir.HAUT;
                j1.ydir = -1;
                j1.xdir = 0;
                    break;
                case KeyEvent.VK_DOWN:
                j1.dir = j1.dir.BAS;
                j1.ydir = 1;
                j1.xdir = 0; 
                    break;
                case KeyEvent.VK_LEFT:
                j1.dir = j1.dir.GAUCHE;
                j1.ydir = 0;
                j1.xdir = -1;
                    break;
                case KeyEvent.VK_RIGHT :
                j1.dir = j1.dir.DROITE;
                j1.ydir = 0;
                j1.xdir = 1;
                    break;
                case KeyEvent.VK_SPACE :
                if (j1.peutPoser)
                    jouerSon("bombePosee.wav");
                j1.poseBombe(liste);
                    break;

                //JOUEUR 2
                case 90: //Z
                j2.dir = j2.dir.HAUT;
                j2.ydir = -1;
                j2.xdir = 0;
                    break;     
                case 83: //S
                j2.dir = j2.dir.BAS;
                j2.ydir = 1;
                j2.xdir = 0; 
                    break;
                case 81: //Q
                j2.dir = j2.dir.GAUCHE;
                j2.ydir = 0;
                j2.xdir = -1;
                    break;    
                case 68: //D
                j2.dir = j2.dir.DROITE;
                j2.ydir = 0;
                j2.xdir = 1;
                    break;   
                case 16:
                if (j2.peutPoser)
                    jouerSon("bombePosee.wav");
                j2.poseBombe(liste);
                break;
             }
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        switch(keyCode) 
        { 
            case KeyEvent.VK_UP:
            j1.ydir = 0;
                break;
            case KeyEvent.VK_DOWN:
            j1.ydir = 0; 
                break;
            case KeyEvent.VK_LEFT:
            j1.xdir = 0;
                break;
            case KeyEvent.VK_RIGHT :
            j1.xdir = 0;
                break;
            
            //JOUEUR 2
            case 90:
            j2.ydir = 0;
                break;     
            case 83:
            j2.ydir = 0;
                break;
            case 81:
            j2.xdir = 0;
                break;    
            case 68:
            j2.xdir = 0;
                break;                
         }
    }

    //game over
    public void gameOver(int index)
    {
        if (index==0)
            joueurGagnant = 2;
        else if (index==1)
            joueurGagnant = 1;
        listeJoueurs.remove(index);
    }

    public void fenetreFinDePartie(int chronoAttente)
    {
        if (chronoAttente == 50)
            optionJoueur = JOptionPane.showConfirmDialog(null,"Le joueur "+joueurGagnant+" a gagné !", "fin du jeu", 2);
    }

    //gestion du son
    public void jouerSon(String path)
    {
        InputStream son;
        try
        {
            son = new FileInputStream(new File(path));
            AudioStream audio = new AudioStream(son);
            AudioPlayer.player.start(audio);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erreur son");
        }
    }

    //méthode surchargée
    public void keyTyped(KeyEvent e){};
}