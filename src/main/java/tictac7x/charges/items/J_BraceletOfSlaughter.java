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

public class J_BraceletOfSlaughter extends ChargedItem {
    public J_BraceletOfSlaughter(
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
        super(ItemKey.BRACELET_OF_SLAUGHTER, ItemID.BRACELET_OF_SLAUGHTER, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store);
        this.config_key = ChargesImprovedConfig.bracelet_of_slaughter;
        this.triggersItems = new TriggerItem[]{
            new TriggerItem(ItemID.BRACELET_OF_SLAUGHTER).needsToBeEquipped(),
        };
        this.triggersChatMessages = new TriggerChatMessage[]{
            new TriggerChatMessage("Your bracelet of slaughter has (?<charges>.+) charges? left."),
            new TriggerChatMessage("Your bracelet of slaughter prevents your slayer count from decreasing. It has (?<charges>.+) charges? left."),
            new TriggerChatMessage("Your bracelet of slaughter prevents your slayer count from decreasing. It then crumbles to dust.").fixedCharges(30).notification("Your slaughter bracelet crumbles to dust.")
        };
    }
}
