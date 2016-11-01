import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class TileLayer extends JComponent {

	private int[][] map;
	private BufferedImage tileSheet;
	protected ArrayList<Tile> tiles = new ArrayList<>();
	protected Hero hero;
	private GameKeyListener keyLis;
	private ArrayList<Monster> m1 = new ArrayList<>();


	public TileLayer(int width, int height, Hero hero, ArrayList<Monster> m12) {
		this.map = new int[height][width];
		this.hero = hero;
		this.m1 = m12;
		// Creates thread to update animation
	}

	public static TileLayer FromFile(String fileName, Hero hero, ArrayList<Monster> m12) {
		TileLayer layer = null;
		ArrayList<ArrayList<Integer>> tempLayout = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				if (currentLine.isEmpty()) {
					continue;
				}
				ArrayList<Integer> row = new ArrayList<>();

				String[] values = currentLine.trim().split(" ");

				for (String str : values) {
					if (!str.isEmpty()) {
						int id = Integer.parseInt(str);
						row.add(id);
					}
				}
				tempLayout.add(row);
			}
		} catch (IOException e) {
			System.out.println("Something screwed up with Level.txt upload");
		}

		int width = tempLayout.get(0).size();
		int height = tempLayout.size();
		layer = new TileLayer(width, height, hero, m12);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				layer.map[y][x] = tempLayout.get(y).get(x);
			}
		}
		layer.tileSheet = layer.LoadTileSheet("part2_tileset.png");
		return layer;
	}

	public GameKeyListener getKeyLis() {
		return this.keyLis;
	}

	public void setKeyLis(GameKeyListener keyLis) {
		this.keyLis = keyLis;
	}

	public BufferedImage LoadTileSheet(String fileName) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.out.println("Something screwed up with image upload");
		}

		return img;
	}

	public void createTiles() {
		for (int y = 0; y < this.map.length; y++) {
			for (int x = 0; x < this.map[y].length; x++) {
				int index = this.map[y][x];
				int yOffset = 0;
				if (index > (this.tileSheet.getWidth() / Engine.TILE_WIDTH) - 1) {
					yOffset++;
					index = index - ((this.tileSheet.getWidth() / Engine.TILE_WIDTH) - 1);
				}

				if (index == 0) {
					this.tiles.add(new GroundTile(index * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							(index * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
				}
				if (index == 1) {
					this.tiles.add(new Wall(index * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							(index * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
				}
				if (index == 5) {
					this.tiles.add(new BrickWall(index * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							(index * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
				}
			}
		}
		this.hero.setTiles(this.tiles);
		
		for (Monster m : this.m1) {
			m.setTiles(this.tiles);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Tile t : this.tiles) {
			t.drawTile(g);
		}
		for (Monster m : this.m1) {
			m.drawMonster(g);
		}
		this.hero.drawCharacter(g);
	}

}
