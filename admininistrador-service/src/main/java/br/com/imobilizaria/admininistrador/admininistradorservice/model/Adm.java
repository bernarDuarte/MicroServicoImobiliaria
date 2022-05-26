package br.com.imobilizaria.admininistrador.admininistradorservice.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Adm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable=false,length=100)
    private String nome;
    
    @Column(nullable=false,length=200)
    private String email;
    
    @Column(nullable=false,length=200)
    private String senha;
    
    @Column(nullable=false,length=1)
    private int status;
}
