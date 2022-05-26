package br.com.imobilizaria.tipoImovel.tipoImovelservice.controller;

import br.com.imobilizaria.tipoImovel.tipoImovelservice.model.TipoImovel;
import br.com.imobilizaria.tipoImovel.tipoImovelservice.repositorio.TipoImovelRepositorio;
import br.com.imobilizaria.tipoImovel.tipoImovelservice.service.ImovelService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoImovel")
public class TipoImovelController {
    
    @Autowired
    private TipoImovelRepositorio tipoImovelRepositorio;
    
    @Autowired
    private ImovelService imovelService;
    
    @GetMapping
    public List<TipoImovel> lista(){
        return tipoImovelRepositorio.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Optional<TipoImovel> listaPelaId(@PathVariable Long id){
        return tipoImovelRepositorio.findById(id);
    }
    
    @GetMapping(value="/imovel/{id}")
    public TipoImovel listaImovelPelaId(@PathVariable Long id){
        Optional<TipoImovel> tipoImovelResponse = tipoImovelRepositorio.findById(id);
        TipoImovel tipoImovel = tipoImovelResponse.get();
        tipoImovel.setImovel(imovelService.listarImovelPeloTipo(id));
        return tipoImovel;
    }
    
    
    
    
    @PostMapping
    public TipoImovel adiciona(@RequestBody TipoImovel cliente){
        return tipoImovelRepositorio.save(cliente);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id,@RequestBody TipoImovel tipoImovel){
        return tipoImovelRepositorio.findById(id)
                .map(record->{
                    record.setNome(tipoImovel.getNome());
                    TipoImovel tipoImovelUpdated = tipoImovelRepositorio.save(record);
                    return ResponseEntity.ok().body(tipoImovelUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        return tipoImovelRepositorio.findById(id)
                .map(record ->{
                    tipoImovelRepositorio.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
