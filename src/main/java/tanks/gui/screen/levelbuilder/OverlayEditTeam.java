package tanks.gui.screen.levelbuilder;

import tanks.Drawing;
import tanks.Game;
import tanks.Team;
import tanks.gui.Button;
import tanks.gui.TextBox;
import tanks.gui.screen.ScreenOptions;

public class OverlayEditTeam extends ScreenLevelBuilderOverlay
{
    public TextBox teamName;
    public Team team;

    public OverlayEditTeam(OverlayLevelOptionsTeams previous, ScreenLevelBuilder screenLevelBuilder, Team team)
    {
        super(previous, screenLevelBuilder);

        this.team = team;

        teamName = new TextBox(this.centerX, this.centerY - this.objYSpace * 2, this.objWidth, this.objHeight, "Team name", new Runnable()
        {
            @Override
            public void run()
            {
                boolean duplicate = false;

                for (int i = 0; i < screenLevelBuilder.teams.size(); i++)
                {
                    if (teamName.inputText.equals(screenLevelBuilder.teams.get(i).name))
                    {
                        duplicate = true;
                        break;
                    }
                }

                if (teamName.inputText.length() <= 0 || duplicate)
                    teamName.inputText = team.name;
                else
                {
                    team.name = teamName.inputText;
                }
            }

        }
                , team.name);

        teamName.lowerCase = true;

        if (team.friendlyFire)
            teamFriendlyFire.text = "Friendly fire: " + ScreenOptions.onText;
        else
            teamFriendlyFire.text = "Friendly fire: " + ScreenOptions.offText;
    }

    public Button back = new Button(this.centerX, this.centerY + 300, this.objWidth, this.objHeight, "Back", new Runnable()
    {
        @Override
        public void run()
        {
            escape();
        }
    }
    );

    public Button deleteTeam = new Button(this.centerX, this.centerY + 300 - this.objYSpace, this.objWidth, this.objHeight, "Delete team", new Runnable()
    {
        @Override
        public void run()
        {
            screenLevelBuilder.teams.remove(team);
            escape();
        }
    }
    );

    public Button teamFriendlyFire = new Button(this.centerX, this.centerY - this.objYSpace, this.objWidth, this.objHeight, "Friendly fire: on", new Runnable()
    {
        @Override
        public void run()
        {
            team.friendlyFire = !team.friendlyFire;
            if (team.friendlyFire)
                teamFriendlyFire.text = "Friendly fire: " + ScreenOptions.onText;
            else
                teamFriendlyFire.text = "Friendly fire: " + ScreenOptions.offText;
        }
    }
    );

    public Button teamColor = new Button(this.centerX, this.centerY, this.objWidth, this.objHeight, "Team color", new Runnable()
    {
        @Override
        public void run()
        {
            Game.screen = new ScreenEditTeamColor(Game.screen, screenLevelBuilder, team);
        }
    }
    );

    public void update()
    {
        deleteTeam.update();
        teamName.update();
        teamFriendlyFire.update();
        teamColor.update();
        back.update();

        super.update();
    }

    public void draw()
    {
        super.draw();
        back.draw();
        teamName.draw();
        teamColor.draw();
        deleteTeam.draw();
        teamFriendlyFire.draw();

        Drawing.drawing.setInterfaceFontSize(this.titleSize);
        Drawing.drawing.setColor(screenLevelBuilder.fontBrightness, screenLevelBuilder.fontBrightness, screenLevelBuilder.fontBrightness);
        Drawing.drawing.drawInterfaceText(this.centerX, this.centerY - this.objYSpace * 3.5, this.team.name);
    }
}
