package br.com.skeleton.business.entity;

import jakarta.ejb.Stateless;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class EquipamentoDAOImpl implements EquipamentoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(Equipamento equipamento) {
        entityManager.persist(equipamento);
    }

    @Override
    public Equipamento findById(Long id) {
        return entityManager.find(Equipamento.class, id);
    }

    @Override
    public List<Equipamento> findAll() {
        return entityManager.createQuery("SELECT e FROM Equipamento e", Equipamento.class).getResultList();
    }

    @Override
    @Transactional
    public void update(Equipamento equipamento) {
        entityManager.merge(equipamento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Equipamento equipamento = findById(id);
        if (equipamento != null) {
            entityManager.remove(equipamento);
        }
    }
}
