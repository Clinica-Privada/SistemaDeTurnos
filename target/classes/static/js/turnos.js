// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	getTurnos();
 	$('#turno').DataTable();
  
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
	
	let botonEliminar='<a href=# onclick="eliminarTurno('+turno.id_turno +')" class=¨btn btn-danger btn-circle btn-sm¨><i class="fas fa-trash"></i> </a>';
	let turnoHtml='<tr><td>'+ turno.id_turno +'</td><td>' + turno.id_especialidad + '</td><td>' + turno.id_profesional + '</td><td>'
		+ turno.fecha_turno + '</td><td>' + turno.hora_turno + '</td><td>' + turno.estado_turno+ '</td><td>' + botonEliminar + '</td></tr>';
		
	listadoHTML+=turnoHtml;
  }

	document.querySelector('#turnos tbody').outerHTML=listadoHTML
}

async function solicitarTurno(){
	//creamos una variable de tipo array que contenga los datos
	let datos={};
	//capturamos los datos con el id de los inputs
	datos.especialidad=document.getElementById('especialidad').value;	
	datos.dia=document.getElementById('dia').value;
	datos.horario=document.getElementById('hora').value;
	
	
	//acá definiremos el estado del turno ...
	//datos.estado=;
	
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
	if(!confirm('Desea eliminar turno?')){
		return;
	}
	
	const request= await fetch('api/turnos/'+id_paciente, {
		method: 'DELETE',
		headers: {
			'Accept':'application/json',
			'Content-Type': 'application/json'
		},
	});
	
	location.reload();
}
