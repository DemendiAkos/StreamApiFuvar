package hu.petrik.streamapifuvar;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FuvarSzolgaltato {

    List<Fuvar> fuvarok = new ArrayList<>();

    public FuvarSzolgaltato(String filename) throws IOException {

        BufferedReader reader = new BufferedReader(new java.io.FileReader(filename));
        reader.readLine();
        String line = reader.readLine();


        while (line != null && !line.isEmpty()) {
            String[] data = line.split(";");
            int taxiId = Integer.parseInt(data[0]);

            String indulas = data[1];
            int idotartam = Integer.parseInt(data[2]);
            double tavolsag = Double.parseDouble(data[3].replace(",", "."));
            double viteldij = Double.parseDouble(data[4].replace(",", "."));
            double borravalo = Double.parseDouble(data[5].replace(",", "."));
            String fizetesModja = data[6];

            fuvarok.add(new Fuvar(taxiId, indulas, idotartam, tavolsag, viteldij, borravalo, fizetesModja));

            line = reader.readLine();
        }

        reader.close();

    }


    public int fuvarSzam() {
        return (int) fuvarok.stream().count();
    }

    public double bevetel(int id) {
        return fuvarok.stream().filter(x -> x.getTaxiId() == id).mapToDouble(Fuvar::getViteldij).sum();
    }

    public double osszTavolsag() {
        return fuvarok.stream().mapToDouble(Fuvar::getTavolsag).sum();
    }

    public Fuvar leghosszabbFuvar() {
        return fuvarok.stream().max(Comparator.comparingDouble(Fuvar::getTavolsag)).get();
    }

    public Fuvar legBokezubbFuvar() {
        return fuvarok.stream().max(Comparator.comparingDouble(f -> f.getBorravalo() - f.getViteldij())).get();
    }

    public double osszTavolsagEgyFuvar(int id) {
        return fuvarok.stream().filter(x -> x.getTaxiId() == id).mapToDouble(Fuvar::getTavolsag).sum() * 1.6;
    }

    public List<String> hibasFuvarok() {
        return fuvarok.stream()
                .filter(x -> x.getIdotartam() > 0 && x.getViteldij() > 0 && x.getTavolsag() == 0)
                .map(x -> String.format("ID: %d, Időtartam: %d, Profit: %.2f", x.getTaxiId(), x.getIdotartam(), x.getViteldij() + x.getBorravalo()))
                .collect(Collectors.toList());
    }

    public boolean letezikE(int id) {
        return fuvarok.stream().anyMatch(x -> x.getTaxiId() == id);
    }

    public List<Fuvar> topHaromLegrovidebbFuvarok() {
        return fuvarok.stream().filter(f -> f.getTavolsag() > 0).sorted(Comparator.comparingDouble(Fuvar::getTavolsag)).limit(3).collect(Collectors.toList());
    }

    public List<Fuvar> karacsonyiFuvarok () {
        return fuvarok.stream().filter(f -> f.getIndulas()
                .contains("12-24"))
                .collect(Collectors.toList());
    }

    public double szilveszteriBorravaloArany() {
        return fuvarok.stream().filter(f -> f.getIndulas().contains("12-31"))
                .mapToDouble(Fuvar::getBorravalo)
                .sum() / fuvarok.stream().filter(f -> f.getIndulas().contains("12-31"))
                .mapToDouble(Fuvar::getViteldij)
                .sum();
    }


}
