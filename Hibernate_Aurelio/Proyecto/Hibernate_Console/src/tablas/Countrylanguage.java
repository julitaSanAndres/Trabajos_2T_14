package tablas;

// Generated 04-feb-2014 1:46:27 by Hibernate Tools 3.4.0.CR1

/**
 * Countrylanguage generated by hbm2java
 */
public class Countrylanguage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CountrylanguageId id;
	private String isOfficial;
	private float percentage;

	public Countrylanguage() {
	}

	public Countrylanguage(CountrylanguageId id, String isOfficial,
			float percentage) {
		this.id = id;
		this.isOfficial = isOfficial;
		this.percentage = percentage;
	}

	public CountrylanguageId getId() {
		return this.id;
	}

	public void setId(CountrylanguageId id) {
		this.id = id;
	}

	public String getIsOfficial() {
		return this.isOfficial;
	}

	public void setIsOfficial(String isOfficial) {
		this.isOfficial = isOfficial;
	}

	public float getPercentage() {
		return this.percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

}