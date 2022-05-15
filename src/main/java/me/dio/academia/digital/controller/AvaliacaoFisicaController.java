package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController
{
    @Autowired
    private AvaliacaoFisicaServiceImpl service;

    @PostMapping
    public AvaliacaoFisica create(@Valid @RequestBody AvaliacaoFisicaForm form)
    {
        return service.create(form);
    }

    @GetMapping
    public List<AvaliacaoFisica> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoFisica> get(@PathVariable long id)
    {
        Optional<AvaliacaoFisica> avaliacaoFisica = Optional.ofNullable(service.get(id));
        if (avaliacaoFisica.isPresent())
        {
            return new ResponseEntity<>(avaliacaoFisica.get(), HttpStatus.OK);
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public AvaliacaoFisica update(@PathVariable long id, @RequestBody AvaliacaoFisicaUpdateForm updateForm)
    {
        return service.update(id, updateForm);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id)
    {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
