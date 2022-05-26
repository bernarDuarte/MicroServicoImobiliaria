package br.com.imobilizaria.interesse.interesseservice.service;

import br.com.imobilizaria.interesse.interesseservice.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="cliente-service")
public interface ClienteService {
    @GetMapping(value="/cliente/interesse/{idCliente}")
    Cliente listarPeloId(@PathVariable("idCliente") Long idCliente);
}
