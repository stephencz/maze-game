package com.mazegen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mazegen.main.GameInfo;
import com.mazegen.maze.GenerationType;
import com.mazegen.maze.TileType;
import com.mazegen.screen.GameScreen;

public class GameUI extends Stage
{

	private final GameScreen screen;
	
	private OrthographicCamera uiCamera;
	
	private ExtendViewport uiViewport;
	
	private Skin skin;
	
	private Table table;
	
	private Pixmap bgPixmap;
	
	private TextureRegionDrawable tableBg;
	
	private Label menuTitleLabel;
	
	private Label tileTypeLabel;
	
	private SelectBox<TileType> tileTypeSelectBox;
	
	private Label genTypeLabel;
	
	private SelectBox<GenerationType> genTypeSelectBox;
	
	private Label mazeWidthLabel;
	
	private TextField mazeWidthInput;
	
	private Label mazeHeightLabel;
	
	private TextField mazeHeightInput;
	
	private TextButton generateButton;
	
	public GameUI(final GameScreen screen)
	{
		this.screen = screen;
		
		this.initUI();
		this.initUIEvents();
	}
	
	private void initUI()
	{
		
		//Create UI Camera and Viewport
		this.uiCamera = new OrthographicCamera();
		this.uiViewport = new ExtendViewport(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT, this.uiCamera);
		this.uiCamera.setToOrtho(false, GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT);
		
		//Load UI Skin and Create Stage
		this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		this.setViewport(uiViewport);

		//Create the Primary Table
		this.table = new Table();		
		this.table.setPosition(100f, 100f);
		this.table.setWidth(300);
		this.table.setHeight(GameInfo.WORLD_HEIGHT - 200f);
		this.table.align(Align.top);
		this.table.pad(10f);
		
		this.bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
		this.bgPixmap.setColor(Color.GRAY);
		this.bgPixmap.fill();
		
		this.tableBg = new TextureRegionDrawable(new TextureRegion(new Texture(this.bgPixmap)));
		this.table.setBackground(this.tableBg);
		
		this.addActor(this.table);
		this.table.row().padBottom(10f);
				
		//Menu Title Label
		this.menuTitleLabel = new Label("Maze Generation Settings", this.skin);
		this.menuTitleLabel.setColor(Color.WHITE);
		this.table.add(this.menuTitleLabel).colspan(2).align(Align.center);
		this.table.row().padBottom(10f);
		
		//Tile Type Label and Select Box
		this.tileTypeLabel = new Label("Tile: ", this.skin);
		this.tileTypeLabel.setColor(Color.WHITE);
		this.table.add(this.tileTypeLabel).align(Align.left);
		
		this.tileTypeSelectBox = new SelectBox<TileType>(this.skin);
		this.tileTypeSelectBox.setItems(TileType.values());
		this.table.add(this.tileTypeSelectBox).align(Align.left);
		this.table.row().padBottom(10f);
		
		//Generation Type Label and Select Box
		this.genTypeLabel = new Label("Gen: ", this.skin);
		this.genTypeLabel.setColor(Color.WHITE);
		this.table.add(this.genTypeLabel).align(Align.left);
		
		this.genTypeSelectBox = new SelectBox<GenerationType>(this.skin);
		this.genTypeSelectBox.setItems(GenerationType.values());
		this.table.add(this.genTypeSelectBox).align(Align.left);
		this.table.row().padBottom(10f);
		
		//Maze Width Label and Input
		this.mazeWidthLabel = new Label("Width: ", this.skin);
		this.mazeWidthLabel.setColor(Color.WHITE);
		this.table.add(this.mazeWidthLabel).align(Align.left);
		
		this.mazeWidthInput = new TextField(Integer.toString(screen.getMazeWidth()), this.skin);
		this.table.add(this.mazeWidthInput).align(Align.left);
		this.table.row().padBottom(10f);
		
		//Maze Height Label and Input
		this.mazeHeightLabel = new Label("Height: ", this.skin);
		this.mazeHeightLabel.setColor(Color.WHITE);
		this.table.add(this.mazeHeightLabel).align(Align.left);
		
		this.mazeHeightInput = new TextField(Integer.toString(screen.getMazeHeight()), this.skin);
		this.table.add(this.mazeHeightInput).align(Align.left);
		this.table.row().padBottom(10f);
		
		//Generate Button
		this.generateButton = new TextButton("Generate", this.skin);
		this.table.add(this.generateButton).colspan(2).align(Align.center);
		
		this.table.setVisible(false);
	}
	
	private void initUIEvents()
	{
		this.generateButton.addListener( new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				//Get maze tile type and generation type
				screen.setTileType(tileTypeSelectBox.getSelected());
				screen.setGenType(genTypeSelectBox.getSelected());
				
				//Try and get maze width from text input
				try
				{
					int width = Integer.parseInt(mazeWidthInput.getText());
					if(width > 1 && width <= 1000)
					{
						screen.setMazeWidth(width);
						screen.createMaze(screen.getTileType(), screen.getGenType(), screen.getMazeWidth(), screen.getMazeHeight());
					}
					else
					{
						mazeWidthInput.setText("ERROR");
					}
				}
				catch(NumberFormatException e)
				{
					mazeWidthInput.setText("ERROR");
				}
				
				//Try and get maze height from text input
				try
				{
					int height = Integer.parseInt(mazeHeightInput.getText());
					if(height > 1 && height <= 1000)
					{
						screen.setMazeHeight(height);
						screen.createMaze(screen.getTileType(), screen.getGenType(), screen.getMazeWidth(), screen.getMazeHeight());
					}
					else
					{
						mazeHeightInput.setText("ERROR");
					}
				}
				catch(NumberFormatException e)
				{
					mazeHeightInput.setText("ERROR");
				}
			}
		});		
	}
	
	
	
	//Show or Hide Settings Menu
	@Override
	public void act(float delta)
	{
		if(Gdx.input.isKeyJustPressed(Keys.H))
		{
			if(!this.table.isVisible())
			{
				this.table.setVisible(true);
				Gdx.input.setInputProcessor(this);
				this.mazeWidthInput.setText(Integer.toString(screen.getMazeWidth()));
				this.mazeHeightInput.setText(Integer.toString(screen.getMazeHeight()));
			}
			else
			{
				this.table.setVisible(false);
			}
		}
		
		super.act(delta);
	}
}
