package br.com.imobilizaria.admininistrador.admininistradorservice.controller;

import br.com.imobilizaria.admininistrador.admininistradorservice.model.Adm;
import br.com.imobilizaria.admininistrador.admininistradorservice.repositorio.AdmRepositorio;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/administrador")
public class AdmController {
    
    @Autowired
    private AdmRepositorio admRepositorio;
            
 
    @GetMapping
    public List<Adm> lista(){
        return admRepositorio.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Optional<Adm> listaPelaId(@PathVariable Long id){
        Optional<Adm> admResponse = admRepositorio.findById(id);
        Adm adm = admResponse.get();
        if(adm.getStatus()==0){
            return null; 
        }
        return admRepositorio.findById(id);
    }
    
    @PostMapping
    public Adm adiciona(@RequestBody Adm adm) throws NoSuchAlgorithmException{
        adm.setSenha(createHash(adm.getSenha()));
        return admRepositorio.save(adm);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id,@RequestBody Adm adm){
        Optional<Adm> admResponse = admRepositorio.findById(id);
        Adm a = admResponse.get();
        if(a.getStatus()==0){
            return null; 
        }
        return admRepositorio.findById(id)
                .map( record ->{
                    record.setNome(adm.getNome());
                    record.setEmail(adm.getEmail());
            try {
                record.setSenha(createHash(adm.getSenha()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AdmController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    record.setStatus(adm.getStatus());
                    Adm AdmUpdated = admRepositorio.save(record);
                    return ResponseEntity.ok().body(AdmUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        return admRepositorio.findById(id)
                .map(record ->{
                    record.setStatus(0);
                    Adm AdmUpdated = admRepositorio.save(record);
                    return ResponseEntity.ok().body(AdmUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    
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
