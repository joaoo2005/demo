package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.Repository;
import com.example.demo.dto.clienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente/v1/")
public class Controller {

    @Autowired
    Repository repository;

    @PostMapping
    public Cliente creat(@RequestBody @Valid Cliente cliente){
        Cliente clienteSaved = repository.save(cliente);
        return clienteSaved;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Cliente> getClineteById(@PathVariable Long id){
        Optional<Cliente> clienteReturned = repository.findById(id);
        return clienteReturned;
    }
    @DeleteMapping("/{id}")
    public String deleteClienteById(@PathVariable Long id){
        try{
            Optional<Cliente> cliente = Optional.of(repository.getById(id));
            if(cliente.isPresent()){
                repository.deleteById(id);
                return "Cliente de " + id + " deletado com sucesso";
            }else{
                throw new Exception("Cliente inexistente");
            }
        }catch(Exception e){
            e.printStackTrace();
            return "O cliente de " + id + " não existe para ser deletado!" +
                    " Por favor, entre em contato com o atendimento ...";
        }
    }

    @GetMapping
    public List<Cliente> listCliente(){
        return repository.findAll();
    }

    @PutMapping("/atualize/{id}")
    public String updateClineteById(@RequestBody clienteDTO clienteDTO, @PathVariable Long id){
        Optional<Cliente> velhoCliente = repository.findById(id);
        if(velhoCliente.isPresent()){
            Cliente cliente = velhoCliente.get();
            cliente.setEndereco(clienteDTO.getEndereco());
            repository.save(cliente);
            return "Cliente de ID" + cliente.getId() + " atualizado com sucesso!";
        }else{
            return "Cliente de ID " + id + " não existe!";
        }
    }
}
