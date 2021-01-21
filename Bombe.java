import java.awt.*;

public class Bombe {
    int x, y;
    int colonne, ligne;
    int larg;
    int chrono;
    Image bombe;

    public Bombe(int x, int y) {
        this.x = x;
        this.y = y;
        larg = 30;
        chrono = 0;
        colonne = ((x + larg / 2) / 50) - 1;
        ligne = ((y + larg / 2) / 50) - 1;
        bombe = Toolkit.getDefaultToolkit().getImage("./images/bombe.png");
    }

    public void dessine(Graphics g, FenetreJeu fen) {
        g.drawImage(bombe, x, y, fen);
    }

    public void ticTac() {
        chrono++;
    }

    public void boum(Cellule liste[][]) {
        // bordures horizontales
        if (colonne == 0) {
            for (int i = 0; i <= 1; i++) {
                if (liste[ligne][colonne + i].symb != "#")
                    liste[ligne][colonne + i].symb = "o";
            }
        } else if (colonne == 12) {
            for (int i = -1; i <= 0; i++) {
                if (liste[ligne][colonne + i].symb != "#")
                    liste[ligne][colonne + i].symb = "o";
            }
        }
        // bordures verticales
        if (ligne == 0) {
            for (int i = 0; i <= 1; i++) {
                if (liste[ligne + i][colonne].symb != "#")
                    liste[ligne + i][colonne].symb = "o";
            }
        } else if (ligne == 10) {
            for (int i = -1; i <= 0; i++) {
                if (liste[ligne + i][colonne].symb != "#")
                    liste[ligne + i][colonne].symb = "o";
            }
        }
        // horizontal
        if (colonne != 0 && colonne != 12) {
            for (int i = -1; i <= 1; i++) {
                if (liste[ligne][colonne + i].symb != "#")
                    liste[ligne][colonne + i].symb = "o";
            }
        }
        // vertical
        if (ligne != 0 && ligne != 10) {
            for (int i = -1; i <= 1; i++) {
                if (liste[ligne + i][colonne].symb != "#")
                    liste[ligne + i][colonne].symb = "o";
            }
        }
    }
}