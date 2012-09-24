/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.facades;

import br.com.sistema.rodoviario.entidades.Linha;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface LinhaFacadeLocal {

    void create(Linha linha);

    void edit(Linha linha);

    void remove(Linha linha);

    Linha find(Object id);

    List<Linha> findAll();

    List<Linha> findRange(int[] range);

    int count();
    
}
