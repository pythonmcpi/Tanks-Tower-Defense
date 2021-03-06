package tanks.gui.screen.levelbuilder;

import tanks.Drawing;
import tanks.Game;
import tanks.gui.Button;
import tanks.gui.TextBox;
import tanks.gui.TextBoxSlider;
import tanks.gui.screen.Screen;

public class OverlayLevelOptionsColor extends ScreenLevelBuilderOverlay
{
    public TextBoxSlider colorRed;
    public TextBoxSlider colorGreen;
    public TextBoxSlider colorBlue;
    public TextBox colorVarRed;
    public TextBox colorVarGreen;
    public TextBox colorVarBlue;

    public OverlayLevelOptionsColor(Screen previous, ScreenLevelBuilder screenLevelBuilder)
    {
        super(previous, screenLevelBuilder);

        colorRed = new TextBoxSlider(this.centerX - objXSpace / 2, this.centerY - this.objYSpace, this.objWidth, this.objHeight, "Red", new Runnable()
        {
            @Override
            public void run()
            {
                if (colorRed.inputText.length() <= 0)
                    colorRed.inputText = colorRed.previousInputText;

                screenLevelBuilder.level.colorR = Integer.parseInt(colorRed.inputText);

                colorVarRed.maxValue = 255 - screenLevelBuilder.level.colorR;
                colorVarRed.performValueCheck();

                screenLevelBuilder.level.colorVarR = Integer.parseInt(colorVarRed.inputText);
                screenLevelBuilder.level.reloadTiles();
            }

        }
                , screenLevelBuilder.level.colorR, 0, 255, 1);

        colorRed.allowLetters = false;
        colorRed.allowSpaces = false;
        colorRed.maxChars = 3;
        colorRed.maxValue = 255;
        colorRed.checkMaxValue = true;
        colorRed.integer = true;

        colorGreen = new TextBoxSlider(this.centerX - this.objXSpace / 2, this.centerY + this.objYSpace / 2, this.objWidth, this.objHeight, "Green", new Runnable()
        {
            @Override
            public void run()
            {
                if (colorGreen.inputText.length() <= 0)
                    colorGreen.inputText = colorGreen.previousInputText;

                screenLevelBuilder.level.colorG = Integer.parseInt(colorGreen.inputText);

                colorVarGreen.maxValue = 255 - screenLevelBuilder.level.colorG;
                colorVarGreen.performValueCheck();

                screenLevelBuilder.level.colorVarG = Integer.parseInt(colorVarGreen.inputText);
                screenLevelBuilder.level.reloadTiles();
            }

        }
                , screenLevelBuilder.level.colorG, 0, 255, 1);

        colorGreen.allowLetters = false;
        colorGreen.allowSpaces = false;
        colorGreen.maxChars = 3;
        colorGreen.maxValue = 255;
        colorGreen.checkMaxValue = true;
        colorGreen.integer = true;

        colorBlue = new TextBoxSlider(this.centerX - this.objXSpace / 2, this.centerY + this.objYSpace * 2, this.objWidth, this.objHeight, "Blue", new Runnable()
        {
            @Override
            public void run()
            {
                if (colorBlue.inputText.length() <= 0)
                    colorBlue.inputText = colorBlue.previousInputText;

                screenLevelBuilder.level.colorB = Integer.parseInt(colorBlue.inputText);

                colorVarBlue.maxValue = 255 - screenLevelBuilder.level.colorB;
                colorVarBlue.performValueCheck();

                screenLevelBuilder.level.colorVarB = Integer.parseInt(colorVarBlue.inputText);
                screenLevelBuilder.level.reloadTiles();
            }

        }
                , screenLevelBuilder.level.colorB, 0, 255, 1);

        colorBlue.allowLetters = false;
        colorBlue.allowSpaces = false;
        colorBlue.maxChars = 3;
        colorBlue.maxValue = 255;
        colorBlue.checkMaxValue = true;
        colorBlue.integer = true;

        colorVarRed = new TextBox(this.centerX + this.objXSpace / 2, this.centerY - this.objYSpace, this.objWidth, this.objHeight, "Red noise", new Runnable()
        {
            @Override
            public void run()
            {
                if (colorVarRed.inputText.length() <= 0)
                    colorVarRed.inputText = colorVarRed.previousInputText;

                screenLevelBuilder.level.colorVarR = Integer.parseInt(colorVarRed.inputText);

                screenLevelBuilder.level.reloadTiles();
            }

        }
                , screenLevelBuilder.level.colorVarR + "");

        colorVarRed.allowLetters = false;
        colorVarRed.allowSpaces = false;
        colorVarRed.maxChars = 3;
        colorVarRed.checkMaxValue = true;

        colorVarGreen = new TextBox(this.centerX + this.objXSpace / 2, this.centerY + this.objYSpace / 2, this.objWidth, this.objHeight, "Green noise", new Runnable()
        {
            @Override
            public void run()
            {
                if (colorVarGreen.inputText.length() <= 0)
                    colorVarGreen.inputText = colorVarGreen.previousInputText;

                screenLevelBuilder.level.colorVarG = Integer.parseInt(colorVarGreen.inputText);

                screenLevelBuilder.level.reloadTiles();
            }

        }
                , screenLevelBuilder.level.colorVarG + "");

        colorVarGreen.allowLetters = false;
        colorVarGreen.allowSpaces = false;
        colorVarGreen.maxChars = 3;
        colorVarGreen.checkMaxValue = true;

        colorVarBlue = new TextBox(this.centerX + this.objXSpace / 2, this.centerY + this.objYSpace * 2, this.objWidth, this.objHeight, "Blue noise", new Runnable()
        {
            @Override
            public void run()
            {
                if (colorVarBlue.inputText.length() <= 0)
                    colorVarBlue.inputText = colorVarBlue.previousInputText;

                screenLevelBuilder.level.colorVarB = Integer.parseInt(colorVarBlue.inputText);

                screenLevelBuilder.level.reloadTiles();
            }

        }
                , screenLevelBuilder.level.colorVarB + "");

        colorVarBlue.allowLetters = false;
        colorVarBlue.allowSpaces = false;
        colorVarBlue.maxChars = 3;
        colorVarBlue.checkMaxValue = true;

        colorVarRed.maxValue = 255 - screenLevelBuilder.level.colorR;
        colorVarGreen.maxValue = 255 - screenLevelBuilder.level.colorG;
        colorVarBlue.maxValue = 255 - screenLevelBuilder.level.colorB;
    }

