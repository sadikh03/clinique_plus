module sn.sadikh.examen_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires org.hibernate.orm.core;
    requires java.sql;
    opens sn.sadikh.examen_javafx.controller.utilisateur to javafx.fxml;

    requires javafx.base;
    requires static lombok;
    requires java.persistence;
    requires com.github.librepdf.openpdf;

    // Autorise Hibernate à accéder aux entités [cite: 4, 7]
    opens sn.sadikh.examen_javafx.model to org.hibernate.orm.core, javafx.base;

    opens sn.sadikh.examen_javafx to javafx.fxml;
    exports sn.sadikh.examen_javafx;
    opens sn.sadikh.examen_javafx.controller.patient to javafx.fxml;
    opens sn.sadikh.examen_javafx.controller.rendez_vous to javafx.fxml;
    opens sn.sadikh.examen_javafx.controller.consultation to javafx.fxml;
    opens sn.sadikh.examen_javafx.controller.facture to javafx.fxml;
    opens sn.sadikh.examen_javafx.validator to javafx.fxml;
}