package br.edu.unifip.ecommerceapi.services;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifip.ecommerceapi.models.Endereco;
import br.edu.unifip.ecommerceapi.repositories.EnderecoRepository;

@Service
public class EnderecoService {

    final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Long id){
        return enderecoRepository.findById(id);
    }

    @Transactional
    public void Delete(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }

    public Endereco partialUpdate(Endereco endereco, Map<Object, Object> objectMap) {
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findRequiredField(Endereco.class, (String) key);
            field.setAccessible(true);

            try {
                value = BigDecimal.valueOf((double) value);
            }
            catch(ClassCastException ignored) { }
            ReflectionUtils.setField(field, endereco, value);
        });
        return enderecoRepository.save(endereco);
    }
}