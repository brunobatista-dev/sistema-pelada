import java.util.Scanner;
import java.util.ArrayList; // Importação necessária para a lista temporária de seleção

public class App {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        String opcao = ""; 

        Time futebol = new Time();
        Pagamento financeiro = new Pagamento();

        while (!opcao.equals("0")) {
            System.out.println("\n===== SISTEMA DE PELADA =====");
            System.out.println("Status da Quadra: " + financeiro.getStatusQuadra());
            
            System.out.println("----------------------------------------");
            System.out.println("[PAINEL DO JOGADOR]");
            System.out.println("1 - Registrar Jogador e Entrar no Sorteio");
            System.out.println("2 - Ver Valor por Jogador (Rateio)");
            System.out.println("----------------------------------------");
            System.out.println("[SITUACAO ATUAL DA PELADA]");
            System.out.println("Time A (" + futebol.QtdTimeA() + "/" + futebol.getLimite() + "): " + futebol.getNomesTimeA());
            System.out.println("Time B (" + futebol.QtdTimeB() + "/" + futebol.getLimite() + "): " + futebol.getNomesTimeB());
            System.out.println("Fila de Espera (" + futebol.QtdListaEspera() + "/" + futebol.getLimiteEspera() + "): " + futebol.getNomesEspera());
            System.out.println("----------------------------------------");
            System.out.println("[PAINEL DO ORGANIZADOR]");
            System.out.println("3 - Liberar Quadra");
            System.out.println("4 - Gerenciar Pagamento (Confirmar ou Remover)");
            System.out.println("5 - Gerar Texto WhatsApp");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = leitor.nextLine(); 

            switch (opcao) {
                case "1":
                    System.out.print("Digite seu nome ou apelido: ");
                    String nome = leitor.nextLine();
                    
                    if (nome.trim().equals("")) {
                        System.out.println("-> Erro: O nome nao pode ser vazio!");
                        break;
                    }
                    
                    Jogador novoJogador = new Jogador(nome);
                    System.out.println("-> Sucesso! " + novoJogador.getNome() + " foi registrado.");
                    
                    System.out.println("-> Realizando sorteio automatico...");
                    String resultadoSorteio = futebol.sortearTime(novoJogador);
                    
                    if (resultadoSorteio.equals("A")) {
                        System.out.println("-> O sistema sorteou! " + novoJogador.getNome() + " entrou no TIME A.");
                    } else if (resultadoSorteio.equals("B")) {
                        System.out.println("-> O sistema sorteou! " + novoJogador.getNome() + " entrou no TIME B.");
                    } else if (resultadoSorteio.equals("ESPERA")) {
                        System.out.println("-> Times lotados! " + novoJogador.getNome() + " foi direcionado para a Fila de Espera.");
                    } else {
                        System.out.println("-> Erro: Nao ha mais vagas disponiveis no sistema hoje!");
                    }
                    break;

                case "2": 
                    int totalNosTimes = futebol.QtdTimeA() + futebol.QtdTimeB();
                    int totalNaEspera = futebol.QtdListaEspera();

                    if ((totalNosTimes + totalNaEspera) > 0) {
                        financeiro.calcularRateio(totalNosTimes, totalNaEspera);
                        
                        System.out.println("\n--- VALOR DO RATEIO ---");
                        System.out.println("Valor da Quadra: R$ " + financeiro.getValorQuadra());
                        System.out.println("Jogadores pagantes (Times + Espera): " + (totalNosTimes + totalNaEspera));
                        System.out.println("Cada um vai pagar: R$ " + financeiro.getValorPorJogador());
                    } else {
                        System.out.println("-> Nao ha jogadores no sistema para dividir.");
                    }
                    break;

                case "3": 
                    financeiro.setStatusQuadra("LIBERADA PARA O JOGO");
                    System.out.println("\n-> Status da quadra alterado com sucesso!");
                    break;

                case "4": 
                    System.out.println("\n--- FLUXO DE PAGAMENTOS ---");
                    int totalPessoas = futebol.QtdTimeA() + futebol.QtdTimeB() + futebol.QtdListaEspera();
                    
                    if (totalPessoas == 0) {
                        System.out.println("-> Nao ha nenhum jogador cadastrado no sistema.");
                        break;
                    }

                    // Criamos uma lista temporária para juntar todos os jogadores na ordem da tela
                    ArrayList<Jogador> todosJogadores = new ArrayList<>();
                    int contador = 1;

                    System.out.println("Selecione o jogador pelo numero:");
                    
                    if (futebol.QtdTimeA() > 0) {
                        System.out.println("\n[Time A]");
                        for (Jogador j : futebol.getListaTimeA()) {
                            System.out.println(contador + " - " + j.getNome() + " " + j.getStatusPagamentoTexto());
                            todosJogadores.add(j);
                            contador++;
                        }
                    }
                    
                    if (futebol.QtdTimeB() > 0) {
                        System.out.println("\n[Time B]");
                        for (Jogador j : futebol.getListaTimeB()) {
                            System.out.println(contador + " - " + j.getNome() + " " + j.getStatusPagamentoTexto());
                            todosJogadores.add(j);
                            contador++;
                        }
                    }
                    
                    if (futebol.QtdListaEspera() > 0) {
                        System.out.println("\n[Fila de Espera]");
                        for (Jogador j : futebol.getListaEspera()) {
                            System.out.println(contador + " - " + j.getNome() + " " + j.getStatusPagamentoTexto());
                            todosJogadores.add(j);
                            contador++;
                        }
                    }

                    System.out.print("\nDigite o numero do jogador: ");
                    String entradaNumero = leitor.nextLine();
                    
                    try {
                        int numeroDigitado = Integer.parseInt(entradaNumero);
                        
                        // Como a exibição começa em 1, o índice na lista é (numeroDigitado - 1)
                        if (numeroDigitado >= 1 && numeroDigitado <= todosJogadores.size()) {
                            Jogador jogadorSelecionado = todosJogadores.get(numeroDigitado - 1);
                            
                            System.out.println("\nO que deseja fazer com " + jogadorSelecionado.getNome() + "?");
                            System.out.println("1 - Confirmar que ele PAGOU");
                            System.out.println("2 - Marcar que NAO PAGOU (Remover do Jogo)");
                            System.out.print("Escolha uma opcao: ");
                            String acaoPagamento = leitor.nextLine();

                            if (acaoPagamento.equals("1")) {
                                jogadorSelecionado.setPago(true);
                                System.out.println("-> Sucesso! Pagamento de " + jogadorSelecionado.getNome() + " confirmado.");
                            } else if (acaoPagamento.equals("2")) {
                                // Remove e já sobe alguém da reserva automaticamente
                                futebol.removerPorFaltaDePagamento(jogadorSelecionado.getNome());
                                System.out.println("-> " + jogadorSelecionado.getNome() + " foi removido por falta de pagamento!");
                            } else {
                                System.out.println("-> Opcao invalida. Operacao cancelada.");
                            }
                        } else {
                            System.out.println("-> Erro: Numero invalida! Escolha um numero da lista.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("-> Erro: Voce deve digitar um numero valido!");
                    }
                    break;

                case "5": 
                    int participantesTimes = futebol.QtdTimeA() + futebol.QtdTimeB();
                    int participantesEspera = futebol.QtdListaEspera();
                    
                    financeiro.calcularRateio(participantesTimes, participantesEspera);
                    
                    System.out.println("\n==================================================");
                    System.out.println("TEXTO PARA COPIAR E COLAR NO WHATSAPP:");
                    System.out.println("==================================================");
                    
                    System.out.println("CONFIRMADO! INFORMACOES DA PELADA\n");
                    System.out.println("Quadra: " + financeiro.getNomeQuadra());
                    System.out.println("Local: " + financeiro.getEnderecoQuadra());
                    System.out.println("Horario: " + financeiro.getHorarioJogo());
                    System.out.println("\n----------------------------------------");
                    
                    System.out.println("CONVOCACAO E STATUS DE PAGAMENTO:");
                    System.out.println("\n*Time A:*");
                    for (Jogador j : futebol.getListaTimeA()) {
                        System.out.println("- " + j.getNome() + " " + j.getStatusPagamentoTexto());
                    }
                    
                    System.out.println("\n*Time B:*");
                    for (Jogador j : futebol.getListaTimeB()) {
                        System.out.println("- " + j.getNome() + " " + j.getStatusPagamentoTexto());
                    }
                    
                    System.out.println("\n*Fila de Espera (Na de fora):*");
                    for (Jogador j : futebol.getListaEspera()) {
                        System.out.println("- " + j.getNome() + " " + j.getStatusPagamentoTexto());
                    }
                    
                    System.out.println("----------------------------------------");
                    System.out.println("\n*Valor do racha por pessoa:* R$ " + financeiro.getValorPorJogador());
                    System.out.println("\n_Favor realizar o Pix e enviar o comprovante!_");
                    System.out.println("==================================================");
                    break;

                case "0":
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("\n-> Erro: Opcao invalida!");
                    break;
            }
        }
        leitor.close();
    }
}