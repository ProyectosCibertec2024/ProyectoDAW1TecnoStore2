package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    private Integer idempresa;
    @Column(columnDefinition = "varchar(100)")
    private String nomempresa;
    @Column(columnDefinition = "varchar(100)")
    private String direccion;
    @Column(columnDefinition = "varchar(11)")
    @Length(min = 9, max = 10, message = "Ingrese de 9 a 10 digitos")
    private String telefono;
    private Integer idtipoempresa;
    private String email;
    @Length(min = 11, max = 13, message = "La longitud es de 11 hasta 13 digitos")
    private String ruc;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecharegistro;
    private Boolean activo;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa", targetEntity = Proveedor.class)
    private List<Proveedor> lstProveedor;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "tipobanco_empresa", joinColumns = @JoinColumn(name = "idempresa"),
                foreignKey = @ForeignKey(name = "FkIdEmpresaToTipoBancoEmpresa"),
                inverseJoinColumns = @JoinColumn(name = "idtipobanco"),
                inverseForeignKey = @ForeignKey(name = "FkIdTipoEmpresaToTipoBancoEmpresa"))
    private List<TipoBanco> tipoBancoList;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idtipoempresa", insertable = false, updatable = false,
    foreignKey = @ForeignKey(name = "FkIdEmpresaToTipoEmpresa"))
    private TipoEmpresa tipoEmpresa;
}
