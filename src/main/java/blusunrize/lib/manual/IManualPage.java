package blusunrize.lib.manual;

import blusunrize.lib.manual.gui.GuiManual;
import net.minecraft.client.gui.GuiButton;

import java.util.List;

public interface IManualPage
{
	public ManualInstance getManualHelper();
	public void initPage(GuiManual gui, int x, int y, List<GuiButton> pageButtons);
	public void renderPage(GuiManual gui, int x, int y, int mx, int my);
	public void buttonPressed(GuiManual gui, GuiButton button);
	public void mouseDragged(int x, int y, int clickX, int clickY, int mx, int my, int lastX, int lastY, int button);
	public boolean listForSearch(String searchTag);
	public void recalculateCraftingRecipes();
}