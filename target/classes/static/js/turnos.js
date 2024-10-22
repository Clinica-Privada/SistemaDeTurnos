// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	getPacientes();
 	$('#pacientes').DataTable();
  
});



async function getTurnos(){
	
	//solicitamos información de los turnos	
  const request = await fetch('api/turnos', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });
  
  //acá hay que solicitar información de los pacientes
  
  const turnos = await request.json();
  
  let listadoHTML='';
  
  for(let turno of turnos){
	
	let botonEliminar='<a href=# onclick="eliminarTurno('+paciente.id_paciente +')" class=¨btn btn-danger btn-circle btn-sm¨><i class="fas fa-trash"></i> </a>';
	let usuarioHtml='<tr><td>'+ paciente.id_paciente +'</td><td>' + paciente.nombre + '</td><td>' + paciente.apellido + '</td><td>'
		+ paciente.email + '</td><td>' + paciente.telefono + '</td><td>' + paciente.direccion+ '</td><td>' + paciente.fecha_nacimiento + '</td><td>' + botonEliminar + '</td></tr>';
		
	listadoHTML+=usuarioHtml;
  }

	document.querySelector('#pacientes tbody').outerHTML=listadoHTML
}

async function solicitarTurno(){
	//creamos una variable de tipo array que contenga los datos
	let datos={};
	//capturamos los datos con el id de los inputs
	datos.especialidad=document.getElementById('txtNombre').value;	
	datos.dia=document.getElementById('txtApellido').value;
	datos.horario=document.getElementById('txtEmail').value;
	
  const request = await fetch('api/turnos', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body:JSON.stringify(datos)
  });
  
  //const usuarios = await request.json();
  
  //let listadoHTML='';
}


async function eliminarTurno(id_paciente){
	
	//alert(id);
	if(!confirm('Desea eliminar paciente?')){
		return;
	}
	
	const request= await fetch('api/pacientes/'+id_paciente, {
		method: 'DELETE',
		headers: {
			'Accept':'application/json',
			'Content-Type': 'application/json'
		},
	});
	
	location.reload();
}
