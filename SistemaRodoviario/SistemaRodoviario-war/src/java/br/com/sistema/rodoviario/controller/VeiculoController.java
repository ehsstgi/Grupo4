/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.controller;

import br.com.sistema.rodoviario.facades.VeiculoFacade;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import br.com.sistema.rodoviario.controller.util.JsfUtil;
import br.com.sistema.rodoviario.controller.util.PagingInfo;
import br.com.sistema.rodoviario.entidades.Veiculo;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Raul
 */
public class VeiculoController {

    public VeiculoController() {
        pagingInfo = new PagingInfo();
        converter = new VeiculoConverter();
    }
    private Veiculo veiculo = null;
    private List<Veiculo> veiculoItems = null;
    private VeiculoFacade jpaController = null;
    private VeiculoConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "SistemaRodoviario-warPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public VeiculoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (VeiculoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "veiculoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getVeiculoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getVeiculoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Veiculo getVeiculo() {
        if (veiculo == null) {
            veiculo = (Veiculo) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentVeiculo", converter, null);
        }
        if (veiculo == null) {
            veiculo = new Veiculo();
        }
        return veiculo;
    }

    public String listSetup() {
        reset(true);
        return "veiculo_list";
    }

    public String createSetup() {
        reset(false);
        veiculo = new Veiculo();
        return "veiculo_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(veiculo);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Veiculo was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("veiculo_detail");
    }

    public String editSetup() {
        return scalarSetup("veiculo_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        veiculo = (Veiculo) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentVeiculo", converter, null);
        if (veiculo == null) {
            String requestVeiculoString = JsfUtil.getRequestParameter("jsfcrud.currentVeiculo");
            JsfUtil.addErrorMessage("The veiculo with id " + requestVeiculoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String veiculoString = converter.getAsString(FacesContext.getCurrentInstance(), null, veiculo);
        String currentVeiculoString = JsfUtil.getRequestParameter("jsfcrud.currentVeiculo");
        if (veiculoString == null || veiculoString.length() == 0 || !veiculoString.equals(currentVeiculoString)) {
            String outcome = editSetup();
            if ("veiculo_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit veiculo. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(veiculo);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Veiculo was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentVeiculo");
        Integer id = new Integer(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Veiculo was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Veiculo> getVeiculoItems() {
        if (veiculoItems == null) {
            getPagingInfo();
            veiculoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return veiculoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "veiculo_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "veiculo_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        veiculo = null;
        veiculoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Veiculo newVeiculo = new Veiculo();
        String newVeiculoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newVeiculo);
        String veiculoString = converter.getAsString(FacesContext.getCurrentInstance(), null, veiculo);
        if (!newVeiculoString.equals(veiculoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
