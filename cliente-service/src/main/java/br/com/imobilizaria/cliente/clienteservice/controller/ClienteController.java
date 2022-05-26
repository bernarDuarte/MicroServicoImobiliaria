package br.com.imobilizaria.cliente.clienteservice.controller;

import br.com.imobilizaria.cliente.clienteservice.model.Cliente;
import br.com.imobilizaria.cliente.clienteservice.repositorio.ClienteRepositorio;
import br.com.imobilizaria.cliente.clienteservice.service.InteresseService;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @Autowired
    private InteresseService intereceService;
    
    @GetMapping
    public List<Cliente> lista(){
        return clienteRepositorio.findAll();
    }
    
    @GetMapping(value="/interesse/{id}")
    public Cliente listaPelaId(@PathVariable Long id){
        Optional<Cliente> clienteResponse = clienteRepositorio.findById(id);
        Cliente cliente = clienteResponse.get();
        if(cliente.getStatus()==0){
            return null; 
        }
        cliente.setInteresse(intereceService.listarImovelPeloId(id));
        return cliente;
    }
    
    @PostMapping
    public Cliente adiciona(@RequestBody Cliente cliente) throws NoSuchAlgorithmException{
            cliente.setSenha(createHash(cliente.getSenha()));
        return clienteRepositorio.save(cliente);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity editar(@PathVariable Long id,@RequestBody Cliente cliente){
        Optional<Cliente> clienteResponse = clienteRepositorio.findById(id);
        Cliente c = clienteResponse.get();
        if(c.getStatus()==0){
            return null; 
        }
        return clienteRepositorio.findById(id)
                .map( record ->{
                    record.setNome(cliente.getNome());
                    record.setEmail(cliente.getEmail());
                    record.setSenha(cliente.getSenha());
                    try {
                record.setSenha(createHash(cliente.getSenha()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    Cliente clienteUpdated = clienteRepositorio.save(record);
                    return ResponseEntity.ok().body(clienteUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        return clienteRepositorio.findById(id)
                .map(record ->{
                    record.setStatus(0);
                    Cliente clienteUpdated = clienteRepositorio.save(record);
                    return ResponseEntity.ok().body(clienteUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    
    
    //hash
    public static String createHash(String pass) throws NoSuchAlgorithmException{
        String passwordToHash = pass;
        String salt = getSalt();
        return get_SHA_256_SecurePassword(passwordToHash,salt);
    }    
    
    private static String get_SHA_256_SecurePassword(String passwordToHash,
            String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
    
    
}
