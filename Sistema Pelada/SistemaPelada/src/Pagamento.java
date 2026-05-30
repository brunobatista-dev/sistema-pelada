public class Pagamento {
    private double valorQuadra = 120.00;
    private double valorPorJogador = 0;
    private String statusQuadra = "BLOQUEADA - AGUARDANDO PAGAMENTO";

    // Getters para permitir a leitura segura dos dados
    public double getValorQuadra() {
        return valorQuadra;
    }

    public double getValorPorJogador() {
        return valorPorJogador;
    }

    public String getStatusQuadra() {
        return statusQuadra;
    }

    // Setter para mudar a situação da quadra
    public void setStatusQuadra(String statusQuadra) {
        this.statusQuadra = statusQuadra;
    }

    // Método com a lógica interna para calcular a divisão do valor
    public void calcularRateio(int totalJogadores) {
        if (totalJogadores > 0) {
            this.valorPorJogador = this.valorQuadra / totalJogadores;
        }
    }
}