package br.com.imobilizaria.tipoImovel.tipoImovelservice.service;

import br.com.imobilizaria.tipoImovel.tipoImovelservice.model.Imovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="imovel-service")
public interface ImovelService {
    @GetMapping(value="/imovel/tipoImovel/{id}")
    Imovel[] listarImovelPeloTipo(@PathVariable("id") Long id);
}
