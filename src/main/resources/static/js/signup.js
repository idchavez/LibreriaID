document.addEventListener("DomContentLoad", function () {
    document.getElementById('signForm').addEventListener('submit', async function (event) {
        event.preventDefault(); // Evita que el formulario se envíe automáticamente

       // Obtener los valores del formulario
            const username = document.getElementById('usuario').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            // Hacer la solicitud POST a la API de registro
            try {
                const response = await fetch('/auth/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ usuario: username, email: email, password: password })
                });

                // Si la respuesta es exitosa, procesamos el JSON
                 if (response.ok) {
                    const data = await response.json();
                    
                    // Mostrar el mensaje de éxito si el token es null
                    const messageDiv = document.getElementById('message');
                    if (data.token === null) {
                        messageDiv.style.display = 'block';
                        messageDiv.innerHTML = `Usuario: ${data.username}. ${data.message}`;
                    } else {
                        // Si por alguna razón viene un token, puedes manejarlo de otra manera
                        console.log('Token recibido:', data.token);
                    }
                }  else {
                    // Manejar errores
                    console.error('Error al registrar el usuario');
                }
            } catch (error) {
                console.error('Error en la solicitud:', error);
            } 

    });
});


