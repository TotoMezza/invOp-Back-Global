package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.EntidadBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository <E extends EntidadBase, ID extends Serializable> extends JpaRepository<E, ID> {
}