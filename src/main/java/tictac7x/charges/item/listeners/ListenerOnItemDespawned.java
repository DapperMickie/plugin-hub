package tictac7x.charges.item.listeners;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ItemDespawned;
import net.runelite.client.Notifier;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.item.storage.StoreableItem;
import tictac7x.charges.item.triggers.OnItemDespawned;
import tictac7x.charges.item.triggers.TriggerBase;
import tictac7x.charges.item.ChargedItemWithStorage;

@Slf4j
public class ListenerOnItemDespawned extends ListenerBase {
    public ListenerOnItemDespawned(final Client client, final ChargedItem chargedItem, final Notifier notifier, final ChargesImprovedConfig config) {
        super(client, chargedItem, notifier, config);
    }

    public void trigger(final ItemDespawned event) {
        for (final TriggerBase triggerBase : chargedItem.triggers) {
            if (!isValidTrigger(triggerBase, event)) continue;
            final OnItemDespawned trigger = (OnItemDespawned) triggerBase;
            boolean triggerUsed = false;

            if (trigger.pickUpToStorage.isPresent()) {
                log.debug(chargedItem.getItemName() + " pickup to storage");
                ((ChargedItemWithStorage) chargedItem).storage.add(event.getItem().getId(), event.getItem().getQuantity());
                triggerUsed = true;
            }

            if (super.trigger(trigger)) {
                triggerUsed = true;
            }

            if (triggerUsed) return;
        }
    }

    public boolean isValidTrigger(final TriggerBase triggerBase, final ItemDespawned event) {
        if (!(triggerBase instanceof OnItemDespawned)) return false;
        final OnItemDespawned trigger = (OnItemDespawned) triggerBase;

        // Despawned item id check.
        boolean despawnedItemCheck = false;
        for (final StoreableItem storeableItem : trigger.despawnedItemIds) {
            if (event.getItem().getId() == storeableItem.itemId) {
                despawnedItemCheck = true;
                break;
            }
        }
        if (!despawnedItemCheck) {
            return false;
        }

        // Pick up to storage check.
        if (trigger.pickUpToStorage.isPresent() && !(chargedItem instanceof ChargedItemWithStorage)) {
            return false;
        }

        return super.isValidTrigger(trigger);
    }
}
