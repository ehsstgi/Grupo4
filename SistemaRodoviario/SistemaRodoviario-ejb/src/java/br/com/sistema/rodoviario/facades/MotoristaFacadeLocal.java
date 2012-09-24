/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.facades;

import br.com.sistema.rodoviario.entidades.Motorista;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface MotoristaFacadeLocal {

    void create(Motorista motorista);

    void edit(Motorista motorista);

    void remove(Motorista motorista);

    Motorista find(Object id);

    List<Motorista> findAll();

    List<Motorista> findRange(int[] range);

    int count();
    
}
