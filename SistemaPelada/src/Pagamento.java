public class Pagamento {
    private double valorQuadra = 120.00;
    private double valorPorJogador = 0;
    private String statusQuadra = "BLOQUEADA - AGUARDANDO PAGAMENTO";
    
    // Variáveis que definem as informações da Quadra e do Jogo
    private String nomeQuadra = "Arena Central BH";
    private String enderecoQuadra = "Av. Raja Gabáglia, 2000 - Estoril";
    private String horarioJogo = "Sábado, das 18:00 às 19:30";

    // Getters para leitura segura
    public double getValorQuadra() { return valorQuadra; }
    public double getValorPorJogador() { return valorPorJogador; }
    public String getStatusQuadra() { return statusQuadra; }
    public String getNomeQuadra() { return nomeQuadra; }
    public String getEnderecoQuadra() { return enderecoQuadra; }
    public String getHorarioJogo() { return horarioJogo; }

    public void setStatusQuadra(String statusQuadra) {
        this.statusQuadra = statusQuadra;
    }

public void calcularRateio(int totalJogadores, int totalEspera) {
    int totalGeral = totalJogadores + totalEspera;
    if (totalGeral > 0) {
        this.valorPorJogador = this.valorQuadra / totalGeral;
    } else {
        this.valorPorJogador = 0;
    }
}
}