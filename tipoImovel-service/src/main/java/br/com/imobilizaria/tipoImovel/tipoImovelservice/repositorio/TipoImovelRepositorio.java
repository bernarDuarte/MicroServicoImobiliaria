package br.com.imobilizaria.tipoImovel.tipoImovelservice.repositorio;

import br.com.imobilizaria.tipoImovel.tipoImovelservice.model.TipoImovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoImovelRepositorio extends JpaRepository<TipoImovel,Long>{
    
}
