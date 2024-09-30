package br.com.skeleton.business.entity;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class EquipamentoService {

    @EJB
    private EquipamentoDAO equipamentoDAO; 

    public void create(Equipamento equipamento) {
        equipamentoDAO.create(equipamento);
    }

    public Equipamento findById(Long id) {
        return equipamentoDAO.findById(id);
    }

    public List<Equipamento> findAll() {
        return equipamentoDAO.findAll();
    }

    public void update(Equipamento equipamento) {
        equipamentoDAO.update(equipamento);
    }

    public void delete(Long id) {
        equipamentoDAO.delete(id);
    }
}
