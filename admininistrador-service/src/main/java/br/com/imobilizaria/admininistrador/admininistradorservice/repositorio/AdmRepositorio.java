package br.com.imobilizaria.admininistrador.admininistradorservice.repositorio;

import br.com.imobilizaria.admininistrador.admininistradorservice.model.Adm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface AdmRepositorio extends JpaRepository<Adm, Long>{
    
}
