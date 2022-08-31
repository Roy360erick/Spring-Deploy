package com.example.entregable02.Repositories;

import com.example.entregable02.Entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILaptopRepository extends JpaRepository<Laptop,Long> {
}
