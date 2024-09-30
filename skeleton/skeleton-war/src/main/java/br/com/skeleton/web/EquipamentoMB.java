package br.com.skeleton.web;

import br.com.skeleton.business.entity.Equipamento;
import br.com.skeleton.business.entity.EquipamentoService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named("equipamentoMB") // Nome usado no XHTML
@ViewScoped // Escopo de requisição
public class EquipamentoMB implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @EJB
    private EquipamentoService equipamentoService;

    private Equipamento equipamento = new Equipamento();
    private List<Equipamento> equipamentos;

    // Método chamado após a construção do bean
    @PostConstruct
    public void init() {
        carregarEquipamentos(); // Carrega a lista de equipamentos
        carregarEquipamento(); // Carrega o equipamento para edição, se o ID estiver presente
    }

    // Método para carregar o equipamento a partir do parâmetro
    public void carregarEquipamento() {
        String parametro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (parametro != null && !parametro.isEmpty()) {
            try {
                Long id = Long.parseLong(parametro);
                equipamento = equipamentoService.findById(id);
            } catch (NumberFormatException e) {
                adicionarMensagemErro("ID do equipamento inválido.", "Erro ao carregar o equipamento.");
            } catch (Exception e) {
                adicionarMensagemErro("Não foi possível encontrar o equipamento.", "Erro ao carregar");
            }
        }
    }

    // Método para salvar o equipamento no banco de dados
    public void salvar() {
        try {
            // Se o equipamento já possui um ID, faz update; caso contrário, faz create
            if (equipamento.getId() != null) {
                equipamentoService.update(equipamento);
                adicionarMensagem("Equipamento atualizado com sucesso!", "");
            } else {
                equipamentoService.create(equipamento);
                adicionarMensagem("Equipamento criado com sucesso!", "");
            }

            equipamento = new Equipamento(); // Limpa o formulário após salvar ou atualizar
            carregarEquipamentos(); // Atualiza a lista de equipamentos
        } catch (Exception e) {
            adicionarMensagemErro("Não foi possível salvar o equipamento.", "Erro ao salvar");
        }
    }

    public void validateValor(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Double valor = (Double) value;

        // Verifica se o valor é igual a zero
        if (valor != null && valor == 0.0) {
            FacesMessage msg = new FacesMessage("*O valor não pode ser igual a 0.0");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    // Método para carregar a lista de equipamentos
    public void carregarEquipamentos() {
        equipamentos = equipamentoService.findAll();
    }

    // Método para exibir mensagens de sucesso
    private void adicionarMensagem(String sumario, String detalhe) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, sumario, detalhe);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }

    // Método para exibir mensagens de erro
    private void adicionarMensagemErro(String sumario, String detalhe) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, sumario, detalhe);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }

    // Getters e Setters
    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<Equipamento> getEquipamentos() {
        if (equipamentos == null) {
            carregarEquipamentos(); // Carrega a lista de equipamentos se não estiver inicializada
        }
        return equipamentos;
    }
}
