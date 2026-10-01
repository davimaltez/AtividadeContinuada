package br.edu.cs.poo.ac.seguro.mediators;

public class StringUtils {
    private StringUtils() {}

    public static boolean ehNuloOuBranco(String str) {
        if (str == null) {
            return true;
        }
        // .trim() remove espaços antes e depois; .isEmpty() checa se ficou vazio
        return str.trim().isEmpty();
    }

    public static boolean temSomenteNumeros(String input) {
        if (ehNuloOuBranco(input)) {
            return false;
        }
        // Limpa espaços extras
        String texto = input.trim();

        // Percorre cada caractere verificando se é dígito de 0 a 9
        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isDigit(texto.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}