package encinas.CotxoxSB.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_conductores")
public class Conductor {

//Atributos
	@Id
	@Column(name="co_nombre")
	private String nombre = null;

	@Column(name="co_modelo")
	private String modelo = null;
	
	@Column(name="co_matricula")
	private String matricula = null;
	
	@Column(name="co_valoracion_media")
	private double valoracion_media = 0d;
	
	@Column(name="co_ocupado")
	private Integer ocupado = 0;
	
	
//Getters y Setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public double getValoracion_media() {
		return valoracion_media;
	}

	public void setValoracion_media(double valoracion_media) {
		this.valoracion_media = valoracion_media;
	}

	public Integer getOcupado() {
		return ocupado;
	}

	public void setOcupado(Integer ocupado) {
		this.ocupado = ocupado;
	}
	

}
