import java.util.Scanner;
public class Usuario {
    Scanner scan = new Scanner(System.in);
    public String nome, cpf, senha, email;

    public double saldo;

    public void cadastro(){
        System.out.println("Digite seu nome de usuário: ");
        nome = scan.nextLine();
        System.out.println("Digite seu CPF: ");
        cpf = scan.nextLine();
        System.out.println("Digite seu email: ");
        email =  scan.nextLine();
        System.out.println("Digite a senha: ");
        senha = scan.nextLine();

    }

}
