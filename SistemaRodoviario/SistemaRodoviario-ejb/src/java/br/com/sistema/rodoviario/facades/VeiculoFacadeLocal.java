/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.facades;

import br.com.sistema.rodoviario.entidades.Veiculo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface VeiculoFacadeLocal {

    void create(Veiculo veiculo);

    void edit(Veiculo veiculo);

    void remove(Veiculo veiculo);

    Veiculo find(Object id);

    List<Veiculo> findAll();

    List<Veiculo> findRange(int[] range);

    int count();
    
}
