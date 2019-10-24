package model;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chariot { //alimenté par le chargeur
    private int poidsMax, poidsCourant, nbMax, nbCourant;
    private boolean plein;
    private ArrayList<AleaObjet> elements;

    //pr la question 4 on peut se passer de ces lignes là en utilisant simplement synchronized
    // mais ça ne sera pas extensible pour nous aider a faire le déchargement
    private Lock lock;
    private Condition nonPlein; //c'est une file d'attente

    public Chariot(int poidsMax, int nbMax) {
        this.poidsMax = poidsMax;
        this.nbMax = nbMax;
        lock = new ReentrantLock();
        nonPlein = lock.newCondition();
        elements = new ArrayList<AleaObjet>();
    }

    public void attendreVide() throws InterruptedException{
        try{
            lock.lock();
            while(plein){
                nonPlein.await(); //qd on se met en attente, on libere automatiquement le lock
            }
        }finally{
            lock.unlock();
        }

    }


    //critique donc on lock
    public void setPlein() throws InterruptedException{
        try{
            lock.lock();
            plein = true;
        }finally {
            lock.unlock();
        }
    }


    //le lock et unlock est automatiquement fait dans le synchronized mais vu que nous creeons notre propre systeme
    //de synchronisation, on doit faire des lock/unlock
    public boolean deposer(AleaObjet aDeposer) throws InterruptedException{
        try{
            lock.lock();
            if((poidsCourant + aDeposer.getPoids() < poidsMax) && (nbCourant < nbMax)){
                poidsCourant = poidsCourant + aDeposer.getPoids();
                nbCourant++;
                elements.add(aDeposer);
                System.out.println("Depot d'un objet de poids "+ aDeposer.getPoids());
                System.out.println("Poids total : "+ poidsCourant + ", nombre total : "+ nbCourant);
                return true;
            }else{
                return false;
            }
        }finally{
            lock.unlock();
        }
    }

    /*
    public boolean deposer(AleaObjet aDeposer) {
        if(aDeposer.getPoids() <= poidsMax)
            elements.add(aDeposer);
        else
            return false;
        return false;
    }*/

    //opération d'attente à mettre ici
}
