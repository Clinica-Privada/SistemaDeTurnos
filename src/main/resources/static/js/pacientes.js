// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	getPacientes();
 	$('#pacientes').DataTable();
  
});



async function getPacientes(){
	
  const request = await fetch('api/pacientes', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });
  
  const pacientes = await request.json();
  
  let listadoHTML='';
  
  for(let paciente of pacientes){
	
	let botonEliminar='<a href=# onclick="eliminarPaciente('+paciente.numeroHistoriaClinica +')" class=¨btn btn-danger btn-circle btn-sm¨><i class="fas fa-trash"></i> </a>';
	let usuarioHtml='<tr><td>'+ paciente.numeroHistoriaClinica +'</td><td>' + paciente.nombre + '</td><td>' + paciente.apellido + '</td><td>'
		+ paciente.email + '</td><td>' + paciente.telefono + '</td><td>' + paciente.direccion+ '</td><td>' + paciente.fechaNacimiento + '</td><td>' + botonEliminar + '</td></tr>';
		
	listadoHTML+=usuarioHtml;
  }

	document.querySelector('#pacientes tbody').outerHTML=listadoHTML
}


async function eliminarPaciente(id){
	//alert(id);
	
	if(!confirm('Desea eliminar paciente?'+id)){
		return;
	}
	
	
	const request= await fetch('eliminarCuenta/'+id, {
		method: 'DELETE',
		headers: {
			'Accept':'application/json',
			'Content-Type': 'application/json'
		},
	});
	
	location.reload();
}
