package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.Articulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends BaseRepository<Articulo,Long>{

}
