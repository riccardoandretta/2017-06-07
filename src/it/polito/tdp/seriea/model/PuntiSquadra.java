package it.polito.tdp.seriea.model;

public class PuntiSquadra implements Comparable<PuntiSquadra>{

	private Team team;
	private int punteggio;

	public PuntiSquadra(Team team, int punteggio) {
		super();
		this.team = team;
		this.punteggio = punteggio;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuntiSquadra other = (PuntiSquadra) obj;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s, %d\n", team, punteggio);
	}

	@Override
	public int compareTo(PuntiSquadra altro) {
		return -(this.getPunteggio()-altro.getPunteggio());
	}

}
