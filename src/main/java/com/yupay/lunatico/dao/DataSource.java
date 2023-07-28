/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.dao;

import com.yupay.lunatico.toolbox.LocalFiles;
import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * A static fancy class to hold a shared EntityManagerFactory.
 * It'll be closed by application upon stop.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class DataSource {
    /**
     * The entity manager factory.
     */
    private static EntityManagerFactory EMF;

    /**
     * Getter for entity manager factory.
     * The EMF is lazily created on demand,
     * so it will return a new EMF if it's null.
     *
     * @return the entity manager factory.
     */
    @NotNull
    private static EntityManagerFactory entityManagerFactory() {
        if (EMF == null) {
            try {
                EMF = Persistence.createEntityManagerFactory("lunatico", LocalFiles.scanCnx());
            } catch (IOException e) {
                throw new PersistenceException("Unable to load connection parameters from local file.");
            }
        }
        return EMF;
    }

    /**
     * Short-hand for
     * {@snippet  :
     *  entityManagerFactory().createEntityManager();
     * }
     *
     * @return the new entity manager. Never null. If EMF is null, it'll be created.
     */
    @Contract("->new")
    public static @NotNull EntityManager em() {
        return entityManagerFactory().createEntityManager();
    }

    /**
     * Closes the entity manager factory and sets to null.
     * It may be invoked to update the connection parameters,
     * or to stop the application while shutting down.
     */
    public static void closeAll() {
        if (EMF != null) {
            EMF.close();
            EMF = null;
        }
    }

    /**
     * Convenient method to check if a transaction
     * is not null and active, so it'll be rolled back.
     *
     * @param et the transaction to safely rollback.
     */
    public static void checkAndRollback(@Nullable EntityTransaction et) {
        if (et != null && et.isActive()) et.rollback();
    }
}
