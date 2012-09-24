/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.facades;

import br.com.sistema.rodoviario.entidades.Plataforma;
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
@Stateless(mappedName="ejb/Plataforma")
public interface PlataformaFacadeLocal {

    void create(Plataforma plataforma);

    void edit(Plataforma plataforma);

    void remove(Plataforma plataforma);

    Plataforma find(Object id);

    List<Plataforma> findAll();

    List<Plataforma> findRange(int[] range);

    int count();
    
}
