/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.controller;

import br.com.sistema.rodoviario.facades.PassageiroFacade;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import br.com.sistema.rodoviario.controller.util.JsfUtil;
import br.com.sistema.rodoviario.controller.util.PagingInfo;
import br.com.sistema.rodoviario.entidades.Passageiro;
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
public class PassageiroController {

    public PassageiroController() {
        pagingInfo = new PagingInfo();
        converter = new PassageiroConverter();
    }
    private Passageiro passageiro = null;
    private List<Passageiro> passageiroItems = null;
    private PassageiroFacade jpaController = null;
    private PassageiroConverter converter = null;
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

    public PassageiroFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (PassageiroFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "passageiroJpa");
        }
        return jpaController;
    }

    public SelectItem[] getPassageiroItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getPassageiroItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Passageiro getPassageiro() {
        if (passageiro == null) {
            passageiro = (Passageiro) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPassageiro", converter, null);
        }
        if (passageiro == null) {
            passageiro = new Passageiro();
        }
        return passageiro;
    }

    public String listSetup() {
        reset(true);
        return "passageiro_list";
    }

    public String createSetup() {
        reset(false);
        passageiro = new Passageiro();
        return "passageiro_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(passageiro);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Passageiro was successfully created.");
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
        return scalarSetup("passageiro_detail");
    }

    public String editSetup() {
        return scalarSetup("passageiro_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        passageiro = (Passageiro) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPassageiro", converter, null);
        if (passageiro == null) {
            String requestPassageiroString = JsfUtil.getRequestParameter("jsfcrud.currentPassageiro");
            JsfUtil.addErrorMessage("The passageiro with id " + requestPassageiroString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String passageiroString = converter.getAsString(FacesContext.getCurrentInstance(), null, passageiro);
        String currentPassageiroString = JsfUtil.getRequestParameter("jsfcrud.currentPassageiro");
        if (passageiroString == null || passageiroString.length() == 0 || !passageiroString.equals(currentPassageiroString)) {
            String outcome = editSetup();
            if ("passageiro_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit passageiro. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(passageiro);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Passageiro was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPassageiro");
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
                JsfUtil.addSuccessMessage("Passageiro was successfully deleted.");
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

    public List<Passageiro> getPassageiroItems() {
        if (passageiroItems == null) {
            getPagingInfo();
            passageiroItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return passageiroItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "passageiro_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "passageiro_list";
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
        passageiro = null;
        passageiroItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Passageiro newPassageiro = new Passageiro();
        String newPassageiroString = converter.getAsString(FacesContext.getCurrentInstance(), null, newPassageiro);
        String passageiroString = converter.getAsString(FacesContext.getCurrentInstance(), null, passageiro);
        if (!newPassageiroString.equals(passageiroString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
