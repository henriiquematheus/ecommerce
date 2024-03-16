package com.example.ecommerce.controllers;

import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class MenuController {

    @Getter
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
        System.out.println("║ 3. Atualizar um produto    ║"); // Opção adicionada
        System.out.println("║ 4. Remover um produto      ║"); // Opção adicionada
        System.out.println("║ 5. Sair                    ║"); // Opção atualizada
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
    public void fecharScanner() {
        scanner.close();
    }

}
