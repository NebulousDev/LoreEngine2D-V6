package nebulous.game;

import org.joml.Vector3f;

import nebulous.graphics.Camera;
import nebulous.graphics.GameWindow;
import nebulous.graphics.Texture;
import nebulous.graphics.shaders.Shader;

public class TileMap extends GameObject{
	
	private Tile[] tiles  = null;
	private int	   height = 0;
	private int	   width  = 0;
	
	public TileMap(Tile tile, int height, int width) {
		
		this.height = height;
		this.width = width;
		this.position = new Vector3f(0);
		this.rotation = new Vector3f(0);
		this.scale = new Vector3f(1);
		tiles = new Tile[width * height];
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tiles[x + y * width] = new Tile(tile.getTexure());
				tiles[x + y * width].setPosition(y, x, 0);
			}
		}
		
	}
	
	@Override
	public void render(GameWindow window, Camera camera, Shader shader) {
		for(int j = 0; j < Texture.maxTextureID; j++) {
			
			shader.bind();
			
			shader.setUniform("projectionMatrix", camera.calculateProjectionMatrix(window));
			shader.setUniform("viewMatrix", camera.calculateViewMatrix(window));
			
			shader.updateUniforms();
			
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					if(tiles[x + y * width].getTexure().textureID == j)
						tiles[x + y * width].render(window, camera, shader);
				}
			}
			
			shader.unbind();
			
		}
		
	}
	
	public void setTile(int x, int y, Tile tile) {
		tiles[x + y * width].getMesh().texture = tile.texure;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[x + y * width];
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
