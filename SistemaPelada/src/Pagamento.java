public class Pagamento {
    private double valorQuadra = 0.0; 
    private double valorPorJogador = 0;
    private String statusQuadra = "BLOQUEADA - AGUARDANDO PAGAMENTO";
    
    private String enderecoQuadra = "[Não Informado]";
    private String dataJogo = "[Não Informada]";
    private String horarioJogo = "[Não Informado]";

    private String chavePix = "[Não Informada]";
    private String nomePix = "[Não Informado]";

    public double getValorQuadra() { return valorQuadra; }
    public double getValorPorJogador() { return valorPorJogador; }
    public String getStatusQuadra() { return statusQuadra; }
    public String getEnderecoQuadra() { return enderecoQuadra; }
    public String getDataJogo() { return dataJogo; } 
    public String getHorarioJogo() { return horarioJogo; }
    public String getChavePix() { return chavePix; } 
    public String getNomePix() { return nomePix; }   

    public void setStatusQuadra(String statusQuadra) { this.statusQuadra = statusQuadra; }
    public void setEnderecoQuadra(String enderecoQuadra) { this.enderecoQuadra = enderecoQuadra; }
    public void setDataJogo(String dataJogo) { this.dataJogo = dataJogo; } 
    public void setHorarioJogo(String horarioJogo) { this.horarioJogo = horarioJogo; }
    public void setValorQuadra(double valorQuadra) { this.valorQuadra = valorQuadra; }
    public void setChavePix(String chavePix) { this.chavePix = chavePix; } 
    public void setNomePix(String nomePix) { this.nomePix = nomePix; }     

    public void calcularRateio(int totalJogadores, int totalEspera) {
        int totalGeral = totalJogadores + totalEspera;
        if (totalGeral > 0) {
            this.valorPorJogador = this.valorQuadra / totalGeral;
        } else {
            this.valorPorJogador = 0;
        }
    }
}