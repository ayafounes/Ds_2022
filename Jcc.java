// Classe Film
class Film {
    protected String titre;
    protected String realisateur;
    protected String pays;
    protected int duree;
    protected int nb_places;

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    public String toString() {
        return titre + " de " + realisateur + " (" + pays + ") - " + duree + " min.";
    }

    public Film(String titre, String r, String p, int d) {
        this.titre = titre;
        this.realisateur = r;
        this.pays = p;
        this.duree = d;
    }

    public float totalVenteBillets(int nbPlacesEtudiants) {
        int tarif_etud = 2;
        int tarif_pub = 3;
        int nbPlacesPublic = nb_places - nbPlacesEtudiants;
        return (tarif_etud * nbPlacesEtudiants) + (tarif_pub * nbPlacesPublic);
    }
}

// Classe Documentaire
class Documentaire extends Film {
    private String sujet;
    private final float tarif = 2;

    public Documentaire(String titre, String realisateur, String pays, int duree, String sujet) {
        super(titre, realisateur, pays, duree);
        this.sujet = sujet;
    }

    @Override
    public String toString() {
        return super.toString() + " - Sujet : " + sujet;
    }

    @Override
    public float totalVenteBillets(int nbPlacesEtudiants) {
        return nb_places * tarif;
    }
}

// Classe principale Jcc avec la méthode main
public class Jcc {
    private Film[] competition;
    private int annee;
    private final int nbFMAX = 30;
    private int nbF = 0;

    public Jcc(int taille, int annee) {
        this.competition = new Film[Math.min(taille, nbFMAX)];
        this.annee = annee;
    }

    public void ajoutFilm(Film f) {
        if (nbF < competition.length) {
            competition[nbF++] = f;
        } else {
            System.out.println("La compétition est complète.");
        }
    }

    public void listeFilmsJcc() {
        for (int i = 0; i < nbF; i++) {
            System.out.println(competition[i].toString());
        }
    }

    public float totalVenteBilletsJcc(int[] nbPlacesEtudiants) {
        float r = 0;
        for (int i = 0; i < nbF; i++) {
            r += competition[i].totalVenteBillets(nbPlacesEtudiants[i]);
        }
        return r;
    }

    public static void main(String[] args) {
        Jcc j = new Jcc(2, 2021);

        Documentaire f1 = new Documentaire("Le dernier refuge", "Ousman", "Mali", 86, "La guerre civile");
        f1.setNb_places(30);

        Film f2 = new Film("Insurrection", "Jilani Saadi", "Tunisie", 105);
        f2.setNb_places(45);

        j.ajoutFilm(f1);
        j.ajoutFilm(f2);

        System.out.println("Les films en compétition JCC 2021 :");
        j.listeFilmsJcc();

        int[] nbPlacesEtudiants = {9, 17};
        float totalVentes = j.totalVenteBilletsJcc(nbPlacesEtudiants);
        System.out.println("Montant total des ventes de billets JCC 2021 : " + totalVentes + " DT");
    }
}
