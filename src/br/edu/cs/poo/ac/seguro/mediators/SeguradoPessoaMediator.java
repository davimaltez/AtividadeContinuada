package br.edu.cs.poo.ac.seguro.mediators;

import br.edu.cs.poo.ac.seguro.daos.SeguradoPessoaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;

public class SeguradoPessoaMediator {

    private static SeguradoPessoaMediator instancia = new SeguradoPessoaMediator();

    private SeguradoMediator seguradoMediator = SeguradoMediator.getInstancia();
    private SeguradoPessoaDAO seguradoPessoaDAO = new SeguradoPessoaDAO();

    private SeguradoPessoaMediator() {}

    public static SeguradoPessoaMediator getInstancia() {
        return instancia;
    }

    public String validarCpf(String cpf) {
        if (StringUtils.ehNuloOuBranco(cpf)) {
            return "CPF deve ser informado";
        }

        String cpfLimpo = cpf.replaceAll("\\D", "");

        if (cpfLimpo.length() != 11) {
            return "CPF deve ter 11 caracteres";
        }

        if (!ValidadorCpfCnpj.ehCpfValido(cpf)) {
            return "CPF com dígito inválido";
        }

        return null;
    }

    public String validarRenda(double renda) {
        if (renda < 0.0) {
            return "Renda deve ser maior ou igual à zero";
        }
        return null;
    }

    public String validarSeguradoPessoa(SeguradoPessoa seg) {
        if (seg == null) {
            return "Segurado pessoa nao pode ser nulo";
        }

        String erroNome = seguradoMediator.validarNome(seg.getNome());
        if (erroNome != null) {
            return erroNome;
        }

        String erroEndereco = seguradoMediator.validarEndereco(seg.getEndereco());
        if (erroEndereco != null) {
            return erroEndereco;
        }

        String erroData = seguradoMediator.validarDataCriacao(seg.getDataNascimento());
        if (erroData != null) {
            return erroData.replace("da criação", "do nascimento");
        }

        String erroCpf = validarCpf(seg.getCpf());
        if (erroCpf != null) {
            return erroCpf;
        }

        String erroRenda = validarRenda(seg.getRenda());
        if (erroRenda != null) {
            return erroRenda;
        }

        return null;
    }

    public String incluirSeguradoPessoa(SeguradoPessoa seg) {
        String msgValidacao = validarSeguradoPessoa(seg);
        if (msgValidacao != null) {
            return msgValidacao;
        }

        boolean sucesso = seguradoPessoaDAO.incluir(seg);
        if (!sucesso) {
            return "CPF do segurado pessoa já existente";
        }

        return null;
    }

    public String alterarSeguradoPessoa(SeguradoPessoa seg) {
        String msgValidacao = validarSeguradoPessoa(seg);
        if (msgValidacao != null) {
            return msgValidacao;
        }

        boolean sucesso = seguradoPessoaDAO.alterar(seg);
        if (!sucesso) {
            return "CPF do segurado pessoa não existente";
        }

        return null;
    }

    public String excluirSeguradoPessoa(String cpf) {
        if (StringUtils.ehNuloOuBranco(cpf)) {
            return "CPF deve ser informado";
        }

        boolean sucesso = seguradoPessoaDAO.excluir(cpf);
        if (!sucesso) {
            return "CPF do segurado pessoa não existente";
        }

        return null;
    }

    public SeguradoPessoa buscarSeguradoPessoa(String cpf) {
        if (StringUtils.ehNuloOuBranco(cpf)) {
            return null;
        }

        return seguradoPessoaDAO.buscar(cpf);
    }
}