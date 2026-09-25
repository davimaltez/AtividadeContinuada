package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;

public class TesteApoliceDAO extends TesteDAO {

    private ApoliceDAO dao = new ApoliceDAO();

    @Override
    protected Class getClasse() {
        return Apolice.class;
    }

    @Test
    public void teste01() {
        String numero = "00000000";
        cadastro.incluir(new Apolice(numero, null, new BigDecimal("1000.00"),
                new BigDecimal("200.00"), new BigDecimal("50000.00")), numero);
        Apolice seg = dao.buscar(numero);
        Assertions.assertNotNull(seg);
    }

    @Test
    public void teste02() {
        String numero = "10000000";
        cadastro.incluir(new Apolice(numero, null, new BigDecimal("1001.00"),
                new BigDecimal("200.00"), new BigDecimal("50000.00")), numero);
        Apolice seg = dao.buscar("11000000");
        Assertions.assertNull(seg);
    }

    @Test
    public void teste03() {
        String numero = "22000000";
        cadastro.incluir(new Apolice(numero, null, new BigDecimal("1002.00"),
                new BigDecimal("200.00"), new BigDecimal("50000.00")), numero);
        boolean ret = dao.excluir(numero);
        Assertions.assertTrue(ret);
    }

    @Test
    public void teste04() {
        String numero = "33000000";
        cadastro.incluir(new Apolice(numero, null, new BigDecimal("1003.00"),
                new BigDecimal("200.00"), new BigDecimal("50000.00")), numero);
        boolean ret = dao.excluir("33100000");
        Assertions.assertFalse(ret);
    }

    @Test
    public void teste05() {
        String numero = "44000000";
        boolean ret = dao.incluir(new Apolice(numero, null, new BigDecimal("1004.00"),
                new BigDecimal("200.00"), new BigDecimal("50000.00")));
        Assertions.assertTrue(ret);
        Apolice seg = dao.buscar(numero);
        Assertions.assertNotNull(seg);
    }

    @Test
    public void teste06() {
        String numero = "55000000";
        Apolice seg = new Apolice(numero, null, new BigDecimal("1005.00"),
                new BigDecimal("200.00"), new BigDecimal("50000.00"));
        cadastro.incluir(seg, numero);
        boolean ret = dao.incluir(seg);
        Assertions.assertFalse(ret);
    }

    @Test
    public void teste07() {
        String numero = "66000000";
        boolean ret = dao.alterar(new Apolice(numero, null, new BigDecimal("1006.00"),
                new BigDecimal("200.00"), new BigDecimal("50000.00")));
        Assertions.assertFalse(ret);
        Apolice seg = dao.buscar(numero);
        Assertions.assertNull(seg);
    }

    @Test
    public void teste08() {
        String numero = "77000000";
        Apolice seg = new Apolice(numero, null, new BigDecimal("1007.00"),
                new BigDecimal("200.00"), new BigDecimal("50000.00"));
        cadastro.incluir(seg, numero);
        seg = new Apolice(numero, null, new BigDecimal("1008.00"),
                new BigDecimal("250.00"), new BigDecimal("50000.00"));
        boolean ret = dao.alterar(seg);
        Assertions.assertTrue(ret);
    }
}