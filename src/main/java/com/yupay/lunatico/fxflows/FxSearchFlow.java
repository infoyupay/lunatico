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
import com.yupay.lunatico.fxforms.FxSearchCard;
import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.fxmview.FxPersonMV;
import com.yupay.lunatico.fxtools.FxSearchCards;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * The JavaFX flow to search items.
 *
 * @param <T> type erasure of the searched item.
 * @author InfoYupay SACS
 * @version 1.0
 */
public abstract class FxSearchFlow<T> implements Function<String, Optional<T>> {
    /**
     * Creates an instance to search ACTIVE warehouse items.
     *
     * @return searchItem(true);
     */
    public static @NotNull FxSearchFlow<FxItemMV> searchItemActive() {
        return searchItem(true);
    }

    /**
     * Creates an instance to search warehouse items.
     *
     * @param justActive if true only ACTIVE elements will be retrieved.
     * @return a new search flow.
     */
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull FxSearchFlow<FxItemMV> searchItem(boolean justActive) {
        return new FxSearchFlow<>() {
            @Override
            protected @NotNull List<FxItemMV> runQuery(@NotNull EntityManager em,
                                                       @NotNull String text) {
                return DAOFactory.item()
                        .search(text, justActive, em)
                        .map(FxItemMV::new)
                        .toList();
            }

            @Override
            protected @NotNull FxSearchCard<FxItemMV> searchCard(@NotNull List<FxItemMV> data) {
                return FxSearchCards.forItem(data);
            }
        };
    }

    /**
     * Creates an instance to search ACTIVE persons.
     *
     * @return searchPerson(true);
     */
    public static @NotNull FxSearchFlow<FxPersonMV> searchPersonActive() {
        return searchPerson(true);
    }

    /**
     * Creates an instance to search persons.
     *
     * @param justActive if true only ACTIVE elements will be retrieved.
     * @return a new search flow.
     */
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull FxSearchFlow<FxPersonMV> searchPerson(boolean justActive) {
        return new FxSearchFlow<>() {
            @Override
            protected @NotNull List<FxPersonMV> runQuery(@NotNull EntityManager em,
                                                         @NotNull String text) {
                return DAOFactory.person()
                        .search(text, justActive, em)
                        .map(FxPersonMV::new)
                        .toList();
            }

            @Override
            protected @NotNull FxSearchCard<FxPersonMV> searchCard(@NotNull List<FxPersonMV> data) {
                return FxSearchCards.forPerson(data);
            }
        };
    }

    /**
     * Effectively runs the query.
     *
     * @param em   entity manager object.
     * @param text text to search.
     * @return the list of results.
     */
    protected abstract @NotNull List<T> runQuery(
            @NotNull EntityManager em,
            @NotNull String text);

    /**
     * Creates a new search card. It'll be invoked
     * when {@link #runQuery(EntityManager, String)}
     * retrieves more than one result, so the user
     * may choose one of them.
     *
     * @param data the data to show in the search card.
     * @return a new card, never null.
     */
    @Contract("_->new")
    protected abstract @NotNull FxSearchCard<T> searchCard(@NotNull List<T> data);

    @Override
    public Optional<T> apply(String s) {
        if (s == null || s.isBlank()) return Optional.empty();
        try (var em = DataSource.em()) {
            var r = runQuery(em, s.strip());
            var size = r.size();
            if (size == 0) {
                return Optional.empty();
            } else if (size == 1) {
                return r.stream().findFirst();
            } else {
                return searchCard(r).showAndWait();
            }
        }
    }
}
