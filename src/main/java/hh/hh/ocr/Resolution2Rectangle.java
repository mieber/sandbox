package hh.hh.ocr;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public enum Resolution2Rectangle {

	RESOLUTION_2560_1440(Res.from(2560, //
			1440, //
			new int[][] { //
					new int[] { 2380, 190, 172, 183 }, //
					new int[] { 2252, 416, 172, 183 }, //
					new int[] { 2380, 642, 172, 183 }, //
					new int[] { 2252, 868, 172, 183 }, //
					new int[] { 2380, 1094, 172, 183 } }, //
			new int[][] { //
					new int[] { 0, 190, 172, 183 }, //
					new int[] { 132, 416, 172, 183 }, //
					new int[] { 0, 642, 172, 183 }, //
					new int[] { 132, 868, 172, 183 }, //
					new int[] { 0, 1094, 172, 183 } }, //
			new int[] { 6, 62, 165, 32 }, //
			new int[] { 6, 54, 158, 32 }, //
			new int[] { 6, 106, 152, 19 }, //
			new int[] { 24, 98, 148, 19 }, //
			30.5d, //
			-30.5d, //
			new int[] { 746, 9, 1128, 42 }));

	private Res resolutionInfo;

	static class Res {

		int width;
		int height;
		List<Rectangle> enemy = new ArrayList<>();
		List<Rectangle> ally = new ArrayList<>();
		Rectangle enemyCut;
		Rectangle allyCut;
		Rectangle enemyHeroCut;
		Rectangle allyHeroCut;
		double enemyAngle;
		double friendAngle;
		Rectangle map;

		static Res from(int width, int height, int[][] enemy, int[][] friend, int[] enemyCut, int[] allyCut,
				int[] enemyHeroCut, int[] allyHeroCut, double enemyAngle, double friendAngle, int[] map) {
			Res s = new Res();
			s.width = width;
			s.height = height;
			s.enemyAngle = enemyAngle;
			s.friendAngle = friendAngle;

			for (int[] i : friend) {
				s.ally.add(from(i));
			}

			for (int[] i : enemy) {
				s.enemy.add(from(i));
			}

			s.allyCut = from(allyCut);
			s.enemyCut = from(enemyCut);
			s.allyHeroCut = from(allyHeroCut);
			s.enemyHeroCut = from(enemyHeroCut);
			s.map = from(map);

			return s;
		}

		static Rectangle from(int[] c) {
			return new Rectangle(c[0], c[1], c[2], c[3]);
		}

	}

	private Resolution2Rectangle(Res s) {
		this.resolutionInfo = s;
	}

	public static Resolution2Rectangle get(int width, int height) {
		Resolution2Rectangle[] values = Resolution2Rectangle.values();
		for (Resolution2Rectangle r : values) {
			if (r.resolutionInfo.width == width && r.resolutionInfo.height == height) {
				return r;
			}
		}

		return null;
	}

	public List<Rectangle> getEnemyRectangles() {
		return resolutionInfo.enemy;
	}

	public List<Rectangle> getAllyRectangles() {
		return resolutionInfo.ally;
	}

	public Rectangle getEnemySubRectangle() {
		return resolutionInfo.enemyCut;
	}

	public Rectangle getAllySubRectangle() {
		return resolutionInfo.allyCut;
	}
	
	public Rectangle getEnemyHeroSubRectangle() {
		return resolutionInfo.enemyHeroCut;
	}

	public Rectangle getAllyHeroSubRectangle() {
		return resolutionInfo.allyHeroCut;
	}

	public double getAllyRotation() {
		return resolutionInfo.friendAngle;
	}

	public double getEnemyRotation() {
		return resolutionInfo.enemyAngle;
	}

	public Rectangle getMapRectangle() {
		return resolutionInfo.map;
	}

}
