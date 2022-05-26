package br.com.imobilizaria.cliente.clienteservice.service;

import br.com.imobilizaria.cliente.clienteservice.model.Interesse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="interesse-service")
public interface InteresseService {
    @GetMapping(value="/interesse/cliente/{id}")
    Interesse[] listarImovelPeloId(@PathVariable("id") Long id);
}
