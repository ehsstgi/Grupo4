/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.facades;

import br.com.sistema.rodoviario.entidades.Passageiro;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Raul
 */
@Local
@Remote
@Stateless(mappedName="ejb/Passageiro")
public interface PassageiroFacadeLocal {

    void create(Passageiro passageiro);

    void edit(Passageiro passageiro);

    void remove(Passageiro passageiro);

    Passageiro find(Object id);

    List<Passageiro> findAll();

    List<Passageiro> findRange(int[] range);

    int count();
    
}
