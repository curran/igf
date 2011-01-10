import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Country> countries = executeSPARQL();

		for (Country country : countries.values())
			System.out.println(country);
//		int uniqueResults = countries.size();
//		System.out.println("resultCount = " + resultCount);
//		System.out.println("uniqueResults = " + uniqueResults);
	}

	public static Map<String, Country> executeSPARQL() {
		Map<String, Country> countries = new ConcurrentHashMap<String, Country>();
//		String nameVar = "name";
		String latVar = "lat";
		String longVar = "long";
//		String populationEstimateVar = "populationEstimate";
		String countryVar = "country";

		StringBuilder b = new StringBuilder();
		b.append("\n");
		b.append("PREFIX owl: <http://www.w3.org/2002/07/owl#>\n");
		b.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n");
		b.append("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n");
		b.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n");
		b.append("PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n");
		b.append("PREFIX dc: <http://purl.org/dc/elements/1.1/>\n");
		b.append("PREFIX : <http://dbpedia.org/resource/>\n");
		b.append("PREFIX dbpedia2: <http://dbpedia.org/property/>\n");
		b.append("PREFIX dbpedia: <http://dbpedia.org/>\n");
		b.append("PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n");
		b.append("PREFIX dbo: <http://dbpedia.org/ontology/>\n");
		b.append("SELECT DISTINCT ?country ?populationEstimate ?lat ?long\n");

		b.append("WHERE {\n");
		b.append("  ?country <http://dbpedia.org/property/populationEstimate> ?populationEstimate.\n");
		b.append("  ?country rdf:type dbo:Country.\n");
		b.append("  ?country <http://www.w3.org/2003/01/geo/wgs84_pos#lat> ?lat.\n");
		b.append("  ?country <http://www.w3.org/2003/01/geo/wgs84_pos#long> ?long\n");
		b.append("}ORDER BY DESC(?populationEstimate)\n");
//		b.append("LIMIT 30\n");

		String queryString = b.toString();
		System.out.println("queryString = " + queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", query);
		int resultCount = 0;
		try {
			ResultSet results = qexec.execSelect();

			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				Resource countryResource = soln.getResource(countryVar);
				Literal latitude = soln.getLiteral(latVar);
				Literal longitude = soln.getLiteral(longVar);
				String uri = countryResource.getURI();
				String localName = countryResource.getLocalName();

				Country country = countries.get(uri);
				double lat = latitude.getDouble();
				double lon = longitude.getDouble();
				if (country == null)
					countries.put(uri, country = new Country(uri, lat, lon,
							localName));
				resultCount++;
			}
		} finally {
			qexec.close();
		}
		return countries;
	}
}