package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.Proveedor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends BaseRepository<Proveedor,Long>{
}
