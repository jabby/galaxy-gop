package fr.jabbytechs.hackathon.galaxygop.strategy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.gson.Gson;

import fr.jabbytechs.hackathon.galaxygop.model.Galaxy;
import fr.jabbytechs.hackathon.galaxygop.model.Planet;

public class StrategyHelperTest {

	@Test
	public void testNearestPlanet() throws Exception {
		
		Gson gson = new Gson();
		Galaxy galaxy = gson.fromJson("{\"planets\":[{\"id\":1,\"x\":782.075044621,\"y\":361.976866726,\"owner\":0,\"units\":134,\"mu\":134,\"gr\":2,\"classe\":\"J\",\"tr\":null},{\"id\":2,\"x\":889.3380670525,\"y\":43.0,\"owner\":1,\"units\":100,\"mu\":200,\"gr\":5,\"classe\":\"M\",\"tr\":null},{\"id\":3,\"x\":674.812022183,\"y\":680.953733455,\"owner\":2,\"units\":100,\"mu\":200,\"gr\":5,\"classe\":\"M\",\"tr\":null},{\"id\":4,\"x\":113.83408543910001,\"y\":257.224445641,\"owner\":0,\"units\":84,\"mu\":84,\"gr\":1,\"classe\":\"M\",\"tr\":null},{\"id\":5,\"x\":1450.316003799,\"y\":466.729287814,\"owner\":0,\"units\":84,\"mu\":84,\"gr\":1,\"classe\":\"M\",\"tr\":null},{\"id\":6,\"x\":755.4439317294999,\"y\":519.414040774,\"owner\":0,\"units\":2,\"mu\":80,\"gr\":2,\"classe\":\"L\",\"tr\":null},{\"id\":7,\"x\":808.706157506,\"y\":204.539692681,\"owner\":0,\"units\":2,\"mu\":80,\"gr\":2,\"classe\":\"L\",\"tr\":null},{\"id\":8,\"x\":716.820471822,\"y\":58.05379083865,\"owner\":0,\"units\":31,\"mu\":40,\"gr\":1,\"classe\":\"K\",\"tr\":null},{\"id\":9,\"x\":847.3296174134999,\"y\":665.899942615,\"owner\":0,\"units\":31,\"mu\":40,\"gr\":1,\"classe\":\"K\",\"tr\":null},{\"id\":10,\"x\":530.5251097991,\"y\":551.8923166210001,\"owner\":0,\"units\":77,\"mu\":77,\"gr\":1,\"classe\":\"H\",\"tr\":null},{\"id\":11,\"x\":1033.624979439,\"y\":172.0614168325,\"owner\":0,\"units\":77,\"mu\":77,\"gr\":1,\"classe\":\"H\",\"tr\":null},{\"id\":12,\"x\":1557.6500892355,\"y\":122.49315814869999,\"owner\":0,\"units\":35,\"mu\":40,\"gr\":1,\"classe\":\"M\",\"tr\":null},{\"id\":13,\"x\":6.5,\"y\":601.4605753060001,\"owner\":0,\"units\":35,\"mu\":40,\"gr\":1,\"classe\":\"M\",\"tr\":null},{\"id\":14,\"x\":60.3382232128,\"y\":168.5634841525,\"owner\":0,\"units\":28,\"mu\":160,\"gr\":4,\"classe\":\"D\",\"tr\":null},{\"id\":15,\"x\":1503.8118660240002,\"y\":555.390249301,\"owner\":0,\"units\":28,\"mu\":160,\"gr\":4,\"classe\":\"D\",\"tr\":null},{\"id\":16,\"x\":218.550339176,\"y\":370.490960575,\"owner\":0,\"units\":28,\"mu\":40,\"gr\":1,\"classe\":\"D\",\"tr\":null},{\"id\":17,\"x\":1345.5997500595001,\"y\":353.462772877,\"owner\":0,\"units\":28,\"mu\":40,\"gr\":1,\"classe\":\"D\",\"tr\":null},{\"id\":18,\"x\":671.6292340614999,\"y\":429.257621842,\"owner\":0,\"units\":25,\"mu\":200,\"gr\":5,\"classe\":\"M\",\"tr\":null},{\"id\":19,\"x\":892.520855174,\"y\":294.69611161119997,\"owner\":0,\"units\":25,\"mu\":200,\"gr\":5,\"classe\":\"M\",\"tr\":null},{\"id\":2,\"x\":174.12698997265,\"y\":541.426769575,\"owner\":0,\"units\":44,\"mu\":80,\"gr\":2,\"classe\":\"D\",\"tr\":null},{\"id\":21,\"x\":1390.0230992635,\"y\":182.52696387909998,\"owner\":0,\"units\":44,\"mu\":80,\"gr\":2,\"classe\":\"D\",\"tr\":null},{\"id\":22,\"x\":1214.708122576,\"y\":321.84859747120004,\"owner\":0,\"units\":27,\"mu\":160,\"gr\":4,\"classe\":\"M\",\"tr\":null},{\"id\":23,\"x\":349.4419666634,\"y\":402.105135982,\"owner\":0,\"units\":27,\"mu\":160,\"gr\":4,\"classe\":\"M\",\"tr\":null}],\"fleets\":[],\"config\":{\"id\":43,\"turn\":0,\"maxTurn\":200}}", Galaxy.class);
		Map<Integer, Planet> neutralPlanets = galaxy.getPlanets().stream().filter(p -> p.getOwner() == 0)
				.collect(Collectors.toMap(Planet::getId, Function.identity()));

		Map<Integer, Planet>empirePlanets = galaxy.getPlanets().stream().filter(p -> p.getOwner() == 1)
				.collect(Collectors.toMap(Planet::getId, Function.identity()));

		Map<Integer, Planet>rebelPlanets = galaxy.getPlanets().stream().filter(p -> p.getOwner() != 1 && p.getOwner() != 0)
				.collect(Collectors.toMap(Planet::getId, Function.identity()));

		List<Planet> enemyPlanets = new ArrayList<>();
		enemyPlanets.addAll(neutralPlanets.values());
		enemyPlanets.addAll(rebelPlanets.values());		
			
		assertEquals(StrategyHelper.findNearestPlanet(empirePlanets.values(), enemyPlanets).get(0)._2, StrategyHelper.findNearestPlanets(empirePlanets.values(), enemyPlanets, 1).get(0)._2.get(0));
	}
	
}
