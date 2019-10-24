package model;

import java.util.ArrayList;

public class AleaStock {
    private ArrayList<AleaObjet> stockO;
    private int taille;

    public AleaStock(int taille) {
        this.taille = taille;
        stockO = new ArrayList<AleaObjet>(taille);
    }

    public void remplir(int pdsMin, int pdsMax){
        for(int i=0; i< taille; i++){
            stockO.add(new AleaObjet (pdsMin, pdsMax));
        }
    }

    public synchronized boolean estVide(){
        return stockO.isEmpty();
    }

    public synchronized AleaObjet extraire(){
        return stockO.remove(0);
    }

}
