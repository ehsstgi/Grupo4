/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.controller;

import br.com.sistema.rodoviario.facades.MotoristaFacade;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import br.com.sistema.rodoviario.controller.util.JsfUtil;
import br.com.sistema.rodoviario.controller.util.PagingInfo;
import br.com.sistema.rodoviario.entidades.Motorista;
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
public class MotoristaController {

    public MotoristaController() {
        pagingInfo = new PagingInfo();
        converter = new MotoristaConverter();
    }
    private Motorista motorista = null;
    private List<Motorista> motoristaItems = null;
    private MotoristaFacade jpaController = null;
    private MotoristaConverter converter = null;
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

    public MotoristaFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (MotoristaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "motoristaJpa");
        }
        return jpaController;
    }

    public SelectItem[] getMotoristaItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getMotoristaItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Motorista getMotorista() {
        if (motorista == null) {
            motorista = (Motorista) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentMotorista", converter, null);
        }
        if (motorista == null) {
            motorista = new Motorista();
        }
        return motorista;
    }

    public String listSetup() {
        reset(true);
        return "motorista_list";
    }

    public String createSetup() {
        reset(false);
        motorista = new Motorista();
        return "motorista_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(motorista);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Motorista was successfully created.");
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
        return scalarSetup("motorista_detail");
    }

    public String editSetup() {
        return scalarSetup("motorista_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        motorista = (Motorista) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentMotorista", converter, null);
        if (motorista == null) {
            String requestMotoristaString = JsfUtil.getRequestParameter("jsfcrud.currentMotorista");
            JsfUtil.addErrorMessage("The motorista with id " + requestMotoristaString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String motoristaString = converter.getAsString(FacesContext.getCurrentInstance(), null, motorista);
        String currentMotoristaString = JsfUtil.getRequestParameter("jsfcrud.currentMotorista");
        if (motoristaString == null || motoristaString.length() == 0 || !motoristaString.equals(currentMotoristaString)) {
            String outcome = editSetup();
            if ("motorista_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit motorista. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(motorista);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Motorista was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentMotorista");
        java.math.BigDecimal id = new java.math.BigDecimal(idAsString);
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
                JsfUtil.addSuccessMessage("Motorista was successfully deleted.");
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

    public List<Motorista> getMotoristaItems() {
        if (motoristaItems == null) {
            getPagingInfo();
            motoristaItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return motoristaItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "motorista_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "motorista_list";
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
        motorista = null;
        motoristaItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Motorista newMotorista = new Motorista();
        String newMotoristaString = converter.getAsString(FacesContext.getCurrentInstance(), null, newMotorista);
        String motoristaString = converter.getAsString(FacesContext.getCurrentInstance(), null, motorista);
        if (!newMotoristaString.equals(motoristaString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
