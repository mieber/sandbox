package hh.hh.ocr;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public enum Resolution2Rectangle {

	RESOLUTION_2560_1440(Res.from(2560, // width
			1440, // height
			new int[][] { // enemy
					new int[] { 2380, 190, 172, 183 }, //
					new int[] { 2252, 416, 172, 183 }, //
					new int[] { 2380, 641, 172, 183 }, //
					new int[] { 2252, 867, 172, 183 }, //
					new int[] { 2380, 1092, 172, 183 } }, //
			new int[][] { // ally
					new int[] { 0, 190, 172, 183 }, //
					new int[] { 132, 416, 172, 183 }, //
					new int[] { 0, 642, 172, 183 }, //
					new int[] { 132, 868, 172, 183 }, //
					new int[] { 0, 1094, 172, 183 } }, //
			new int[] { 6, 62, 165, 32 }, // enemyCut
			new int[] { 6, 54, 158, 32 }, // allyCut
			new int[] { 6, 106, 152, 19 }, // enemyHeroCut
			new int[] { 24, 98, 148, 19 }, // allyHeroCut
			30.5d, // enemyAngle
			-30.5d, // friendAngle
			new int[] { 746, 9, 1128, 42 }, // map
			80, // fontSize
			23, // fontOffset
			10 // usmHalfWidth
	)),

	RESOLUTION_1920_1080(Res.from(1920, // width
			1080, // height
			new int[][] { // enemy
					new int[] { 1785, 142, 129, 138 }, //
					new int[] { 1689, 312, 129, 138 }, //
					new int[] { 1785, 481, 129, 138 }, //
					new int[] { 1689, 650, 129, 138 }, //
					new int[] { 1785, 819, 129, 138 } }, //
			new int[][] { // ally
					new int[] { 0, 142, 129, 138 }, //
					new int[] { 99, 312, 129, 138 }, //
					new int[] { 0, 481, 129, 138 }, //
					new int[] { 99, 650, 129, 138 }, //
					new int[] { 0, 819, 129, 138 } }, //
			new int[] { 5, 46, 123, 24 }, // enemyCut
			new int[] { 5, 40, 118, 24 }, // allyCut
			new int[] { 5, 79, 114, 15 }, // enemyHeroCut
			new int[] { 18, 73, 111, 15 }, // allyHeroCut
			30.5d, // enemyAngle
			-30.5d, // friendAngle
			new int[] { 560, 7, 844, 31 }, // map
			60, // fontSize
			17, // fontOffset
			8 // usmHalfWidth
	));

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
		int fontSize;
		int fontOffset;
		int usmHalfWidth;

		static Res from(int width, int height, int[][] enemy, int[][] friend, int[] enemyCut, int[] allyCut,
				int[] enemyHeroCut, int[] allyHeroCut, double enemyAngle, double friendAngle, int[] map, int fontSize,
				int fontOffset, int usmHalfWidth) {
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
			s.fontSize = fontSize;
			s.fontOffset = fontOffset;
			s.usmHalfWidth = usmHalfWidth;

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

	public int getFontSize() {
		return resolutionInfo.fontSize;
	}

	public int getFontOffset() {
		return resolutionInfo.fontOffset;
	}

	public int getUsmHalfWidth() {
		return resolutionInfo.usmHalfWidth;
	}

}
