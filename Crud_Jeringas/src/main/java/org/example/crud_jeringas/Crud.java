package org.example.crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class Crud {
    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, Integer> colId;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colCorreo;
    @FXML private TableColumn<Usuario, String> colTelefono;
    @FXML private TableColumn<Usuario, String> colDireccion;

    @FXML private TextField idField;
    @FXML private TextField nombreField;
    @FXML private TextField correoField;
    @FXML private TextField telefonoField;
    @FXML private TextField direccionField;

    private final ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("numeroTelefonico"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        List<Usuario> usuariosDesdeBD = Conection.obtenerTodosLosUsuarios();
        listaUsuarios.addAll(usuariosDesdeBD);

        tablaUsuarios.setItems(listaUsuarios);
    }

    @FXML
    private void handleAgregarUsuario() {
        String nombre = nombreField.getText().trim();
        String correo = correoField.getText().trim();
        String numero_telefonico = telefonoField.getText().trim();
        String direccion = direccionField.getText().trim();

        if (nombre.isEmpty() || correo.isEmpty() || numero_telefonico.isEmpty() || direccion.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.", Alert.AlertType.ERROR);
            return;
        }

        boolean success = Conection.insertarUsuario(nombre, correo, numero_telefonico, direccion);
        if (!success) {
            mostrarAlerta("Error BD", "No se pudo guardar el usuario en la base de datos.", Alert.AlertType.ERROR);
            return;
        }

        // No conocemos el ID generado, así que puedes recargar la tabla o actualizar manualmente
        listaUsuarios.add(new Usuario(0, nombre, correo, numero_telefonico, direccion)); // Si tienes autoincremento en BD
        limpiarCampos();
    }

    @FXML
    private void handleEditarUsuario() {
        Usuario sel = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (sel == null) {
            mostrarAlerta("Advertencia", "Selecciona un usuario para editar.", Alert.AlertType.WARNING);
            return;
        }

        sel.setNombre(nombreField.getText().trim());
        sel.setCorreo(correoField.getText().trim());
        sel.setNumeroTelefonico(telefonoField.getText().trim());
        sel.setDireccion(direccionField.getText().trim());

        tablaUsuarios.refresh();
        limpiarCampos();
    }

    @FXML
    private void handleEliminarUsuario() {
        Usuario sel = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (sel != null) {
            listaUsuarios.remove(sel);
            // Aquí puedes también eliminarlo de la BD si tienes esa función
        } else {
            mostrarAlerta("Advertencia", "Selecciona un usuario para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void handleSubirUsuario() {
        int i = tablaUsuarios.getSelectionModel().getSelectedIndex();
        if (i > 0) {
            Usuario u = listaUsuarios.remove(i);
            listaUsuarios.add(i - 1, u);
            tablaUsuarios.getSelectionModel().select(i - 1);
        }
    }

    @FXML
    private void handleBajarUsuario() {
        int i = tablaUsuarios.getSelectionModel().getSelectedIndex();
        if (i < listaUsuarios.size() - 1) {
            Usuario u = listaUsuarios.remove(i);
            listaUsuarios.add(i + 1, u);
            tablaUsuarios.getSelectionModel().select(i + 1);
        }
    }

    private void limpiarCampos() {
        idField.clear();
        nombreField.clear();
        correoField.clear();
        telefonoField.clear();
        direccionField.clear();
    }

    private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
        Alert a = new Alert(tipo);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}