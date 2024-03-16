package com.example.ecommerce.controllers;

import java.util.Scanner;

public class MenuController {

    private final Scanner scanner;

    public MenuController() {
        this.scanner = new Scanner(System.in);
    }

    public int exibirMenu() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║       MENU DE OPÇÕES       ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ 1. Adicionar um produto    ║");
        System.out.println("║ 2. Listar os produtos      ║");
        System.out.println("║ 3. Sair                    ║");
        System.out.println("╚════════════════════════════╝");
        System.out.println("╭───────────────────────────╮");
        System.out.println("│ Digite a opção desejada:  │");
        System.out.println("╰───────────────────────────╯");

        int opcao = -1;
        try {
            opcao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("A opção digitada não é um número válido!");
        }
        return opcao;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
