package nebulous.testGame;

import org.joml.Vector3f;

import nebulous.Game;
import nebulous.component.Level2D;
import nebulous.component.TileMap;
import nebulous.graphics.Camera;
import nebulous.graphics.primatives.Mesh;
import nebulous.graphics.primatives.Texture;
import nebulous.logic.Input;
import nebulous.physics.BoundingBox2D;

public class TestLevel extends Level2D {

	public TileMap map        = null;
	public TileMap map2       = null;
	public TileMap map3		  = null;
	public Player player      = null;
	public BlockEntity block  = null;
	public BlockEntity block2 = null;
	public BlockEntity block3 = null;
	public BlockEntity block4 = null;
	
	public LogoGui logoGUI = null;
	
	@Override
	public void init(Game game) {
		
		camera.setPerspective(Camera.PERSPECTIVE);
		camera.setPosition(new Vector3f(0,0,10f));
		
		Texture STONE = new Texture("/textures/stone.png");
		Texture GRASS = new Texture("/textures/grass_side.png");
		Texture TORCH = new Texture("/textures/torch_on.png");
		
		map = new TileMap(STONE, 32, 32, 24, 14, false);
		map2 = new TileMap(32, 32, 24, 14, true);
		map3 = new TileMap(32, 32, 24, 14, false);
		
		logoGUI = new LogoGui(100 , game.getWindow().getHeight() - 100);
		
		for(int i = 0; i < map2.getWidth(); i++) {
			map2.setTile(i, 0, GRASS);
			map2.getTile(i, 0).boundingBox = new BoundingBox2D(1, 1, map2.getTile(i, 0).getPosition());
		}
		
		for(int i = 4; i < 16; i++) {
			map2.setTile(i, 3, GRASS);
			map2.getTile(i, 3).boundingBox = new BoundingBox2D(1, 1, map2.getTile(i, 3).getPosition());
		}
		
		for(int i = 10; i < 16; i++) {
			map2.setTile(i, 5, GRASS);
			map2.getTile(i, 5).boundingBox = new BoundingBox2D(1, 1, map2.getTile(i, 5).getPosition());
		}
		
		for(int i = 17; i < 21; i++) {
			map2.setTile(i, 3, GRASS);
			map2.getTile(i, 3).boundingBox = new BoundingBox2D(1, 1, map2.getTile(i, 3).getPosition());
		}
		
		for(int i = 3; i < 7; i++) {
			map2.setTile(21, i, GRASS);
			map2.getTile(21, i).boundingBox = new BoundingBox2D(1, 1, map2.getTile(21, i).getPosition());
		}
		
		map3.setTile(4, 4, TORCH);
		map2.setTile(18, 5, GRASS);
		map2.setTile(19, 5, GRASS);
		map2.setTile(19, 6, GRASS);
		
		map2.setTile(0, 0, null);
		
		map2.setTile(31, 0, null);
		
		player = new Player(Mesh.PLANE(Texture.UNKNOWN2), 4, 4);
		block = new BlockEntity(Mesh.PLANE(Texture.UNKNOWN), 8, 8);
		block2 = new BlockEntity(Mesh.PLANE(Texture.UNKNOWN), 9, 8);
		block3 = new BlockEntity(Mesh.PLANE(Texture.UNKNOWN), 10, 8);
		block4 = new BlockEntity(Mesh.PLANE(Texture.UNKNOWN), 11, 8);
		
		addTileMap("map1", map);
		addTileMap("map2", map2);
		addTileMap("map3", map3);
		addEntity("player", player);
		addEntity("block", block);
		addEntity("block2", block2);
		addEntity("block3", block3);
		addEntity("block4", block4);
		addGuiElement("test", logoGUI);
		
	}

	boolean moveUp = true;
	boolean moveRight = true;
	
	@Override
	public void update(Game game, double delta) {
		
		if(block.getPosition().x > 8) moveRight = false;
		if(block.getPosition().x < 4) moveRight = true;
		if(moveRight) block.move(0.05f, 0);
		else block.move(-0.05f, 0);
	
		if(block4.getPosition().y > 12) moveUp = false;
		if(block4.getPosition().y < 6) moveUp = true;
		if(moveUp) block4.move(0, 0.05f);
		else block4.move(0, -0.05f);
		
		controlCamera();
		
	}
	
	float scrollVelocity = 0;
	
	public void controlCamera() {
		
		if(Input.isKeyHeld(Input.KEY_G)) camera.setPosition(camera.getPosition().add(new Vector3f(0,0,0.1f)));
		if(Input.isKeyHeld(Input.KEY_H)) camera.setPosition(camera.getPosition().add(new Vector3f(0,0,-0.1f)));
		if(Input.isKeyHeld(Input.KEY_E)) camera.setRotation(camera.getRotation().add(new Vector3f(0,0.1f,0)));
		if(Input.isKeyHeld(Input.KEY_R)) camera.setRotation(camera.getRotation().add(new Vector3f(0,-0.1f,0)));
		
		if(Input.isKeyHeld(Input.KEY_P)) camera.setRotation(0,0,0);
		
		if(Input.isMouseScrollIncrease()){ 
			scrollVelocity = -60;
		}
		
		if(Input.isMouseScrollDecrease()){
			scrollVelocity = 60;
		}
		
		if(scrollVelocity > 0) {
			camera.setPosition(camera.getPosition().add(new Vector3f(0, 0, 0.005f * scrollVelocity)));
			scrollVelocity -= 2;
		}
		
		if(scrollVelocity < 0) {
			camera.setPosition(camera.getPosition().add(new Vector3f(0, 0, 0.005f * scrollVelocity)));
			scrollVelocity += 2;
		}
		
	}

}