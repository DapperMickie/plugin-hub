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
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.Store;
import tictac7x.charges.item.triggers.TriggerChatMessage;
import tictac7x.charges.item.triggers.TriggerItem;

public class U_SoulBearer extends ChargedItem {
    public U_SoulBearer(
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
        super(ItemKey.SOUL_BEARER, ItemID.SOUL_BEARER, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, plugin);
        this.config_key = ChargesImprovedConfig.soul_bearer;
        this.triggersItems = new TriggerItem[]{
            new TriggerItem(ItemID.SOUL_BEARER),
            new TriggerItem(ItemID.DAMAGED_SOUL_BEARER).fixedCharges(0),
        };
        this.triggersChatMessages = new TriggerChatMessage[]{
            new TriggerChatMessage("You remove the runes from the soul bearer.").fixedCharges(0),
            new TriggerChatMessage("(The|Your) soul bearer( now)? has one charge.").fixedCharges(1),
            new TriggerChatMessage("Your soul bearer carries the ensouled heads to your bank. It has run out of charges.").fixedCharges(0).notification(),
            new TriggerChatMessage("The soul bearer has (?<charges>.+) charges?."),
            new TriggerChatMessage("You add .+ charges? to your soul bearer. It now has (?<charges>.+) charges?."),
            new TriggerChatMessage("Your soul bearer carries the ensouled heads to your bank. It has (?<charges>.+) charges? left.")
        };
    }
}
