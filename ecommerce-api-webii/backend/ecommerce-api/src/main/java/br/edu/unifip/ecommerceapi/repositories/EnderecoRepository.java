package br.edu.unifip.ecommerceapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifip.ecommerceapi.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    Optional<Endereco> findById(Long id);
    void delete(Endereco endereco);
}