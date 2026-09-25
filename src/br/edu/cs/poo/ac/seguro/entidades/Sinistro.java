package br.edu.cs.poo.ac.seguro.entidades;
import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Getter @Setter
public class Sinistro implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String numero;
    private Veiculo veiculo;
    private LocalDateTime dataHoraSinistro;
    private LocalDateTime dataHoraRegistro;
    private String usuarioRegistro;
    private BigDecimal valorSinistro;
    private TipoSinistro tipo;

}
