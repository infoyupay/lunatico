/**
 * The lunatico application monolythic module.
 *
 * @author InfoYupay SACS
 */
module lunatico {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires jakarta.persistence;
    requires eclipselink;
    requires org.postgresql.jdbc;

    requires org.jetbrains.annotations;

    exports com.yupay.lunatico.fxforms;
    exports com.yupay.lunatico.fxmview;
    exports com.yupay.lunatico.model;

    opens com.yupay.lunatico.fxforms
            to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
    opens com.yupay.lunatico.fxmview
            to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
}