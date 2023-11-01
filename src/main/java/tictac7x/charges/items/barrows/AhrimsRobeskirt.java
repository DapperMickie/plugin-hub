package tictac7x.charges.items.barrows;

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
import tictac7x.charges.item.triggers.TriggerBase;
import tictac7x.charges.item.triggers.OnChatMessage;
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.Store;
import tictac7x.charges.item.triggers.TriggerItem;

public class AhrimsRobeskirt extends ChargedItem {
    public AhrimsRobeskirt(
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
        super(ItemKey.BARROWS_GEAR, ItemID.AHRIMS_ROBESKIRT, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store);
        this.triggersItems = new TriggerItem[]{
            new TriggerItem(ItemID.AHRIMS_ROBESKIRT).fixedCharges(100),
            new TriggerItem(ItemID.AHRIMS_ROBESKIRT_100).fixedCharges(100),
            new TriggerItem(ItemID.AHRIMS_ROBESKIRT_75).fixedCharges(75),
            new TriggerItem(ItemID.AHRIMS_ROBESKIRT_50).fixedCharges(50),
            new TriggerItem(ItemID.AHRIMS_ROBESKIRT_25).fixedCharges(25),
            new TriggerItem(ItemID.AHRIMS_ROBESKIRT_0).fixedCharges(0)
        };
        this.triggers = new TriggerBase[]{
            new OnChatMessage("Ahrim's skirt has broken!").notification(),
        };
    }
}