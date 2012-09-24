/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.facades;

import br.com.sistema.rodoviario.entidades.Assento;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Raul
 */
@Remote
@Local
@Stateless(mappedName="ejb/Assento")
public interface AssentoFacadeLocal {

    void create(Assento assento);

    void edit(Assento assento);

    void remove(Assento assento);

    Assento find(Object id);

    List<Assento> findAll();

    List<Assento> findRange(int[] range);

    int count();
    
}
