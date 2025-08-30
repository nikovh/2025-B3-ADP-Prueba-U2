Feature: Login de usuario
  Como usuario registrado quiero iniciar sesión para acceder a mi cuenta

  Background:
    Given el servicio de autenticación está disponible
    And estoy en la página de login

  Scenario: Autenticación exitosa con credenciales válidas
    Given existe un usuario activo con email "user@example.com" y contraseña "Correcta"
    When ingreso el email "user@example.com" y la contraseña "Correcta"
    And envío el formulario de login
    Then veo la página de inicio
    And no veo mensajes de error

  Scenario: Inicio de sesión exitoso con correo en mayúsculas (insensible al caso)
    When el usuario introduce el correo "Usuario.Valido@EMAIL.COM" y la contraseña "pass123"
    And hace clic en el botón "Ingresar"
    Then veo la página de inicio

  Scenario Outline: Mensaje genérico para credenciales inválidas
    Given el sistema no confirma si el usuario existe por seguridad
    When ingreso el email "<email>" y la contraseña "<password>"
    And envío el formulario de login
    Then veo el mensaje "Credenciales inválidas"
    And permanezco en la página de login

    Examples:
      | correo                     | contrasena        | mensaje_error                     |
      | usuario.valido@email.com   | pass_incorrecto   | "Correo o contraseña incorrectos" |
      | no.existe@email.com        | cualquier_pass    | "Correo o contraseña incorrectos" |
      | Usuario.Valido@email.com   | pass_incorrecto   | "Correo o contraseña incorrectos" |
      | usuario.valido@email.com   |                   | "Correo o contraseña incorrectos" |