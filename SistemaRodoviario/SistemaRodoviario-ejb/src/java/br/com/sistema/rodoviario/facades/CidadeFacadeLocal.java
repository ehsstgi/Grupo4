/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.facades;

import br.com.sistema.rodoviario.entidades.Cidade;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface CidadeFacadeLocal {

    void create(Cidade cidade);

    void edit(Cidade cidade);

    void remove(Cidade cidade);

    Cidade find(Object id);

    List<Cidade> findAll();

    List<Cidade> findRange(int[] range);

    int count();
    
}
