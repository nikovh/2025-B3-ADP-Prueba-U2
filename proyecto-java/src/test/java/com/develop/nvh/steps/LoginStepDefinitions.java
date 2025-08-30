package com.develop.nvh.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LoginStepDefinitions {

    // Simulacion de una base de datos de usuarios
    private Map<String, String> userDatabase = new HashMap<>();

    // Simulacion del estado de la UI
    private String currentUserEmail;
    private String currentUserPassword;
    private String errorMessage;
    private String redirectionUrl;

    @Given("existe un usuario con email {string} y password {string}")
    public void existe_un_usuario_registrado(String email, String password) {
        userDatabase.put(email.toLowerCase(), password);
    }

    @Given("que el usuario está en la página de login")
    public void que_el_usuario_está_en_la_página_de_login() {
        this.currentUserEmail = null;
        this.currentUserPassword = null;
        this.errorMessage = null;
        this.redirectionUrl = null;
        System.out.println("Usuario en la página de login");
    }

    @When("el usuario intenta iniciar sesión con email {string} y password {string}")
    public void el_usuario_intenta_iniciar_sesión(String email, String password) {
        this.currentUserEmail = email;
        this.currentUserPassword = password;
    }

    @When("hace clic en el botón {string}")
    public void hace_clic_en_el_boton(String buttonName) {
        String emailToValidate = this.currentUserEmail != null ? this.currentUserEmail.toLowerCase() : "";
        String passwordToValidate = this.currentUserPassword != null ? this.currentUserPassword : "";

        if (userDatabase.containsKey(emailToValidate) && userDatabase.get(emailToValidate).equals(passwordToValidate)) {
            this.redirectionUrl = "/dashboard";
        } else {
            this.errorMessage = "Correo o contraseña incorrectos";
        }
    }

    @Then("el usuario es redirigido a su panel personal")
    public void el_usuario_es_redirigido_a_su_panel_personal() {
        assertNull(errorMessage, "No debería haber un mensaje de error en un login exitoso.");
        assertEquals("/dashboard", redirectionUrl, "El usuario no fue redirigido a la URL correcta.");
    }

    @Then("el usuario debe ver el mensaje de error {string}")
    public void el_usuario_debe_ver_el_mensaje_de_error(String expectedErrorMessage) {
        assertNull(redirectionUrl, "No debería haber redirección en un login fallido.");
        assertEquals(expectedErrorMessage, this.errorMessage, "El mensaje de error no es el esperado.");
    }
}