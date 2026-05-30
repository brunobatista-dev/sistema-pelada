public class Time {
    private int timeA = 0;
    private int timeB = 0;
    private int listaEspera = 0;
    private int limite = 6; 
    private int limiteEspera = 3; // Limite fixo sugerido para quem está de fora

    // Métodos Getters
    public int getTimeA() { return timeA; }
    public int getTimeB() { return timeB; }
    public int getListaEspera() { return listaEspera; }
    public int getLimite() { return limite; }
    public int getLimiteEspera() { return limiteEspera; }

    // Tenta adicionar no Time A se houver vaga
    public boolean adicionarTimeA() {
        if (this.timeA < this.limite) {
            this.timeA++;
            return true; 
        }
        return false; 
    }

    // Tenta adicionar no Time B se houver vaga (BUG ANTERIOR CORRIGIDO)
    public boolean adicionarTimeB() {
        if (this.timeB < this.limite) { 
            this.timeB++;
            return true;
        }
        return false;
    }

    // Tenta adicionar na lista de espera se houver vaga dentro do limite fixo
    public boolean adicionarListaEspera() {
        if (this.listaEspera < this.limiteEspera) {
            this.listaEspera++;
            return true;
        }
        return false;
    }
}