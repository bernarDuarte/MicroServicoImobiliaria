package br.com.imobilizaria.interesse.interesseservice.service;

import br.com.imobilizaria.interesse.interesseservice.model.Imovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="imovel-service")
public interface ImovelService {
    @GetMapping(value="/imovel/{idImovel}")
    Imovel listarPeloId(@PathVariable("idImovel") Long idImovel);
}
