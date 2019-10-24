package main;

import model.AleaObjet;
import model.AleaStock;
import model.Chargeur;
import model.Chariot;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        //AleaObjet ao = new AleaObjet(5,10);
        AleaStock as = new AleaStock(5);
        as.remplir(5,10);

        Chariot chariot = new Chariot(20,15);
        Chargeur c = new Chargeur(as,chariot);

        c.run();

    }
}
