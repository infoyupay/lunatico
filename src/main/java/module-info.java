/**
 * The lunatico application monolythic module.
 *
 * @author InfoYupay SACS
 */
module lunatico {
    /*==============================================*
     * JavaFX dependencies.                         *
     *  NOTICE! WARNING!                            *
     * The requires directives have been            *
     * carefully selected, so the transitive        *
     * dependencies added with theese directives    *
     * maps all required javaFX modules.            *
     * BE CAREFUL WHEN TOUCHING THIS.               *
     *==============================================*/
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    /*======================*
     * JPA and JDBC driver.  *
     *=======================*/
    requires jakarta.persistence;
    requires eclipselink;
    requires org.postgresql.jdbc;

    /*==============*
     * IDE Tools.   *
     *==============*/
    requires org.jetbrains.annotations;

    /*======================*
     * Export directives.   *
     *======================*/
    //Forms package.
    exports com.yupay.lunatico.fxforms;
    //Model view entities.
    exports com.yupay.lunatico.fxmview;
    //Tools for javaFX.
    exports com.yupay.lunatico.fxtools;
    //Model entities.
    exports com.yupay.lunatico.model;
    //Security stuff.
    exports com.yupay.lunatico.security;

    /*===========================*
     * Open directives - JavaFX. *
     *===========================*/
    opens com.yupay.lunatico.fxforms
            to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
    opens com.yupay.lunatico.fxmview
            to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
    opens com.yupay.lunatico.fxtools
            to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
    opens com.yupay.lunatico.fxflows
            to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
}