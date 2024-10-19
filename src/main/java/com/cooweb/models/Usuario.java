package com.cooweb.models;

import java.util.Date;




import jakarta.persistence.Column;

import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class Usuario {
	@Column(name="nombre", nullable = false)
	private String nombre;
	
	@Column(name="apellido", nullable = false)
	private String apellido;

	@Column(name="dni", nullable = false)
	private String dni;
		
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="telefono", nullable = false)
	private String telefono;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="fecha_nacimiento", nullable = false)
	private String fechaNacimiento;

	
	
	@Column(name="direccion", nullable = false)
	private String direccion;
	

	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
//constructor con parametros y vacio

	public Usuario() {
			
	}
		
	public Usuario(String nombre, String apellido, String dni, String email, String telefono, String password, String fechaNacimiento,
			String direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
	}
	
}

	
