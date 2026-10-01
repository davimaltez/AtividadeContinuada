package br.edu.cs.poo.ac.seguro.mediators;

public class ValidadorCpfCnpj {

    private ValidadorCpfCnpj() {}

    public static boolean ehCpfValido(String cpf) {
        if (StringUtils.ehNuloOuBranco(cpf)) {
            return false;
        }

        // 1. Remove caracteres nao numericos (pontos e traço)
        String cpfLimpo = cpf.replaceAll("\\D", "");

        // 2. CPF deve ter exatamente 11 digitos
        if (cpfLimpo.length() != 11) {
            return false;
        }

        // 3. Rejeita CPFs com todos os digitos iguais (ex: 111.111.111-11)
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }

        // 4. Calculo do primeiro digito verificador
        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += (cpfLimpo.charAt(i) - '0') * (10 - i);
        }
        int resto1 = soma1 % 11;
        int dv1 = (resto1 < 2) ? 0 : 11 - resto1;

        if ((cpfLimpo.charAt(9) - '0') != dv1) {
            return false;
        }

        // 5. Calculo do segundo digito verificador
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += (cpfLimpo.charAt(i) - '0') * (11 - i);
        }
        int resto2 = soma2 % 11;
        int dv2 = (resto2 < 2) ? 0 : 11 - resto2;

        return (cpfLimpo.charAt(10) - '0') == dv2;
    }

    public static boolean ehCnpjValido(String cnpj) {
        if (StringUtils.ehNuloOuBranco(cnpj)) {
            return false;
        }

        // 1. Remove caracteres nao numericos (pontos, barra e traço)
        String cnpjLimpo = cnpj.replaceAll("\\D", "");

        // 2. CNPJ deve ter exatamente 14 digitos
        if (cnpjLimpo.length() != 14) {
            return false;
        }

        // 3. Rejeita CNPJs com todos os digitos iguais
        if (cnpjLimpo.matches("(\\d)\\1{13}")) {
            return false;
        }

        // 4. Calculo do primeiro digito verificador
        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma1 = 0;
        for (int i = 0; i < 12; i++) {
            soma1 += (cnpjLimpo.charAt(i) - '0') * peso1[i];
        }
        int resto1 = soma1 % 11;
        int dv1 = (resto1 < 2) ? 0 : 11 - resto1;

        if ((cnpjLimpo.charAt(12) - '0') != dv1) {
            return false;
        }

        // 5. Calculo do segundo digito verificador
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma2 = 0;
        for (int i = 0; i < 13; i++) {
            soma2 += (cnpjLimpo.charAt(i) - '0') * peso2[i];
        }
        int resto2 = soma2 % 11;
        int dv2 = (resto2 < 2) ? 0 : 11 - resto2;

        return (cnpjLimpo.charAt(13) - '0') == dv2;
    }
}