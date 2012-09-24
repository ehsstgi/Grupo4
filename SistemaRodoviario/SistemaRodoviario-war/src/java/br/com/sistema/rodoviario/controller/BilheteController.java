/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.controller;

import br.com.sistema.rodoviario.facades.BilheteFacade;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import br.com.sistema.rodoviario.controller.util.JsfUtil;
import br.com.sistema.rodoviario.controller.util.PagingInfo;
import br.com.sistema.rodoviario.entidades.Bilhete;
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
public class BilheteController {

    public BilheteController() {
        pagingInfo = new PagingInfo();
        converter = new BilheteConverter();
    }
    private Bilhete bilhete = null;
    private List<Bilhete> bilheteItems = null;
    private BilheteFacade jpaController = null;
    private BilheteConverter converter = null;
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

    public BilheteFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (BilheteFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "bilheteJpa");
        }
        return jpaController;
    }

    public SelectItem[] getBilheteItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getBilheteItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Bilhete getBilhete() {
        if (bilhete == null) {
            bilhete = (Bilhete) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentBilhete", converter, null);
        }
        if (bilhete == null) {
            bilhete = new Bilhete();
        }
        return bilhete;
    }

    public String listSetup() {
        reset(true);
        return "bilhete_list";
    }

    public String createSetup() {
        reset(false);
        bilhete = new Bilhete();
        return "bilhete_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(bilhete);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Bilhete was successfully created.");
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
        return scalarSetup("bilhete_detail");
    }

    public String editSetup() {
        return scalarSetup("bilhete_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        bilhete = (Bilhete) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentBilhete", converter, null);
        if (bilhete == null) {
            String requestBilheteString = JsfUtil.getRequestParameter("jsfcrud.currentBilhete");
            JsfUtil.addErrorMessage("The bilhete with id " + requestBilheteString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String bilheteString = converter.getAsString(FacesContext.getCurrentInstance(), null, bilhete);
        String currentBilheteString = JsfUtil.getRequestParameter("jsfcrud.currentBilhete");
        if (bilheteString == null || bilheteString.length() == 0 || !bilheteString.equals(currentBilheteString)) {
            String outcome = editSetup();
            if ("bilhete_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit bilhete. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(bilhete);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Bilhete was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentBilhete");
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
                JsfUtil.addSuccessMessage("Bilhete was successfully deleted.");
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

    public List<Bilhete> getBilheteItems() {
        if (bilheteItems == null) {
            getPagingInfo();
            bilheteItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return bilheteItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "bilhete_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "bilhete_list";
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
        bilhete = null;
        bilheteItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Bilhete newBilhete = new Bilhete();
        String newBilheteString = converter.getAsString(FacesContext.getCurrentInstance(), null, newBilhete);
        String bilheteString = converter.getAsString(FacesContext.getCurrentInstance(), null, bilhete);
        if (!newBilheteString.equals(bilheteString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
