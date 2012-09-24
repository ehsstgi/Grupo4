/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.facades;

import br.com.sistema.rodoviario.entidades.Plataforma;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Raul
 */
@Stateless
public class PlataformaFacade extends AbstractFacade<Plataforma> implements PlataformaFacadeLocal {
    @PersistenceContext(unitName = "SistemaRodoviario-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlataformaFacade() {
        super(Plataforma.class);
    }
    
}
