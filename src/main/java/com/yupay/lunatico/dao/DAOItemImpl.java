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

import com.yupay.lunatico.model.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * DAO implementation for Item entities.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOItemImpl implements DAO<Item> {
    /**
     * Package protected constructor.
     * To instanciate use {@link DAOFactory}
     */
    DAOItemImpl() {
    }

    @Override
    public @NotNull Class<Item> entity() {
        return Item.class;
    }

    /**
     * Searches for one or more items in the database.
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
    public @NotNull Stream<Item> search(@Nullable String text,
                                        boolean justActive,
                                        @NotNull EntityManager em) {
        var cb = em.getCriteriaBuilder();
        var qry = cb.createQuery(entity());
        var root = qry.from(entity());
        var wheres = new ArrayList<Predicate>();
        if (text != null && !text.isBlank()) {
            var whereText = text.matches("\\d+")
                    ? cb.equal(root.get("id"), Long.parseLong(text))
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
