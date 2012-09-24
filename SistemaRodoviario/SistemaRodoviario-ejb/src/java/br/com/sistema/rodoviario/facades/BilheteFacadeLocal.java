/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.facades;

import br.com.sistema.rodoviario.entidades.Bilhete;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface BilheteFacadeLocal {

    void create(Bilhete bilhete);

    void edit(Bilhete bilhete);

    void remove(Bilhete bilhete);

    Bilhete find(Object id);

    List<Bilhete> findAll();

    List<Bilhete> findRange(int[] range);

    int count();
    
}
