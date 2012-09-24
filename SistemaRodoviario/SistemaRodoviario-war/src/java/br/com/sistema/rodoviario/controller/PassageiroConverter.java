/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.controller;

import br.com.sistema.rodoviario.entidades.Passageiro;
import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Raul
 */
public class PassageiroConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        BigDecimal id = new BigDecimal(string);
        PassageiroController controller = (PassageiroController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "passageiro");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: br.com.sistema.rodoviario.entidades.Passageiro");
        }
    }
    
}