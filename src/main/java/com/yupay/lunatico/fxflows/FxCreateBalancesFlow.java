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
