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
import tictac7x.charges.triggers.TriggerChatMessage;
import tictac7x.charges.triggers.TriggerItem;
import tictac7x.charges.triggers.TriggerReset;
import tictac7x.charges.triggers.TriggerWidget;

public class C_MagicCape extends ChargedItem {
    private final int MAGIC_CAPE_T = 9763;
    private final String regex_used = "You have changed your spellbook (?<used>.+)/(?<total>.+) times today.";

    public C_MagicCape(
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
        super(ItemKey.MAGIC_CAPE, ItemID.MAGIC_CAPE, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, plugin);
        this.config_key = ChargesImprovedConfig.magic_cape;

        this.triggers_items = new TriggerItem[]{
            new TriggerItem(ItemID.MAGIC_CAPE),
            new TriggerItem(MAGIC_CAPE_T)
        };

        this.triggers_chat_messages = new TriggerChatMessage[]{
            new TriggerChatMessage(regex_used).useDifference(),
        };

        this.triggers_widgets = new TriggerWidget[]{
            new TriggerWidget(219,1,0, "Choose spellbook: \\((?<charges>.+)/5 left\\)")
        };

        this.triggers_resets = new TriggerReset[]{
            new TriggerReset(5)
        };
    }
}