    public Button back = new Button(this.centerX, (int) (this.centerY + this.objYSpace * 3), this.objWidth, this.objHeight, "Back", new Runnable()
    {
        @Override
        public void run()
        {
            escape();
        }
    }
    );

    public void update()
    {
        this.colorRed.update();
        this.colorGreen.update();
        this.colorBlue.update();
        this.colorVarRed.update();
        this.colorVarGreen.update();
        this.colorVarBlue.update();
        this.back.update();

        super.update();
    }

    public void draw()
    {
        super.draw();

        colorRed.r1 = 0;
        colorRed.r2 = 255;
        colorRed.g1 = colorGreen.value;
        colorRed.g2 = colorGreen.value;
        colorRed.b1 = colorBlue.value;
        colorRed.b2 = colorBlue.value;

        colorGreen.r1 = colorRed.value;
        colorGreen.r2 = colorRed.value;
        colorGreen.g1 = 0;
        colorGreen.g2 = 255;
        colorGreen.b1 = colorBlue.value;
        colorGreen.b2 = colorBlue.value;

        colorBlue.r1 = colorRed.value;
        colorBlue.r2 = colorRed.value;
        colorBlue.g1 = colorGreen.value;
        colorBlue.g2 = colorGreen.value;
        colorBlue.b1 = 0;
        colorBlue.b2 = 255;

        this.colorBlue.draw();
        this.colorGreen.draw();
        this.colorRed.draw();
        this.colorVarBlue.draw();
        this.colorVarGreen.draw();
        this.colorVarRed.draw();
        Drawing.drawing.setInterfaceFontSize(this.titleSize);

        Drawing.drawing.setColor(screenLevelBuilder.fontBrightness, screenLevelBuilder.fontBrightness, screenLevelBuilder.fontBrightness);
        Drawing.drawing.drawInterfaceText(this.centerX, this.centerY - this.objYSpace * 2.5, "Background colors");
        this.back.draw();
    }
}
