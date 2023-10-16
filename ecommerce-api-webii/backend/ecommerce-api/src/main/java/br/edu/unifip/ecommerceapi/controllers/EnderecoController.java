package br.edu.unifip.ecommerceapi.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifip.ecommerceapi.dtos.EnderecoDto;
import br.edu.unifip.ecommerceapi.models.Endereco;
import br.edu.unifip.ecommerceapi.repositories.EnderecoRepository;
import br.edu.unifip.ecommerceapi.services.EnderecoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Autowired
    private EnderecoRepository repository;


    @PostMapping
    @Transactional
    public void cadastrarEndereco(@RequestBody @Valid EnderecoDto endereco){
        repository.save(new Endereco(endereco));
    }

    @GetMapping
    public Page<EnderecoDto> listarEnderecos(Pageable paginacao) {
        return repository.findAll(paginacao).map(EnderecoDto::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEnderecoById(@PathVariable(value = "id") Long id) {
        Optional<Endereco> enderecoOptional = enderecoService.findById(id);
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecoOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEndereco(@PathVariable(value = "id") Long id) {
        Optional<Endereco> enderecoOptional = enderecoService.findById(id);
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco not found.");
        }
        enderecoService.Delete(enderecoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Endereco deleted successfully.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateEndereco(@PathVariable(value = "id") Long id, @RequestBody Map<Object, Object> objectMap) {
        Optional<Endereco> enderecoOptional = enderecoService.findById(id);
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco not found.");
        }
        enderecoService.partialUpdate(enderecoOptional.get(), objectMap);
        return ResponseEntity.status(HttpStatus.OK).body("Endereco updated successfully.");
    }

}