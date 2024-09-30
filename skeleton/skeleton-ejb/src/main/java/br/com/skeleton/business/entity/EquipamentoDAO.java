package br.com.skeleton.business.entity;

import jakarta.ejb.Local;
import java.util.List;

@Local
public interface EquipamentoDAO {

    void create(Equipamento equipamento);

    Equipamento findById(Long id);

    List<Equipamento> findAll();

    void update(Equipamento equipamento);

    void delete(Long id);
}

