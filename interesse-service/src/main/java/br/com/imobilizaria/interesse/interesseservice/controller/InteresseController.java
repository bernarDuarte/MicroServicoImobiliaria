package br.com.imobilizaria.interesse.interesseservice.controller;

import br.com.imobilizaria.interesse.interesseservice.model.Imovel;
import br.com.imobilizaria.interesse.interesseservice.model.Interesse;
import br.com.imobilizaria.interesse.interesseservice.model.InteresseIdentity;
import br.com.imobilizaria.interesse.interesseservice.repositorio.InteresseRepositorio;
import br.com.imobilizaria.interesse.interesseservice.service.ClienteService;
import br.com.imobilizaria.interesse.interesseservice.service.ImovelService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interesse")
public class InteresseController {
    
    @Autowired
    private InteresseRepositorio interesseRepository;
    
    @Autowired
    private ImovelService imovelService;
    
    @Autowired
    private ClienteService clienteService;

    
    @GetMapping(value="/cliente/{idCliente}")
    public List<Interesse> listarImovelPeloId(@PathVariable Long idCliente) {
        List<Interesse> interesseResponse = interesseRepository.findByInteresseIdentityIdCliente(idCliente);
        for(Interesse interesse: interesseResponse){
            interesse.setImovel(imovelService.listarPeloId(interesse.getInteresseIdentity().getIdImovel()));
        }            
        return interesseResponse;
    }
    
    @GetMapping(value="/imovel/{idImovel}")
    public List<Interesse> listarClientePeloId(@PathVariable Long idImovel) {
        List<Interesse> interesseResponse = interesseRepository.findByInteresseIdentityIdImovel(idImovel);
        for(Interesse interesse: interesseResponse){
            interesse.setCliente(clienteService.listarPeloId(interesse.getInteresseIdentity().getIdCliente()));
        }    
        return interesseResponse;
    }
    
    @PostMapping
    public Interesse adicionar(@RequestBody Interesse interesse) {
        return interesseRepository.save(interesse);
    }
    
    @DeleteMapping(value="/{idImovel}/{idCliente}")
    public ResponseEntity deletar(@PathVariable Long idImovel,@PathVariable Long idCliente){
         final InteresseIdentity identity = new InteresseIdentity(idImovel,idCliente);
        return interesseRepository.findById(identity)
                .map(record ->{
                    interesseRepository.deleteById(identity);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    
    
     
       
    
}
