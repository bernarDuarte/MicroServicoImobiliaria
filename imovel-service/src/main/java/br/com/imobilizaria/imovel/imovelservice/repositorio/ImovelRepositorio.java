package br.com.imobilizaria.imovel.imovelservice.repositorio;

import br.com.imobilizaria.imovel.imovelservice.model.Imovel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepositorio extends JpaRepository<Imovel,Long> {
}
