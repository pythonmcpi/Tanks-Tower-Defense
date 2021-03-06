package tanks.gui.screen;

import tanks.Drawing;
import tanks.Game;
import tanks.gui.Button;
import tanks.gui.TextBox;
import tanks.tank.TankPlayerRemote;

public class ScreenOptionsMultiplayer extends Screen
{
	public static final String chatFilterText = "Chat filter: ";
	public static final String anticheatText = "Anticheat: ";

	public static final String weakText = "\u00A7200100000255weak";
	public static final String strongText = "\u00A7000200000255strong";

	TextBox username = new TextBox(this.centerX, this.centerY - this.objYSpace * 1.5, this.objWidth, this.objHeight, "Username", new Runnable()
	{
		@Override
		public void run() 
		{
			Game.player.username = username.inputText;
			username.inputText = Game.player.username + "";
			
			if (!Game.player.username.equals(Game.chatFilter.filterChat(Game.player.username)))
				Game.screen = new ScreenUsernameWarning();
		}
	},
			Game.player.username, "Pick a username that players---will see in multiplayer");

	Button chatFilter = new Button(this.centerX, this.centerY + this.objYSpace / 2, this.objWidth, this.objHeight, "", new Runnable()
	{
		@Override
		public void run() 
		{
			Game.enableChatFilter = !Game.enableChatFilter;

			if (Game.enableChatFilter)
				chatFilter.text = chatFilterText + ScreenOptions.onText;
			else
				chatFilter.text = chatFilterText + ScreenOptions.offText;
		}
	},
			"Filters chat of potentially---inappropriate words");

	Button anticheat = new Button(this.centerX, this.centerY + this.objYSpace * 1.5, this.objWidth, this.objHeight, "", new Runnable()
	{
		@Override
		public void run()
		{
			if (!TankPlayerRemote.checkMotion)
			{
				TankPlayerRemote.checkMotion = true;
				TankPlayerRemote.weakTimeCheck = false;
				TankPlayerRemote.anticheatMaxTimeOffset = TankPlayerRemote.anticheatStrongTimeOffset;
			}
			else if (!TankPlayerRemote.weakTimeCheck)
			{
				TankPlayerRemote.weakTimeCheck = true;
				TankPlayerRemote.anticheatMaxTimeOffset = TankPlayerRemote.anticheatWeakTimeOffset;
			}
			else
				TankPlayerRemote.checkMotion = false;

			if (!TankPlayerRemote.checkMotion)
				anticheat.text = anticheatText + ScreenOptions.offText;
			else if (!TankPlayerRemote.weakTimeCheck)
				anticheat.text = anticheatText + strongText;
			else
				anticheat.text = anticheatText + weakText;
		}
	},
			"When this option is enabled---while hosting a party,---other players' positions and---velocities will be checked---and corrected if invalid.------Weaker settings work better---with less stable connections.");

	Button color = new Button(this.centerX, this.centerY - this.objYSpace / 2, this.objWidth, this.objHeight, "Tank color", new Runnable()
	{
		@Override
		public void run()
		{
			Game.screen = new ScreenOptionsMultiplayerColor();
		}
	},
			"Personalize your tank---to stand out in multiplayer!");


	Button back = new Button(this.centerX, this.centerY + this.objYSpace * 3.5, this.objWidth, this.objHeight, "Back", new Runnable()
	{
		@Override
		public void run() 
		{
			Game.screen = new ScreenOptions();
		}
	}
			);
	
	public ScreenOptionsMultiplayer()
	{
		this.music = "menu_options.ogg";
		this.musicID = "menu";

		username.enableCaps = true;
		username.enableSpaces = false;

		if (Game.enableChatFilter)
			chatFilter.text = chatFilterText + ScreenOptions.onText;
		else
			chatFilter.text = chatFilterText + ScreenOptions.offText;

		if (!TankPlayerRemote.checkMotion)
			anticheat.text = anticheatText + ScreenOptions.offText;
		else if (!TankPlayerRemote.weakTimeCheck)
			anticheat.text = anticheatText + strongText;
		else
			anticheat.text = anticheatText + weakText;
	}
	
	@Override
	public void update() 
	{
		chatFilter.update();
		back.update();
		username.update();
		color.update();
		anticheat.update();
	}

	@Override
	public void draw()
	{
		this.drawDefaultBackground();
		back.draw();
		anticheat.draw();
		chatFilter.draw();
		color.draw();
		username.draw();

		Drawing.drawing.setInterfaceFontSize(this.titleSize);
		Drawing.drawing.setColor(0, 0, 0);
		Drawing.drawing.drawInterfaceText(this.centerX, this.centerY - this.objYSpace * 3.5, "Multiplayer options");
	}

}
