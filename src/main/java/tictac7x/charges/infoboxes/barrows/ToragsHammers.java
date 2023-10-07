package tictac7x.charges.infoboxes.barrows;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargedItemInfoBox;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.store.ChargesItem;
import tictac7x.charges.store.Store;
import tictac7x.charges.triggers.TriggerItem;

public class ToragsHammers extends ChargedItemInfoBox {
    public ToragsHammers(
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
        super(ChargesItem.BARROWS_GEAR, ItemID.TORAGS_HAMMERS, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, plugin);
        this.triggers_items = new TriggerItem[]{
            new TriggerItem(ItemID.TORAGS_HAMMERS).fixedCharges(100),
            new TriggerItem(ItemID.TORAGS_HAMMERS_100).fixedCharges(100),
            new TriggerItem(ItemID.TORAGS_HAMMERS_75).fixedCharges(75),
            new TriggerItem(ItemID.TORAGS_HAMMERS_50).fixedCharges(50),
            new TriggerItem(ItemID.TORAGS_HAMMERS_25).fixedCharges(25),
            new TriggerItem(ItemID.TORAGS_HAMMERS_0).fixedCharges(0)
        };
    }
}