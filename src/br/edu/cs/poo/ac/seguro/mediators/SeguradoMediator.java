package br.edu.cs.poo.ac.seguro.mediators;

import java.math.BigDecimal;
import java.time.LocalDate;
import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.Segurado;

public class SeguradoMediator {

    private static SeguradoMediator instancia = new SeguradoMediator();

    private SeguradoMediator() {}

    public static SeguradoMediator getInstancia() {
        return instancia;
    }

    public String validarNome(String nome) {
        if (StringUtils.ehNuloOuBranco(nome)) {
            return "Nome deve ser informado";
        }
        if (nome.trim().length() > 100) {
            return "Tamanho do nome deve ser no máximo 100 caracteres";
        }
        return null;
    }

    public String validarEndereco(Endereco endereco) {
        if (endereco == null) {
            return "Endereço deve ser informado";
        }

        if (StringUtils.ehNuloOuBranco(endereco.getLogradouro())) {
            return "Logradouro deve ser informado";
        }

        if (StringUtils.ehNuloOuBranco(endereco.getCep())) {
            return "CEP deve ser informado";
        }

        if (endereco.getCep().trim().length() != 8) {
            return "Tamanho do CEP deve ser 8 caracteres";
        }

        if (!StringUtils.temSomenteNumeros(endereco.getCep())) {
            return "CEP deve ter formato NNNNNNNN";
        }

        if (StringUtils.ehNuloOuBranco(endereco.getCidade())) {
            return "Cidade deve ser informada";
        }

        if (endereco.getCidade().trim().length() > 100) {
            return "Tamanho da cidade deve ser no máximo 100 caracteres";
        }

        if (StringUtils.ehNuloOuBranco(endereco.getEstado())) {
            return "Sigla do estado deve ser informada";
        }

        if (endereco.getEstado().trim().length() != 2) {
            return "Tamanho da sigla do estado deve ser 2 caracteres";
        }

        if (StringUtils.ehNuloOuBranco(endereco.getPais())) {
            return "País deve ser informado";
        }

        if (endereco.getPais().trim().length() > 40) {
            return "Tamanho do país deve ser no máximo 40 caracteres";
        }

        if (!StringUtils.ehNuloOuBranco(endereco.getNumero()) && endereco.getNumero().trim().length() > 20) {
            return "Tamanho do número deve ser no máximo 20 caracteres";
        }

        if (!StringUtils.ehNuloOuBranco(endereco.getComplemento()) && endereco.getComplemento().trim().length() > 30) {
            return "Tamanho do complemento deve ser no máximo 30 caracteres";
        }

        return null;
    }

    public BigDecimal ajustarDebitoBonus(BigDecimal bonus, BigDecimal valorDebito) {
        if (bonus == null) {
            bonus = BigDecimal.ZERO;
        }

        if (valorDebito == null) {
            valorDebito = BigDecimal.ZERO;
        }

        // Se o débito solicitado for maior que o bônus disponível,
        // o máximo que podemos debitar é o próprio valor do bônus.
        if (valorDebito.compareTo(bonus) > 0) {
            return bonus;
        }

        // Caso o bônus seja suficiente, o débito autorizado é o próprio valor solicitado.
        return valorDebito;
    }

    public String validarDataCriacao(LocalDate dataCriacao) {
        if (dataCriacao == null) {
            return "Data da criação deve ser informada";
        }
        if (dataCriacao.isAfter(LocalDate.now())) {
            return "Data da criação deve ser menor ou igual à data atual";
        }
        return null;
    }
}
