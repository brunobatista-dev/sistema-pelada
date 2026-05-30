public class Jogador {
    private String nome = ""; 
    private boolean pago = false; 

    public Jogador(String nome) {
        this.nome = nome;
        this.pago = false; // Todo jogador começa devendo até que se confirme o Pix
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPago() {
        return this.pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public String getStatusPagamentoTexto() {
        return this.pago ? "[PAGO]" : "[PENDENTE]";
    }
}