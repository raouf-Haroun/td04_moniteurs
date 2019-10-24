package model;

import java.util.Random;

public class AleaObjet {
    private int poids;
    private static Random r = new Random();

    public AleaObjet(int min, int max) {
        this.poids = min + r.nextInt(max - min);
    }

    public int getPoids() {
        return poids;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AleaObjet{");
        sb.append("poids=").append(poids);
        sb.append('}');
        return sb.toString();
    }
}
