import java.util.Scanner;

public class App {
    
    //função temporária para o cadastro
    public static String Cadastro(String nome){
        return "Cadastro realizado. Olá "+nome;
    }
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario();
        Scanner leitor = new Scanner(System.in);
        // variáveis de controle
        String opcao = " ";
        Boolean controle = true;

        // Variáveis que vamos usar para a lógica de negócio nas próximas sprints
        int timeA = 0;
        int timeB = 0;
        int listaEspera = 0;
        int limite = 6; // Exemplo de 6 jogadores por time

        // Laço de repetição para manter o menu ativo (Requisito da Sprint 1)
        while (controle) {
            System.out.println("\n===== SISTEMA DE PELADA =====");
            System.out.println("1 - Cadastro e Login (Simulado)");
            System.out.println("2 - Escolher Time A (" + timeA + "/" + limite + ")");
            System.out.println("3 - Escolher Time B (" + timeB + "/" + limite + ")");
            System.out.println("4 - Entrar na Lista de Espera");
            System.out.println("5 - Calcular Rateio do Valor");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            // Estrutura de decisão para o controle do menu (Requisito da Sprint 1)
            opcao = leitor.nextLine();

            switch (opcao) {
                case "1":
                    usuario1.cadastro();
                    break;
                case "2":
                    System.out.println("-> Você escolheu o Time A.");
                    // Lógica simples para demonstrar funcionamento
                    if (timeA < limite) timeA++;
                    break;
                case "3":
                    System.out.println("-> Você escolheu o Time B.");
                    if (timeB < limite) timeB++;
                    break;
                case "4":
                    System.out.println("-> Lista de espera para substituição.");
                    listaEspera++;
                    break;
                case "5":
                    System.out.println("-> Calculando valor por pessoa (incluindo reservas)...");
                    break;
                case "0":
                    System.out.println(" Saindo...");
                    controle = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

        leitor.close();

    }
}