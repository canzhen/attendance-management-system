package dbentity;

// Generated 2015-12-10 22:12:38 by Hibernate Tools 4.0.0

/**
 * Tc generated by hbm2java
 */
public class Tc implements java.io.Serializable {

	private TcId id;
	private Integer checkTime;
	private Integer maxAbsence;

	public Tc() {
	}

	public Tc(TcId id) {
		this.id = id;
	}

	public Tc(TcId id, Integer checkTime, Integer maxAbsence) {
		this.id = id;
		this.checkTime = checkTime;
		this.maxAbsence = maxAbsence;
	}

	public TcId getId() {
		return this.id;
	}

	public void setId(TcId id) {
		this.id = id;
	}

	public Integer getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Integer checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getMaxAbsence() {
		return this.maxAbsence;
	}

	public void setMaxAbsence(Integer maxAbsence) {
		this.maxAbsence = maxAbsence;
	}

}