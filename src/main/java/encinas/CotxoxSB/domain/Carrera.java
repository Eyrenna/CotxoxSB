package encinas.CotxoxSB.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_carreras")
public class Carrera {

	@Id
	@Column(name = "c_id")
	private Integer id = 0;

	@Column(name = "c_tarjeta_credito")
	private String tarjeta_credito = null;

	@Column(name = "c_origen")
	private String origen = null;

	@Column(name = "c_destino")
	private String destino = null;

	@Column(name = "c_distancia")
	private double distancia = 0d;

	@Column(name = "c_tiempo_esperado")
	private Integer tiempo_esperado = 0;

	@Column(name = "c_tiempo_carrera")
	private Integer tiempo_carrera = 0;

	@Column(name = "c_coste_total")
	private double coste_total = 0d;

	@Column(name = "c_propina")
	private Integer propina = 0;

	@ManyToOne
	@JoinColumn(name = "c_conductor")
	private Conductor conductor = null;

//Getters y Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTarjeta_credito() {
		return tarjeta_credito;
	}

	public void setTarjeta_credito(String tarjeta_credito) {
		this.tarjeta_credito = tarjeta_credito;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public Integer getTiempo_esperado() {
		return tiempo_esperado;
	}

	public void setTiempo_esperado(Integer tiempo_esperado) {
		this.tiempo_esperado = tiempo_esperado;
	}

	public Integer getTiempo_carrera() {
		return tiempo_carrera;
	}

	public void setTiempo_carrera(Integer tiempo_carrera) {
		this.tiempo_carrera = tiempo_carrera;
	}

	public double getCoste_total() {
		return coste_total;
	}

	public void setCoste_total(double coste_total) {
		this.coste_total = coste_total;
	}

	public Integer getPropina() {
		return propina;
	}

	public void setPropina(Integer propina) {
		this.propina = propina;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

}
