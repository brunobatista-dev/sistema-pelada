import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao = -1;

        // Criando as instâncias dos objetos de forma isolada
        Jogador jogadorAtual = new Jogador();
        Time futebol = new Time();
        Pagamento financeiro = new Pagamento();

        while (opcao != 0) {
            System.out.println("\n===== SISTEMA DE PELADA =====");
            
            // Exibição do jogador atual usando a estrutura de Getters
            if (jogadorAtual.getNome().equals("")) {
                System.out.println("Jogador Atual: [Nenhum - Vá na opção 1]");
            } else {
                System.out.println("Jogador Atual: " + jogadorAtual.getNome());
            }
            
            System.out.println("Status da Quadra: " + financeiro.getStatusQuadra());
            
            System.out.println("----------------------------------------");
            System.out.println("[PAINEL DO JOGADOR]");
            System.out.println("1 - Informar Nome / Apelido");
            System.out.println("2 - Escolher TIME A (" + futebol.getTimeA() + "/" + futebol.getLimite() + ")");
            System.out.println("3 - Escolher TIME B (" + futebol.getTimeB() + "/" + futebol.getLimite() + ")");
            System.out.println("4 - Entrar na Lista de Espera (" + futebol.getListaEspera() + "/" + futebol.getLimiteEspera() + ")");
            System.out.println("5 - Ver Valor por Jogador (Rateio)");
            System.out.println("----------------------------------------");
            System.out.println("[PAINEL DO ORGANIZADOR]");
            System.out.println("6 - Confirmar Pagamento (Liberar Quadra)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = leitor.nextInt();
            leitor.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite seu nome ou apelido: ");
                    jogadorAtual.setNome(leitor.nextLine());
                    System.out.println("-> Sucesso! " + jogadorAtual.getNome() + " foi registrado.");
                    break;

                case 2:
                    if (futebol.adicionarTimeA()) {
                        System.out.println("-> Sucesso! Você entrou no Time A.");
                    } else {
                        System.out.println("-> Erro: O Time A está lotado!");
                    }
                    break;

                case 3:
                    if (futebol.adicionarTimeB()) {
                        System.out.println("-> Sucesso! Você entrou no Time B.");
                    } else {
                        System.out.println("-> Erro: O Time B está lotado!");
                    }
                    break;

                case 4:
                    if (futebol.adicionarListaEspera()) {
                        System.out.println("-> Sucesso! Você foi para a lista de espera.");
                    } else {
                        System.out.println("-> Erro: A Lista de Espera também está lotada!");
                    }
                    break;

                case 5:
                    int totalNoJogo = futebol.getTimeA() + futebol.getTimeB();

                    if (totalNoJogo > 0) {
                        financeiro.calcularRateio(totalNoJogo);
                        
                        System.out.println("\n--- VALOR DO RATEIO ---");
                        System.out.println("Valor da Quadra: R$ " + financeiro.getValorQuadra());
                        System.out.println("Jogadores nos times: " + totalNoJogo);
                        System.out.println("Cada um vai pagar: R$ " + financeiro.getValorPorJogador());
                    } else {
                        System.out.println("-> Não há jogadores nos times para dividir.");
                    }
                    break;

                case 6:
                    financeiro.setStatusQuadra("LIBERADA PARA O JOGO");
                    System.out.println("-> Pagamento confirmado! Quadra liberada pelo Organizador.");
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        leitor.close();
    }
}