import java.util.ArrayList;
import java.util.Random;

public class Time {
    private ArrayList<Jogador> listaTimeA = new ArrayList<>();
    private ArrayList<Jogador> listaTimeB = new ArrayList<>();
    private ArrayList<Jogador> listaEspera = new ArrayList<>();
    
    private int limite = 6; 
    private int limiteEspera = 3; 

    public ArrayList<Jogador> getListaTimeA() { return listaTimeA; }
    public ArrayList<Jogador> getListaTimeB() { return listaTimeB; }
    public ArrayList<Jogador> getListaEspera() { return listaEspera; }
    
    public int QtdTimeA() { return listaTimeA.size(); }
    public int QtdTimeB() { return listaTimeB.size(); }
    public int QtdListaEspera() { return listaEspera.size(); }
    
    public int getLimite() { return limite; }
    public int getLimiteEspera() { return limiteEspera; }

    public ArrayList<String> getNomesTimeA() { return extrairNomes(listaTimeA); }
    public ArrayList<String> getNomesTimeB() { return extrairNomes(listaTimeB); }
    public ArrayList<String> getNomesEspera() { return extrairNomes(listaEspera); }

    private ArrayList<String> extrairNomes(ArrayList<Jogador> lista) {
        ArrayList<String> nomes = new ArrayList<>();
        for (Jogador j : lista) {
            nomes.add(j.getNome());
        }
        return nomes;
    }

    public String sortearTime(Jogador jogador) {
        if (this.listaTimeA.size() >= this.limite && this.listaTimeB.size() >= this.limite) {
            if (this.listaEspera.size() < this.limiteEspera) {
                this.listaEspera.add(jogador);
                return "ESPERA";
            } else {
                return "LOTADO";
            }
        }

        if (this.listaTimeA.size() < this.limite && this.listaTimeB.size() >= this.limite) {
            this.listaTimeA.add(jogador);
            return "A";
        }

        if (this.listaTimeB.size() < this.limite && this.listaTimeA.size() >= this.limite) {
            this.listaTimeB.add(jogador);
            return "B";
        }

        Random gerador = new Random();
        boolean escolha = gerador.nextBoolean();

        if (escolha) {
            this.listaTimeA.add(jogador);
            return "A";
        } else {
            this.listaTimeB.add(jogador);
            return "B";
        }
    }

    public Jogador buscarJogador(String nome) {
        for (Jogador j : listaTimeA) {
            if (j.getNome().equalsIgnoreCase(nome)) return j;
        }
        for (Jogador j : listaTimeB) {
            if (j.getNome().equalsIgnoreCase(nome)) return j;
        }
        for (Jogador j : listaEspera) {
            if (j.getNome().equalsIgnoreCase(nome)) return j;
        }
        return null;
    }

    public String removerPorFaltaDePagamento(String nome) {
        for (Jogador j : listaTimeA) {
            if (j.getNome().equalsIgnoreCase(nome)) {
                listaTimeA.remove(j);
                puxarProximoDaEspera("A");
                return "REMOVIDO_TIME_A";
            }
        }
        for (Jogador j : listaTimeB) {
            if (j.getNome().equalsIgnoreCase(nome)) {
                listaTimeB.remove(j);
                puxarProximoDaEspera("B");
                return "REMOVIDO_TIME_B";
            }
        }
        for (Jogador j : listaEspera) {
            if (j.getNome().equalsIgnoreCase(nome)) {
                listaEspera.remove(j);
                return "REMOVIDO_ESPERA";
            }
        }
        return "NAO_ENCONTRADO";
    }

    private void puxarProximoDaEspera(String timeComVaga) {
        if (!listaEspera.isEmpty()) {
            Jogador proximo = listaEspera.remove(0); // Remove e captura o primeiro elemento (índice 0)
            if (timeComVaga.equals("A")) {
                listaTimeA.add(proximo);
                System.out.println("-> [FILA DE ESPERA] " + proximo.getNome() + " subiu para o TIME A!");
            } else {
                listaTimeB.add(proximo);
                System.out.println("-> [FILA DE ESPERA] " + proximo.getNome() + " subiu para o TIME B!");
            }
        }
    }
}