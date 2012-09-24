/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.controller;

import br.com.sistema.rodoviario.entidades.Plataforma;
import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Raul
 */
public class PlataformaConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        BigDecimal id = new BigDecimal(string);
        PlataformaController controller = (PlataformaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "plataforma");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Plataforma) {
            Plataforma o = (Plataforma) object;
            return o.getIdPlataforma() == null ? "" : o.getIdPlataforma().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: br.com.sistema.rodoviario.entidades.Plataforma");
        }
    }
    
}
