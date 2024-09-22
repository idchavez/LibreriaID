document.addEventListener("DOMContentLoaded", function() {
document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const usuario = document.getElementById('usuario').value;
    const password = document.getElementById('password').value;
    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({usuario, password})
    })
            .then(response => response.json())
            .then(data => {
                if (data.token) {
                    sessionStorage.setItem('token', data.token);
                    window.location.href = '/home';
                } else {
                    showError('Error: Credenciales incorrectas.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                showError('Login failed: Something went wrong. Please try again.');
            });
});
});
function showError(message) {
    const errorDiv = document.getElementById('error-message');
    errorDiv.innerText = message;
    errorDiv.classList.remove('d-none');  // Muestra el mensaje de error
}