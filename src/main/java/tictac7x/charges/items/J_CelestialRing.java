package tictac7x.charges.items;

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
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.Store;
import tictac7x.charges.item.triggers.TriggerChatMessage;
import tictac7x.charges.item.triggers.TriggerItem;

public class J_CelestialRing extends ChargedItem {
    public J_CelestialRing(
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
        super(ItemKey.CELESTIAL_RING, ItemID.CELESTIAL_RING, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store);
        this.config_key = ChargesImprovedConfig.celestial_ring;
        this.triggersItems = new TriggerItem[]{
            new TriggerItem(ItemID.CELESTIAL_RING_UNCHARGED).fixedCharges(0),
            new TriggerItem(ItemID.CELESTIAL_RING).needsToBeEquipped()
        };
        this.triggersChatMessages = new TriggerChatMessage[]{
            new TriggerChatMessage("You add .+ charges? to your Celestial ring. It now has (?<charges>.+) charges?."),
            new TriggerChatMessage("You add (?<charges>.+) charges? to your Celestial ring."),
            new TriggerChatMessage("You fully uncharge your Celestial ring.").fixedCharges(0),
            new TriggerChatMessage("Your Celestial ring has (?<charges>.+) charges?."),
            new TriggerChatMessage("Your Celestial ring has run out of charges").fixedCharges(0).notification(),
            new TriggerChatMessage("You manage to mine").ignore("You manage to mine some pay-dirt.").equipped().decreaseCharges(1)
        };
    }
}
