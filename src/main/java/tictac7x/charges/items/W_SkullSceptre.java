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

public class W_SkullSceptre extends ChargedItem {
    public W_SkullSceptre(
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
        super(ItemKey.SKULL_SCEPTRE, ItemID.SKULL_SCEPTRE, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store);
        this.config_key = ChargesImprovedConfig.skull_sceptre;
        this.triggersItems = new TriggerItem[]{
            new TriggerItem(ItemID.SKULL_SCEPTRE),
            new TriggerItem(ItemID.SKULL_SCEPTRE_I)
        };
        this.triggersChatMessages = new TriggerChatMessage[]{
            new TriggerChatMessage("Your Skull Sceptre has (?<charges>.+) charges? left."),
            new TriggerChatMessage("Concentrating deeply, you divine that the sceptre has (?<charges>.+) charges? left."),
            new TriggerChatMessage("You charge the Skull Sceptre with .+ fragments?. It now contains( the maximum number of charges,)? (?<charges>.+)( charges?)?.")
        };
    }
}
