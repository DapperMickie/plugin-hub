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
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.store.ItemContainerType;
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.Store;
import tictac7x.charges.triggers.TriggerChatMessage;
import tictac7x.charges.triggers.TriggerItem;
import tictac7x.charges.triggers.TriggerItemContainer;

public class U_HerbSack extends ChargedItem {
    public U_HerbSack(
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
        super(ItemKey.HERB_SACK, ItemID.HERB_SACK, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, plugin);
        this.config_key = ChargesImprovedConfig.herb_sack;
        this.zero_charges_is_positive = true;
        this.triggers_items = new TriggerItem[]{
            new TriggerItem(ItemID.HERB_SACK),
            new TriggerItem(ItemID.OPEN_HERB_SACK),
        };
        this.triggers_chat_messages = new TriggerChatMessage[]{
            new TriggerChatMessage("The herb sack is empty.").fixedCharges(0),
            new TriggerChatMessage("You put the Grimy .* herb into your herb sack.").increaseCharges(1),
            new TriggerChatMessage("You look in your herb sack and see:").fixedCharges(0),
            new TriggerChatMessage("(?<charges>.+) x Grimy").increaseDynamically(),
        };
        this.triggers_item_containers = new TriggerItemContainer[]{
            new TriggerItemContainer(ItemContainerType.INVENTORY).menuTarget("Herb sack").menuOption("Fill").increaseByInventoryDifference(),
            new TriggerItemContainer(ItemContainerType.INVENTORY).menuTarget("Open herb sack").menuOption("Fill").increaseByInventoryDifference(),
            new TriggerItemContainer(ItemContainerType.INVENTORY).menuTarget("Herb sack").menuOption("Empty").decreaseByInventoryDifference(),
            new TriggerItemContainer(ItemContainerType.INVENTORY).menuTarget("Open herb sack").menuOption("Empty").decreaseByInventoryDifference(),
            new TriggerItemContainer(ItemContainerType.BANK).menuTarget("Herb sack").menuOption("Empty").decreaseByBankDifference(),
            new TriggerItemContainer(ItemContainerType.BANK).menuTarget("Open herb sack").menuOption("Empty").decreaseByBankDifference(),
        };
    }
}
