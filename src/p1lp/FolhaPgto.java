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

    /*MÉTODO PARA CALCULAR O INSS*/
    public static double CalculoINSS(double salario) { //parâmetro do tipo double (salario)
        double inss = 0, faixaDoSal; //variável do inss e da faixa do salario digitado pelo usuário
        /*
                        Salario (de)    Salário (até)   Alíquota
        1° faixa    0,00                1412              7,5%
        2° faixa    1412,01          2666,68         9%
        3° faixa    2666,68          4000,03         12%
        4° faixa    4000,04          7786,02         14%
        
            // fórmula para calcular o valor da faixaDoSal
            (salario digitado - salário limite da faixa anterior) * %da faixa correspondente
            Exemplo: salário digitado = 5000
            A faixa dele é a 4°, então o limite anterior é de 4000,03... logo:
            faixaDoSal = (5000 - 4000.03) * 0.14;
         */

        //Estabelecendo os cálculos de cada faixa salarial  
        double faixa1 = 1412 * 0.075;
        double faixa2 = (2666.68 - 1412.00) * 0.09;
        double faixa3 = (4000.03 - 2666.68) * 0.12;
        double faixa4 = (7786.02 - 4000.03) * 0.14;

        if (salario <= 1412) { //faixa de até 1412 reais
            faixaDoSal = salario * 0.075;
            inss = faixaDoSal; // valor final do inss
        } else if (salario <= 2666.68) { //faixa entre 1412,01 e 2666.68
            faixaDoSal = (salario - 1412) * 0.09;
            inss = faixa1 + faixaDoSal; //valor final do inss é a faixa1 + a faixa do salario digitado
        } else if (salario <= 4000.03) { //faixa entre 2666.69 e 4000.03
            faixaDoSal = (salario - 2666.68) * 0.12;
            inss = faixa1 + faixa2 + faixaDoSal; //valor final do inss é a faixa1 + faixa2 + a faixa do salario digitado
        } else if (salario <= 7786.02) {//faixa entre 4000.04 e 7786.02
            faixaDoSal = (salario - 4000.03) * 0.14;
            inss = faixa1 + faixa2 + faixa3 + faixaDoSal; //valor final do inss é a faixa1 + faixa2 + faixa3 + a faixa do salario digitado
        } else {// faixa acima de 7786.02
            inss = faixa1 + faixa2 + faixa3 + faixa4;//valor final do inss é a soma de todas as faixas salariais
        }

        return inss; //retorna o valor final do INSS
    }

    public static void main(String[] args) {
        //Declarando as variáveis
        String nome;
        int ht; //horas trabalhadas
        double salBruto, salLiq; //salário bruto e salário líquido

        //Declarando o scanner para ler o teclado
        Scanner teclado = new Scanner(System.in);

        System.out.println("*****FOLHA DE PAGAMENTO*****");

        //Iniciando as entradas de dados
        System.out.print("Digite seu nome: ");
        nome = teclado.next(); //lê o nome do usuario
        do {
            System.out.print("\nDigite seu salario bruto: ");
            salBruto = teclado.nextDouble();
        } while (salBruto < 1); //repete enquanto o usuário digitar um salário menor que 1

        do {
            System.out.print("\nHoras trabalhadas: ");
            ht = teclado.nextInt();
        } while (ht < 0); //repete enquanto o usuário digitar um valor de horas trabalhadas menor que 0

        double heOuDesc = HoraExtraOuDesconto(salBruto, ht); //variável double para receber o valor de hora extra ou desconto
        double irpf = CalculoIRPF(salBruto); //variável double que recebe o resultado do método de calcular o IRPF
        double inss = CalculoINSS(salBruto); //variável double que recebe o resultado do método de cálculo do INSS
        salLiq = salBruto - irpf - inss + heOuDesc;

        System.out.println("\n*****CALCULO DA FOLHA DE PAGAMENTO*****");
        System.out.println("Nome do colaborador.....: " + nome);
        System.out.println("Salario Bruto.....: " + salBruto);
        System.out.println("IRPF retido.....: " + formatacao(irpf)); //imprime o valor do irpf
        System.out.println("INSS retido.....: " + formatacao(inss)); //imprime o valor do inss
        if (heOuDesc > 0) {
            System.out.println("Hora extra a receber.....: " + formatacao(heOuDesc)); //imprime o valor da hora extra
        }
        if (heOuDesc < 0) {
            System.out.println("Desconto por atraso.....: " + formatacao(heOuDesc)); //imprime o valor do desconto
        }
        System.out.println("Salario liquido.....: " + formatacao(salLiq));
        teclado.close();
    }
}
