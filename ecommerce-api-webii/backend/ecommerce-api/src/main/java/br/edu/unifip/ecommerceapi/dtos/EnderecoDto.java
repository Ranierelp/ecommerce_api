package br.edu.unifip.ecommerceapi.dtos;

import br.edu.unifip.ecommerceapi.models.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDto(
        Long id,
        @NotBlank
        String uf,
        @NotBlank
        String bairro,
        @NotBlank
        String rua
)   {
    public EnderecoDto(Endereco endereco){
        this(endereco.getId(), endereco.getUf(), endereco.getBairro(), endereco.getRua());
    }
}