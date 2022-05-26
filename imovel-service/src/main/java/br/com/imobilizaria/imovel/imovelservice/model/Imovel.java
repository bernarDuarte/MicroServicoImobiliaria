package br.com.imobilizaria.imovel.imovelservice.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Imovel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length=100)
    private String titulo;
    
    @Column(nullable = true, length=500)
    private String descricao;
    
    @Column(nullable = false,name="data_criacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCriacao;
    
    @Column(nullable = true,precision=8, scale=2)
    private Double valor;
    
    @Column(nullable = false, length=1)
    private int status;
    
    @Column(name="id_tipo_imovel",nullable = false)
    private Long idTipoImovel;
    
    @Transient
    TipoImovel tipoImovel;
    
    @Transient
    Interesse[] interesses;
}
