import dominio.Banco;
import dominio.Conta;
import excecoes.AplicacaoError;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class App {
    private static final Banco banco = new Banco();
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int opcao = 0;

        do {
            try {
                System.out.format("(1) - inserir%n(2) - consultar%n(3) - alterar%n(4) - excluir%n(5) - sacar%n" +
                        "(6) - depositar%n(7) - transferir%n(8) - quant. contas%n(9) - total saldos%n" +
                        "(10) - media saldos%n(11) - sair%n");

                System.out.print("Digite uma opção: ");
                opcao = Integer.parseInt(input.readLine());
                if (opcao == 1) {
                    inserir();
                } else if (opcao == 2) {
                    consultar();
                } else if (opcao == 3) {
                    alterar();
                } else if (opcao == 4) {
                    excluir();
                } else if (opcao == 5) {
                    sacar();
                } else if (opcao == 6) {
                    depositar();
                } else if (opcao == 7) {
                    transferir();
                } else if (opcao == 8) {
                    banco.quantidadeContas();
                } else if (opcao == 9) {
                    banco.totalSaldos();
                } else if (opcao == 10) {
                    banco.mediaSaldos();
                } else if (opcao == 11) {
                    input.close();
                }
            } catch (NumberFormatException | IOException | AplicacaoError exception) {
                if (exception instanceof IOException) {
                    System.out.println("Erro interno. Contate o adm :)");
                }else if(exception instanceof NumberFormatException){
                    System.out.println("Opção inválida");
                }else{
                    System.out.println(((AplicacaoError) exception).getMessage());
                }
            } finally {
                System.out.format("Operação finalizada!%n%n");
            }
        } while (opcao != 11);
    }

    private static void inserir() throws IOException {
        System.out.print("Número da conta: ");
        String numero = input.readLine();

        System.out.print("Saldo inicial: ");
        String saldo = input.readLine();

        banco.inserirConta(new Conta(numero, new BigDecimal(saldo).doubleValue()));
    }

    public static void consultar() throws IOException {
        System.out.print("Número da conta: ");
        String numero = input.readLine();

        System.out.println(banco.consultarConta(numero));
    }

    public static void alterar() throws IOException {
        System.out.print("Número da conta: ");
        String numero = input.readLine();

        System.out.print("Novo saldo: ");
        String saldo = input.readLine();

        banco.alterarConta(new Conta(numero, new BigDecimal(saldo).doubleValue()));
    }

    public static void excluir() throws IOException {
        System.out.print("Número da conta: ");
        String numero = input.readLine();

        banco.excluirConta(numero);
    }

    public static void depositar() throws IOException {
        System.out.print("Número da conta: ");
        String numero = input.readLine();

        System.out.print("Valor do deposito: ");
        String valor = input.readLine();

        banco.depositar(numero, new BigDecimal(valor).doubleValue());
    }

    public static void sacar() throws IOException {
        System.out.print("Número da conta: ");
        String numero = input.readLine();

        System.out.print("Valor do saque: ");
        String valor = input.readLine();

        banco.sacar(numero, new BigDecimal(valor).doubleValue());
    }

    public static void transferir() throws IOException {
        System.out.print("Número da conta (débito): ");
        String debito = input.readLine();

        System.out.print("Número da conta (crédito): ");
        String credito = input.readLine();

        System.out.print("Valor da transferência: ");
        String valor = input.readLine();

        banco.transferir(debito, credito, new BigDecimal(valor).doubleValue());
    }

}
