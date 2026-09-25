package br.edu.cs.poo.ac.seguro.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.math.BigDecimal;
import java.io.Serializable;


@AllArgsConstructor
@Setter
@Getter
public class Apolice implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String numero;
    private Veiculo veiculo;
    private BigDecimal valorFranquia;
    private BigDecimal valorPremio;
    private BigDecimal valorMaximoSegurado;

}
