package com.cooweb.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	private int dni;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="contraseña")
	private String contraseña;
	
	private Date fechaNacimiento;
	
	private String direccion;
	
	//hasta aca atributos

	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	//hasta aca getters y setters
	
	
	public Usuario(Long id, int dni, String nombre, String apellido, String email, String telefono, String contraseña,
			Date fechaNacimiento, String direccion) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.contraseña = contraseña;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion; }
		
		public Usuario() {}
		
		//constructor con parametros y vacio
		//y ahora metodos
		
		public void registrarse(String email, String password) {
		    this.nombre = nombre;
		    this.email = email;
		    this.contraseña = contraseña;
		    // Lógica adicional para guardar el usuario en la base de datos
		    // Si usas un servicio o repositorio, puedes llamar a un método que guarde este objeto en la BD
		    System.out.println("Usuario registrado exitosamente.");
		}
		public boolean iniciarSesion(String email, String password) {
		    if (this.email.equals(email) && this.contraseña.equals(contraseña)) {
		        System.out.println("Inicio de sesión exitoso.");
		        return true;
		    } else {
		        System.out.println("Error: email o contraseña incorrectos.");
		        return false;
		    }
		}
		public void cerrarSesion() {
		    // Lógica para finalizar la sesión
		    System.out.println("Sesión cerrada.");
		}
		
		public void actualizarDatos(String nuevoNombre, String nuevoEmail, String nuevaContraseña) {
		    if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
		        this.nombre = nuevoNombre;
		    }
		    if (nuevoEmail != null && !nuevoEmail.isEmpty()) {
		        this.email = nuevoEmail;
		    }
		    if (nuevaContraseña != null && !nuevaContraseña.isEmpty()) {
		        this.contraseña = nuevaContraseña;
		    }
		    System.out.println("Datos actualizados exitosamente.");
		}

		
		
		
	}

	
