package pattern_maker;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

public class MatchDemoTest {
	
	@Test
	public void black_matches_black() {
		Color one = new Color(0,0,0);
		Color two = new Color(0,0,0);
		double output  = MatchDemo.RGBDistance(one, two);
		Assert.assertEquals(0, output, 0.001);
	}
	@Test
	public void white_matches_white() {
		Color one = new Color(255,255,255);
		Color two = new Color(255,255,255);
		double output  = MatchDemo.RGBDistance(one, two);
		Assert.assertEquals(0, output, 0.001);
	}
	
	@Test
	public void checking_differeces_calc_one_red() {
		Color one = new Color(0,0,0);
		Color two = new Color(1,0,0);
		double output  = MatchDemo.RGBDistance(one, two);
		Assert.assertEquals(1, output, 0.001);
	}
	
	@Test
	public void checking_differeces_calc_all_one() {
		Color one = new Color(0,0,0);
		Color two = new Color(1,1,1);
		double output  = MatchDemo.RGBDistance(one, two);
		Assert.assertEquals(1.732, output, 0.001);
	}
	
	@Test
	public void checking_differeces_calc_all_one_flipped() {
		Color one = new Color(1,1,1);
		Color two = new Color(0,0,0);
		double output  = MatchDemo.RGBDistance(one, two);
		Assert.assertEquals(1.732, output, 0.001);
	}
	
	@Test
	public void checking_differeces_different_amounts_one_side() {
		Color one = new Color(0,0,0);
		Color two = new Color(54,62,74);
		double output  = MatchDemo.RGBDistance(one, two);
		Assert.assertEquals(110.616, output, 0.001);
	}
	@Test
	public void checking_differeces_different_amounts_two_sides() {
		Color one = new Color(25,30,65);
		Color two = new Color(54,62,74);
		double output  = MatchDemo.RGBDistance(one, two);
		Assert.assertEquals(44.113, output, 0.001);
	} 
}
