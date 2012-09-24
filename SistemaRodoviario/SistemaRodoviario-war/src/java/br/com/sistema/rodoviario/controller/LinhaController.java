/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.controller;

import br.com.sistema.rodoviario.facades.LinhaFacade;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import br.com.sistema.rodoviario.controller.util.JsfUtil;
import br.com.sistema.rodoviario.controller.util.PagingInfo;
import br.com.sistema.rodoviario.entidades.Linha;
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
public class LinhaController {

    public LinhaController() {
        pagingInfo = new PagingInfo();
        converter = new LinhaConverter();
    }
    private Linha linha = null;
    private List<Linha> linhaItems = null;
    private LinhaFacade jpaController = null;
    private LinhaConverter converter = null;
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

    public LinhaFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (LinhaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "linhaJpa");
        }
        return jpaController;
    }

    public SelectItem[] getLinhaItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getLinhaItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Linha getLinha() {
        if (linha == null) {
            linha = (Linha) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentLinha", converter, null);
        }
        if (linha == null) {
            linha = new Linha();
        }
        return linha;
    }

    public String listSetup() {
        reset(true);
        return "linha_list";
    }

    public String createSetup() {
        reset(false);
        linha = new Linha();
        return "linha_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(linha);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Linha was successfully created.");
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
        return scalarSetup("linha_detail");
    }

    public String editSetup() {
        return scalarSetup("linha_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        linha = (Linha) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentLinha", converter, null);
        if (linha == null) {
            String requestLinhaString = JsfUtil.getRequestParameter("jsfcrud.currentLinha");
            JsfUtil.addErrorMessage("The linha with id " + requestLinhaString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String linhaString = converter.getAsString(FacesContext.getCurrentInstance(), null, linha);
        String currentLinhaString = JsfUtil.getRequestParameter("jsfcrud.currentLinha");
        if (linhaString == null || linhaString.length() == 0 || !linhaString.equals(currentLinhaString)) {
            String outcome = editSetup();
            if ("linha_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit linha. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(linha);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Linha was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentLinha");
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
                JsfUtil.addSuccessMessage("Linha was successfully deleted.");
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

    public List<Linha> getLinhaItems() {
        if (linhaItems == null) {
            getPagingInfo();
            linhaItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return linhaItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "linha_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "linha_list";
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
        linha = null;
        linhaItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Linha newLinha = new Linha();
        String newLinhaString = converter.getAsString(FacesContext.getCurrentInstance(), null, newLinha);
        String linhaString = converter.getAsString(FacesContext.getCurrentInstance(), null, linha);
        if (!newLinhaString.equals(linhaString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
