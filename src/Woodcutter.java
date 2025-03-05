import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import Scripts1.RandomizerHelper;

import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.stream.IntStream;

@ScriptManifest(info = "Wood and Drop", logo = "", version = 1.0, author = "p0ntus", name = "Woodcutter")

public class Woodcutter extends Script{

    private static final int MAX_IDLE_TIMER = 200;
    private static final int AXE_ID = 1337;
    private int idleCounter = 0;
    RandomizerHelper rand;

    static String WOODCUTTING_TYPE;
    static int[] LOGS_ID = new int[8];
    static int[] INVENTORY_TO_KEEP = new int[8];
    static int[] WOOD_CUT_IDS = new int[8];
    static int WOODCUTTING_SPEED = 1;
    static int[] TREE_IDS = new int[8];

    @Override
    public void onStart() throws InterruptedException {
        GUI gui = new GUI();
        getBot().getScriptExecutor().pause();

        while (gui.isActive() || gui.isVisible) {
            sleep(1427);
            log("SLEEPING @onStart()");
        }

        if (!gui.isVisible() || !gui.isActive())
            getBot().getScriptExecutor().resume();

        if (INVENTORY_TO_KEEP == null || WOOD_CUT_IDS == null ||
                WOODCUTTING_TYPE == null)
            stop();
    }

    @Override
    public int onLoop() throws InterruptedException {
        log("Idle Counter = " + idleCounter + "of " + MAX_IDLE_TIMER);
        idleCounter++;

        rand = new RandomizerHelper();

        closeDialog();

        cutWood();

        callAllAntiBanMethods();

        sleepRandomTime();

        stopExcessiveIdling();

        return 500;
    }

    private void stopExcessiveIdling() {
    }

    private void sleepRandomTime() {
    }

    private void callAllAntiBanMethods() {
    }

    public void cutWood() throws InterruptedException {
        log("cutWood()");
        boolean triedToCut = false;

        NPC tree = getNpcs().closest(TREE_IDS);
        if (isReadyToCut() && tree != null && !getInventory().isFull()) {
            sleep(rand.hoverTime);
            triedToCut = tree.interact(WOODCUTTING_TYPE);
        }
        sleep(rand.waitAfterFishClick);

        if(triedToCut)
            idleCounter = 0;

        log("Cut successful = " + triedToCut);
    }

    void dropWood() throws InterruptedException {
        log("dropWood()");

        if (rand.randomDropVersion < 3)
            getInventory().dropAllExcept(INVENTORY_TO_KEEP);
        else if (rand.randomDropVersion == 3)
            while(getInventory().dropAllExcept(WOOD_CUT_IDS)) {
                getInventory().getItem(WOOD_CUT_IDS).interact("Drop");
                sleep(new Random().nextInt(544) + 676);
            }
        else if(rand.randomDropVersion == 4) 
            getInventory().dropAll(WOOD_CUT_IDS);
        else
            customDrop();
    }

    private void customDrop() throws InterruptedException {
        log("customDrop()");

        sleep(rand.dropSleepBeforeShiftPress);
        getKeyboard().pressKey(KeyEvent.VK_SHIFT);

        for (int x = 0; x < 5; x++) {
            int xItemID = getInventory().getItemInSlot(x).getId();
            if(IntStream.of(INVENTORY_TO_KEEP).noneMatch(y -> y == xItemID) && getInventory().getItemInSlot(x) != null) {
                if(getInventory().isItemSelected()) {
                    getInventory().deselectItem();
                    woodDropSpeedSleep();
                }
                woodDropSpeedSleep();
                getMouse().click(false);
                sleep(new Random().nextInt(246)+ 230);
            }
        }
    }

    private void woodDropSpeedSleep() {

    }

    private boolean isReadyToCut() throws InterruptedException {
        log("isReadyToCut() = ");

        if (isInventoryFull())
            dropWood();

        if (myPlayer().isAnimating() || myPlayer().isMoving() && myPlayer().getInteracting() != null) {
            log(false);
            return false;
        }

        if(!getInventory().contains(AXE_ID)) {
            log(false + " no axe.");
            endEverything();
        }

        log(true);
        return true;
    }

    private void endEverything() {
    }

    private boolean isInventoryFull() {

    }

    private void closeDialog() throws InterruptedException {
        log("Closing Dialog");

        if (getWidgets().getWidgetContainingText("Click here to continue") != null) {
            sleep((long)(rand.closeDialogueTimer * WOODCUTTING_SPEED));
            getKeyboard().typeKey(' ');
        }
        idleCounter++;
    }
}
