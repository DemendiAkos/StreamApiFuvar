package hu.petrik.streamapifuvar;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        FuvarSzolgaltato fuvarSzolgaltato = new FuvarSzolgaltato("fuvar.csv");


//        for (Fuvar f : fuvarSzolgaltato.fuvarok) {
//            System.out.println(f);
//        }


        System.out.println("Fuvarok száma: " + fuvarSzolgaltato.fuvarSzam());
        System.out.println("Bevétel: " + fuvarSzolgaltato.bevetel(6185));
        System.out.println("Össz távolság: " + fuvarSzolgaltato.osszTavolsag());
        System.out.println("Leghosszabb fuvar időben: " + fuvarSzolgaltato.leghosszabbFuvar());
        System.out.println("Legbőkezűbb fuvar: " + fuvarSzolgaltato.legBokezubbFuvar());
        System.out.println("Össz távolság egy futarként: " + fuvarSzolgaltato.osszTavolsagEgyFuvar(4261));
        System.out.println("Hibás Fuvarok: ");
        for (String f : fuvarSzolgaltato.hibasFuvarok()) {
            System.out.println(f);
        }
        System.out.println("Letezik e a Fuvar: " + fuvarSzolgaltato.letezikE(1452));
        System.out.println("Top 3 legrövidebb fuvar: ");
        for (Fuvar f : fuvarSzolgaltato.topHaromLegrovidebbFuvarok()) {
            System.out.println(f);
        }

        System.out.println("Karácsonyi fuvarok: ");
        for (Fuvar f : fuvarSzolgaltato.karacsonyiFuvarok()) {
            System.out.println(f);
        }

        System.out.println("Fuvarok borravaló aránya 31-én: " + fuvarSzolgaltato.szilveszteriBorravaloArany());
    }
}