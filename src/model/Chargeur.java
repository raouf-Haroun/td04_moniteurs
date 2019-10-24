package model;

public class Chargeur implements Runnable{ //extraire les marchandises du stocke et les empiler sur le chariot tant que celui ci n'est pas plein
    //càd qu'on atteint ni le poids max ni le nb de marchandise max
    private AleaStock stock;
    private Chariot chariot;

    public Chargeur(AleaStock s, Chariot c) {
        this.stock = s;
        this.chariot = c;
    }

    @Override
    public void run() {
        AleaObjet aDeposer;
        boolean depotOK;

        try{
            while(!stock.estVide()){ //tant que le stock n'est pas vide
                aDeposer = stock.extraire(); //on extrait un objet
                System.out.println("Extraction d'un objet. "+aDeposer.toString());
                depotOK = chariot.deposer(aDeposer); //on le depose dans le chariot
                if(!depotOK){ //si on ne peut plus deposer alors
                    chariot.setPlein(); //le chariot est plein, on doit le décharger
                    chariot.attendreVide(); //on attend qu'il soit vider
                    depotOK = chariot.deposer(aDeposer);  //et la on depose l'objet
                    if(!depotOK){ //s'il veut tjrs pas entrer c'est qu'il est plus grand que l'objet
                        System.out.println("Cet objet n'entre pas dans le chariot");
                    }
                }
            }
            /* on signale la fin du dernier chargement, possiblement vide */
            chariot.setPlein();
        }catch(InterruptedException e){
            System.out.println("Chargeur interrompu. Terminaison");
        }

    }
}
