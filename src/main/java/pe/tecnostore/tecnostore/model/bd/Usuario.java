package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    private Integer idusuario;
    private String nombre;
    @NotEmpty(message = "Ingrese El Email")
    @Column(name = "correo")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "Ingrese El Password")
    @Column(name = "contraseña")
    private String password;
    @Column(name = "rep_contra")
    private String rep_pass;
    private String urlfoto;
    private String nombrefoto;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecharegistro;
    @Column(length = 8, columnDefinition = "char(8)")
    private String dni;
    private int idrol;
    private Boolean activo;

    @JoinColumn(name = "idrol", insertable = false, updatable = false)
    @ManyToOne
    private Rol rol;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", targetEntity = Venta.class)
    private List<Venta> ventaList;
}
