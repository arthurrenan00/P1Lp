/*
NOME: Arthur Renan Gutierrez Dias Pereira
CURSO:  Análise e Desenvolvimento de Sistemas (2° Sem.)
*/
package p1lp;

import java.util.Scanner;


public class FolhaPgto {
  
    public static void main(String[] args) {
        /*Declarando as variáveis*/
        String nome;
        int ht;
        double salBruto;

        /*Declarando o scanner para ler o teclado*/
        Scanner teclado = new Scanner(System.in);

        /*Iniciando as entradas de dados*/
        System.out.print("Digite seu nome: ");
        nome = teclado.next(); //lê o nome do usuario
        do {
            System.out.print("\nDigite seu salário bruto: ");
            salBruto = teclado.nextDouble();
        } while (salBruto < 1);

        do {
            System.out.print("\nHoras trabalhadas: ");
            ht = teclado.nextInt();
        } while (ht < 1);

    }
}
        

