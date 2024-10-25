<?php
// login.php

session_start();

// Obtener datos del formulario
$email = $_POST['email'];
$password = $_POST['password'];

// URL de la API Java (modifícala según tu configuración)
$url = "http://localhost:8080/api/pacientes";

// Crear el array con los datos
$data = array(
    "email" => $email,
    "password" => $password
);

// Configurar la solicitud HTTP para enviar los datos en formato JSON
$options = array(
    'http' => array(
        'header'  => "Content-Type: application/json\r\n",
        'method'  => 'POST',
        'content' => json_encode($data)
    )
);

$context  = stream_context_create($options);
$result = file_get_contents($url, false, $context);
$response = json_decode($result, true);

// Procesar la respuesta
if ($http_response_header[0] === "HTTP/1.1 200 OK") {
    // Guardar los datos en la sesión si el login fue exitoso
    $_SESSION['paciente'] = $response;
    header("Location: index.html"); // Redirigir a una página protegida
    exit();
} else {
    // Mensaje de error si la autenticación falla
    echo "Error: " . $response;
}
?>
