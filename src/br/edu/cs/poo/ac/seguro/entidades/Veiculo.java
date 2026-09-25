package br.edu.cs.poo.ac.seguro.entidades;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;



@AllArgsConstructor
@Getter @Setter
public class Veiculo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String placa;
    private int ano;
    private SeguradoEmpresa proprietarioEmpresa;
    private SeguradoPessoa proprietarioPessoa;
    private CategoriaVeiculo categoria;


}
