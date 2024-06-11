package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Prediccion;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.PrediccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrediccionServiceImpl extends BaseServiceImpl<Prediccion,Long> implements PrediccionService{

    @Autowired
    private PrediccionRepository prediccionRepository;

    public PrediccionServiceImpl(BaseRepository<Prediccion, Long> baseRepository) {
        super(baseRepository);
    }
}
