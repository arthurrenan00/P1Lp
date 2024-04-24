/*
NOME: Arthur Renan Gutierrez Dias Pereira
CURSO:  Análise e Desenvolvimento de Sistemas (2° Sem.)
 */
package p1lp;

import java.text.DecimalFormat;
import java.util.Scanner;

public class FolhaPgto {

    public static String formatacao(double valor) { //método para formatar valores decimais do tipo double
        DecimalFormat df = new DecimalFormat(); //classe para formatação de valores
        df.applyPattern("R$ #,##0.00"); // formatação para valores em REAIS

        return df.format(valor); //retorna a string com o valor formatado
    }

    /*
        Método para calcular o valor da hora extra ou desconto por atraso:
        Este método tem duas variáveis como parâmetro:
        double salario -> variável do tipo double que recebe o salário bruto informado pelo usuário
        int horasT -> variável do tipo int que recebe o número de horas trabalhadas pelo usuário
     */
    public static double HoraExtraOuDesconto(double salario, int horasT) {
        double sph = salario / 160; //cálculo do salário por hora
        double valorLiquido = sph * (horasT - 160); // cálculo do valor líquido de acordo com a diferença das horas trabalhadas por 160

        if (horasT < 160) { //se as horas trabalhadas serem menores que 160
            return valorLiquido; //irá retornar a string do desconto por atraso com o valor formatado
        } else if (horasT > 160) { //se as horas trabalhadas serem maiores que 160
            return valorLiquido; //irá retornar a string da hora extra a receber com o valor formatado
        } else { // se as horas trabalhadas não forem nem maiores nem menores que 160 (então são iguais)
            return 0; //retorna um valor 0, pois não irá alterar o valor do salário nesse caso
        }
    }

    /*MÉTODO PARA CALCULAR O IRPF*/
    public static double CalculoIRPF(double salario) { //parâmetro do tipo double (no caso, o salário)
        double irpf = 0;
        if (salario <= 2259.20) {//um salário de até 2259.20 não recebe alteração do IRPF
            return 0; //retorna um valor vazio;
        } else if (salario <= 2826.65) { //de 2259.21 até 2826.65
            irpf = salario * 0.075 - 169.44; // alíquota de 7.50% e parcela a deduzir do IR: 169.44
        } else if (salario <= 3751.05) {//de 2826.66 até 3751.05
            irpf = salario * 0.15 - 381.44; // alíquota de 15% e parcela a deduzir do IR: 381.44
        } else if (salario <= 4664.68) {//de 3751.06 até 4664.68
            irpf = salario * 0.225 - 662.77; // alíquota de 22.5% e parcela a deduzir do IR: 662.77
        } else {//acima de 4664.68
            irpf = salario * 0.275 - 896; // alíquota de 27.5% e parcela a deduzir do IR: 896
        }
        return irpf; //retorna o valor do IRPF 
    }
    public static void main(String[] args) {
        /*Declarando as variáveis*/
        String nome;
        int ht; //horas trabalhadas
        double salBruto, salPorHora, irpf, inss;

        /*Declarando o scanner para ler o teclado*/
        Scanner teclado = new Scanner(System.in);

        /*Iniciando as entradas de dados*/
        System.out.print("Digite seu nome: ");
        nome = teclado.next(); //lê o nome do usuario
        do {
            System.out.print("\nDigite seu salario bruto: ");
            salBruto = teclado.nextDouble();
        } while (salBruto < 1);

        do {
            System.out.print("\nHoras trabalhadas: ");
            ht = teclado.nextInt();
        } while (ht < 1);
        String HeOuDesc = HoraExtraOuDesconto(salBruto, ht); //variável String para receber o valor de hora extra ou desconto
        System.out.println(HeOuDesc); //imprime a String pro usuário
    }
}
