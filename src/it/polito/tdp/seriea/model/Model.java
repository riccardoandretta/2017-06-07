package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {

	private List<Season> stagioni;
	private SerieADAO serieaDAO;
	private Graph<Team, DefaultWeightedEdge> grafo;
	private List<PuntiSquadra> classifica;

	public Model() {
		serieaDAO = new SerieADAO();

	}

	public List<Season> getSeasons() {
		stagioni = serieaDAO.listSeasons();
		return stagioni;
	}

	public List<PuntiSquadra> creaGrafo(Season s) {
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

		List<Team> teams = serieaDAO.listTeamsBySeason(s);

		Graphs.addAllVertices(grafo, teams);

		List<Match> matches = serieaDAO.listMatchesBySeason(s);

		for (Match m : matches) {
			if (m.getFtr().equals("H")) {
				Graphs.addEdgeWithVertices(grafo, m.getHomeTeam(), m.getAwayTeam(), 1);
			} else if (m.getFtr().equals("D")) {
				Graphs.addEdgeWithVertices(grafo, m.getHomeTeam(), m.getAwayTeam(), 0);
			} else if (m.getFtr().equals("A")) {
				Graphs.addEdgeWithVertices(grafo, m.getHomeTeam(), m.getAwayTeam(), -1);
			}
		}

		classifica = new ArrayList<>();

		for (Team t1 : teams) {
			for (Team t2 : teams) {
				if (grafo.containsEdge(t1, t2) && (grafo.getEdgeWeight(grafo.getEdge(t1, t2))) == 0) {
					classifica.add(new PuntiSquadra(t1, 1));
					classifica.add(new PuntiSquadra(t2, 1));
				}
				if (grafo.containsEdge(t1, t2) && (grafo.getEdgeWeight(grafo.getEdge(t1, t2))) == 1) {
					classifica.add(new PuntiSquadra(t1, 3));
				}
				if (grafo.containsEdge(t1, t2) && (grafo.getEdgeWeight(grafo.getEdge(t1, t2))) == -1) {
					classifica.add(new PuntiSquadra(t2, 3));
				}
			}
		}

		return classifica;
		// System.out.println("Vertici: "+grafo.vertexSet().size()+" Archi: "+
		// grafo.edgeSet().size());

	}

	public List<PuntiSquadra> calcolaClassifica(List<PuntiSquadra> classifica) {
		
		List<PuntiSquadra> result = new ArrayList<>();
		
		for (PuntiSquadra p : classifica) {
			int somma = 0;
			for (PuntiSquadra p2 : classifica) {
				if (p.getTeam().equals(p2.getTeam())) {
					somma += p.getPunteggio();
				}
			}
			if(result != null && !result.contains(p))
				result.add(new PuntiSquadra(p.getTeam(), somma));
		}
		Collections.sort(result);
		
		return result;

	}

}
