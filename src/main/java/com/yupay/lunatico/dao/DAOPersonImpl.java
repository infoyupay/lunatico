package com.yupay.lunatico.dao;

import com.yupay.lunatico.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * DAO implementation for Person entities.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOPersonImpl implements DAO<Person> {
    @Override
    public @NotNull Class<Person> entity() {
        return Person.class;
    }

    /**
     * Searches for one or more persons in the database.
     *
     * @param text       the text to find.
     * @param justActive if true only active elements will
     *                   be retrieved, any other item will
     *                   be ignored.
     * @param em         the entity manager object.
     * @return the result stream.
     */
    @SuppressWarnings("DuplicatedCode")
    @Contract("_,_,_->new")
    public @NotNull Stream<Person> search(@Nullable String text,
                                          boolean justActive,
                                          @NotNull EntityManager em) {
        var cb = em.getCriteriaBuilder();
        var qry = cb.createQuery(entity());
        var root = qry.from(entity());
        var wheres = new ArrayList<Predicate>();
        if (text != null && !text.isBlank()) {
            var whereText = text.matches("\\d+")
                    ? cb.like(root.get("doiNum"), text + "%")
                    : cb.like(root.get("name"), "%" + text.toUpperCase() + "%");
            wheres.add(whereText);
        }
        if (justActive) {
            var whereActive = cb.isTrue(root.get("active"));
            wheres.add(whereActive);
        }
        if (wheres.size() == 1) {
            qry.where(wheres.get(0));
        } else if (wheres.size() > 1) {
            qry.where(cb.and(wheres.toArray(Predicate[]::new)));
        }
        return em.createQuery(qry).setMaxResults(100).getResultStream();
    }
}
