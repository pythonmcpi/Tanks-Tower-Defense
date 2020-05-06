package tanksonline.screen;

import tanks.event.online.*;
import tanks.gui.Button;
import tanks.gui.TextBox;
import tanks.gui.UUIDTextBox;
import tanks.gui.screen.ScreenOnline;
import tanks.gui.screen.ScreenOverlayOnline;
import tanksonline.TanksOnlineServerHandler;

import java.util.ArrayList;

public abstract class ScreenLayout
{
    public double sizeX = 1400;
    public double sizeY = 900;

    public ArrayList<ScreenOnline.Shape> shapes = new ArrayList<ScreenOnline.Shape>();
    public ArrayList<ScreenOnline.Text> texts = new ArrayList<ScreenOnline.Text>();
    public ArrayList<Button> buttons = new ArrayList<Button>();
    public ArrayList<TextBox> textBoxes = new ArrayList<TextBox>();

    public TanksOnlineServerHandler player;

    public final ScreenLayout instance = this;

    public ScreenLayout(TanksOnlineServerHandler player)
    {
        this.player = player;
    }

    public void buttonPressed(int id)
    {
        if (id < 0 && id >= -ScreenOverlayOnline.max_button_count && this.player.menuButtons[-(1 + id)] != null)
        {
            player.menuButtons[-(1 + id)].function.run();
        }
        else if (id >= 0 && id < buttons.size() && buttons.get(id) != null && buttons.get(id).enabled)
        {
            buttons.get(id).function.run();
        }
    }

    public void textboxEdited(int id, String in)
    {
        if (id >= 0 && id < textBoxes.size())
        {
            TextBox t = textBoxes.get(id);
            t.inputText = in;
            t.performValueCheck();
            t.function.run();
            t.previousInputText = t.inputText;
        }
    }

    public void setScreen()
    {
        this.player.screen = this;

        this.player.sendEvent(new EventNewScreen());

        for (int i = 0; i < this.shapes.size(); i++)
        {
            ScreenOnline.Shape s = this.shapes.get(i);

            if (s != null)
                this.player.sendEvent(new EventAddShape(i, s));
        }

        for (int i = 0; i < this.texts.size(); i++)
        {
            ScreenOnline.Text t = this.texts.get(i);

            if (t != null)
               this.player.sendEvent(new EventAddText(i, t));
        }

        for (int i = 0; i < this.buttons.size(); i++)
        {
            Button b = this.buttons.get(i);

            if (b != null)
                this.player.sendEvent(new EventAddButton(i, b));
        }

        for (int i = 0; i < this.textBoxes.size(); i++)
        {
            TextBox t = this.textBoxes.get(i);

            if (t != null)
            {
                if (t instanceof UUIDTextBox)
                    this.player.sendEvent(new EventAddUUIDTextBox(i, (UUIDTextBox) t));
                else
                    this.player.sendEvent(new EventAddTextBox(i, t));
            }
        }
    }
}