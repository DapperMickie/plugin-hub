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
import tictac7x.charges.store.InventoryType;
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.Store;
import tictac7x.charges.triggers.TriggerChatMessage;
import tictac7x.charges.triggers.TriggerItem;
import tictac7x.charges.triggers.TriggerItemContainer;

public class U_FishBarrel extends ChargedItem {
    private final int FISH_BARREL_SIZE = 28;

    public U_FishBarrel(
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
        super(ItemKey.FISH_BARREL, ItemID.FISH_BARREL, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, plugin);

        this.config_key = ChargesImprovedConfig.fish_barrel;
        this.zero_charges_is_positive = true;
        this.triggers_items = new TriggerItem[]{
            new TriggerItem(ItemID.FISH_BARREL),
            new TriggerItem(ItemID.OPEN_FISH_BARREL),
            new TriggerItem(ItemID.FISH_SACK_BARREL),
            new TriggerItem(ItemID.OPEN_FISH_SACK_BARREL)
        };
        this.triggers_chat_messages = new TriggerChatMessage[]{
            new TriggerChatMessage("(Your|The) barrel is empty.").onItemClick().fixedCharges(0),
            new TriggerChatMessage("The barrel is full. It may be emptied at a bank.").onItemClick().fixedCharges(FISH_BARREL_SIZE),
            new TriggerChatMessage("(You catch .*)|(.* enabled you to catch an extra fish.)").specificItem(ItemID.OPEN_FISH_BARREL, ItemID.OPEN_FISH_SACK_BARREL).increaseCharges(1),
            new TriggerChatMessage("The barrel contains:").onItemClick().multipleCharges()
        };
        this.triggers_item_containers = new TriggerItemContainer[]{
            new TriggerItemContainer(InventoryType.INVENTORY).menuTarget("Fish barrel").menuOption("Fill").increaseByInventoryDifference(),
            new TriggerItemContainer(InventoryType.INVENTORY).menuTarget("Open fish barrel").menuOption("Fill").increaseByInventoryDifference(),
            new TriggerItemContainer(InventoryType.BANK).menuTarget("Fish barrel").menuOption("Empty").decreaseByBankDifference(),
            new TriggerItemContainer(InventoryType.BANK).menuTarget("Open fish barrel").menuOption("Empty").decreaseByBankDifference(),
        };
    }
}
