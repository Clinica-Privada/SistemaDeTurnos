<?php
// login.php

session_start();

// Obtener datos del formulario
$email = $_POST['email'];
$password = $_POST['password'];

// URL del endpoint de login en Java
$url = "http://localhost:8080/iniciarSesion";

// Crear el array con los datos y codificarlo para enviarlo como application/x-www-form-urlencoded
$data = http_build_query(array(
    "email" => $email,
    "password" => $password
));

// Configurar la solicitud HTTP
$options = array(
    'http' => array(
        'header'  => "Content-Type: application/x-www-form-urlencoded\r\n",
        'method'  => 'POST',
        'content' => $data,
    )
);

$context  = stream_context_create($options);
$result = file_get_contents($url, false, $context);

// Verificar la respuesta
if ($result !== false && strpos($http_response_header[0], "200") !== false) {
    // Login exitoso: redirigir al usuario a una página protegida
    header("Location: index.html");
    exit();
} else {
    // Error de autenticación: mostrar un mensaje
    echo "Usuario o contraseña incorrectos.";
}
?>

