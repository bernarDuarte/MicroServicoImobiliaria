package br.com.imobilizaria.cliente.clienteservice.repositorio;

import br.com.imobilizaria.cliente.clienteservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{
}
