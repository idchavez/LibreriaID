/*function logout(){
    const token = sessionStorage.getItem('token');
    console.log("Token antes de eliminar: ", token);
    sessionStorage.removeItem('token');
    //debugger;
    const tokenDeleted = sessionStorage.getItem('token');
    console.log("Token despues de eliminar: ", tokenDeleted);
    //debugger;
    
    // Eliminar la cookie JSESSIONID
    document.cookie = "JSESSIONID=; Path=/; Expires=Thu, 01 Jan 1970 00:00:00 GMT;";
    
    // Redirigir a la página de login u otra página
    window.location.href = '/auth/login';
}*/
function logout() {
    const token = sessionStorage.getItem('token');
    console.log("Token antes de eliminar: ", token);
    
    //const fetch = require('node-fetch');
    
    // Realizar solicitud POST al endpoint de logout
    fetch('/auth/logout', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (response.ok) {
            console.log("Logout exitoso");
            sessionStorage.removeItem('token'); // Eliminar token del almacenamiento
            document.cookie = "JSESSIONID=; Path=/; Expires=Thu, 01 Jan 1970 00:00:00 GMT;"; // Eliminar cookie
            window.location.href = '/auth/login'; // Redirigir a la página de login
        } else {
            console.error("Error en el logout");
        }
    });
}


