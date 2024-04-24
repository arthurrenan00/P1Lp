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
        Este método tem duas variáveis como parâmetro:
        double salario -> variável do tipo double que recebe o salário bruto informado pelo usuário
        int horas -> variável do tipo int que recebe o número de horas trabalhadas pelo usuário
     */
    public static String HoraExtraOuDesconto(double salario, int horasT) {
        double sph = salario / 160; //cálculo do salário por hora
        double valorLiquido = sph * (horasT - 160); // cálculo do valor líquido de acordo com a diferença das horas trabalhadas por 160

        DecimalFormat df = new DecimalFormat(); //classe para formatação de valores
        df.applyPattern("R$ #,##0.00"); // formatação para valores em REAIS

        if (horasT < 160) { //se as horas trabalhadas serem menores que 160
            return "Desconto por atraso: " + df.format(valorLiquido); //irá retornar a string do desconto por atraso com o valor formatado
        } else if (horasT > 160) { //se as horas trabalhadas serem maiores que 160
            return "Hora extra a receber: " + df.format(valorLiquido); //irá retornar a string da hora extra a receber com o valor formatado
        } else { // se as horas trabalhadas não forem nem maiores nem menores que 160 (então são iguais)
            return ""; //retorna uma string vazia, pois não irá alterar o valor do salário nesse caso
        }
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
