package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;

public class TesteSinistroDAO extends TesteDAO {

    private SinistroDAO dao = new SinistroDAO();
    protected Class getClasse() {
        return Sinistro.class;
    }

    //Teste de Busca bem-sucedida
    @Test
    public void teste01() {
        String numero = "00000000";
        cadastro.incluir(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
                "TESTE 1", new BigDecimal("1000.00"), TipoSinistro.COLISAO),numero);
        Sinistro seg = dao.buscar(numero);
        Assertions.assertNotNull(seg);
    }
    //Teste de Busca por registro inexistente
    @Test
    public void teste02() {
        String numero = "10000000";
        cadastro.incluir(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
                "TESTE 2", new BigDecimal("1001.00"), TipoSinistro.COLISAO),numero);
        Sinistro seg = dao.buscar("11000000");
        Assertions.assertNull(seg);
    }
    //Teste de Exclusão bem-sucedida
    @Test
    public void teste03() {
        String numero = "22000000";
        cadastro.incluir(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
                "TESTE 3", new BigDecimal("1002.00"), TipoSinistro.COLISAO),numero);
        boolean ret = dao.excluir(numero);
        Assertions.assertTrue(ret);
    }
    //Teste de Exclusão de registro inexistente
    @Test
    public void teste04() {
        String numero = "33000000";
        cadastro.incluir(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
                "TESTE 4", new BigDecimal("1003.00"), TipoSinistro.COLISAO),numero);
        boolean ret = dao.excluir("33100000");
        Assertions.assertFalse(ret);
    }
    //Teste de Inclusão bem-sucedida via DAO
    @Test
    public void teste05() {
        String numero = "44000000";
        boolean ret = dao.incluir(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
                "TESTE 5", new BigDecimal("1004.00"), TipoSinistro.COLISAO));
        Assertions.assertTrue(ret);
        Sinistro seg = dao.buscar(numero);
        Assertions.assertNotNull(seg);
    }
    //Teste de Bloqueio de Duplicidade (Chave Repetida)
    @Test
    public void teste06() {
        String numero = "55000000";
        Sinistro seg = new Sinistro("TESTE6", null, LocalDateTime.now(), LocalDateTime.now(),
                "TESTE 6", new BigDecimal("1005.00"), TipoSinistro.COLISAO);
        cadastro.incluir(seg, numero);
        boolean ret = dao.incluir(seg);
        Assertions.assertFalse(ret);
    }
    //Teste de Alteração de registro inexistente
    @Test
    public void teste07() {
        String numero = "66000000";
        boolean ret = dao.alterar(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
                "TESTE 7", new BigDecimal("1006.00"), TipoSinistro.COLISAO));
        Assertions.assertFalse(ret);
        Sinistro seg = dao.buscar(numero);
        Assertions.assertNull(seg);
    }

    //Teste de Alteração bem-sucedida
    @Test
    public void teste08() {
        String numero = "77000000";
        Sinistro seg = new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
                "TESTE 8", new BigDecimal("1007.00"), TipoSinistro.COLISAO);
        cadastro.incluir(seg, numero);

        seg = new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
                "TESTE 9", new BigDecimal("1008.00"), TipoSinistro.COLISAO);
        boolean ret = dao.alterar(seg);
        Assertions.assertTrue(ret);
    }
}

