package tictac7x.charges.infoboxes;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.store.ChargesItem;
import tictac7x.charges.store.Store;
import tictac7x.charges.triggers.TriggerChatMessage;
import tictac7x.charges.triggers.TriggerGraphic;
import tictac7x.charges.triggers.TriggerItem;

public class S_DragonfireShield extends ChargedItem {
    public S_DragonfireShield(
        final Client client,
        final ClientThread client_thread,
        final ConfigManager configs,
        final ItemManager items,
        final InfoBoxManager infoboxes,
        final ChatMessageManager chat_messages,
        final Notifier notifier,
        final ChargesImprovedConfig config,
        final Store store,
        final Plugin plugin
    ) {
        super(ChargesItem.DRAGONFIRE_SHIELD, ItemID.CRYSTAL_SHIELD, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, plugin);
        this.config_key = ChargesImprovedConfig.dragonfire_shield;
        this.triggers_items = new TriggerItem[]{
            new TriggerItem(ItemID.DRAGONFIRE_SHIELD_11284).fixedCharges(0),
            new TriggerItem(ItemID.DRAGONFIRE_SHIELD),
            new TriggerItem(ItemID.DRAGONFIRE_WARD_22003).fixedCharges(0),
            new TriggerItem(ItemID.DRAGONFIRE_WARD)
        };
        this.triggers_graphics = new TriggerGraphic[]{
                new TriggerGraphic(1165).decreaseCharges(1)
        };
        this.triggers_chat_messages = new TriggerChatMessage[]{
            new TriggerChatMessage("The shield has (?<charges>.+) charges?.").onItemClick(),
            new TriggerChatMessage("You vent the shield's remaining charges harmlessly into the air.").fixedCharges(0),
            new TriggerChatMessage("Your dragonfire (shield|ward) glows more brightly.").increaseCharges(1),
            new TriggerChatMessage("Your dragonfire shield is already fully charged.").fixedCharges(50)
        };
    }
}
