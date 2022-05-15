package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.service.impl.MatriculaServiceImpl;
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
@RequestMapping("/matriculas")
public class MatriculaController
{
    @Autowired
    private MatriculaServiceImpl service;

    @PostMapping
    public Matricula create(@Valid @RequestBody MatriculaForm form)
    {
        return service.create(form);
    }

    @GetMapping
    public List<Matricula> getAll(@RequestParam(value = "bairro", required = false) String bairro)
    {
        return service.getAll(bairro);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Matricula> get(@PathVariable long id)
    {
        Optional<Matricula> matricula = Optional.ofNullable(service.get(id));
        if (matricula.isPresent())
        {
            return new ResponseEntity<>(matricula.get(), HttpStatus.OK);
        }
        else
        {
            return null;
        }
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
