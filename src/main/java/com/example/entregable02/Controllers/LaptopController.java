package com.example.entregable02.Controllers;

import com.example.entregable02.Entities.Laptop;
import com.example.entregable02.Repositories.ILaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private ILaptopRepository laptopRepository;

    public LaptopController(ILaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public ResponseEntity findAll(){
        List<Laptop> laptopList = this.laptopRepository.findAll();
        return ResponseEntity.ok(laptopList);

    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        if(!this.laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Optional<Laptop> laptop = this.laptopRepository.findById(id);
        return ResponseEntity.ok(laptop);
    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> save(@RequestBody Laptop laptop){
        if(laptop == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.laptopRepository.save(laptop));
    }

    @PutMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> update(@PathVariable Long id, @RequestBody Laptop laptop){
        if(laptop == null){
            return ResponseEntity.badRequest().build();
        }
        if(!this.laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Optional<Laptop> result = this.laptopRepository.findById(id);
        Laptop updateLaptop = result.get();
        updateLaptop.setDisco(laptop.getDisco());
        updateLaptop.setPrice(laptop.getPrice());
        updateLaptop.setFabricante(laptop.getFabricante());
        updateLaptop.setMemoria(laptop.getMemoria());
        return ResponseEntity.ok(this.laptopRepository.save(updateLaptop));
    }

    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if(!this.laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        Optional<Laptop> result = this.laptopRepository.findById(id);
        this.laptopRepository.delete(result.get());
        return ResponseEntity.ok().build();
    }

    @ApiIgnore
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        List<Laptop> result = this.laptopRepository.findAll();
        this.laptopRepository.deleteAll(result);
        return ResponseEntity.ok().build();
    }
}
