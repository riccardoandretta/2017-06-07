package it.polito.tdp.seriea.model;

public class Match {
	
	private int id ;
	private Season season ;
	private Team homeTeam ;
	private Team awayTeam ;
	private String ftr ; // full time result (H, A, D)
	// è possibile aggiungere altri campi, se risulteranno necessari
	


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getFtr() {
		return ftr;
	}

	public void setFtr(String ftr) {
		this.ftr = ftr;
	}

	public Match(int id, Season season, Team homeTeam, Team awayTeam, String ftr) {
		super();
		this.id = id;
		this.season = season;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.ftr = ftr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}
