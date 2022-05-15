package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
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
@RequestMapping("/alunos")
public class AlunoController
{
    @Autowired
    private AlunoServiceImpl service;

    @GetMapping
    public List<Aluno> getAll(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento)
    {
        return service.getAll(dataDeNascimento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> get(@PathVariable long id)
    {
        Optional<Aluno> aluno = Optional.ofNullable(service.get(id));
        if (aluno.isPresent())
        {
            return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
        }
        else
        {
            return null;
        }
    }

    @PostMapping
    public Aluno create(@Valid @RequestBody AlunoForm form)
    {
        return service.create(form);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id)
    {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/update/{id}")
    public Aluno update(@PathVariable long id, @RequestBody AlunoUpdateForm updateForm)
    {
        return service.update(id, updateForm);
    }

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id)
    {
        return service.getAllAvaliacaoFisicaId(id);
    }

}
