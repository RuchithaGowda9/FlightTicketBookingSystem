package com.crimsonlogic.flightticketbookingsystem.util;

import java.util.HashMap;
import java.util.Map;

public class DistanceCalculator {

	private static final Map<String, Map<String, Integer>> distances = new HashMap<>();

	static {
		Map<String, Integer> fromDelhi = new HashMap<>();
		fromDelhi.put("Chhatrapati Shivaji Maharaj International Airport", 1032);
		fromDelhi.put("Kempegowda International Airport", 842);
		fromDelhi.put("Rajiv Gandhi International Airport", 707);
		fromDelhi.put("Netaji Subhas Chandra Bose International Airport", 1330);
		fromDelhi.put("John F. Kennedy International Airport", 11922);
		fromDelhi.put("London Heathrow Airport", 6771);
		distances.put("Indira Gandhi International Airport", fromDelhi);

		Map<String, Integer> fromMumbai = new HashMap<>();
		fromMumbai.put("Indira Gandhi International Airport", 1032);
		fromMumbai.put("Kempegowda International Airport", 842);
		fromMumbai.put("Rajiv Gandhi International Airport", 707);
		fromMumbai.put("Netaji Subhas Chandra Bose International Airport", 1330);
		fromMumbai.put("John F. Kennedy International Airport", 11922);
		fromMumbai.put("London Heathrow Airport", 6771);
		distances.put("Chhatrapati Shivaji Maharaj International Airport", fromMumbai);

		Map<String, Integer> fromBengaluru = new HashMap<>();
		fromBengaluru.put("Indira Gandhi International Airport", 1742);
		fromBengaluru.put("Chhatrapati Shivaji Maharaj International Airport", 842);
		fromBengaluru.put("Rajiv Gandhi International Airport", 647);
		fromBengaluru.put("Netaji Subhas Chandra Bose International Airport", 1461);
		fromBengaluru.put("John F. Kennedy International Airport", 12005);
		fromBengaluru.put("London Heathrow Airport", 6742);
		distances.put("Kempegowda International Airport", fromBengaluru);

		Map<String, Integer> fromHyderabad = new HashMap<>();
		fromHyderabad.put("Indira Gandhi International Airport", 1252);
		fromHyderabad.put("Chhatrapati Shivaji Maharaj International Airport", 707);
		fromHyderabad.put("Kempegowda International Airport", 647);
		fromHyderabad.put("Netaji Subhas Chandra Bose International Airport", 1406);
		fromHyderabad.put("John F. Kennedy International Airport", 11881);
		fromHyderabad.put("London Heathrow Airport", 6755);
		distances.put("Rajiv Gandhi International Airport", fromHyderabad);

		Map<String, Integer> fromKolkata = new HashMap<>();
		fromKolkata.put("Indira Gandhi International Airport", 1304);
		fromKolkata.put("Chhatrapati Shivaji Maharaj International Airport", 1330);
		fromKolkata.put("Kempegowda International Airport", 1461);
		fromKolkata.put("Rajiv Gandhi International Airport", 1406);
		fromKolkata.put("John F. Kennedy International Airport", 11846);
		fromKolkata.put("London Heathrow Airport", 6703);
		distances.put("Netaji Subhas Chandra Bose International Airport", fromKolkata);

		Map<String, Integer> fromJFK = new HashMap<>();
		fromJFK.put("Indira Gandhi International Airport", 11922);
		fromJFK.put("Chhatrapati Shivaji Maharaj International Airport", 11922);
		fromJFK.put("Kempegowda International Airport", 12005);
		fromJFK.put("Rajiv Gandhi International Airport", 11881);
		fromJFK.put("Netaji Subhas Chandra Bose International Airport", 11846);
		fromJFK.put("London Heathrow Airport", 5567);
		distances.put("John F. Kennedy International Airport", fromJFK);

		Map<String, Integer> fromHeathrow = new HashMap<>();
		fromHeathrow.put("Indira Gandhi International Airport", 6771);
		fromHeathrow.put("Chhatrapati Shivaji Maharaj International Airport", 6771);
		fromHeathrow.put("Kempegowda International Airport", 6742);
		fromHeathrow.put("Rajiv Gandhi International Airport", 6755);
		fromHeathrow.put("Netaji Subhas Chandra Bose International Airport", 6703);
		fromHeathrow.put("John F. Kennedy International Airport", 5567);
		distances.put("London Heathrow Airport", fromHeathrow);
	}

	public static Integer getDistance(String fromAirport, String toAirport) {
		return distances.getOrDefault(fromAirport, new HashMap<>()).get(toAirport);
	}

}
