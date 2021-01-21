import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FenetreDessin extends JPanel {
    ArrayList<Joueur> listeJoueurs;
    Cellule liste[][];
    FenetreJeu fen;

    public FenetreDessin(ArrayList<Joueur> listeJoueurs, Cellule liste[][], FenetreJeu fen) {
        this.listeJoueurs = listeJoueurs;
        this.liste = liste;
        this.fen = fen;

        setLocation(400, 120);
        setSize(750, 650);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // affichage du plateau
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                liste[i][j].dessine(g, fen);
            }
        }

        // affichage des joueurs
        for (int i = 0; i < listeJoueurs.size(); i++) {
            listeJoueurs.get(i).dessine(g, fen);
        }

        // affichage des bombes
        for (int i = 0; i < listeJoueurs.size(); i++) {
            for (Bombe b : listeJoueurs.get(i).listeBombes) {
                b.dessine(g, fen);
            }
        }
    }
}