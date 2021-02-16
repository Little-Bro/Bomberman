package src;

import java.awt.*;

public class Objet {
  int numero;
  int chrono;
  int x, y;
  boolean fin;

  public Objet(int numero, int x, int y) {
    this.numero = numero;
    this.x = x;
    this.y = y;
    chrono = 0;
    fin = false;
  }

  public void ticTac() {
    chrono++;
    if (chrono == 200)
      fin = true;
  }

  public void effet(Joueur j) {
    switch (numero) {
      case 1:
        System.out.println("Bonus vitesse !");
        break;
      case 2:
        System.out.println("Malus vitesse !");
        break;
      default:
        System.out.println("Un objet a été ramassé !");
        break;
    }
  }

  public void dessine(Graphics g, FenetreJeu fen) {
    g.setColor(Color.black);
    g.fillOval(x, y, 30, 30);
  }
}