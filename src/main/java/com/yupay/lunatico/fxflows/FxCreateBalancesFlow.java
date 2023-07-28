/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxforms.EasyAlert;
import com.yupay.lunatico.fxforms.ErrorHandler;
import com.yupay.lunatico.toolbox.BalanceSnapshooter;
import jakarta.persistence.EntityTransaction;

/**
 * JavaFX workflow to create (and persist) the current snapshot of the system.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxCreateBalancesFlow {
    /**
     * Initiates the flow.
     */
    public void go() {
        //Outline transaction.
        EntityTransaction et = null;
        //Create entity manager.
        try (var em = DataSource.em()) {
            //Create transaction.
            et = em.getTransaction();
            //Begin transaction.
            et.begin();

            var dao = DAOFactory.balance();
            //Retrieve current system state.
            dao.listAll(em)
                    .map(new BalanceSnapshooter())
                    .forEach(b -> dao.insertOne(em, b));

            //Commit transaction.
            et.commit();

            //On successful completion
            EasyAlert.info()
                    .withTitle("Operación Completada")
                    .withHeaderText("Se completó satisfactoriamente la creación de una instantánea del sistema.")
                    .buttonOkOnly()
                    .run();
        } catch (RuntimeException e) {
            //Rollback transaction.
            DataSource.checkAndRollback(et);
            //Handle exception.
            new ErrorHandler()
                    .withBriefing("Ha ocurrido un error al generar la instantánea de saldos.")
                    .accept(e);
        }
    }
}
