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
	protected ArrayList<Monster> m = new ArrayList<>();
	private ArrayList<MonsterThree> bossMonster = new ArrayList<>();
	private boolean bossExsists;
	

	public TileLayer(int width, int height, Hero hero) {
		this.map = new int[height][width];
		this.hero = hero;
		this.bossExsists = false;
	}

	public static TileLayer FromFile(String fileName, Hero hero) {
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
		layer = new TileLayer(width, height, hero);

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

				if (index == -1) {
					this.tiles.add(new GroundTile((index + 1) * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							((index + 1) * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
					MonsterThree tempMonster = new MonsterThree((x * Engine.TILE_WIDTH) + 25,
							(y * Engine.TILE_HIEGHT + 23), this.hero);
					this.m.add(tempMonster);
					this.bossMonster.add(tempMonster);
					this.bossExsists = true;
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
				if (index == 2) {
					this.tiles.add(new GroundTile((index - 2) * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							((index - 2) * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
					this.m.add(new MonsterOne((x * Engine.TILE_WIDTH) + 20, (y * Engine.TILE_HIEGHT)));
				}
				if (index == 3) {
					this.tiles.add(new GroundTile((index - 3) * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							((index - 3) * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
					this.m.add(new MonsterTwo((x * Engine.TILE_WIDTH) - 24, (y * Engine.TILE_HIEGHT) + 24));
				}
				if (index == 4) {
					this.tiles.add(new GroundTile((index - 4) * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							((index - 4) * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
					this.m.add(new BouncingMonster((x * Engine.TILE_WIDTH) - 24, (y * Engine.TILE_HIEGHT) + 24));
				}
				if (index == 5) {
					this.tiles.add(new BrickWall(index * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							(index * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
				}
				if (index == 6) {
					this.tiles.add(new BrickWall((index - 1) * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							((index - 1) * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
					this.tiles.get(this.tiles.size() - 1).setPowerUp(true);
					this.tiles.get(this.tiles.size() - 1).setPowerTileType("Detonate");
					
				}

				if (index == 7) {
					this.tiles.add(new BrickWall((index - 2) * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							((index - 2) * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
					this.tiles.get(this.tiles.size() - 1).setPowerUp(true);
					this.tiles.get(this.tiles.size() - 1).setPowerTileType("IncreaseRange");
					
				}
				if (index == 8) {
					this.tiles.add(new BrickWall((index - 3) * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							((index - 3) * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
					this.tiles.get(this.tiles.size() - 1).setPowerUp(true);
					this.tiles.get(this.tiles.size() - 1).setPowerTileType("MoreBombs");
					
				}
				if (index == 9) {
					this.tiles.add(new BrickWall((index - 4) * Engine.TILE_WIDTH, yOffset * Engine.TILE_HIEGHT,
							((index - 4) * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
							(yOffset * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.tileSheet, x, y));
					this.tiles.get(this.tiles.size() - 1).setPowerUp(true);
					this.tiles.get(this.tiles.size() - 1).setPowerTileType("AddLife");
				}
			}
		}
		this.hero.setMonsters(this.m);
		this.hero.setTiles(this.tiles);
		for (Monster m1 : this.m) {
			m1.setTiles(this.tiles);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Tile t : this.tiles) {
			t.drawTile(g);
		}
		for (Monster m1 : this.m) {
			m1.drawMonster(g);
		}
		if (this.hero.bombs.size() != 0) {
			for (Bomb bomb : this.hero.bombs) {
				bomb.drawCharacter(g);
			}
		}
		if (this.bossExsists ) {
			for (MonsterThree monsterThree : this.bossMonster) {
				if (monsterThree.bombs.size() != 0) {
					for (Bomb bomb : monsterThree.bombs) {
						bomb.drawCharacter(g);
					}
				}
			}

		}

		this.hero.drawCharacter(g);
	}
	
}
