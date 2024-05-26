package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    private Integer idcliente;
    @Column(columnDefinition = "varchar(100)")
    private String nomcliente;
    @Column(columnDefinition = "varchar(100)")
    private String apecliente;
    @Column(columnDefinition = "varchar(8)")
    @Pattern(regexp = "^\\d{8}$", message = "DNI invalido")
    private String dnicliente;
    @Column(columnDefinition = "varchar(100)")
    private String direccion;
    @Column(columnDefinition = "varchar(11)")
    @Pattern(regexp = "^\\d{9,11}$", message = "El Telefono debe tener entre 9 y 11 digitos")
    private String telefono;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecharegistro;
    private Boolean activo;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", targetEntity = Venta.class)
    private List<Venta> ventaList;
}