package br.edu.cs.poo.ac.seguro.mediators;

import br.edu.cs.poo.ac.seguro.daos.SeguradoEmpresaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;

public class SeguradoEmpresaMediator {

    private static SeguradoEmpresaMediator instancia = new SeguradoEmpresaMediator();

    private SeguradoMediator seguradoMediator = SeguradoMediator.getInstancia();
    private SeguradoEmpresaDAO seguradoEmpresaDAO = new SeguradoEmpresaDAO();

    private SeguradoEmpresaMediator() {}

    public static SeguradoEmpresaMediator getInstancia() {
        return instancia;
    }

    public String validarCnpj(String cnpj) {
        if (StringUtils.ehNuloOuBranco(cnpj)) {
            return "CNPJ deve ser informado";
        }

        String cnpjLimpo = cnpj.replaceAll("\\D", "");

        if (cnpjLimpo.length() != 14) {
            return "CNPJ deve ter 14 caracteres";
        }

        if (!ValidadorCpfCnpj.ehCnpjValido(cnpj)) {
            return "CNPJ com dígito inválido";
        }

        return null;
    }

    public String validarFaturamento(double faturamento) {
        if (faturamento <= 0.0) {
            return "Faturamento deve ser maior que zero";
        }
        return null;
    }

    public String validarSeguradoEmpresa(SeguradoEmpresa seg) {
        if (seg == null) {
            return "Segurado empresa nao pode ser nulo";
        }

        String erroNome = seguradoMediator.validarNome(seg.getNome());
        if (erroNome != null) {
            return erroNome;
        }

        String erroEndereco = seguradoMediator.validarEndereco(seg.getEndereco());
        if (erroEndereco != null) {
            return erroEndereco;
        }

        String erroData = seguradoMediator.validarDataCriacao(seg.getDataAbertura());
        if (erroData != null) {
            return erroData.replace("da criação", "da abertura");
        }

        String erroCnpj = validarCnpj(seg.getCnpj());
        if (erroCnpj != null) {
            return erroCnpj;
        }

        String erroFaturamento = validarFaturamento(seg.getFaturamento());
        if (erroFaturamento != null) {
            return erroFaturamento;
        }

        return null;
    }

    public String incluirSeguradoEmpresa(SeguradoEmpresa seg) {
        String msgValidacao = validarSeguradoEmpresa(seg);
        if (msgValidacao != null) {
            return msgValidacao;
        }

        boolean sucesso = seguradoEmpresaDAO.incluir(seg);
        if (!sucesso) {
            return "CNPJ do segurado empresa já existente";
        }

        return null;
    }

    public String alterarSeguradoEmpresa(SeguradoEmpresa seg) {
        String msgValidacao = validarSeguradoEmpresa(seg);
        if (msgValidacao != null) {
            return msgValidacao;
        }

        boolean sucesso = seguradoEmpresaDAO.alterar(seg);
        if (!sucesso) {
            return "CNPJ do segurado empresa não existente";
        }

        return null;
    }

    public String excluirSeguradoEmpresa(String cnpj) {
        if (StringUtils.ehNuloOuBranco(cnpj)) {
            return "CNPJ deve ser informado";
        }

        boolean sucesso = seguradoEmpresaDAO.excluir(cnpj);
        if (!sucesso) {
            return "CNPJ do segurado empresa não existente";
        }

        return null;
    }

    public SeguradoEmpresa buscarSeguradoEmpresa(String cnpj) {
        if (StringUtils.ehNuloOuBranco(cnpj)) {
            return null;
        }

        return seguradoEmpresaDAO.buscar(cnpj);
    }
}