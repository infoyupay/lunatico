package com.yupay.lunatico.dao;

import com.yupay.lunatico.model.User;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * DAO Implementation for User.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOUserImpl implements DAO<User> {
    /**
     * Package-private constructor. Use {@link DAOFactory}
     */
    DAOUserImpl() {
    }

    @Override
    public @NotNull Class<User> entity() {
        return User.class;
    }

    /**
     * Checks a given user login matches password and is active.
     *
     * @param em       the entity manager object.
     * @param user     the user ID to check.
     * @param password the user password to check.
     * @return Optional containing the authenticated user.
     * {@snippet : Optional.empty()} if authentication fail
     * due non existing user, or password didn't match.
     */
    @Contract("_,_,_->new")
    public @NotNull Optional<User> authenticateUser(@NotNull EntityManager em,
                                                    @NotNull String user,
                                                    @NotNull String password) {
        var qry = em.createNativeQuery(
                "SELECT * FROM mmq_user U " +
                        "WHERE U.id = ?1" +
                        " AND U.active" +
                        " AND U.password = crypt(?2, U.password)",
                User.class);
        qry.setParameter(1, user);
        qry.setParameter(2, password);
        var r = qry.getResultStream().findAny();
        if (r.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(r.get() instanceof User usr ? usr : null);
        }
    }

    /**
     * Updates the user password after authenticating that user
     * is active and old password matches that from database.
     *
     * @param em          entity manager.
     * @param user        the user ID (login name).
     * @param oldPassword old user password.
     * @param newPassword new user password.
     * @return updated count.
     */
    public int updatePassword(@NotNull EntityManager em,
                              @NotNull String user,
                              @NotNull String oldPassword,
                              @NotNull String newPassword) {
        var qry = em.createNativeQuery(
                "UPDATE mmq_user SET " +
                        "password = crypt(?1, gen_salt('bf')) " +
                        "WHERE id = ?2" +
                        " AND active" +
                        " AND password = crypt(password, ?3)");
        qry.setParameter(1, newPassword);
        qry.setParameter(2, user);
        qry.setParameter(3, oldPassword);
        return qry.executeUpdate();
    }

    /**
     * Forces password update without authenticating user.
     *
     * @param em          the entity manager.
     * @param user        the user ID (login name).
     * @param newPassword the new password to write.
     * @return updated count.
     */
    public int forcePassword(@NotNull EntityManager em,
                             @NotNull String user,
                             @NotNull String newPassword) {
        var qry = em.createNativeQuery(
                "UPDATE mmq_user SET " +
                        "password = crypt(?, gen_salt('bf')) " +
                        "WHERE id = ?");
        qry.setParameter(1, newPassword);
        qry.setParameter(2, user);
        return qry.executeUpdate();
    }
}
