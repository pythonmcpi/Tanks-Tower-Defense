package tanks;

import java.awt.Color;

public class EnemyTankMagenta extends EnemyTankDynamic
{
	public EnemyTankMagenta(double x, double y, double angle)
	{
		super(x, y, Game.tank_size, new Color(230, 0, 200), angle, ShootAI.reflect);
		this.enablePlayerReaction = false;
		this.speed = 1.5;
		this.enableMineLaying = false;
		this.liveBulletMax = 3;
		this.cooldownRandom = 20;
		this.cooldownBase = 40;
	}
}
