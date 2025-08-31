Feature: Login de usuario
  Como usuario registrado quiero iniciar sesión para acceder a mi cuenta

  Background:
    Given que el usuario está en la página de login

  Scenario: Autenticación exitosa con credenciales válidas
    Given existe un usuario con email "user@example.com" y password "Correcta"
    When el usuario intenta iniciar sesión con email "user@example.com" y password "Correcta"
    And hace clic en el botón "Ingresar"
    Then el usuario es redirigido a su panel personal

  Scenario: Inicio de sesión exitoso con correo en mayúsculas (insensible al caso)
    Given existe un usuario con email "usuario.valido@email.com" y password "pass123"
    When el usuario intenta iniciar sesión con email "Usuario.Valido@EMAIL.COM" y password "pass123"
    And hace clic en el botón "Ingresar"
    Then el usuario es redirigido a su panel personal

  Scenario Outline: Mensaje genérico para credenciales inválidas
    When el usuario intenta iniciar sesión con email "<email>" y password "<password>"
    And hace clic en el botón "Ingresar"
    Then el usuario debe ver el mensaje de error "Correo o contraseña incorrectos"

    Examples:
      | email                      | password        |
      | usuario.valido@email.com   | pass_incorrecto |
      | no.existe@email.com        | cualquier_pass  |
      | Usuario.Valido@email.com   | pass_incorrecto |
      | usuario.valido@email.com   |                 |