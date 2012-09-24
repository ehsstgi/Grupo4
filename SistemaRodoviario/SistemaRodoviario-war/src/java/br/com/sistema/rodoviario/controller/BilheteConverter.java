/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.controller;

import br.com.sistema.rodoviario.entidades.Bilhete;
import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Raul
 */
public class BilheteConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        BigDecimal id = new BigDecimal(string);
        BilheteController controller = (BilheteController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "bilhete");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Bilhete) {
            Bilhete o = (Bilhete) object;
            return o.getIdBilhete() == null ? "" : o.getIdBilhete().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: br.com.sistema.rodoviario.entidades.Bilhete");
        }
    }
    
}
