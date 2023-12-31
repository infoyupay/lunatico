/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.fxmview;

import com.yupay.lunatico.model.DoiType;
import com.yupay.lunatico.model.ModelView;
import com.yupay.lunatico.model.Person;
import javafx.beans.property.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * JavaFX Model view for Person entities.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxPersonMV extends ModelView<Person, FxPersonMV> {
    /**
     * The autogenerated ID.
     */
    private final LongProperty id =
            new SimpleLongProperty(this, "id");
    /**
     * The type of DOI.
     */
    private final ObjectProperty<DoiType> doiType =
            new SimpleObjectProperty<>(this, "doiType");
    /**
     * The number of DOI.
     */
    private final StringProperty doiNum =
            new SimpleStringProperty(this, "doiNum");
    /**
     * Person's name.
     */
    private final StringProperty name =
            new SimpleStringProperty(this, "name");
    /**
     * Holds false if entity is no longer valid.
     */
    private final BooleanProperty active =
            new SimpleBooleanProperty(this, "active");

    /**
     * Constructor to copy information from a model entity.
     *
     * @param model the model entity.
     */
    public FxPersonMV(@NotNull Person model) {
        fromModel(model);
    }

    /**
     * Default no-op constructor.
     */
    public FxPersonMV() {
    }

    /**
     * Static factory to create new instances with active flag true.
     *
     * @return a new instance, never null.
     */
    @Contract(value = "->new", pure = true)
    public static @NotNull FxPersonMV blank() {
        var r = new FxPersonMV();
        r.setActive(true);
        return r;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #id}.get();
     */
    public final long getId() {
        return id.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param id value to assign into {@link #id}.
     */
    public final void setId(long id) {
        this.id.set(id);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #id}.
     */
    public final LongProperty idProperty() {
        return id;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #doiType}.get();
     */
    public final DoiType getDoiType() {
        return doiType.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param doiType value to assign into {@link #doiType}.
     */
    public final void setDoiType(DoiType doiType) {
        this.doiType.set(doiType);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #doiType}.
     */
    public final ObjectProperty<DoiType> doiTypeProperty() {
        return doiType;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #doiNum}.get();
     */
    public final String getDoiNum() {
        return doiNum.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param doiNum value to assign into {@link #doiNum}.
     */
    public final void setDoiNum(String doiNum) {
        this.doiNum.set(doiNum);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #doiNum}.
     */
    public final StringProperty doiNumProperty() {
        return doiNum;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #name}.get();
     */
    public final String getName() {
        return name.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param name value to assign into {@link #name}.
     */
    public final void setName(String name) {
        this.name.set(name);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #name}.
     */
    public final StringProperty nameProperty() {
        return name;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #active}.get();
     */
    public final boolean isActive() {
        return active.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param active value to assign into {@link #active}.
     */
    public final void setActive(boolean active) {
        this.active.set(active);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #active}.
     */
    public final BooleanProperty activeProperty() {
        return active;
    }

    @Override
    public @NotNull FxPersonMV deepCopy() {
        var r = new FxPersonMV();
        r.setActive(isActive());
        r.setDoiNum(getDoiNum());
        r.setDoiType(getDoiType());
        r.setId(getId());
        r.setName(getName());
        return r;
    }

    @Override
    public @NotNull Person toModel() {
        var r = new Person();
        r.setActive(isActive());
        r.setDoiNum(getDoiNum());
        r.setDoiType(getDoiType());
        r.setId(getId());
        r.setName(getName());
        return r;
    }

    @Override
    public void fromModel(@NotNull Person m) {
        setActive(m.isActive());
        setDoiNum(m.getDoiNum());
        setDoiType(m.getDoiType());
        setId(m.getId());
        setName(m.getName());
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxPersonMV that &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
