package br.com.imobilizaria.interesse.interesseservice.repositorio;

import br.com.imobilizaria.interesse.interesseservice.model.InteresseIdentity;
import br.com.imobilizaria.interesse.interesseservice.model.Interesse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InteresseRepositorio extends JpaRepository<Interesse,InteresseIdentity>{
    
    public List <Interesse> findByInteresseIdentityIdCliente(Long idCliente);
    
    public List <Interesse> findByInteresseIdentityIdImovel(Long idImovel);
    
}
