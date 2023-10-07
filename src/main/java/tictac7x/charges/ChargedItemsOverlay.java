package tictac7x.charges;

import net.runelite.api.Client;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.ui.overlay.components.TextComponent;
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.store.Charges;
import tictac7x.charges.triggers.TriggerItem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class ChargedItemsOverlay extends WidgetItemOverlay {
    private final Client client;
    private final ChargesImprovedConfig config;
    private final ChargedItem[] infoboxes_charged_items;

    public ChargedItemsOverlay(final Client client, final ChargesImprovedConfig config, final ChargedItem[] infoboxes_charged_items) {
        this.client = client;
        this.config = config;
        this.infoboxes_charged_items = infoboxes_charged_items;
        showOnInventory();
        showOnEquipment();
        showOnInterfaces(84);
        showOnBank();
    }

    private boolean isBankWidget(final WidgetItem item_widget) {
        return
            item_widget.getWidget().getParentId() == 786442 ||
            item_widget.getWidget().getParentId() == 786443 ||
            item_widget.getWidget().getParentId() == 786444 ||
            item_widget.getWidget().getParentId() == 786445
        ;
    }


    @Override
    public void renderItemOverlay(final Graphics2D graphics, final int item_id, final WidgetItem item_widget) {
        if (!config.showItemOverlays()) return;

        for (final ChargedItem infobox : infoboxes_charged_items) {
            if (infobox.getTriggersItems() == null || config.getHiddenItemOverlays().contains(infobox.infobox_id) || infobox.getCharges() == Charges.UNLIMITED) continue;

            TriggerItem trigger_item_to_use = null;
            for (final TriggerItem trigger_item : infobox.getTriggersItems()) {
                if (trigger_item.item_id == item_id && !trigger_item.hide_overlay) {
                    trigger_item_to_use = trigger_item;
                    break;
                }
            }
            if (trigger_item_to_use == null) continue;

            // Charges from infobox.
            String charges = ChargesImprovedPlugin.getChargesMinified(infobox.getCharges());

            graphics.setFont(FontManager.getRunescapeSmallFont());

            // Charges from name (override the infobox).
            if (trigger_item_to_use.fixed_charges != null) {
                charges = ChargesImprovedPlugin.getChargesMinified(trigger_item_to_use.fixed_charges);
            }

            final Rectangle bounds = item_widget.getCanvasBounds();
            final TextComponent charges_component = new TextComponent();

            charges_component.setPosition(new Point(bounds.x, (int) bounds.getMaxY()));
            charges_component.setText(charges);

            if (charges.equals("?")) {
                charges_component.setColor(config.getColorUnknown());
            } else if (
                !isBankWidget(item_widget) && (infobox.needsToBeEquipped() && !infobox.inEquipment()) ||
                charges.equals("0") && !infobox.zero_charges_is_positive ||
                infobox.negative_full_charges != null && infobox.getCharges() == infobox.negative_full_charges ||
                infobox.isDeactivated()
            ) {
                charges_component.setColor(config.getColorEmpty());
            } else {
                charges_component.setColor(config.getColorDefault());
            }

            charges_component.render(graphics);
            return;
        }
    }
}
