/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.controller;

import br.com.sistema.rodoviario.facades.AssentoFacade;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import br.com.sistema.rodoviario.controller.util.JsfUtil;
import br.com.sistema.rodoviario.controller.util.PagingInfo;
import br.com.sistema.rodoviario.entidades.Assento;
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
public class AssentoController {

    public AssentoController() {
        pagingInfo = new PagingInfo();
        converter = new AssentoConverter();
    }
    private Assento assento = null;
    private List<Assento> assentoItems = null;
    private AssentoFacade jpaController = null;
    private AssentoConverter converter = null;
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

    public AssentoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (AssentoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "assentoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getAssentoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getAssentoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Assento getAssento() {
        if (assento == null) {
            assento = (Assento) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAssento", converter, null);
        }
        if (assento == null) {
            assento = new Assento();
        }
        return assento;
    }

    public String listSetup() {
        reset(true);
        return "assento_list";
    }

    public String createSetup() {
        reset(false);
        assento = new Assento();
        return "assento_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(assento);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Assento was successfully created.");
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
        return scalarSetup("assento_detail");
    }

    public String editSetup() {
        return scalarSetup("assento_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        assento = (Assento) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAssento", converter, null);
        if (assento == null) {
            String requestAssentoString = JsfUtil.getRequestParameter("jsfcrud.currentAssento");
            JsfUtil.addErrorMessage("The assento with id " + requestAssentoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String assentoString = converter.getAsString(FacesContext.getCurrentInstance(), null, assento);
        String currentAssentoString = JsfUtil.getRequestParameter("jsfcrud.currentAssento");
        if (assentoString == null || assentoString.length() == 0 || !assentoString.equals(currentAssentoString)) {
            String outcome = editSetup();
            if ("assento_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit assento. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(assento);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Assento was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentAssento");
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
                JsfUtil.addSuccessMessage("Assento was successfully deleted.");
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

    public List<Assento> getAssentoItems() {
        if (assentoItems == null) {
            getPagingInfo();
            assentoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return assentoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "assento_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "assento_list";
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
        assento = null;
        assentoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Assento newAssento = new Assento();
        String newAssentoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newAssento);
        String assentoString = converter.getAsString(FacesContext.getCurrentInstance(), null, assento);
        if (!newAssentoString.equals(assentoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
